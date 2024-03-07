package net.ryan.cli;

public class CliFilterOption implements CliOption {


    @Override
    public String getName() {
        return "Filter Beans";
    }

    @Override
    public void run() {
        System.out.println("Filter");
    }

}