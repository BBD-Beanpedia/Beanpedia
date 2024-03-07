package net.ryan.cli;

import net.ryan.util.InputUtils;

import java.util.List;
import java.util.function.Consumer;

public class CliViewOption implements CliOption {


    @Override
    public String getName() {
        return "View All Beans";
    }

    @Override
    public void run() {
        System.out.println("---Viewing all beans---");
        pagination(1, 2);
    }

    private void pagination(int page, int maxPages) {
        System.out.printf("Page %d of %d\n", page, maxPages);

        // Make web call to get all the beans for page x
        List<String> list = List.of("1. ");
        list.forEach(s -> System.out.println("Bean there done that"));
        final int size = list.size();

        System.out.print("Enter a number to see more details, ");
        if (page != 1) System.out.print("'prev' for previous page,");
        if (page < maxPages) System.out.print("'next' for next page,");
        System.out.println("or 'exit' to exit");

//        InputUtils.getInstance().readStringFromConsole()

//        InputUtils.getInstance().readStringFromConsoleInArray(List.of("next", "prev", "exit")).ifSuccess(System.out::println);


        Consumer<String> x = (s) -> {
//            System.out.println("1");
            System.out.println("Hello " + s);
        };

        Consumer<Integer> runInt = (i) -> {
            // Show the page stuff
            System.out.println(i);
        };
        InputUtils.getInstance().runMenuFunction(1, 2, List.of("next", "prev", "exit"), x, runInt).ifError(s -> {
            System.out.println(s.getFirst());
        });

//        InputUtils.getInstance().readMenuIntOptions()

//        InputUtils.getInstance().readIntRangeFromConsole()

    }

}