package net.ryan.cli.options;

import net.ryan.bean.BeanModel;
import net.ryan.cli.Nameable;
import net.ryan.util.*;

import java.util.List;

public class CliCreateOption implements CliOption {

    private BeanModel model;

    @Override
    public String getName() {
        return "Create New Bean";
    }

    @Override
    public void run() {

        model = new BeanModel(-1, "", "", "", -1, -1, -1, -1);

        System.out.println("---Create new bean---");

        System.out.println("Enter the name of the bean");
        final String beanName = getStringAssured();
        model.setBeanName(beanName);
        model.setNewBeanName(beanName);

        System.out.println("Enter the scientific name of the bean");
        model.setScientificName(getStringAssured());

        System.out.println("Provide a description for the bean");
        model.setContent(getStringAssured());

        System.out.println("Enter the originating continent of the bean");
        model.setOrigin(getStringAssured());

        System.out.println("Enter the type of the bean");
        model.setType(getStringAssured());

        System.out.println("Enter the shape of the bean");
        model.setShape(getStringAssured());

        System.out.println("Enter the colour of the bean");
        model.setColour(getStringAssured());

        Result<String> response = BeanDataHandler.getInstance().createBean(model.toJsonStringUpdate());

        System.out.println(response.get());




      /*  // Not to sure if we should always do this call but to keep things dynamic we will
        final BeanOriginModel beanOriginModel = showMenuFor(BeanDataHandler.getInstance()
                                                                           .requestAllOrigins());
        System.out.println("Select Bean Shape:");
        final BeanShapeModel beanShapeModel = showMenuFor(BeanDataHandler.getInstance()
                                                                         .requestAllShapes());
        System.out.println("Select Bean Type:");
        final BeanTypeModel beanTypeModel = showMenuFor(BeanDataHandler.getInstance()
                                                                       .requestAllTypes());
        System.out.println("Select Bean Colour:");
        final BeanColourModel beanColourModel = showMenuFor(BeanDataHandler.getInstance()
                                                                           .requestAllColours());*/

//        new BeanModel()

//        BeanDataHandler.getInstance()
//                       .insertBean();
    }

    private static String getStringAssured() {
        final Result<String> stringFromConsole = InputUtils.getInstance()
                                                           .readStringFromConsoleLowerCase();
        if (stringFromConsole.isError()) {
            //Print the error
            System.out.println(stringFromConsole.getError()
                                                .getMessage());
            return getStringAssured();
        } else return stringFromConsole.get();
    }

    private static <T> T getDataAssured(Result<T> result) {
        if (result.isError()) {
            //Print the error
            System.out.println(result.getError()
                                     .getMessage());
            return getDataAssured(result);
        } else return result.get();
    }


    private <T extends Nameable> Result<T> showMenuFor(Result<List<T>> dataInput) {
        dataInput.ifError(stringExceptionPair -> {
                     // TODO: Unable to make request/ parse the data show the error here
                 })
                 .map(MapUtils::listToMap)
                 .map(data -> {
                     data.forEach(DisplayHelper::displayOption);
                     return InputUtils.getInstance()
                                      .readMapChoiceRangeFromConsole(data)
                                      .ifError(e -> {
                                          // TODO: Error not in range somehow prompt
                                      });
                 });
        return null;
    }

}