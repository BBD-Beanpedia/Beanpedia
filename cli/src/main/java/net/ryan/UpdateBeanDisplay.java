package net.ryan;

import net.ryan.CliOptionHelper;
import net.ryan.bean.BeanModelFull;
import net.ryan.cli.Nameable;
import net.ryan.cli.PaginationField;
import net.ryan.cli.UpdateField;
import net.ryan.util.*;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UpdateBeanDisplay {

    public static void showInsert() {
        System.out.println("Insert a new bean: ");

    }


    public static void showUpdate(BeanModelFull modelFull) {

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
                      CliOptionHelper.getInstance()
                                     .show();
                  })
                  .ifError(e -> getMapInput(modelFull, updateFieldMap));
    }


/*    private static Consumer<UpdateField> handleInsert() {
        final BeanDataHandler dataHandler = BeanDataHandler.getInstance();
        BeanModelFull beanModelFull = new BeanModelFull();
        UpdateField.createMap()
                   .forEach((integer, updateField) -> {
                       updateField.getName()
                   });


    }*/

    private static Consumer<UpdateField> handleSelection(BeanModelFull beanModel) {
        return (updateField) -> {
            final BeanDataHandler dataHandler = BeanDataHandler.getInstance();
            switch (updateField) {
                case NAME -> showUpdateDataSimple(updateField, beanModel::getName, beanModel::setBeanName);
                case CONTENT -> showUpdateDataSimple(updateField, beanModel::getContent, beanModel::setContent);
                case SCIENTIFIC_NAME ->
                        showUpdateDataSimple(updateField, beanModel::getScientificName, beanModel::setScientificName);
                case ORIGIN ->
                        showUpdateData(updateField, dataHandler::requestAllOrigins, beanModel::getOrigin, beanModel::setOrigin);
                case TYPE ->
                        showUpdateData(updateField, dataHandler::requestAllTypes, beanModel::getType, beanModel::setType);
                case SHAPE ->
                        showUpdateData(updateField, dataHandler::requestAllShapes, beanModel::getShape, beanModel::setShape);
                case COLOUR ->
                        showUpdateData(updateField, dataHandler::requestAllColours, beanModel::getColour, beanModel::setColour);
                case FINISH -> dataHandler.updateBean(beanModel.toJsonString())
                                          .ifSuccess(_s -> System.out.println("Bean Updated."))
                                          .ifError(e -> System.out.println("Error:" + e.getMessage()));
            }
        };
    }


    private static void showUpdateDataSimple(UpdateField updateField, Supplier<String> originalData, Consumer<String> updateFunction) {
        System.out.printf("Current Bean %s: %s%n", updateField.getName(), originalData.get());
        showDataSimple(updateField, updateFunction);
    }

    private static void showDataSimple(UpdateField updateField, Consumer<String> updateFunction) {

        System.out.printf("Enter new Bean %s:\n", updateField.getName());
        readStringFromConsole(updateFunction);
    }

    private static void readStringFromConsole(Consumer<String> updateFunction) {
        InputUtils.getInstance()
                  .readStringFromConsoleDirect()
                  .ifSuccess(updateFunction)
                  .ifError(e -> {
                      System.out.println("Error:" + e.getMessage());
                      readStringFromConsole(updateFunction);
                  });
    }

    private static <T extends Nameable> void showUpdateData(UpdateField updateField, Supplier<Result<List<T>>> requestSupplier, Supplier<String> originalData, Consumer<String> updateFunction) {
        System.out.printf("Current Bean %s: %s%n", updateField.getName(), originalData.get());
        showData(updateField, requestSupplier, updateFunction);
    }

    private static <T extends Nameable> void showData(UpdateField updateField, Supplier<Result<List<T>>> requestSupplier, Consumer<String> updateFunction) {
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
            readInputFromOptions(updateFunction, input);
        };
    }

    private static <T extends Nameable> void readInputFromOptions(Consumer<String> updateFunction, Map<Integer, T> input) {
        InputUtils.getInstance()
                  .readMapChoiceRangeFromConsole(input)
                  .map(Nameable::getName)
                  .ifSuccess(updateFunction.andThen(string -> {
                  }))
                  .ifError(e -> {
                      System.out.println("Error:" + e.getMessage());
                      readInputFromOptions(updateFunction, input);
                  });
    }
}
