package DealKosarah;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DealKosarah extends Application {
	StackPane stack = new StackPane();
	Label offerDisplay = new Label();
	VBox vbox = new VBox();
	HBox hb = new HBox();
	GridPane grid = new GridPane();
	public static boolean matched0 = false;
	public static boolean matched1 = false;
	public static boolean matched2 = false;
	public static boolean matched3 = false;
	public static boolean matched4 = false;
	public static boolean matched5 = false;
	public static boolean matched6 = false;
	public static boolean matched7 = false;
	public static boolean matched8 = false;
	public static boolean matched9 = false;
	public static boolean gameOver = false;
	public static boolean state = false;
	public static Suitcase myCase2;
	public static Suitcase[] suitcase_list = new Suitcase[10];
	public static String key_str;
	public static int value;
	public static LabelDisplay lbl = new LabelDisplay();
	public static int noDealClicks = 0;
	public static Button deal_btn = new Button();
	public static Button noDeal_btn = new Button();
	public static int offer = 0;
	public static int casesRemaining = 10;
	public static int runningTotal;
	public static int currOffer;
	public static String offer_str;
	public static Integer[] dollarValues = new Integer[]{1, 5, 10, 100, 1000, 5000, 10000, 100000, 500000, 1000000000};

	@Override
	public void start(Stage stage) {
		initUI(stage);

	}

	public static void main(String[] args) {
		launch(args);
	}


	// This gets the offer to be displayed
	public static void getOffer(int runningTotal, int casesRemaining, Label offerDisplay) {
		if (casesRemaining == 0) {
			offerDisplay.setText("                You won $" + offer);	
			deal_btn.setDisable(true);
			noDeal_btn.setDisable(true);
		}
		else {
			offer = runningTotal/casesRemaining;
			offer = (int)(offer * .90);
			offerDisplay.setText("The dealer is offering $" + offer);
		}

	}

	// This returns the offer 
	public static int getOfferForDeal(int runningTotal, int casesRemaining) {
		if (casesRemaining == 0) {
			return offer;		
		}
		else {
			offer = runningTotal/casesRemaining;
			offer = (int)(offer * .90);
			return offer;
		}
	}

	// When the user clicks on firstSuitcase, this is called
	// Disables the buttons and makes the buttons invisible
	public static void firstSuitcase(int casesRemaining, Suitcase myCase, Label offerDisplay) {
		state = false;
		offerDisplay.setText("            Your suitcase is case #" + myCase.getNum());
		deal_btn.setDisable(true);
		noDeal_btn.setDisable(true);
		deal_btn.setVisible(true);
		noDeal_btn.setVisible(true);
	}

	// This disables suitcases
	public static void disableSuitcases(GridPane grid) {
		grid.setDisable(true);
	}
	
	// When the user chooses "deal",
	// the user accepts the offer 
	// and game is over
	public static void deal(Label offerDisplay, GridPane grid, LabelDisplay lbl) {
		grid.setDisable(true);
		for (int k=0; k < lbl.getChildren().size(); k++) {
			lbl.isDisabled(k);
		}
		offer_str = Integer.toString(getOfferForDeal(runningTotal, casesRemaining));
		offerDisplay.setText("              You won $" + offer_str);
		deal_btn.setDisable(true);
		noDeal_btn.setDisable(true);

	}

	// This makes the suitcases disabled
	// If the casesremaining is 1 then disables all the labels and buttons
	//Prints out open the last case
	// If not, prints out open a case and disabled the labels and the buttons
	public static void noDeal(Label offerDisplay, int casesRemaining, Button deal_btn, GridPane grid) {
		grid.setDisable(false);
		if(casesRemaining == 1) {
			for (int k=0; k < lbl.getChildren().size(); k++) {
				lbl.isDisabled(k);
			}
			offerDisplay.setText("                  Open the last case");
			deal_btn.setDisable(true);
			noDeal_btn.setDisable(true);
		}
		else {
			noDealClicks += 1;
			if (noDealClicks == 1) {
				offerDisplay.setText("                    Open a case");
				deal_btn.setDisable(true);
				noDeal_btn.setDisable(true);
				noDealClicks -=1;
			}

		}

	}
	public void initUI(Stage stage) {
		// Usual setup
		Scene scene = new Scene(stack, 960, 700);
		stage.setTitle("DealKosarah");
		stage.setScene(scene);
		stage.show();

		// Setting background img
		Image img = new Image("Images/background.jpg");
		ImageView imgv = new ImageView(img);
		stack.getChildren().add(imgv);

		// Create a borderpane for the different parts
		BorderPane bp = new BorderPane();
		List<Integer> arr = Arrays.asList(dollarValues);
		// Randomize by shuffling
		Collections.shuffle(arr);
		dollarValues = arr.toArray(dollarValues);
		// Create rectangles for the prize dollars
		Rectangle rec = new Rectangle();
		rec.setFill(Color.BLACK);
		rec.setWidth(500);
		rec.setHeight(200);
		Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 30);
		// The display for the offer
		offerDisplay.setFont(font);
		offerDisplay.setTextFill(Color.WHITE);
		hb.setPadding(new Insets(0, 0, 10, 200));
		hb.getChildren().add(offerDisplay);
		vbox.getChildren().add(hb);

		// Creating suitcases and 
		// Displaying it
		for (int i=0; i <10; i++) {
			int k = i;
			Suitcase myCase = new Suitcase(i+1);
			myCase.setNum(i+1);
			myCase.setValue(dollarValues[i]);
			// Initialize the suitcase_list with suitcases
			suitcase_list[i] = myCase;
			runningTotal += myCase.getValue();
			System.out.println(dollarValues[i]);
			int j = i;
			if (i==8 || i==9) {
				j+=1;
			}
			grid.setHgap(20);
			grid.setVgap(20);
			grid.add(myCase, j%4, j/4);


			scene.setOnKeyPressed
			(  (KeyEvent ke) -> 
			{
				KeyCode key;
				// Store the keycode in key
				key = ke.getCode();
				if (!gameOver) {
					// If there are no more cases
					// then game is over
					if (casesRemaining == 0) {
						gameOver = true;
					}

					// If state is false then enter the digit cases
					if (!state) {
						switch (key) {
						// If the user inputs 1
						case DIGIT1:
							// Set the value to the first element in dollarvalues
							value = dollarValues[0];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									// myCase2 is the suitcase that matches the value
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched1) {
								matched1 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									// make the state true so the next time 
									// it's deal or no deal
									state = true;
									// cases remaining is -1
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									// make the state true
									state = true;
									// enable both deal and no deal buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									// cases remaining is -1
									casesRemaining -= 1; 
									// remove the value of the suitcase from the running total
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[0] = -1;
								}
							}
							break;
						// If the user inputs 2
						case DIGIT2:
							value = dollarValues[1];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									// mycase2 is set to whichever suitcase
									// matches the value
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched2) {
								matched2 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									// make the state true so the next time
									// it's deal or no deal
									state = true;
									// cases remaining is -1
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									// make the state true
									state = true;
									// enable both deal and no deal buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									// cases remaining is -1
									casesRemaining -= 1; 
									// remove the value of the suitcase from the running total
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[1] = -1;
								}
							}
							break;
						// If the user inputs 3
						case DIGIT3:
							value = dollarValues[2];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									// mycase2 is set to whichever suitcase
									// matches the value
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched3) {
								matched3 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									// make the state true so the next time
									// it's deal or no deal
									state = true;
									// cases remaining is -1
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									// make the state true
									state = true;
									// enable both deal and no dea buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									// cases remaining is -1
									casesRemaining -= 1; 
									// remove the value of the suitcase from the running total
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[2] = -1;
								}
							}
							break;
						// If the user inputs 4
						case DIGIT4:
							value = dollarValues[3];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									// mycase2 is set to whichever suitcase
									// matches the value
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched4) {
								// make the state true so the next time
								// it's deal or no deal
								matched4 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									state = true;
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									// make the state true
									state = true;
									// enable both deal and no deal buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									// cases remaining is -1
									casesRemaining -= 1; 
									// remove the value of the suitcase from the running total
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[3] = -1;
								}
							}
							break;   
						// If the user inputs 5
						case DIGIT5:
							value = dollarValues[4];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									// mycase2 is set to whichever suitcase
									// matches the value
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched5) {
								// make the state true so the next time
								// it's deal or no deal
								matched5 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									state = true;
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									// make the state true so the next time
									// it's deal or no deal
									state = true;
									// emnable both deal and no deal buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									// cases remaining is -1
									casesRemaining -= 1; 
									// remove the value of the suitcase from the running total
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[4] = -1;
								}
							}
							break;
						// If the user inputs 6
						case DIGIT6:
							value = dollarValues[5];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched6) {
								matched6 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									state = true;
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									state = true;
									// enable both deal and no deal buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									// remove 1 from the cases remaining
									casesRemaining -= 1; 
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[5] = -1;
								}
							}
							break;
						// If the user inputs 7
						case DIGIT7:
							value = dollarValues[6];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched7) {
								matched7 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								// if the  beginning of the game
								if (casesRemaining == 10) {
									state = true;
									// remove 1 from the cases remaining
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									state = true;
									// enable both deal and no deal buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									// remove one from the cases remaining
									casesRemaining -= 1; 
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[6] = -1;
								}
							}
							break;   
						// If the user inputs 8
						case DIGIT8:
							value = dollarValues[7];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched8) {
								matched8 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									// make the state true so the next time 
					                 // it's deal or no deal
									state = true;
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									state = true;
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									casesRemaining -= 1; 
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[7] = -1;
								}
							}
							break; 
						// If the user inputs 9
						case DIGIT9:
							value = dollarValues[8];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched9) {
								boolean matched = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									// make the state true so the next time 
					                 // it's deal or no deal
									state = true;
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									state = true;
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									casesRemaining -= 1; 
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[8] = -1;
								}
							}
							break;  
						// If the user inputs 0, which is suitcase 10
						case DIGIT0:
							value = dollarValues[9];
							for (int w = 0; w < suitcase_list.length; w++) {
								if (suitcase_list[w].getValue() == value) {
									myCase2 = suitcase_list[w];
								}
							}
							if(!matched0) {
								matched0 = true;
								myCase2.setOpened(true);
								myCase2.setDisable(true);
								if (casesRemaining == 10) {
									// make the state true so the next time 
					                 // it's deal or no deal
									state = true;
									casesRemaining -= 1;
									runningTotal -= myCase2.getValue();
									firstSuitcase(casesRemaining, myCase2, offerDisplay);
								}
								else {
									state = true;
									// enable both deal and no deal buttons
									deal_btn.setDisable(false);
									noDeal_btn.setDisable(false);
									lbl.isDisabled("$" + value);
									casesRemaining -= 1; 
									runningTotal -= myCase2.getValue();
									disableSuitcases(grid);
									getOffer(runningTotal, casesRemaining, offerDisplay);
									dollarValues[9] = -1;
								}
							}
							break;  
						}
					}

					// If the state is true then allow for 
					// deal or no deal buttons
					else {
						switch (key) {
						case D:
							key_str = "d";
							deal(offerDisplay, grid, lbl);
							state = false;
							break;  
						case N:
							key_str = "n";
							noDeal(offerDisplay, casesRemaining, deal_btn, grid);
							state = false;
							break;  
						default:
							break;
						}
					}
				}

			});

			// If the user clicks on suitcase 1
			if (myCase.getValue() == 1 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					// Disable the suitcase
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$1");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 5 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$5");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 10 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$10");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 100 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$100");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 1000 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$1000");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 5000 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$5000");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 10000 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$10000");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 100000 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						lbl.isDisabled("$100000");
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 500000 || myCase.getValue() == value) {
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						lbl.isDisabled("$500000");
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}

			else if (myCase.getValue() == 1000000000 || myCase.getValue() == value){
				myCase.setOnAction((ActionEvent event) -> {
					myCase.setOpened(true);
					myCase.setDisable(true);
					// If it's the first suitcase
					// then don't allow user to deal or no deal
					if (casesRemaining == 10) {
						casesRemaining -= 1;
						runningTotal -= myCase.getValue();
						firstSuitcase(casesRemaining, myCase, offerDisplay);
					}
					// Otherwise user can choose to deal or no deal
					else {
						deal_btn.setDisable(false);
						noDeal_btn.setDisable(false);
						casesRemaining -= 1; 
						runningTotal -= myCase.getValue();
						lbl.isDisabled("$1000000000");
						disableSuitcases(grid);
						getOffer(runningTotal, casesRemaining, offerDisplay);
					}
					System.out.println("" + casesRemaining);
				});
			}
		}


		bp.setCenter(grid);
		stack.getChildren().add(bp);
		// Add a top label for spacing purposes
		Label topLabel = new Label();
		Font ff = Font.font("Verdana", FontWeight.EXTRA_BOLD, 60);
		topLabel.setFont(ff);
		// Empty top label for top spacing
		topLabel.setText("");
		topLabel.setTextFill(Color.WHITE);
		topLabel.setPadding(new Insets(5, 0, 0, 0));
		bp.setTop(topLabel);
		// Top, right, bottom, left
		lbl.setPadding(new Insets(0, 40, 0, 100));
		bp.setLeft(lbl);


		// Deal or no deal buttons
		HBox hb2 = new HBox();
		// Add css for the deal and no deal buttons
		deal_btn.setStyle("-fx-font-size:30px; -fx-font-family: \"Arial\"; -fx-font-weight: bold");
		noDeal_btn.setStyle("-fx-font-size:30px; -fx-font-family: \"Arial\"; -fx-font-weight: bold");
		// Set the text for the 2 buttons
		deal_btn.setText("DEAL");
		noDeal_btn.setText("NO DEAL"); 
		deal_btn.prefHeight(200);
		deal_btn.prefWidth(200);
		// When the game begins
		// Make the buttons invisible
		if (casesRemaining == 10) {
			deal_btn.setVisible(false);
			noDeal_btn.setVisible(false);
		}
		// Otherwise set the buttons visible
		else {
			deal_btn.setVisible(true);
			noDeal_btn.setVisible(true);
		}

		//Setting the action for the deal button
		deal_btn.setOnAction((ActionEvent event) -> {
			deal(offerDisplay, grid, lbl);
		});
		//Setting the action for the no deal button
		noDeal_btn.setOnAction((ActionEvent event) -> {
			noDeal(offerDisplay, casesRemaining, deal_btn, grid);
		});
		noDeal_btn.prefHeight(200);
		noDeal_btn.prefWidth(200);
		// Add it to the hb2
		hb2.getChildren().add(deal_btn);
		hb2.getChildren().add(noDeal_btn);
		hb2.setSpacing(20);
		hb2.setPadding(new Insets(0, 0, 30, 350));
		// Add hb2 to vbox
		vbox.getChildren().add(hb2);
		// Add vbox to borderpane
		bp.setBottom(vbox);

	}

}
