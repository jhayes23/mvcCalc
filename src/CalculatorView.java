import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CalculatorView {
    private final Label display;
    private final VBox primaryBox;

    private final String[] buttonsAsText = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "AC", "0", "=", "+"
    };
    private final Button[] buttons = new Button[buttonsAsText.length];

    public CalculatorView() {
        primaryBox = new VBox();
        primaryBox.setAlignment(Pos.CENTER);

        display = new Label("0");
        display.setFont(Font.font("Courier", FontWeight.BOLD, 24));
        display.setMinSize(200, 50);
        display.setAlignment(Pos.CENTER_RIGHT);

        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setHgap(5);
        buttonsGrid.setVgap(5);
        buttonsGrid.setAlignment(Pos.CENTER);

        for (int i = 0; i < buttonsAsText.length; i++) {
            buttons[i] = new Button(buttonsAsText[i]);
            buttons[i].setMinSize(50, 50);
            buttons[i].setUserData(buttonsAsText[i]);
            int col = i % 4;
            int row = i / 4;
            buttonsGrid.add(buttons[i], col, row);
        }
        primaryBox.getChildren().addAll(display, buttonsGrid);
    }

    public Parent getParent() {return primaryBox;}

    public String getDisplay() {return display.getText();}
    public void setDisplay(String input) {display.setText(input);}

    public void setActions(EventHandler<ActionEvent> handler) {
        for (Button button : buttons) {
            button.setOnAction(handler);
        }
    }
}