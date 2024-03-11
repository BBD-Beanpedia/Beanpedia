package net.ryan.cli;

import net.ryan.bean.BeanDataHandler;
import net.ryan.bean.ShortBeanModel;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;
import net.ryan.util.Pair;

import java.util.Map;

public class CliSearchOption implements CliOption {

    @Override
    public String getName() {
        return "Search Beans";
    }

    @Override
    public void run() {
        System.out.println("---" + getName() + "---");
        System.out.println("Enter bean name to search for, or type back to return to the main menu:");
        InputUtils.getInstance()
                  .readStringFromConsole()
                  .ifSuccess(beanName -> searchForBean(beanName, 0))
                  .ifError(e -> {
                  });
    }

    private void searchForBean(String beanName, int currentPage) {
        BeanDataHandler.getInstance()
                       .searchBean(beanName, currentPage)
                       .map(data -> new Pair<>(data.first(), MapUtils.listToMap(data.second())))
                       .ifSuccess(integerListPair -> showSearchedBeans(integerListPair, currentPage))
                       .ifError(e -> {
                           System.out.println("Error making request to Api would you like to try again");
                           InputUtils.getInstance()
                                     .readStringFromConsole();
                           searchForBean(beanName, currentPage);
                       });
    }

    private void showSearchedBeans(Pair<Integer, Map<Integer, ShortBeanModel>> integerListPair, int currentPage) {
        System.out.printf("Showing page %d of %d\n", currentPage, integerListPair.first());
        integerListPair.second()
                       .forEach(this::displayOption);

    }

    // TODO: Generic for this::::->>>>
    private <T extends Nameable> void displayOption(Integer integer, T nameable) {
        System.out.printf("\t%d. %s\n", integer + 1, nameable.getName());
    }

}