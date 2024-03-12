package net.ryan;

import net.ryan.cli.*;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

import java.util.List;
import java.util.Map;

public class CliOptionHelper {

    private final Map<Integer, CliOption> cliOptions;

    public static CliOptionHelper register() {
        List<CliOption> options = List.of(
                new CliViewOption(),
                new CliSearchOption(),
                new CliFilterOption(),
                new CliCreateOption(),
                new CliUpdateOption(),
                new CliAuthOption(),
                new CliExitOption());
        return new CliOptionHelper(MapUtils.listToMap(options));
    }


    public CliOptionHelper(Map<Integer, CliOption> options) {
        this.cliOptions = options;
    }

    public void show() {
        System.out.println("Chose an option");
        cliOptions.forEach((integer, cliOption) -> System.out.printf("\t%d. %s\n", integer + 1, cliOption.getName()));
        System.out.println("Enter a number to chose an option: ");
        getInputInRange();
    }

    public void getInputInRange() {
        InputUtils.getInstance()
                  .readIntRangeFromConsole(1, cliOptions.size())
                  .map(num -> cliOptions.get(num - 1))
                  .ifSuccess(CliOption::run)
                  .ifError(error -> {
                      System.out.println("Error: Unrecognized command " + error.getMessage());
                      getInputInRange();
                  });
    }


}
