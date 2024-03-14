package net.ryan.cli;

import net.ryan.CliOptionHelper;
import net.ryan.bean.BeanModelFull;
import net.ryan.bean.BeanModelPage;
import net.ryan.util.DisplayHelper;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BeanDisplay {

    public static void handleDisplayBeansPaginated(int currentPage, BeanModelPage pageBeanList, Consumer<PaginationField> runSuccessForString, Consumer<BeanModelFull> runSuccessForInt) {

        final Integer totalPages = pageBeanList.maxPages();

        final Map<Integer, BeanModelFull> beanModelMap = MapUtils.listToMap(pageBeanList.beanList());
        beanModelMap.forEach(DisplayHelper::displayOption);
        StringBuffer stringBuffer = new StringBuffer();

        if (totalPages != 0) {
            System.out.printf("Page %d of %d\n", currentPage + 1, totalPages);
            stringBuffer.append("Enter an number to select an option ");
        }
        if (totalPages == 0) {
            System.out.println("No results found.");
        }

        final List<PaginationField> options = new ArrayList<>();
        if (currentPage < totalPages - 1) {
            options.add(PaginationField.NEXT);
            stringBuffer.append(" or 'next' to view the next page");
        }
        if (currentPage > 0) {
            options.add(PaginationField.PREVIOUS);
            stringBuffer.append(" or 'prev' to view the previous page");
        }
        options.add(PaginationField.MENU);
        if (totalPages != 0) stringBuffer.append(" or 'menu' to return to main menu");
        else stringBuffer.append("Enter 'menu' to return to main menu");
        System.out.println(stringBuffer);

        processUserInput(runSuccessForString, runSuccessForInt, beanModelMap, options);

    }

    private static void processUserInput(Consumer<PaginationField> runSuccessForPagination, Consumer<BeanModelFull> runSuccessForMap, Map<Integer, BeanModelFull> beanModelMap, List<PaginationField> options) {
        InputUtils.getInstance()
                  .runMenuFunction(beanModelMap, options, runSuccessForMap, runSuccessForPagination)
                  .ifError(s -> processUserInput(runSuccessForPagination, runSuccessForMap, beanModelMap, options));
    }


    public static void showBean(BeanModelFull modelFull) {
        System.out.println("\n---Bean Details---");
        System.out.println("- Name: " + modelFull.getName());
        System.out.println("- Scientific Name: " + modelFull.getScientificName());
        System.out.println("- Type: " + modelFull.getType());
        System.out.println("- Shape: " + modelFull.getShape());
        System.out.println("- Colour: " + modelFull.getColour());
        System.out.println("- Origin: " + modelFull.getOrigin());
        System.out.println("- Bean Content: " + modelFull.getContent());

        System.out.println("\nEnter 'edit' to update bean or 'menu' to return to main menu");
        InputUtils.getInstance()
                  .readStringFromConsoleLowerCase()
                  .ifSuccess(s -> {
                      if (s.equals("edit")) {
                          // TODO: update bean
                          UpdateBeanDisplay.showUpdate(modelFull);
                      } else if (s.equals("menu")) {
                          // TODO: back to cli
                          CliOptionHelper.getInstance()
                                         .show();
                      }
                  });
    }

}