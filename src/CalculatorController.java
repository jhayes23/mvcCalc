import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CalculatorController extends Application {
    private final CalculatorView view;
    private final CalculatorModel model;
    private String previousClick = "";
    private String input;
    private String output;

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
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void processButtonClick(ActionEvent event) {
        if(event.getSource() instanceof Button button) {
            input = button.getUserData().toString();
            System.out.println("Button clicked: " + input);
            output = view.getDisplay();

            if (input.equals("AC")) {
                clearCalculator();
            }
            else if (input.equals("=")) {
                processEquals();
            }
            else if (input.matches("[+\\-*/]")) {
                 processOperator();
                }
            else if (input.matches("\\d+")) {
                processNum();
                }

            previousClick = input;
        }
    }

    private void processNum() {
        if (previousClick.matches("[=+\\-*/]") && !output.isEmpty()) {
           output = "";
        }
        view.setDisplay(output.isEmpty() || output.equals("0") ? input : output.concat(input));
    }
    private void processOperator() {
        if (previousClick.matches("[+\\-*/]")) {
            model.setOperator(input);
        } else {
            if (!output.isEmpty()) {
                model.pushOperand(Integer.parseInt(output), "Process  Operator");
            }

            if (model.getOperandSize() == 2 && model.getOperator() != null) {

                model.pushOperand(computeAndDisplayResult(), "Process  Operator");
            }

            model.setOperator(input);
        }
    }

    private void processEquals() {
        if (!output.isEmpty() && model.getOperandSize() == 1 && !previousClick.matches("[+\\-*/=]")) {
            model.pushOperand(Integer.parseInt(output), "Process  Equals");
        }

        if (model.getOperandSize() == 2 && model.getOperator() != null) {
            computeAndDisplayResult();
        }
    }
    private void clearCalculator() {
        view.setDisplay("");
        model.clearOperands();
        model.clearOperator();
    }
    private int computeAndDisplayResult() {
        int result = model.getCalculationResult();
        view.setDisplay(String.valueOf(result));
        model.clearOperands();
        model.clearOperator();

        return result;
    }

    }
