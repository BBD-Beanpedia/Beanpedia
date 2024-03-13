package net.ryan.cli;

import net.ryan.bean.BeanModel;
import net.ryan.bean.ShortBeanModel;
import net.ryan.util.DisplayHelper;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

import java.util.Arrays;
import java.util.Map;

public class CliUpdateOption implements CliOption {

    @Override
    public String getName() {
        return "Update Existing Bean";
    }

    @Override
    public void run() {

        System.out.println("---Update existing bean---");
        // TODO: pass in a bean to update
        ShortBeanModel model = new ShortBeanModel(1, "");
        System.out.printf("Selected Beam: %s\n", model);

        final Map<Integer, UpdateOption> updateOptions = MapUtils.listToMap(Arrays.stream(UpdateOption.values())
                                                                                  .toList());
        updateOptions.forEach(DisplayHelper::displayOption);

    }

    private void getInputFromOptions(Map<Integer, UpdateOption> updateOptions, BeanModel beanModel) {
        InputUtils.getInstance()
                  .readMapChoiceRangeFromConsole(updateOptions)
                  .ifSuccess(updateOptionSelected -> handleSelection(updateOptions, updateOptionSelected, beanModel))
                  .ifError(e -> {
                      System.out.println("Error: Unable to read int from console, " + e.getMessage());
                      getInputFromOptions(updateOptions, beanModel);
                  });
    }


    private void handleSelection(Map<Integer, UpdateOption> updateOptions, UpdateOption selectedOption, BeanModel beanModel) {
        switch (selectedOption) {
            case NAME -> {
                System.out.println("Current Bean Name: " + beanModel.getBeanName());
                System.out.println("Enter new Bean Name: ");
                InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setBeanName);
                System.out.println("Updated Bean Name: " + beanModel.getBeanName());
                System.out.println("Select a number to continue editing or the number of " + UpdateOption.FINISH.getName() + " to finish");
                getInputFromOptions(updateOptions, beanModel);
            }
            case SCIENTIFIC_NAME -> {
                System.out.println("Current Bean Scientific Name: " + beanModel.getScientificName());
                System.out.println("Enter new Bean Scientific Name: ");
                InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setScientificName);
            }
            case CONTENT -> {
                System.out.println("Current Bean Content: " + beanModel.getContent());
                System.out.println("Enter new Bean Content: ");
                InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setContent);
            }
            case ORIGIN -> {
                System.out.println("Current Bean Origin: " + beanModel.getContent());
        /*        InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setOrigin);*/

            }
            case TYPE -> {

            }
            case SHAPE -> {

            }
            case COLOUR -> {

            }
            case FINISH -> {
            }/* BeanDataHandler.getInstance()
                                          .updateBean(beanModel)
                                          .ifError(e -> {
                                              System.out.printf("Error updating bean type: %s message: %s\n", e.getCause(), e.getMessage());
                                          })
                                          .ifSuccess(s -> System.out.println("Bean Updated Successfully"));*/
        }

    }


    enum UpdateOption implements Nameable {
        NAME("Update Bean Name"), SCIENTIFIC_NAME("Update Scientific Name"), CONTENT("Update Content"), ORIGIN("Update Origin"), TYPE("Update Type"), SHAPE("Update Shape"), COLOUR("Update Colour"), FINISH("Finish Update");

        private final String name;

        UpdateOption(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

}