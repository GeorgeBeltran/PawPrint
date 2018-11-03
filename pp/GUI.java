


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

	private VBox login;
	private VBox scene1;
	private VBox scene2;
	private VBox scene3;
	private final int HEIGHT = 500;
	private final int WIDTH = 500;

	// ChoiceBox for "Body Type"
	ChoiceBox<String> choices = new ChoiceBox<>();

	// ChoiceBox for "Choose Your Age!"
	ComboBox<String> ageBox = new ComboBox<String>();

	// ChoiceBoxes for "Scene2"
	ChoiceBox<String> scene2ComboBox1 = new ChoiceBox<>();
	ChoiceBox<String> scene2ComboBox2 = new ChoiceBox<>();
	ChoiceBox<String> scene2ComboBox3 = new ChoiceBox<>();
	ChoiceBox<String> scene2ComboBox4 = new ChoiceBox<>();
	ChoiceBox<String> scene2ComboBox5 = new ChoiceBox<>();
	ChoiceBox<String> scene2ComboBox6 = new ChoiceBox<>();
	ChoiceBox<String> scene2ComboBox7 = new ChoiceBox<>();

	// ChoiceBox Array for "Scene2"
	ChoiceBox[] scene2ChoiceBoxes = { scene2ComboBox1, scene2ComboBox2, scene2ComboBox3, scene2ComboBox4,
			scene2ComboBox5, scene2ComboBox6, scene2ComboBox7 };

	// Setting for main program window
	public GUI() {
		this.setMaxSize(WIDTH, HEIGHT);
		this.setMinSize(WIDTH, HEIGHT);
		this.getStyleClass().add("body");
		createLogin();
		createScene1();
		setScene1BackGround();
		setTitle();
	}
 
	// Create Scene 0 (Login Module)
	public void createLogin() {
		login = new VBox();
		login.getStyleClass().add("boxes");
		login.setAlignment(Pos.CENTER);

		// Gridpane for textfields
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setAlignment(Pos.CENTER);

		Label username = new Label("User Name: ");
		TextField inputName = new TextField("John Doe");

		Label password = new Label("Password: ");
		TextField inputPass = new TextField("******");

		gridPane.add(username, 0, 0);
		gridPane.add(inputName, 1, 0);
		gridPane.add(password, 0, 1);
		gridPane.add(inputPass, 1, 1);

		Button b = new Button("Login");
		switchFromLogin(b);

		login.getChildren().addAll(gridPane, b);

		this.setCenter(login);

	}

	// Create Scene 1 (Goal Scene)
	public void createScene1() {
		scene1 = new VBox();
		scene1.getStyleClass().add("boxes");

		// Make horizontal box for each option
		HBox horizontalBox1 = new HBox(8);
		horizontalBox1.getStyleClass().add("boxes");
		HBox horizontalBox2 = new HBox(8);
		horizontalBox2.getStyleClass().add("boxes");
		HBox horizontalBox3 = new HBox(8);
		horizontalBox3.getStyleClass().add("boxes");

		// Label "Body Goal!"
		Label bodyGoal = new Label("Body Goal!");
		bodyGoal.getStyleClass().add("labels");

		// Fill "Body Type" ChoiceBox
		choices.getItems().addAll("Bulking", "Maintaining Weight", "Losing Weight");
		choices.getSelectionModel().select("Maintaining Weight");

		// Set action for "Body Type" ChoiceBox or change picture based on body type
		// choice
		HBox bodyTypePictureBox1 = new HBox(8);
		bodyTypePictureBox1.getChildren().clear();
		bodyTypePictureBox1.setPrefHeight(100);

		Group scene1Group1 = new Group();
		scene1Group1.getChildren().addAll(bodyTypePictureBox1);

		/*//Images for scene1
		Image image1 = new Image(
				"Image1.jpg");
		ImageView imageView1 = imageViewSetup(image1);

		Image image2 = new Image(
				"Image2.png");
		ImageView imageView2 = imageViewSetup(image2);

		Image image3 = new Image(
				"Image3.png");
		ImageView imageView3 = imageViewSetup(image3);*/
		
		Node[] array = { invisibleImage(), invisibleImage(), invisibleImage(), invisibleImage() };
		scene1Group1.getChildren().clear();
		resetHBox(bodyTypePictureBox1, array);
		scene1Group1.getChildren().addAll(bodyTypePictureBox1);

		// Picture changes for Body Goal
		choices.setOnAction((event) -> {
			if (choices.getSelectionModel().isSelected(0)) {
				// Show Bulking Body Picture
				Node[] arrayNode = { invisibleImage(), invisibleImage(), invisibleImage(), invisibleImage() };
				scene1Group1.getChildren().clear();
				resetHBox(bodyTypePictureBox1, arrayNode);
				scene1Group1.getChildren().addAll(bodyTypePictureBox1);
			}

			if (choices.getSelectionModel().isSelected(1)) {
				// Show Maintaining Weight Body Picture
				Node[] arrayNode = { invisibleImage(), invisibleImage(), invisibleImage(), invisibleImage() };
				scene1Group1.getChildren().clear();
				resetHBox(bodyTypePictureBox1, arrayNode);
				scene1Group1.getChildren().addAll(bodyTypePictureBox1);
			}

			if (choices.getSelectionModel().isSelected(2)) {
				// Show Losing Weight Picture
				Node[] arrayNode = { invisibleImage(), invisibleImage(), invisibleImage(), invisibleImage() };
				scene1Group1.getChildren().clear();
				resetHBox(bodyTypePictureBox1, arrayNode);
				scene1Group1.getChildren().addAll(bodyTypePictureBox1);
			}

		});

		// Button and Label for finish
		Label finish = new Label("Finished?");
		Button switch_button = new Button("Click Here!!");
		//switchToScene2(switch_button);

		// Put each label ComboBox into a HorizontalBox
		horizontalBox1.getChildren().addAll(bodyGoal, choices);
		horizontalBox1.setAlignment(Pos.BASELINE_CENTER);
		horizontalBox3.getChildren().addAll(finish, switch_button);
		horizontalBox3.setAlignment(Pos.BASELINE_CENTER);

		scene1.getChildren().addAll(emptyBoxPrinter(), horizontalBox1, emptyBoxPrinter(), scene1Group1,
				emptyBoxPrinter(), horizontalBox2, emptyBoxPrinter(), emptyBoxPrinter(), emptyBoxPrinter(),
				emptyBoxPrinter(), emptyBoxPrinter(), horizontalBox3, emptyBoxPrinter());
		scene1.setAlignment(Pos.CENTER);
		scene1.setAlignment(Pos.TOP_CENTER);
		// this.setCenter(scene1);

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
	public HBox emptyBoxPrinter() {
		HBox emptyBox = new HBox(8);
		emptyBox.setAlignment(Pos.BASELINE_CENTER);
		emptyBox.getChildren().addAll(new Label());
		emptyBox.setPrefHeight(100000000);
		emptyBox.setVisible(false);
		return emptyBox;
	}

	// Set title
	public void setTitle() {
		VBox topBox = new VBox();

		Label title = new Label("Hemp!");
		title.getStyleClass().add("title");

		Label description = new Label("Hungry. Eating. Meal. Planner.");

		topBox.getChildren().addAll(title, description);
		topBox.setAlignment(Pos.CENTER);

		BackgroundImage myBI = new BackgroundImage(new Image("Scene1BackGround.jpg", WIDTH, HEIGHT, false, true),
				BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

		topBox.setBackground(new Background(myBI));

		this.setTop(topBox);
	}

	// Set Scene 1 picture
	public void setScene1BackGround() {

		// URL for main fruit Image
		// https://hdwallsource.com/img/2014/5/fruit-background-20359-20869-hd-wallpapers.jpg
		//BackgroundImage myBI = new BackgroundImage(new Image("Scene1BackGround.jpg", WIDTH, HEIGHT, false, true),
		//		BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

		// then you set to your node
		//scene1.setBackground(new Background(myBI));
	}

	// Switch from Login
	private void switchFromLogin(Button button) {
		button.setOnAction(e -> {
			this.setCenter(scene1);
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
}