package net.ryan.cli;

public class CliExitOption implements CliOption {

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public void run() {
        System.out.println("Thank you for using the Bean Encyclopedia!");
        System.exit(0);
    }

}