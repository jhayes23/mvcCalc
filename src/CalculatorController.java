import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CalculatorController extends Application {
    private final CalculatorView view;
    private final CalculatorModel model;
    private boolean resetCalculator = false;

    public CalculatorController() {
        view = new CalculatorView();
        model = new CalculatorModel();

        view.setActions(this::processButtonClick);
    }
    @Override
    public void start(Stage primaryStage){
        CalculatorController calcController = new CalculatorController();
        Scene scene = new Scene(calcController.view.getParent(), 250, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void processButtonClick(ActionEvent event) {
        if(event.getSource() instanceof Button button) {
            String input = button.getUserData().toString();

            if (input.equals("AC")) {
                view.setDisplay("0");
                model.clearOperands();
                model.clearOperator();
            }
            else if (input.matches("[+\\-*/]")) {
                if( model.getOperator()== null) {
                    model.pushOperand(Integer.parseInt(view.getDisplay()));
                    view.setDisplay("0");
                }
                model.setOperator(input);
            }
            else if(input.matches("\\d+")) {
                if(resetCalculator) {
                    view.setDisplay("0");
                    resetCalculator = false;
                }
                view.setDisplay(view.getDisplay().equals("0") ? input : view.getDisplay().concat(input));
            }
            else if(input.equals("=")){
                if (model.operandIsEmpty() && !model.getOperator().isEmpty() && !view.getDisplay().equals("0")) {
                    model.pushOperand(Integer.parseInt(view.getDisplay()));

                    int result = model.getCalculationResult();
                    view.setDisplay(String.valueOf(result));

                    model.clearOperands();
                    model.clearOperator();
                    resetCalculator = true;
                }
            }

        }

    }
}