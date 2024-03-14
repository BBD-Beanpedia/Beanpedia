package net.ryan.cli.options;

import net.ryan.CliOptionHelper;
import net.ryan.bean.BeanModelPage;
import net.ryan.cli.BeanDisplay;
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
        System.out.println("\n---Viewing all beans---");
        final int page = 0;
        showBeanPage(page);
    }

    private void showBeanPage(int page) {
        BeanDataHandler.getInstance()
                       .requestListOfBeansPaged(page)
                       .ifSuccess(givenPage -> displayBeans(page, givenPage))
                       .ifError(e -> e.printStackTrace());
    }

    private void displayBeans(int givenPage, BeanModelPage pageBeanList) {
        BeanDisplay.handleDisplayBeansPaginated(givenPage, pageBeanList, (f) -> handlePage(f, givenPage), BeanDisplay::showBean);
    }

    private void handlePage(PaginationField paginationField, int givenPage) {
        switch (paginationField) {
            case NEXT -> showBeanPage(givenPage + 1);
            case PREVIOUS -> showBeanPage(givenPage - 1);
            case MENU -> CliOptionHelper.getInstance()
                                        .show();
        }
    }

}