package net.ryan.util;

import net.ryan.cli.Nameable;

public class DisplayHelper {
    public static <T extends Nameable> void displayOption(Integer integer, T nameable) {
        System.out.printf("\t%d. %s\n", integer + 1, nameable.getName());
    }
}
