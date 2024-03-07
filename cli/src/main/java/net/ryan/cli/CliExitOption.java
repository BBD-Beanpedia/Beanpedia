package net.ryan.cli;

public class CliExitOption implements CliOption {


    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public void run() {
        System.exit(0);
    }

}