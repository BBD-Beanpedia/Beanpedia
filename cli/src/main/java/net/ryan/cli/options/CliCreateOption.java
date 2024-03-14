package net.ryan.cli.options;


import net.ryan.CliOptionHelper;
import net.ryan.bean.BeanModel;
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


        System.out.println("\n---Create new bean---");

        System.out.println("Enter the name of the bean.");
        final String beanName = getStringAssured();
        if (beanName.equalsIgnoreCase("back")) {
            System.out.println();
            CliOptionHelper options = CliOptionHelper.getInstance();
            options.show();
        }

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

        Result<String> response = BeanDataHandler.getInstance()
                                                 .createBean(model.toJsonStringUpdate());

        System.out.println(model.toJsonStringUpdate());

        if (!response.get()
                     .equalsIgnoreCase("Bean saved successfully!")) {
            System.out.println("\n---" + response.get() + "---");
            this.run();
        } else {
            System.out.println("\n---" + response.get() + "---\n");
            System.out.println("---Redirecting to Main Menu---\n");

            CliOptionHelper options = CliOptionHelper.getInstance();
            options.show();
        }

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

}