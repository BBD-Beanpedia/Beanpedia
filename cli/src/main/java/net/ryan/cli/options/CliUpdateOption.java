package net.ryan.cli.options;

import net.ryan.CliOptionHelper;
import net.ryan.bean.BeanModel;
import net.ryan.bean.BeanModelFull;
import net.ryan.cli.Nameable;
import net.ryan.util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CliUpdateOption implements CliOption {

    private BeanModel model;
    private  Integer selection;

    @Override
    public String getName() {
        return "Update Existing Bean";
    }

    @Override
    public void run() {

        System.out.println("\n---Update existing bean---");

        System.out.println("Enter the name of the bean you would like to update! Or type back to return to the main menu");

        selectBean();
        System.out.println("\nUpdating bean: " + model.getBeanName());

        final Map<Integer, UpdateOption> updateOptions = MapUtils.listToMap(Arrays.stream(UpdateOption.values())
                                                                                  .toList());

        updateOptions.forEach(DisplayHelper::displayOption);

        getInputFromOptions(updateOptions, model);

        model.setSelection(Integer.toString(this.selection + 1));

        if(BeanDataHandler.getInstance().updateBean(model.toJsonStringUpdate()).get()){
            System.out.println("\n--Bean updated successfully!---");
            System.out.println("---Redirecting to Main Menu---\n");

            CliOptionHelper options = CliOptionHelper.getInstance();
            options.show();

        }
        else{
            System.out.println("\n--Bean update failed!---");
            System.out.println("--Please try again---");
            this.run();
        }

    }

    private void selectBean() {
        InputUtils.getInstance()

                .readStringFromConsoleDirect()
                .ifSuccess(searchTerm -> {
                    if(searchTerm.equalsIgnoreCase("back")){
                        System.out.println();
                        CliOptionHelper options = CliOptionHelper.getInstance();
                        options.show();
                    }
                    constructBeanModel(searchTerm);
                })
                .ifError(e -> {
                    System.out.println("Error: Unable to read input from console, " + e.getMessage());
                });

    }

    private void constructBeanModel(String searchTerm) {

        //Get the first bean that most closely matches the search term
        List<BeanModelFull> modelFullList = BeanDataHandler.getInstance().searchBean(searchTerm, 0).get().beanList();

        BeanModelFull modelFull = null;

        if(modelFullList.size() < 1){
            System.out.println("\n--The requested bean was not found!---");
            System.out.println("--Please try again---");
            this.run();
        }
        else {
            modelFull = modelFullList.getFirst();
        }

        BeanModel model = new BeanModel(-1, modelFull.getName(), modelFull.getScientificName(), modelFull.getContent(), -1, -1, -1, -1);
        model.setOrigin(modelFull.getOrigin());
        model.setType(modelFull.getType());
        model.setShape(modelFull.getShape());
        model.setColour(modelFull.getColour());

        this.model = model;
    }

    private void getInputFromOptions(Map<Integer, UpdateOption> updateOptions, BeanModel beanModel) {
        InputUtils.getInstance()
                  .readMapChoiceRangeFromConsole(updateOptions)
                  .ifSuccess(updateOptionSelected -> {
                      handleSelection(updateOptions, updateOptionSelected, beanModel);
                      if(updateOptionSelected.ordinal() != 7){
                          this.selection = updateOptionSelected.ordinal();
                      }
                  })
                  .ifError(e -> {
                      System.out.println("Error: Unable to read int from console, " + e.getMessage());
                      getInputFromOptions(updateOptions, beanModel);
                  });
    }


    private void handleSelection(Map<Integer, UpdateOption> updateOptions, UpdateOption selectedOption, BeanModel beanModel) {
        System.out.println();
        switch (selectedOption) {
            case NAME -> {
                System.out.println("Current Bean Name: " + beanModel.getBeanName());
                System.out.println("Enter new Bean Name: ");
                InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setNewBeanName);

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
                System.out.println("Current Bean Origin: " + beanModel.getOrigin());
                System.out.println("Enter new bean origin");
                InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setOrigin);
            }
            case TYPE -> {
                System.out.println("Current Bean Type: " + beanModel.getType());
                System.out.println("Enter new bean type");
                InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setType);
            }
            case SHAPE -> {
                System.out.println("Current Bean Shape: " + beanModel.getShape());
                System.out.println("Enter new bean shape");
                InputUtils.getInstance()
                          .readStringFromConsoleDirect()
                          .ifError(e -> System.out.println("Unable to read string from console"))
                          .ifSuccess(beanModel::setShape);
            }
            case COLOUR -> {
                System.out.println("Current Bean Colour: " + beanModel.getColour());
                System.out.println("Enter new bean colour");
                InputUtils.getInstance()
                        .readStringFromConsoleDirect()
                        .ifError(e -> System.out.println("Unable to read string from console"))
                        .ifSuccess(beanModel::setColour);
            }
            case FINISH -> {
                CliOptionHelper options = CliOptionHelper.getInstance();
                options.show();
            }
            default -> {
                System.out.println("\n Invalid selection!---");
                this.run();
            }

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
