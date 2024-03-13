package net.ryan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class InputUtils {
    private final BufferedReader bufferedReader;
    private static InputUtils inputUtils;

    private InputUtils(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public static InputUtils getInstance() {
        //noinspection StatementWithEmptyBody
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
            return Result.fail(e);
        }
    }

    public Result<Integer> readIntFromConsole() {
        return readFromConsoleToType(Integer::parseInt).mapError(Result::fail);
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

    public <T> Result<T> readMapChoiceRangeFromConsole(Map<Integer, T> inputMap) {
        return readIntRangeFromConsole(1, inputMap.size()).map(i -> i - 1)
                                                          .map(inputMap::get);
    }

    private Result<Integer> notInRangeMapper(int givenNum, int start, int end) {
        if (givenNum < start || givenNum > end)
            return Result.fail(new Exception(String.format("%d is not in the range [%d-%d]", givenNum, start, end)));
        else return Result.success(givenNum);
    }

    private Result<Double> nonNumberTypesMapper(Double input) {
        if (input.isNaN() || input.isInfinite())
            return Result.fail(new Exception("Number entered must not be NaN or Infinite."));
        else return Result.success(input);
    }

    private Result<String> foundInStrings(String input, List<String> list) {
        if (!list.contains(input)) return Result.fail(new Exception("String not in array"));
        else return Result.success(input);
    }

    public Result<String> runMenuFunction(int start, int end, List<String> strings, Consumer<String> runSuccessForString, Consumer<Integer> runSuccessForInt) {
        return readStringFromConsoleLowerCase().mapDirect(str -> {
            //First we will try and parse the as int if this fails we will try the strings.
            //We will not talk about this type conversion hackery.
            return Result.from(() -> Integer.parseInt(str))
                         .mapToNew(i -> notInRangeMapper(i, start, end).ifSuccess(runSuccessForInt))
                         .map(String::valueOf)
                         .mapError(_e -> foundInStrings(str, strings).ifSuccess(runSuccessForString));
        });
    }
}
