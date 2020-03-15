package DealKosarah;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.*;


public class Suitcase extends Button {
	int value;
	int num;
	boolean isOpened;
	ImageView imgv;

	// Constructor
	public Suitcase(int i) {
		value = 0;
		num = 0;
		isOpened = false;
		Image img = new Image("/images/suitcase" + i + ".png");
		imgv = new ImageView(img);
		imgv.setFitHeight(100);
		imgv.setFitWidth(100);
		// Pass the imageview to setgraphic
		setGraphic(imgv);
	}

	public Suitcase(int value, int num, boolean isOpened, ImageView img) {
		this.value = value;
		this.num = num;
		this.isOpened = isOpened;
	}

	public ImageView getImage() {
		return imgv;
	}

	// Set the getters
	public int getValue() {
		return value;
	}

	public int getNum() {
		return num;
	}

	public boolean getOpened() {
		return isOpened;
	}

	// Set the setters
	public void setValue(int value) {
		this.value = value;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setOpened(boolean isOpened) {
		this.isOpened = isOpened;
	}

	public void setImage(ImageView imgv) {
		this.imgv = imgv;
	}
}
