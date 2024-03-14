package net.ryan.cli.options;

import net.ryan.CliOptionHelper;
import net.ryan.bean.BeanModelFull;
import net.ryan.bean.BeanModelPage;
import net.ryan.bean.FilterPageModel;
import net.ryan.cli.BeanDisplay;
import net.ryan.cli.Identifiable;
import net.ryan.cli.Nameable;
import net.ryan.cli.PaginationField;
import net.ryan.util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class CliFilterOption implements CliOption {

    enum BeanProps implements Nameable {
        ORIGIN("Origin"), SHAPE("Shape"), TYPE("Type"), COLOUR("Colour");
        private final String name;

        BeanProps(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }


    @Override
    public String getName() {
        return "Filter Beans";
    }

    @Override
    public void run() {
        System.out.println("\n---Filter---");
        System.out.println("What would you like to filter by?\n");

        final List<BeanProps> list = Arrays.stream(BeanProps.values())
                                           .toList();
        final Map<Integer, BeanProps> beanPropsMap = MapUtils.listToMap(list);
        beanPropsMap.forEach(DisplayHelper::displayOption);

        System.out.println("\nEnter a number to select or enter 'menu' to return to main menu.");
        InputUtils.getInstance()
                  .runMenuFunction(beanPropsMap, List.of(PaginationField.MENU), this::showBeanOptionSelection, this::menu);
    }

    private void menu(PaginationField paginationField) {
        System.out.println("Returning to main menu...");
        CliOptionHelper.getInstance().show();
    }

    private void showBeanOptionSelection(BeanProps beanProps) {
        final BeanDataHandler dataHandler = BeanDataHandler.getInstance();
        System.out.println("Enter a number to select an option to filter by " + beanProps.getName() + ": ");
        switch (beanProps) {
            case ORIGIN -> showOptionsAndSearch(0, dataHandler::requestAllOrigins, dataHandler::searchBeanByOrigin);
            case SHAPE -> showOptionsAndSearch(0, dataHandler::requestAllShapes, dataHandler::searchBeanByShape);
            case TYPE -> showOptionsAndSearch(0, dataHandler::requestAllTypes, dataHandler::searchBeanByType);
            case COLOUR -> showOptionsAndSearch(0, dataHandler::requestAllColours, dataHandler::searchBeanByColour);
        }
    }

    private <T extends Nameable & Identifiable> void showOptionsAndSearch(int pageNum, Supplier<Result<List<T>>> requestSupplier, Function<FilterPageModel, Result<BeanModelPage>> searchFunction) {
        requestSupplier.get()
                       .map(MapUtils::listToMap)
                       .ifError(e -> failedToMakeNetworkCall(() -> showOptionsAndSearch(pageNum, requestSupplier, searchFunction)))
                       .ifSuccess(options -> {
                           options.forEach(DisplayHelper::displayOption);
                           InputUtils.getInstance()
                                     .readMapChoiceRangeFromConsole(options)
                                     .ifSuccess(beanFilterModel -> searchFilter(pageNum, beanFilterModel, searchFunction))
                                     .ifError(e -> failedToMakeNetworkCall(() -> showOptionsAndSearch(pageNum, requestSupplier, searchFunction)));
                       });
    }

    private <T extends Identifiable> void searchFilter(int pageNum, T beanFilterModel, Function<FilterPageModel, Result<BeanModelPage>> searchFunction) {
        searchFunction.apply(new FilterPageModel(beanFilterModel.getId(), pageNum))
                      .ifSuccess(page -> displayBeans(pageNum, beanFilterModel, searchFunction, page))
                      .ifError(e -> {
                          //TODO: Error
                          System.out.println("error ");
                          System.out.println(e.getMessage());
                      });
    }

    private <T extends Identifiable> void displayBeans(int givenPage, T beanFilterModel, Function<FilterPageModel, Result<BeanModelPage>> searchFunction, BeanModelPage pageBeanList) {
        BeanDisplay.handleDisplayBeansPaginated(givenPage, pageBeanList, (f) -> handlePage(f, beanFilterModel, searchFunction, givenPage), BeanDisplay::showBean);
    }

    private <T extends Identifiable> void handlePage(PaginationField f, T beanFilterModel, Function<FilterPageModel, Result<BeanModelPage>> searchFunction, int givenPage) {
        switch (f) {
            case NEXT -> searchFilter(givenPage + 1, beanFilterModel, searchFunction);
            case PREVIOUS -> searchFilter(givenPage - 1, beanFilterModel, searchFunction);
            case MENU -> {
                System.out.println("Returning to main menu...");
                CliOptionHelper.getInstance()
                               .show();
            }
        }
    }


    private void failedToMakeNetworkCall(Runnable reRun) {
        System.out.println("Failed to make network call, please try again later");
        System.out.println("Press enter to continue");
        InputUtils.getInstance()
                  .readStringFromConsoleLowerCase();
        reRun.run();
    }


}