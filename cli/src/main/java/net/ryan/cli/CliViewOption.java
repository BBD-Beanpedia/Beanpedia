package net.ryan.cli;

import net.ryan.util.HttpHandler;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CliViewOption implements CliOption {


    @Override
    public String getName() {
        return "View All Beans";
    }

    @Override
    public void run() {
        pagination(1, 2);
    }

    private void pagination(int page, int maxPages) {
        System.out.println("---Viewing all beans---");
        System.out.printf("Page %d of %d\n", page, maxPages);

        // TODO: when this errors how should we not remake this call...
        List<String> list = List.of("Bean 1", "Bean 2", "Bean 3", "Bean 4", "Bean 5");
        final Map<Integer, String> optionMap = MapUtils.listToMap(list);
        HttpHandler.newGetRequest("http://localhost:8080/users/greeting")
                .mapToNew(HttpHandler.Request::sendString)
                .ifError(stringExceptionPair -> System.out.println("Unable to make http request"))
                .ifSuccess(System.out::println);


        optionMap.forEach((integer, cliOption) -> System.out.printf("\t%d. %s\n", integer + 1, cliOption));
        System.out.print("Enter a number to see more details, ");
        if (page != 1) System.out.print("'prev' for previous page,");
        if (page < maxPages) System.out.print("'next' for next page,");
        System.out.println("or 'exit' to exit");

        Consumer<String> x = (s) -> {
            //TODO: match string based on provided and run action for it.
            System.out.println("Hello " + s);
        };

        Consumer<Integer> runInt = (i) -> {
            // TODO: Show info on bean
//            ShortBeanModel s = optionMap.get(i);
            HttpHandler.newGetRequest("").map(HttpHandler.Request::sendString).ifSuccess(System.out::println);


            System.out.println(i);
        };
        InputUtils.getInstance().runMenuFunction(1, 2, List.of("next", "prev", "exit"), x, runInt).ifError(s -> {
            System.out.println(s.first());
            pagination(1, 2);
        });

//        InputUtils.getInstance().readMenuIntOptions()

//        InputUtils.getInstance().readIntRangeFromConsole()

    }

}