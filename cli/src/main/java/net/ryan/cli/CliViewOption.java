package net.ryan.cli;

import net.ryan.bean.BeanModelFull;
import net.ryan.bean.BeanModelPage;
import net.ryan.util.BeanDataHandler;
import net.ryan.util.DisplayHelper;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CliViewOption implements CliOption {


    @Override
    public String getName() {
        return "View All Beans";
    }

    @Override
    public void run() {
        // Make a call to get the short bean list as well as page size.
        System.out.println("---Viewing all beans---");
        final int page = 0;
        showBeanPage(page);
    }

    private void showBeanPage(int page) {
        BeanDataHandler.getInstance()
                       .requestListOfBeansPaged(page)
                       .ifError(e -> System.out.println("Error -> Cant make call to server: " + e.getMessage()))
                       .ifSuccess(givenPage -> displayBeans(page, givenPage));
    }

    private void displayBeans(int givenPage, BeanModelPage pageBeanList) {
        final Integer totalPages = pageBeanList.maxPages();
        System.out.printf("Page %d of %d\n", givenPage + 1, totalPages);
        final Map<Integer, BeanModelFull> beanModelMap = MapUtils.listToMap(pageBeanList.beanList());
        beanModelMap.forEach(DisplayHelper::displayOption);
        promptForInput(givenPage, beanModelMap, totalPages);
    }

    private void promptForInput(int givenPage, Map<Integer, BeanModelFull> beanModelMap, Integer totalPages) {
        Consumer<Integer> runInt = i -> handleBean(beanModelMap.get(i));
        Consumer<String> x = s -> handleStringSelected(s, givenPage);

        final List<String> options = new ArrayList<>();
        options.add("exit");
        if (givenPage > 0) {
            options.addFirst("prev");
        }
        if (givenPage < totalPages - 1) {
            options.addFirst("next");
        }

        //TODO: better message
        System.out.println("Enter an number between 1 and " + beanModelMap.size() + " to select a bean or enter 'exit' to exit");


        InputUtils.getInstance()
                  .runMenuFunction(1, beanModelMap.size(), options, x, runInt)
                  .ifError(s -> System.out.println("Error: " + s.getMessage()))
                  .ifError(s -> promptForInput(givenPage, beanModelMap, totalPages));
    }


    private void handleStringSelected(String string, int givenPage) {
        if (string.equals("next")) {
            showBeanPage(givenPage + 1);
        } else if (string.equals("prev")) {
            showBeanPage(givenPage - 1);
        } else if (string.equals("exit")) {
            // TODO: back to cli
        }
    }

    private void handleBean(BeanModelFull modelFull) {
        System.out.println("Bean Details: ");
        System.out.println("- Name: " + modelFull.getName());
        System.out.println("- Scientific Name: " + modelFull.getScientificName());
        System.out.println("- Type: " + modelFull.getType());
        System.out.println("- Shape: " + modelFull.getShape());
        System.out.println("- Colour: " + modelFull.getColour());
        System.out.println("- Origin: " + modelFull.getOrigin());
        System.out.println("- Bean Content: " + modelFull.getContent());

        InputUtils.getInstance()
                  .readIntFromConsole();

    }

     /*   BeanDataHandler.getInstance()
                       .requestBeanDetail(beanId)
                       .ifError(e -> {

                       })
                       .ifSuccess(s -> {
                           System.out.println(s.toJsonString());
                       });*/

/*    private void showBeanData(Map<Integer, ShortBeanModel> beanMap) {
        // Show options for the user to select e.g. 1. String Bean
        beanMap.forEach((integer, cliOption) -> System.out.printf("\t%d. %s\n", integer + 1, cliOption));
        System.out.print("Enter a number to see more details, ");
        if (page != 1) System.out.print("'prev' for previous page,");
        if (page < maxPages) System.out.print("'next' for next page,");
        System.out.println("or 'exit' to exit");

        Consumer<String> x = (s) -> {
            //TODO: match string based on provided and run action for it.
            System.out.println("Hello " + s);
        };

        Consumer<Integer> runInt = (i) -> {
            // TODO: Show info on bean
//            ShortBeanModel s = optionMap.get(i);
            HttpHandler.newGetRequest("")
                       .map(HttpHandler.Request::sendString)
                       .ifSuccess(System.out::println);


            System.out.println(i);
        };
        InputUtils.getInstance()
                  .runMenuFunction(1, 2, List.of("next", "prev", "exit"), x, runInt)
                  .ifError(s -> {
                      System.out.println(s.first());
                      pagination(1, 2);
                  });

    }*/


}