package net.ryan.cli;

import net.ryan.bean.BeanModelFull;
import net.ryan.util.*;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UpdateBeanDisplay {


    public static void show(BeanModelFull modelFull) {

        //TODO: check for auth before we make a request.

        System.out.println("Update Bean: " + modelFull.getName());

        final Map<Integer, UpdateField> updateFieldMap = UpdateField.createMap();
        updateFieldMap.forEach(DisplayHelper::displayOption);
        System.out.println("Please select a field from the range above : ");
        getMapInput(modelFull, updateFieldMap);
    }

    private static void getMapInput(BeanModelFull modelFull, Map<Integer, UpdateField> updateFieldMap) {

        InputUtils.getInstance()
                  .runMenuFunction(updateFieldMap, List.of(PaginationField.MENU), handleSelection(modelFull), (s) -> {
                      //TODO: menu
                  });
    }

    private static Consumer<UpdateField> handleSelection(BeanModelFull beanModel) {
        return (updateField) -> {
            final BeanDataHandler dataHandler = BeanDataHandler.getInstance();
            switch (updateField) {
                case NAME -> showDataSimple(updateField, beanModel::getName, beanModel::setName);
                case CONTENT -> showDataSimple(updateField, beanModel::getContent, beanModel::setContent);
                case SCIENTIFIC_NAME ->
                        showDataSimple(updateField, beanModel::getScientificName, beanModel::setScientificName);
                case ORIGIN ->
                        showData(updateField, dataHandler::requestAllOrigins, beanModel::getOrigin, beanModel::setOrigin);
                case TYPE ->
                        showData(updateField, dataHandler::requestAllShapes, beanModel::getType, beanModel::setType);
                case SHAPE ->
                        showData(updateField, dataHandler::requestAllShapes, beanModel::getShape, beanModel::setShape);
                case COLOUR ->
                        showData(updateField, dataHandler::requestAllColours, beanModel::getColour, beanModel::setColour);
                case FINISH -> dataHandler.updateBean(beanModel)
                                          .ifSuccess(_s -> System.out.println("Bean Updated."))
                                          .ifError(e -> System.out.println("Error:" + e.getMessage()));
            }
        };
    }


    private static <T extends Nameable> void showDataSimple(UpdateField updateField, Supplier<String> originalData, Consumer<String> updateFunction) {
        System.out.printf("Current Bean %s: %s%n", updateField.getName(), originalData.get());
        System.out.printf("Enter new Bean %s:\n", updateField.getName());
        InputUtils.getInstance()
                  .readStringFromConsoleDirect()
                  .ifSuccess(updateFunction)
                  .ifError(e -> System.out.println("Error:" + e.getMessage()));
    }

    private static <T extends Nameable> void showData(UpdateField updateField, Supplier<Result<List<T>>> requestSupplier, Supplier<String> originalData, Consumer<String> updateFunction) {
        System.out.printf("Current Bean %s: %s%n", updateField.getName(), originalData.get());
        System.out.printf("Enter new Bean %s:\n", updateField.getName());
        requestData(requestSupplier, updateFunction);
    }

    private static <T extends Nameable> void requestData(Supplier<Result<List<T>>> requestSupplier, Consumer<String> updateFunction) {
        requestSupplier.get()
                       .map(MapUtils::listToMap)
                       .ifSuccess(processSelection(updateFunction))
                       .ifError(e -> System.out.println("Error:" + e.getMessage()));
    }


    private static <T extends Nameable> Consumer<Map<Integer, T>> processSelection(Consumer<String> updateFunction) {
        return (input) -> {
            //TODO:
            System.out.println("Select from the following options: ");
            input.forEach(DisplayHelper::displayOption);
            InputUtils.getInstance()
                      .readMapChoiceRangeFromConsole(input)
                      .map(Nameable::getName)
                      .ifSuccess(updateFunction)
                      .ifError(e -> System.out.println("Error:" + e.getMessage()));
        };
    }
}
