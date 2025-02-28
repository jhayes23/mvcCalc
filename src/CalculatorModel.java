import java.util.Stack;

public class CalculatorModel {
    private final Stack<Integer> operands = new Stack<>();
    private String operator = null;

    public void clearOperator(){operator = null;}
    public String getOperator(){return operator;}
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void clearOperands(){operands.clear();}
    public void pushOperand(int operand) {operands.push(operand);}
    public int getOperandSize(){return operands.size();}
    public int getCalculationResult() {
        int operand2 = operands.pop();
        int operand1 =  operands.pop();
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> (operand2 != 0) ? operand1 / operand2 : 0;
            default -> 0;
        };
    }
}