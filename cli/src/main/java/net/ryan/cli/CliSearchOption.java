package net.ryan.cli;

public class CliSearchOption implements CliOption {

    @Override
    public String getName() {
        return "Search Beans";
    }

    @Override
    public void run() {
        System.out.println("Search");
    }

}