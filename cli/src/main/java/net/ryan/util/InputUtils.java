package net.ryan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class InputUtils {
    private final BufferedReader bufferedReader;
    private static InputUtils inputUtils;

    private InputUtils(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public static InputUtils getInstance() {

        if (inputUtils == null) inputUtils = new InputUtils(new BufferedReader(new InputStreamReader(System.in)));
        else { /* NO-OPP */}
        return inputUtils;
    }

    private <T> Result<T> readFromConsoleToType(Function<String, T> conversionFunction) {
        try {
            // Read a line from the console and apply the given function
            final String line = bufferedReader.readLine();
            return Result.from(() -> conversionFunction.apply(line));
        } catch (IOException e) {
            return Result.fail("Unable to read input from ", e);
        }
    }

    public Result<Integer> readIntFromConsole() {
        return readFromConsoleToType(Integer::parseInt)
                .mapError(exception -> Result.fail("Unable to parse string to integer.", exception));
    }

    public Result<String> readStringFromConsoleDirect() {
        return readFromConsoleToType(String::toString);
    }

    public Result<String> readStringFromConsoleLowerCase() {
        return readFromConsoleToType(String::toLowerCase);
    }

    public Result<Integer> readIntRangeFromConsole(int start, int end) {
        return readIntFromConsole().mapDirect(num -> notInRangeMapper(num, start, end));
    }

    public Result<String> readStringFromConsoleInArray(List<String> list) {
        return readStringFromConsoleLowerCase().mapDirect(item -> foundInStrings(item, list));
    }

    public Result<String> readStringFromConsoleInArray(int start, int end, List<String> list) {
        return readStringFromConsoleLowerCase().mapDirect(item -> foundInStrings(item, list));
    }

    public Result<Double> readDoubleFromConsole() {
        return readFromConsoleToType(Double::parseDouble)
                .mapDirect(this::nonNumberTypesMapper)
                .mapError(exception -> Result.fail("Unable to parse string as double", exception));
    }

    private Result<Integer> notInRangeMapper(int givenNum, int start, int end) {
        if (givenNum < start || givenNum > end)
            return Result.fail("", new Exception(String.format("%d is not in the range [%d-%d] \n", givenNum, start, end)));
        else return Result.success(givenNum);
    }

    private Result<Double> nonNumberTypesMapper(Double input) {
        if (input.isNaN() || input.isInfinite())
            return Result.fail(new Exception("Number entered must not be NaN or Infinite."));
        else return Result.success(input);
    }

    private Result<String> foundInStrings(String input, List<String> list) {
        if (!list.contains(input))
            return Result.fail(String.format("%s is not found in %s", input, list), new Exception("String not in array"));
        else return Result.success(input);
    }

    public Result<String> runMenuFunction(int start, int end, List<String> strings, Consumer<String> x, Consumer<Integer> runInt) {
        return readStringFromConsoleLowerCase().mapDirect(str -> {
            //First we will try and parse the as int if this fails we will try the strings.
            // We will not talk about this type conversion hackery.
            return Result.from(() -> Integer.parseInt(str))
                         .mapToNew(i -> notInRangeMapper(i, start, end).ifSuccess(runInt))
                         .map(String::valueOf)
                         .mapError(_e -> foundInStrings(String.valueOf(str), strings).ifSuccess(x));
        });
    }
}
