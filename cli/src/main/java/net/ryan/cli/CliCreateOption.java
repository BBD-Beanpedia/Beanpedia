package net.ryan.cli;

import net.ryan.util.DisplayHelper;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;
import net.ryan.util.Result;

import java.util.List;

public class CliCreateOption implements CliOption {

    @Override
    public String getName() {
        return "Create New Bean";
    }

    @Override
    public void run() {

        System.out.println("---Create new bean---");
        System.out.println("Enter beans name");
        final String name = getStringAssured();
        System.out.println("Enter beans scientific name");
        final String sciName = getStringAssured();
        System.out.println("Select Bean Origin:");
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
                                      .readIntRangeFromConsole(1, data.size())
                                      .ifError(e -> {
                                          // TODO: Error not in range somehow prompt
                                      })
                                      .map(data::get);
                 });
        return null;
    }

}