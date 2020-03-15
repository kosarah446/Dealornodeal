package DealKosarah;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class LabelDisplay extends VBox {
	Label[] label_array = new Label[10];
	public static int[] dollarValues = new int[]{1, 5, 10, 100, 1000, 5000, 10000, 100000, 500000, 1000000000};

	public LabelDisplay() {
		for (int i = 0; i < dollarValues.length; i++) {
			// Creating label and rectangle
			Label label = new Label();
			Rectangle rec = new Rectangle();
			Font ff = Font.font("Verdana", FontWeight.EXTRA_BOLD, 30);
			label.setFont(ff);
			label.setText("$" + dollarValues[i]);
			label.setTextFill(Color.WHITE);
			rec.setFill(Color.BLACK);
			rec.setWidth(250);
			rec.setHeight(50);
			label_array[i] = label;
			StackPane stack = new StackPane();
			// add the rec to stackpane
			// then add label to the rec
			stack.getChildren().add(rec);
			stack.getChildren().add(label);
			getChildren().add(stack);
		}
	}

	public LabelDisplay(int[] dollarValues) {
		for (int i = 0; i < dollarValues.length; i++) {
			Label label = new Label();
			Rectangle rec = new Rectangle();
			Font ff = Font.font("Verdana", FontWeight.EXTRA_BOLD, 30);
			label.setFont(ff);
			label.setText("$" + dollarValues[i]);
			label.setTextFill(Color.WHITE);
			rec.setWidth(250);
			rec.setHeight(50);
			label_array[i] = label;
			StackPane stack = new StackPane();
			// add the rec to stackpane
			// then add label to the rec
			stack.getChildren().add(rec);
			stack.getChildren().add(label);
			getChildren().add(stack);
		}

	}

	// This gets a string input and 
	// disables the corresponding label
	public void isDisabled(String val) {
		for (int i=0; i < getChildren().size(); i++) {
			if (val.equals(((Label)(((StackPane)(getChildren().get(i))).getChildren().get(1))).getText())) {
				((Rectangle)((StackPane)(getChildren().get(i))).getChildren().get(0)).setFill(Color.GREY);
			}
		}

	}

	// This gets an index and 
	// disables the corresponding label
	public void isDisabled(int k) {
		((Rectangle)((StackPane)(getChildren().get(k))).getChildren().get(0)).setFill(Color.GREY);		
	}

	// gets the dollarValues
	public int[] getDollarValues() {
		return dollarValues;
	}
}
