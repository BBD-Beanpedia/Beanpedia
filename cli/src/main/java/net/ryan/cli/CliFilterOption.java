package net.ryan.cli;

import net.ryan.bean.BeanModelFull;
import net.ryan.bean.BeanModelPage;
import net.ryan.util.BeanDataHandler;
import net.ryan.util.DisplayHelper;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;
import net.ryan.util.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
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
        System.out.println("---Filter---");
        System.out.println("What would you like to filter by?");

        final Map<Integer, BeanProps> beanPropsMap = MapUtils.listToMap(Arrays.stream(BeanProps.values())
                                                                              .toList());
        beanPropsMap.forEach(DisplayHelper::displayOption);

        InputUtils.getInstance()
                  .readMapChoiceRangeFromConsole(beanPropsMap)
                  .ifSuccess(this::showBeanOptionSelection);
    }

    private void showBeanOptionSelection(BeanProps beanProps) {
        final BeanDataHandler dataHandler = BeanDataHandler.getInstance();
        System.out.println("Please select from the list below to filter by " + beanProps.getName() + ": ");
        switch (beanProps) {
            case ORIGIN -> showOptionsAndSearch(dataHandler::requestAllOrigins, dataHandler::searchBeanByOrigin);
            case SHAPE -> showOptionsAndSearch(dataHandler::requestAllShapes, dataHandler::searchBeanByShape);
            case TYPE -> showOptionsAndSearch(dataHandler::requestAllTypes, dataHandler::searchBeanByType);
            case COLOUR -> showOptionsAndSearch(dataHandler::requestAllColours, dataHandler::searchBeanByColour);
        }
    }

    private <T extends Nameable & Identifiable> void showOptionsAndSearch(Supplier<Result<List<T>>> requestSupplier, Function<Integer, Result<BeanModelPage>> searchFunction) {
        requestSupplier.get()
                       .map(MapUtils::listToMap)
                       .ifError(e -> failedToMakeNetworkCall(() -> showOptionsAndSearch(requestSupplier, searchFunction)))
                       .ifSuccess(options -> {
                           options.forEach(DisplayHelper::displayOption);
                           InputUtils.getInstance()
                                     .readMapChoiceRangeFromConsole(options)
                                     .ifSuccess(x -> searchFilter(x, searchFunction))
                                     .ifError(e -> failedToMakeNetworkCall(() -> showOptionsAndSearch(requestSupplier, searchFunction)));
                       });
    }

    private <T extends Identifiable> void searchFilter(T x, Function<Integer, Result<BeanModelPage>> searchFunction) {
        searchFunction.apply(x.getId())
                      .ifSuccess(modelFull -> {
                          final Map<Integer, BeanModelFull> beanModelMap = modelFull.createBeanModelMap();
                          beanModelMap.forEach(DisplayHelper::displayOption);
                          //TODO: handle Input after

                      })
                      .ifError(e -> {
                          //TODO: Error
                          System.out.println("error ");
                          System.out.println(e.getMessage());
                      });
    }


    private void failedToMakeNetworkCall(Runnable reRun) {
        System.out.println("Failed to make network call, please try again later");
        System.out.println("Press enter to continue");
        InputUtils.getInstance()
                  .readStringFromConsoleLowerCase();
        reRun.run();
    }


}