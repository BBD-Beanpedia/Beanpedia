package net.ryan.cli;

import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

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

        //
        List<String> list = List.of("Bean 1", "Bean 2", "Bean 3", "Bean 4", "Bean 5");
        MapUtils.listToMap(list).forEach((integer, cliOption) -> System.out.printf("\t%d. %s\n", integer + 1, cliOption));
        System.out.print("Enter a number to see more details, ");
        if (page != 1) System.out.print("'prev' for previous page,");
        if (page < maxPages) System.out.print("'next' for next page,");
        System.out.println("or 'exit' to exit");

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