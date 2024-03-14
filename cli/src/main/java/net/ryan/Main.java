package net.ryan;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Bean Encyclopedia CLI!\n");
        CliOptionHelper.getInstance()
                       .show();
    }
}