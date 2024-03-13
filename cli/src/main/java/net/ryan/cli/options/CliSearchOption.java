package net.ryan.cli.options;

import net.ryan.bean.BeanModelFull;
import net.ryan.bean.BeanModelPage;
import net.ryan.util.BeanDataHandler;
import net.ryan.util.DisplayHelper;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

import java.util.Map;

public class CliSearchOption implements CliOption {

    @Override
    public String getName() {
        return "Search Beans";
    }

    @Override
    public void run() {
        System.out.println("---Search Beans---");
        System.out.println("Enter bean name to search for, or type back to return to the main menu:");

        InputUtils.getInstance()
                  .readStringFromConsoleDirect()
                  .ifSuccess(s -> searchForBean(s, 0))
                  .ifError(s -> System.out.println("Error: " + s.getMessage()));
    }

    private static void searchForBean(String beanName, int page) {
        BeanDataHandler.getInstance()
                       .searchBean(beanName, page)
                       .ifSuccess(beanModelPage -> displayBeans(page, beanModelPage))
                       .ifError(e -> {
                           //TODO: better message
                           System.out.println(e.getMessage());

                           System.out.println("Error making request to Api would you like to try again");
                           InputUtils.getInstance()
                                     .readStringFromConsoleLowerCase();
//                           searchForBean(beanName, page);
                       });


    }

    // TODO: UTIL
    private static void displayBeans(int givenPage, BeanModelPage pageBeanList) {
        final Integer totalPages = pageBeanList.maxPages();
        System.out.printf("Page %d of %d\n", givenPage + 1, totalPages);
        final Map<Integer, BeanModelFull> beanModelMap = MapUtils.listToMap(pageBeanList.beanList());
        beanModelMap.forEach(DisplayHelper::displayOption);
//        promptForInput(givenPage, beanModelMap, totalPages);
    }


/*    private void searchForBean(String beanName, int currentPage) {
        BeanDataHandler.getInstance()
                       .searchBean(beanName, currentPage)
                       .map(data -> new Pair<>(data.first(), MapUtils.listToMap(data.second())))
                       .ifSuccess(integerListPair -> showSearchedBeans(integerListPair, currentPage))
                       .ifError(e -> {
                           System.out.println("Error making request to Api would you like to try again");
                           InputUtils.getInstance()
                                     .readStringFromConsoleLowerCase();
                           searchForBean(beanName, currentPage);
                       });
    }

    private void showSearchedBeans(Pair<Integer, Map<Integer, ShortBeanModel>> integerListPair, int currentPage) {
        System.out.printf("Showing page %d of %d\n", currentPage, integerListPair.first());
        integerListPair.second()
                       .forEach(DisplayHelper::displayOption);

    }*/


}