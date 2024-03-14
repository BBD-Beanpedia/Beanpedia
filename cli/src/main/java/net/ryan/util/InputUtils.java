package net.ryan.util;

import net.ryan.bean.BeanModelFull;
import net.ryan.cli.Nameable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputUtils {
    private static InputUtils inputUtils;

    private final Supplier<Result<String>> readLineSupplier;

    private InputUtils(BufferedReader bufferedReader) {
        this.readLineSupplier = () -> Result.from(bufferedReader::readLine);
    }

    public static InputUtils getInstance() {
        //noinspection StatementWithEmptyBody
        if (inputUtils == null) inputUtils = new InputUtils(new BufferedReader(new InputStreamReader(System.in)));
        else { /* NO-OPP */}
        return inputUtils;
    }

    private <T> Result<T> readFromConsoleToType(Supplier<Result<String>> lineSupplier, Function<String, T> conversionFunction) {
        return lineSupplier.get()
                           .mapToNew(s -> Result.from(() -> conversionFunction.apply(s)));
    }

    public Result<Integer> readIntFromConsole() {
        return readFromConsoleToType(readLineSupplier, Integer::parseInt);
    }


    public Result<String> readStringFromConsoleDirect() {
        return readLineSupplier.get();
    }

    public Result<String> readStringFromConsoleLowerCase() {
        return readFromConsoleToType(readLineSupplier, String::toLowerCase);
    }

    public Result<Integer> readIntRangeFromConsole(Supplier<Result<String>> readLineSupplier, int start, int end) {
        return notInRangeMapper(readFromConsoleToType(readLineSupplier, Integer::parseInt).mapError((r) -> new RuntimeException("Unable to parse string to int " + r.getMessage())), start, end);
    }

    public <T> Result<T> readMapChoiceRangeFromConsole(Map<Integer, T> inputMap) {
        return readIntRangeFromConsole(readLineSupplier, 1, inputMap.size()).map(i -> i - 1)
                                                                            .map(inputMap::get);
    }

    public <T> Result<T> readMapChoiceRangeFromConsole(Supplier<Result<String>> readLineSupplier, Map<Integer, T> inputMap) {
        return readIntRangeFromConsole(readLineSupplier, 1, inputMap.size()).map(i -> i - 1)
                                                                            .map(inputMap::get);
    }

    private Result<Integer> notInRangeMapper(Result<Integer> givenNum, int start, int end) {
        return givenNum.mapDirect(num -> Result.from(() -> notInRangeMapper(num, start, end)));
    }

    private Integer notInRangeMapper(int givenNum, int start, int end) throws Exception {
        if (givenNum < start || givenNum > end)
            throw new Exception(String.format("%d is not in the range [%d-%d]", givenNum, start, end));
        else return givenNum;
    }

    private <T extends Nameable> Result<T> foundInNameable(String input, List<T> list) {
        Optional<T> foundItem = list.stream()
                                    .filter(t -> t.getName()
                                                  .equalsIgnoreCase(input))
                                    .findFirst();

        return foundItem.map(Result::success)
                        .orElseGet(() -> Result.fail(new Exception("String not in array")));
    }

    public <T extends Nameable, M> Result<Boolean> runMenuFunction(Map<Integer, M> inputMap, List<T> strings, Consumer<M> runSuccessForMap, Consumer<T> runSuccessForString) {

        return readStringFromConsoleLowerCase().mapToNew(str -> {
            final Result<T> stringResult = foundInNameable(str, strings).ifSuccess(runSuccessForString);
            final Result<M> mappedResult = stringKeyToValue(str, inputMap).ifSuccess(runSuccessForMap);

            stringResult.ifError(e -> {
                if (mappedResult.isError()) System.out.println(e.getMessage());
            });
            mappedResult.ifError(e -> {
                if (stringResult.isError()) System.out.println(e.getMessage());
            });

            if (stringResult.isError() && mappedResult.isError()) return Result.fail(new Exception("Both failed"));
            else return Result.success(true);
        });
    }

    private <M> Result<M> stringKeyToValue(String str, Map<Integer, M> inputMap) {
        return readIntRangeFromConsole(() -> Result.success(str), 1, inputMap.size()).map(i -> i - 1)
                                                                                     .map(inputMap::get);
    }

}
