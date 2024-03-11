package net.ryan.cli;

import net.ryan.bean.BeanDataHandler;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;
import net.ryan.util.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
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
        return "---Filter Beans---";
    }

    @Override
    public void run() {
        System.out.println("Filter");
        System.out.println("What would you like to filter by?");

        final Map<Integer, BeanProps> beanPropsMap = MapUtils.listToMap(Arrays.stream(BeanProps.values())
                                                                              .toList());


        beanPropsMap.forEach(this::displayOption);

        InputUtils.getInstance()
                  .readIntRangeFromConsole(0, beanPropsMap.size())
                  .map(choice -> beanPropsMap.get(choice - 1))
                  .ifSuccess(this::showBeanOptionSelection);
    }

    private void showBeanOptionSelection(BeanProps beanProps) {
        final BeanDataHandler dataHandler = BeanDataHandler.getInstance();
        switch (beanProps) {
            case ORIGIN -> showOptionsAndSearch(dataHandler::requestAllOrigins, dataHandler::searchBeanByOrigin);
            case SHAPE -> showOptionsAndSearch(dataHandler::requestAllTypes, dataHandler::searchBeanByShape);
            case TYPE -> showOptionsAndSearch(dataHandler::requestAllTypes, dataHandler::searchBeanByType);
            case COLOUR -> showOptionsAndSearch(dataHandler::requestAllColours, dataHandler::searchBeanByColour);
        }
    }

    private <T extends Nameable> void showOptionsAndSearch(Supplier<Result<List<T>>> requestSupplier, BiConsumer<String, Integer> searchFunction) {
        requestSupplier.get()
                       .map(MapUtils::listToMap)
                       .ifError(e -> failedToMakeNetworkCall(() -> showOptionsAndSearch(requestSupplier, searchFunction)))
                       .ifSuccess(options -> {
                           options.forEach(this::displayOption);
                           InputUtils.getInstance()
                                     .readIntRangeFromConsole(0, options.size())
                                     .map(options::get)
                                     .ifSuccess(beanOriginModel -> searchFunction.accept(beanOriginModel.getName(), 0))
                                     .ifError(e -> failedToMakeNetworkCall(() -> showOptionsAndSearch(requestSupplier, searchFunction)));
                       });
    }

    private void failedToMakeNetworkCall(Runnable reRun) {
        System.out.println("Failed to make network call, please try again later");
        System.out.println("Press enter to continue");
        InputUtils.getInstance()
                  .readStringFromConsole();
        reRun.run();
    }


    // TODO: Generic for this::::->>>>
    private <T extends Nameable> void displayOption(Integer integer, T nameable) {
        System.out.printf("\t%d. %s\n", integer + 1, nameable.getName());
    }
}