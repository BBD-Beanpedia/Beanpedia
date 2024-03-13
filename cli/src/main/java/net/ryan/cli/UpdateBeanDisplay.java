package net.ryan.cli;

import net.ryan.bean.BeanModelFull;

public class UpdateBeanDisplay {


    public static void show(BeanModelFull modelFull) {

        //TODO: check for auth before we make a request.

        System.out.println("Update Bean: " + modelFull.getName());






//        System.out.println("Enter a number to update the field or 'exit' to return to main menu");

    }
}
