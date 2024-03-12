package net.ryan.cli;

import net.ryan.util.BeanDataHandler;
import net.ryan.bean.ShortBeanModel;
import net.ryan.util.*;

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
        BeanDataHandler.getInstance()
                       .requestListOfShortBeansPaged(page)
                       .ifError(e -> System.out.println("Error -> Cant make call to server: " + e.getMessage()))
                       .ifError(e -> System.out.println("Error: " + e.getMessage()))
                       .ifSuccess(givenPage -> displayBeans(page, givenPage));
    }

    private void displayBeans(int givenPage, Pair<Integer, List<ShortBeanModel>> pageBeanList) {
        System.out.printf("Page %d of %d\n", givenPage, pageBeanList.first());
        final Map<Integer, ShortBeanModel> beanModelMap = MapUtils.listToMap(pageBeanList.second());
        beanModelMap.forEach(DisplayHelper::displayOption);
        promptForInput(beanModelMap);
    }

    private void promptForInput(Map<Integer, ShortBeanModel> beanModelMap) {
        Consumer<Integer> runInt = i -> handleIntSelected(i, beanModelMap);
        Consumer<String> x = s -> System.out.println("STRING");
        InputUtils.getInstance()
                  .runMenuFunction(1, 2, List.of("next", "prev", "exit"), x, runInt)
                  .ifError(s -> System.out.println("Error: " + s.getMessage()))
                  .ifError(s -> promptForInput(beanModelMap));
    }

    private void handleIntSelected(int i, Map<Integer, ShortBeanModel> beanModelMap) {
        BeanDataHandler.getInstance()
                       .requestBeanDetail(beanModelMap.get(i)
                                                      .beanId())
                       .ifError(e -> System.out.println("Error: " + e.getMessage()))
                       .ifSuccess(s -> System.out.println(s.toJsonString()));
     }


    private void pagination(int page, int maxPages) {
        System.out.println("---Viewing all beans---");
        System.out.printf("Page %d of %d\n", page, maxPages);

        BeanDataHandler.getInstance()
                       .requestListOfShortBeansPaged(0)
                       .ifError(e -> {
                           System.out.printf("Unable to load beans: %s - %s", e.getCause(), e.getMessage());
                           // TODO: Back to main to show
                       })
                       .ifSuccess(this::showAllBeans);

//        beanModelMap.forEach((integer, cliOption) -> System.out.printf("\t%d. %s\n", integer + 1, cliOption));

    }

    private void showAllBeans(Pair<Integer, List<ShortBeanModel>> pagedBeanList) {
        final Map<Integer, ShortBeanModel> indexToBeanMap = MapUtils.listToMap(pagedBeanList.second());
        indexToBeanMap.forEach(DisplayHelper::displayOption);


        Consumer<String> x = (s) -> {
            //TODO: match string based on provided and run action for it.
            System.out.println("Hello " + s);
        };

        Consumer<Integer> runInt = (i) -> {


            System.out.println(i);
        };


    }

    private void processAction(String action) {

    }

    private void showBeanDetails(Integer index, Map<Integer, ShortBeanModel> indexToBeanMap) {
        final int beanId = indexToBeanMap.get(index)
                                         .beanId();
        BeanDataHandler.getInstance()
                       .requestBeanDetail(beanId)
                       .ifError(e -> {

                       })
                       .ifSuccess(s -> {
                           System.out.println(s.toJsonString());
                       });
    }

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