package pp;

import java.util.ArrayList;
import java.util.List;

//import calculations.Cheat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class GUI extends BorderPane {

	private VBox loginScene;
	private VBox chooseScene;
	private VBox donateDogScene;
	private VBox adoptDogScene;
	//private VBox scene3;
	private final int HEIGHT = 750;
	private final int WIDTH = 750;

	// Setting for main program window
	public GUI() {
		this.setMaxSize(WIDTH, HEIGHT);
		this.setMinSize(WIDTH, HEIGHT);
		// this.getStyleClass().add("body");
		createLogin();
		createChooseScene();
		donateDogScene();
		adoptDogScene();
		//setScene1BackGround();
		setTitle();
	}

	// Create Scene 0 (Login Module)
	public void createLogin() {
		loginScene = new VBox();
		loginScene.getStyleClass().add("boxes");
		loginScene.setAlignment(Pos.CENTER);

		// Gridpane for textfields
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);

		Label username = new Label("User Name: ");
		TextField inputName = new TextField("********");

		Label password = new Label("Password: ");
		TextField inputPass = new TextField("********");

		gridPane.add(username, 0, 0);
		gridPane.add(inputName, 1, 0);
		gridPane.add(password, 0, 1);
		gridPane.add(inputPass, 1, 1);

		Button loginButton = new Button("Login");
		goToChooseScene(loginButton);

		loginScene.getChildren().addAll(gridPane, loginButton);

		this.setCenter(loginScene);
	}

	// Create Scene 1 (Donate or Adopt Module)
	public void createChooseScene() {
		chooseScene = new VBox();

		Button donateSceneButton = new Button("Donate a Dog");
		goToDonateDogScene(donateSceneButton);
		Button adoptSceneButton = new Button("Adopt a Dog");
		goToAdoptDogScene(adoptSceneButton);
		HBox donateOrAdoptHBox = new HBox();
		donateOrAdoptHBox.getChildren().addAll(emptyVBoxPrinter(), donateSceneButton, emptyVBoxPrinter(), adoptSceneButton, emptyVBoxPrinter());
		donateOrAdoptHBox.setAlignment(Pos.BASELINE_CENTER);

		// Button and Label for going back to loginScene
		Button backToLoginButton = new Button("Back to Login");
		goToLoginScene(backToLoginButton);

		HBox returnToLoginHBox = new HBox();
		returnToLoginHBox.getChildren().addAll(backToLoginButton);
		returnToLoginHBox.setAlignment(Pos.BASELINE_CENTER);

		// All that is displayed is here
		chooseScene.getChildren().addAll(emptyHBoxPrinter(),donateOrAdoptHBox, emptyHBoxPrinter(), returnToLoginHBox, emptyHBoxPrinter());
		chooseScene.setAlignment(Pos.CENTER);
		chooseScene.setAlignment(Pos.TOP_CENTER);
	}

	// Create Scene 2 (Donate a Dog Module) Need help to get this to display
		public void donateDogScene() {
			donateDogScene = new VBox();
			
			//Size
			Label dogSizeLabel = new Label("Dog Size");
			ComboBox<String> dogSizeComboBox = new ComboBox<>();
			dogSizeComboBox.getItems().addAll("","Small","Medium","Large");
			HBox dogSizeHBox = new HBox();
			dogSizeHBox.getChildren().addAll(emptyVBoxPrinter(), dogSizeLabel, emptyVBoxPrinter(), dogSizeComboBox, emptyVBoxPrinter());
			dogSizeHBox.setAlignment(Pos.BASELINE_CENTER);
			
			//Breed
			Label dogBreedLabel = new Label("Dog Size");
			ComboBox<String> dogBreedComboBox = new ComboBox<>();
			dogBreedComboBox.getItems().addAll("","A","B","C");
			HBox dogBreedHBox = new HBox();
			dogBreedHBox.getChildren().addAll(emptyVBoxPrinter(), dogBreedLabel, emptyVBoxPrinter(), dogBreedComboBox, emptyVBoxPrinter());
			dogBreedHBox.setAlignment(Pos.BASELINE_CENTER);
			
			//Gender
			Label dogGenderLabel = new Label("Dog Size");
			ComboBox<String> dogGenderCombobox = new ComboBox<>();
			dogGenderCombobox.getItems().addAll("","Male","Female");
			HBox dogGenderHBox = new HBox();
			dogGenderHBox.getChildren().addAll(emptyVBoxPrinter(), dogGenderLabel, emptyVBoxPrinter(), dogGenderCombobox, emptyVBoxPrinter());
			dogGenderHBox.setAlignment(Pos.BASELINE_CENTER);
			
			//Color
			Label dogColorLabel = new Label("Dog Color");
			ComboBox<String> dogColorComboBox = new ComboBox<>();
			dogColorComboBox.getItems().addAll("","Brown","Black","White");
			HBox dogColorHBox = new HBox();
			dogColorHBox.getChildren().addAll(emptyVBoxPrinter(), dogColorLabel, emptyVBoxPrinter(), dogColorComboBox, emptyVBoxPrinter());
			dogColorHBox.setAlignment(Pos.BASELINE_CENTER);
			
			//Age
			Label dogAgeLabel = new Label("Dog Size");
			ComboBox<String> dogAgeComboBox = new ComboBox<>();
			dogAgeComboBox.getItems().add("");
			for (int i = 1; i < 21; i++)
			{
				dogAgeComboBox.getItems().add(Integer.toString(i));
			}
			dogAgeComboBox.getSelectionModel().select("");
			HBox dogAgeHBox = new HBox();
			dogAgeHBox.getChildren().addAll(emptyVBoxPrinter(), dogAgeLabel, emptyVBoxPrinter(), dogAgeComboBox, emptyVBoxPrinter());
			dogAgeHBox.setAlignment(Pos.BASELINE_CENTER);

			
			
			// Button and Label for going back to loginScene
			Label loginSceneLabel = new Label("Back To Login");
			Button backToLoginButton = new Button("Click Here!!");
			goToLoginScene(backToLoginButton);
			// Button and Label for going to next Scene
			Label goToInvoiceLabel = new Label("Go to Invoice");
			Button goToInvoiceButton = new Button("Click Here!!");
			goToLoginScene(goToInvoiceButton);
			HBox buttonsHBox = new HBox();
			buttonsHBox.getChildren().addAll(emptyVBoxPrinter(),loginSceneLabel, emptyVBoxPrinter(), backToLoginButton, 
					emptyVBoxPrinter(), goToInvoiceLabel, emptyVBoxPrinter(), goToInvoiceButton, emptyVBoxPrinter());
			buttonsHBox.setAlignment(Pos.BASELINE_CENTER);

			
			// All that is displayed is here
			donateDogScene.getChildren().addAll(emptyHBoxPrinter(), dogSizeHBox, emptyHBoxPrinter(), dogBreedHBox,
					emptyHBoxPrinter(), dogGenderHBox, emptyHBoxPrinter(), dogColorHBox,  
					emptyHBoxPrinter(), dogAgeHBox, emptyHBoxPrinter(), emptyHBoxPrinter(), buttonsHBox, emptyHBoxPrinter());
			donateDogScene.setAlignment(Pos.CENTER);
			donateDogScene.setAlignment(Pos.TOP_CENTER);
			
		}

	// Create Scene 3 (Adopt a Dog Module)
	public void adoptDogScene() {
		adoptDogScene = new VBox();
		
		//Size
		Label dogSizeLabel = new Label("Dog Size");
		ComboBox<String> dogSizeComboBox = new ComboBox<>();
		dogSizeComboBox.getItems().addAll("","Small","Medium","Large");
		
		//Breed
		Label dogBreedLabel = new Label("Dog Breed");
		ComboBox<String> dogBreedComboBox = new ComboBox<>();
		dogBreedComboBox.getItems().addAll("","A","B","C");
		HBox dogSizeAndBreedHBox = new HBox();
		dogSizeAndBreedHBox.getChildren().addAll( dogSizeLabel, dogSizeComboBox, emptyVBoxPrinter(),
				dogBreedLabel,  dogBreedComboBox, emptyVBoxPrinter());
		dogSizeAndBreedHBox.setAlignment(Pos.BASELINE_CENTER);
		
		//Gender
		Label dogGenderLabel = new Label("Dog Gender");
		ComboBox<String> dogGenderCombobox = new ComboBox<>();
		dogGenderCombobox.getItems().addAll("","Male","Female");
		
		//Color
		Label dogColorLabel = new Label("Dog Color");
		ComboBox<String> dogColorComboBox = new ComboBox<>();
		dogColorComboBox.getItems().addAll("","Brown","Black","White");
		
		//Age
		Label dogAgeLabel = new Label("Dog Age");
		ComboBox<String> dogAgeComboBox = new ComboBox<>();
		dogAgeComboBox.getItems().add("");
		for (int i = 1; i < 21; i++)
		{
			dogAgeComboBox.getItems().add(Integer.toString(i));
		}
		dogAgeComboBox.getSelectionModel().select("");
		HBox dogGenderAndColorAndAgeHBox = new HBox();
		dogGenderAndColorAndAgeHBox.getChildren().addAll(dogGenderLabel, dogGenderCombobox, emptyVBoxPrinter(), 
				dogColorLabel, dogColorComboBox, emptyVBoxPrinter(),
				dogAgeLabel, dogAgeComboBox, emptyVBoxPrinter());
		dogGenderAndColorAndAgeHBox.setAlignment(Pos.BASELINE_CENTER);

		
		
		// Button and Label for going back to loginScene
		Label loginSceneLabel = new Label("Back To Login");
		Button backToLoginButton = new Button("Click Here!!");
		goToLoginScene(backToLoginButton);
		// Button and Label for going to next Scene
		Label goToListLabel = new Label("Go to List");
		Button goToListButton = new Button("Click Here!!");
		goToLoginScene(goToListButton);
		HBox buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(loginSceneLabel, backToLoginButton, 
				emptyVBoxPrinter(), goToListLabel, goToListButton, emptyVBoxPrinter());
		buttonsHBox.setAlignment(Pos.BASELINE_CENTER);

		
		// All that is displayed is here
		adoptDogScene.getChildren().addAll(emptyHBoxPrinter(), dogSizeAndBreedHBox,
				emptyHBoxPrinter(), dogGenderAndColorAndAgeHBox,
				emptyHBoxPrinter(), emptyHBoxPrinter(), buttonsHBox, emptyHBoxPrinter());
		adoptDogScene.setAlignment(Pos.CENTER);
		adoptDogScene.setAlignment(Pos.TOP_CENTER);
	}
	// Return invisible Image
	public ImageView invisibleImage() {
		Image image = new Image(
				"https://st.depositphotos.com/1026456/1245/v/450/depositphotos_12458533-stock-illustration-stick-figure.jpg");
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.setVisible(false);
		return imageView;
	}

	// Return empty Horizontal Box
	public HBox emptyHBoxPrinter() {
		HBox emptyBox = new HBox(8);
		emptyBox.setAlignment(Pos.BASELINE_CENTER);
		emptyBox.getChildren().addAll(new Label());
		emptyBox.setPrefHeight(100000);
		emptyBox.setVisible(false);
		return emptyBox;
	}

	// Return empty Vertical Box
	public VBox emptyVBoxPrinter() {
		VBox emptyBox = new VBox(8);
		emptyBox.setAlignment(Pos.BASELINE_CENTER);
		emptyBox.getChildren().addAll(new Label());
		emptyBox.setPrefWidth(100);
		emptyBox.setVisible(false);
		return emptyBox;
	}

	// Set title or top portion of window
	public void setTitle() {
		VBox loginVBox = new VBox();

		Label title = new Label("PawPrint");
		// title.getStyleClass().add("title");

		Label description = new Label("Make a dog happy!");

		loginVBox.getChildren().addAll(title, description);
		loginVBox.setAlignment(Pos.CENTER);

		// BackgroundImage myBI = new BackgroundImage(new Image("Scene1BackGround.jpg",
		// WIDTH, HEIGHT, false, true),
		// BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT,
		// BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

		// topBox.setBackground(new Background(myBI));

		this.setTop(loginVBox);
	}

	// Set Scene 1 background picture
	public void setScene1BackGround() {

		// URL for main fruit Image
		// https://hdwallsource.com/img/2014/5/fruit-background-20359-20869-hd-wallpapers.jpg
		// BackgroundImage myBI = new BackgroundImage(new Image("Scene1BackGround.jpg",
		// WIDTH, HEIGHT, false, true),
		// BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT,
		// BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

		// then you set to your node
		// scene1.setBackground(new Background(myBI));
	}

	// Go to LoginScene
	public void goToLoginScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(loginScene);
		});
	}
	
	// Go to ChooseScene
	private void goToChooseScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(chooseScene);
		});
	}

	// Go to DonateDogScene
	private void goToDonateDogScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(donateDogScene);
		});
	}

	// Go to ListingsScene
	private void goToAdoptDogScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(adoptDogScene);		
		});
	}

	// Reset HorizontalBox for new Image
	public HBox resetHBox(HBox h, Node[] n) {
		h.getChildren().clear();

		// Set Box
		h.getChildren().addAll(n);
		h.setPrefHeight(100);
		h.setAlignment(Pos.CENTER_LEFT);
		return h;

	}

	// Setup ImageView for new Image
	public ImageView imageViewSetup(Image a) {
		ImageView tempImageView = new ImageView(a);
		tempImageView.setFitHeight(100);
		tempImageView.setFitWidth(100);
		return tempImageView;
	}

	//Calculate dog ages into int array
	public int[] zeroToTwenty()
	{
		int [] numbers = null;
		return numbers;
	}
}