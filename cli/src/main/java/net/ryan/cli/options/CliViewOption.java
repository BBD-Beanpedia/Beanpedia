/*
package net.ryan.cli.options;

import net.ryan.CliOptionHelper;
import net.ryan.bean.BeanModelFull;
import net.ryan.bean.BeanModelPage;
import net.ryan.cli.PaginationField;
import net.ryan.cli.UpdateBeanDisplay;
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
        Consumer<PaginationField> x = s -> handleStringSelected(s.getName(), givenPage);

        final List<PaginationField> options = new ArrayList<>();
        options.add(PaginationField.MENU);
        if (givenPage > 0) {
            options.addFirst(PaginationField.NEXT);
        }
        if (givenPage < totalPages - 1) {
            options.addFirst(PaginationField.PREV);
        }

        //TODO: better message
        System.out.println("Enter an number between 1 and " + beanModelMap.size() + " to select a bean or enter 'exit' to exit");


        InputUtils.getInstance()
                  .runMenuFunction(beanModelMap, options, x, runInt)
                  .ifError(s -> {
                      System.out.println("Error: " + s.getMessage());
                      showBeanPage(0);
                  })
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

        System.out.println("Enter 'edit' to update bean or 'menu' to return to main menu");
        InputUtils.getInstance()
                  .readStringFromConsoleLowerCase()
                  .ifSuccess(s -> {
                      if (s.equals("edit")) {
                          // TODO: update bean
                          UpdateBeanDisplay.showUpdate(modelFull);
                      } else if (s.equals("menu")) {
                          // TODO: back to cli
                          CliOptionHelper.getInstance()
                                         .show();
                      }
                  });


    }

     */
/*   BeanDataHandler.getInstance()
                       .requestBeanDetail(beanId)
                       .ifError(e -> {

                       })
                       .ifSuccess(s -> {
                           System.out.println(s.toJsonString());
                       });*//*


 */
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

    }*//*



}*/
package net.ryan.cli.options;

import net.ryan.bean.BeanModelPage;
import net.ryan.cli.BeanDisplay;
import net.ryan.cli.UpdateBeanDisplay;
import net.ryan.cli.PaginationField;
import net.ryan.util.BeanDataHandler;

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
                       .ifSuccess(givenPage -> displayBeans(page, givenPage))
                       .ifError(e -> System.out.println("Error -> Cant make call to server: " + e.getMessage()));
    }

    private void displayBeans(int givenPage, BeanModelPage pageBeanList) {
        BeanDisplay.handleDisplayBeansPaginated(givenPage, pageBeanList, (f) -> handlePage(f, givenPage), BeanDisplay::showBean);
    }

    private void handlePage(PaginationField paginationField, int givenPage) {
        switch (paginationField) {
            case NEXT -> showBeanPage(givenPage + 1);
            case PREV -> showBeanPage(givenPage - 1);
            case MENU -> {
                //TODO: back to cli
            }
        }
    }

}