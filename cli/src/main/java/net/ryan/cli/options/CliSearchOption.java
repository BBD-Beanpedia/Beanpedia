package net.ryan.cli.options;

import net.ryan.bean.BeanModelFull;
import net.ryan.bean.BeanModelPage;
import net.ryan.bean.FilterPageModel;
import net.ryan.cli.BeanDisplay;
import net.ryan.cli.Identifiable;
import net.ryan.cli.PaginationField;
import net.ryan.util.*;

import java.util.Map;
import java.util.function.Function;

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
                  .ifSuccess(s -> searchForBean(0, s))
                  .ifError(s -> System.out.println("Error: " + s.getMessage()));
    }

    private static void searchForBean(int page, String beanName) {
        BeanDataHandler.getInstance()
                       .searchBean(beanName, page)
                       .ifSuccess(beanModelPage -> displayBeans(beanName, page, beanModelPage))
                       .ifError(e -> {
                           //TODO: better message
                           System.out.println(e.getMessage());
                       });
    }

    private static void displayBeans(String name, int currentPage, BeanModelPage pageBeanList) {
        BeanDisplay.handleDisplayBeansPaginated(currentPage, pageBeanList, (f) -> handlePage(f, currentPage, name), BeanDisplay::showBean);
    }

    private static void handlePage(PaginationField f, int givenPage, String name) {
        switch (f) {
            case NEXT -> searchForBean(givenPage + 1, name);
            case PREVIOUS -> searchForBean(givenPage - 1, name);
            case MENU -> System.out.println("Returning to main menu...");
        }
    }
}