package test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.xdevapi.Statement;

//import calculations.Cheat;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
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
import javafx.util.Duration;
import javafx.scene.layout.BackgroundRepeat;
import javafx.animation.Transition;
import javafx.animation.FadeTransition;
import javafx.scene.shape.Rectangle;

public class TesterPage extends BorderPane {

//	private static final BackgroundRepeat REPEAT = null;

	private int invoiceNumber = 0; 
	
	// Scenes sceneAdjust
//	private VBox introScene;
	private VBox loginScene;
	private VBox chooseScene;
	private VBox donateDogScene;
	private VBox donateInvoiceScene;
	private VBox adoptDogScene;
	private final int HEIGHT = 800;
	private final int WIDTH = 800;
	
	//dogDonateSceneObjects
	Label dogBreedLabel = new Label("Dog Breed");
	ChoiceBox<String> dogBreedChoiceBox = new ChoiceBox<>();
	Label dogGenderLabel = new Label("Dog Gender");
	ChoiceBox<String> dogGenderChoiceBox = new ChoiceBox<>();
	Label dogSizeLabel = new Label("Dog Size");
	ChoiceBox<String> dogSizeChoiceBox = new ChoiceBox<>();
	Label dogColorLabel = new Label("Dog Color");
	ChoiceBox<String> dogColorChoiceBox = new ChoiceBox<>();
	Label dogAgeLabel = new Label("Dog Age");
	ChoiceBox<String> dogAgeChoiceBox = new ChoiceBox<>();
	Label ownerLabel = new Label("Owner Name");
	TextField ownerTextField = new TextField();
	Label emailLabel = new Label("E-mail");
	TextField emailTextField = new TextField();
	Label phoneLabel = new Label("Phone");
	TextField phoneTextField = new TextField();
	
	//dogDonateInvoiceObjects
	TextArea donateResultTextField = new TextArea();



	// Setting for main program window
	public TesterPage() {
		this.setMaxSize(WIDTH, HEIGHT);
		this.setMinSize(WIDTH, HEIGHT);
		this.getStyleClass().add("black");
//		createIntro();
		createLogin();
		createChooseScene();
		createDonateDogScene();
		createDonateInvoiceScene();
		adoptDogScene();
		//setScene1BackGround();
		setTitle();
	}
	
	//Introduction Page
//	public void createIntro() {
//		Rectangle rect = new Rectangle ();
//		rect.setWidth(WIDTH);
//		rect.setHeight(HEIGHT);
//		rect.setArcHeight(50);
//		rect.setArcWidth(50);
//		rect.setFill(Color.BLUE);
//		
//		FadeTransition ft = new FadeTransition(Duration.millis(3000), rect);
//		ft.setFromValue(1.0);
//		ft.setToValue(0.05);
//		ft.setCycleCount(40);
//		ft.setAutoReverse(true);
//		ft.play();
//		
//		introScene = new VBox();
//		introScene.setAlignment(Pos.CENTER);
//		
//		GridPane gridPane = new GridPane();
//		gridPane.setPadding(new Insets(20, 20, 20, 20));
//		gridPane.setHgap(5);
//		gridPane.setVgap(5);
//		gridPane.setAlignment(Pos.CENTER);
//		gridPane.add(rect, 0, 0);
//		
//		
//		Button welcome = new Button("Welcome");
//		goToIntroScene(welcome);
//		introScene.getChildren().addAll(gridPane,welcome);
//		this.setCenter(introScene);
//	}//End Intro

	// Create Scene 0 (Login Module)
	public void createLogin() {
		loginScene = new VBox();
		loginScene.getStyleClass().add("boxes");
		loginScene.setAlignment(Pos.CENTER);
		
		
		
		BackgroundImage myBI = new BackgroundImage(new Image("https://thumbs.gfycat.com/ElatedSparseCooter-size_restricted.gif",WIDTH, HEIGHT, false, true),BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		
		loginScene.setBackground(new Background(myBI));

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
	}//End create Login

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
	public void createDonateDogScene() {
		donateDogScene = new VBox();
		
		///1st Row
		//Breed
		dogBreedLabel.setPrefWidth(100);
		//dogBreedLabel.getStyleClass().add("labels");
		dogBreedChoiceBox.getItems().addAll("Affenpinscher","Afghan Hound","Atlas Mountain Dog","Airedale Terrier","Akita","Alaskan Malamute","Alpine Dachsbracke","American Akita","American Cocker Spaniel","American Foxhound","American Staffordshire Terrier","American Water Spaniel","Appenzeller Mountain Dog","Ariege Hound","Australian Cattle Dog","Australian Kelpie","Australian Shepherd","Australian Silky Terrier","Australian Stumpy Tail Cattle Dog","Australian Terrier","Azawakh","Barbet","Basenji","Artesian-Norman Basset","Blue Gascony Basset","Fawn Brittany Basset","Basset Hound","Bavarian Mountain Scent Hound","Beagle","Beagle-Harrier","Bearded Collie","Bedlington Terrier","Swiss White Shepherd Dog","Beauceron","Briard","Berger Picard","Bernese Mountain Dog","Bichon Frise","Havanese","Billy","Black-and-Tan Coonhound","Bolognese","Border Collie","Border Terrier","Bosnian Coarse-Haired Hound","Boston Terrier","French Bulldog","Bouvier des Ardennes","Bouvier des Flandres","Bracco Italiano","Austrian Black-and-Tan Hound","Braque de l'Ariege","Braque d'Auvergne","Braque du Bourbonnais","Braque Francais Gascony","Braque Francais Pyrenean","Barque Saint-Germain","Briquet Griffon Vendeen","Broholmer","Bull Terrier","Bulldog","Bullmastiff","Majorcan Shepherd Dog","Majorcan Mastiff","Cairn Terrier","Canaan Dog","Canadian Eskimo Dog","Cane Corso","Bergamasco Shepherd","Maremma Sheepdog","Poodle","Portuguese Water Dog","Portuguese Cattle Dog","Saint Miguel Cattle Dog","Portuguese Sheepdog","Estrela Mountain Dog","Cavalier King Charles Spaniel","Czechoslovakian Wolfdog","Bohemian Wirehaired Pointing Griffon","Cesky Terrier","Polish Greyhound","Chesapeake Bay Retriever","Artois Hound","Belgian Shepherd Dog");
		dogBreedChoiceBox.setPrefWidth(125);
		dogBreedChoiceBox.getSelectionModel().selectFirst();
		//Gender
		dogGenderLabel.setPrefWidth(100);
		dogGenderChoiceBox.getItems().addAll("","Male","Female");
		dogGenderChoiceBox.setPrefWidth(125);
		dogGenderChoiceBox.getSelectionModel().selectFirst();
		//Size
		dogSizeLabel.setPrefWidth(100);
		dogSizeChoiceBox.getItems().addAll("","Small","Medium","Large");
		dogSizeChoiceBox.setPrefWidth(125);
		dogSizeChoiceBox.getSelectionModel().selectFirst();
		//Color
		dogColorLabel.setPrefWidth(100);
		dogColorChoiceBox.getItems().addAll("","Brown","Black","White");	
		dogColorChoiceBox.setPrefWidth(125);
		dogColorChoiceBox.getSelectionModel().selectFirst();
		
		HBox dogBreedGenderSizeColorHBox = new HBox();
		dogBreedGenderSizeColorHBox.getChildren().addAll(dogBreedLabel, dogBreedChoiceBox, emptyVBoxPrinter(),  
				dogGenderLabel, dogGenderChoiceBox, emptyVBoxPrinter(), 
				dogSizeLabel, dogSizeChoiceBox, emptyVBoxPrinter(), 
				dogColorLabel, dogColorChoiceBox);
		dogBreedGenderSizeColorHBox.setAlignment(Pos.BASELINE_CENTER);
	
		/// 2nd Row
		//Age
		dogAgeLabel.setPrefWidth(100);
		dogAgeChoiceBox.getItems().add("");
		for (int i = 1; i < 21; i++)
		{
			dogAgeChoiceBox.getItems().add(Integer.toString(i));
		}
		dogAgeChoiceBox.getSelectionModel().select("");
		dogAgeChoiceBox.setPrefWidth(125);
		dogAgeChoiceBox.getSelectionModel().selectFirst();
		//OwnerName
		ownerLabel.setPrefWidth(100);
		ownerTextField.setPrefWidth(125);
		//Email
		emailLabel.setPrefWidth(100);
		emailTextField.setPrefWidth(125);
		//Phone
		phoneLabel.setPrefWidth(100);
		phoneTextField.setPrefWidth(125);
		HBox dogAgeOwnerEmailPhoneHBox = new HBox();
		dogAgeOwnerEmailPhoneHBox.getChildren().addAll(dogAgeLabel,dogAgeChoiceBox,emptyVBoxPrinter(), 
				ownerLabel,ownerTextField,emptyVBoxPrinter(), 
				emailLabel,emailTextField,emptyVBoxPrinter(), 
				phoneLabel,phoneTextField);
		dogAgeOwnerEmailPhoneHBox.setAlignment(Pos.BASELINE_CENTER);
		
		//CheckPoint
		System.out.println(dogAgeChoiceBox.getSelectionModel().getSelectedItem().toString()+dogBreedChoiceBox.getSelectionModel().getSelectedItem().toString()+
				dogGenderChoiceBox.getSelectionModel().getSelectedItem().toString()+dogColorChoiceBox.getSelectionModel().getSelectedItem().toString()+
				dogAgeChoiceBox.getSelectionModel().getSelectedItem().toString()+ownerTextField.getText().toString()+
				emailTextField.getText().toString()+phoneTextField.getText().toString()+"here");

		/// 3rd Row Buttons
		// Button and Label for going back to loginScene
		Label backToLoginLabel = new Label("Back To Login");
		Button backToLoginButton = new Button("Click Here!!");
		backToLoginButton.setStyle("button");
		goToLoginScene(backToLoginButton);
		// Button and Label for going to next Scene
		Label goToInvoiceLabel = new Label("Go to Invoice");
		Button goToInvoiceButton = new Button("Click Here!!");
		goToDonateInvoiceScene(goToInvoiceButton,
				dogBreedChoiceBox.getSelectionModel().getSelectedItem(),dogGenderChoiceBox.getSelectionModel().getSelectedItem(),
				dogSizeChoiceBox.getSelectionModel().getSelectedItem(),dogColorChoiceBox.getSelectionModel().getSelectedItem(),
				dogAgeChoiceBox.getSelectionModel().getSelectedItem(),ownerTextField.getText().toString(),
				emailTextField.getText().toString(),phoneTextField.getText().toString());
		HBox buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(emptyVBoxPrinter(),backToLoginLabel, backToLoginButton, 
				emptyVBoxPrinter(), goToInvoiceLabel, goToInvoiceButton, emptyVBoxPrinter());
		buttonsHBox.setAlignment(Pos.BASELINE_CENTER);

		
		
		// All that is displayed is here
		donateDogScene.getChildren().addAll(emptyHBoxPrinter(), dogBreedGenderSizeColorHBox, emptyHBoxPrinter(), dogAgeOwnerEmailPhoneHBox,
				emptyHBoxPrinter(), buttonsHBox, emptyHBoxPrinter());
		donateDogScene.setAlignment(Pos.CENTER);
		donateDogScene.setAlignment(Pos.TOP_CENTER);
		
	}

	// Create Scene 3 (Donate Dog Invoice)
	public void createDonateInvoiceScene() {
		donateInvoiceScene = new VBox();

		donateResultTextField.setEditable(false);
		donateResultTextField.setPrefHeight(550);
		Button backToLoginButton = new Button("Back To Login");
		backToLoginButton.setAlignment(Pos.CENTER);
		goToLoginScene(backToLoginButton);
		HBox buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(emptyVBoxPrinter(), backToLoginButton, emptyVBoxPrinter());
		buttonsHBox.setAlignment(Pos.CENTER);

		donateInvoiceScene.getChildren().addAll(donateResultTextField,buttonsHBox);
	}

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
	// Go to Introduction
//		public void goToIntroScene(Button button) {
//			button.setOnAction(e -> {
//				this.setCenter(introScene);
//			});
//		}
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
	
	// Go to DonateInvoiceScene
	private void goToDonateInvoiceScene(Button button,
			String breed, String gender, String size, String color, 
			String age, String ownerName, String email, String phone) 
	{
		button.setOnAction(e -> {
			if (dogBreedChoiceBox.getSelectionModel().getSelectedItem() == "" ||
					dogGenderChoiceBox.getSelectionModel().getSelectedItem() == "" ||
					dogSizeChoiceBox.getSelectionModel().getSelectedItem() == "" ||
					dogColorChoiceBox.getSelectionModel().getSelectedItem() == "" ||
					dogAgeChoiceBox.getSelectionModel().getSelectedItem() == "" ||
					ownerTextField.getText().toString() == "" ||
					emailTextField.getText().toString() == "" ||
					phoneTextField.getText().toString() == ""
					) {
				this.setCenter(donateInvoiceScene);
				// Erase bottom
				System.out.println(dogBreedChoiceBox.getSelectionModel().getSelectedItem()
						+ dogGenderChoiceBox.getSelectionModel().getSelectedItem()
						+ dogSizeChoiceBox.getSelectionModel().getSelectedItem()
						+ dogColorChoiceBox.getSelectionModel().getSelectedItem()
						+ dogAgeChoiceBox.getSelectionModel().getSelectedItem() 
						+ ownerTextField.getText().toString()
						+ emailTextField.getText().toString() 
						+ phoneTextField.getText().toString() + "1");
				//end of bottom
			} else {
				String invoice = "Invoice: " + "\t\t\t" + invoiceNumber + "\n" +
			"Dog Breed:" + "\t\t\t" + dogBreedChoiceBox.getSelectionModel().getSelectedItem() + "\n" +
			"Dog Gender:" + "\t\t" + dogGenderChoiceBox.getSelectionModel().getSelectedItem() + "\n" +
			"Dog Color:" + "\t\t\t" + dogColorChoiceBox.getSelectionModel().getSelectedItem() + "\n" +
			"Dog Age:" + "\t\t\t" + dogAgeChoiceBox.getSelectionModel().getSelectedItem() + " Years Old" + "\n" +
			"Owner Name" + "\t\t" + ownerTextField.getText().toString() + "\n" +
			"Email:" + "\t\t\t" + emailTextField.getText().toString() + "\n" +
			"Owner's Phone #:" + "\t" + phoneTextField.getText().toString();
				
 				donateResultTextField.setText(invoice);
				this.setCenter(donateInvoiceScene);
				System.out.println(dogBreedChoiceBox.getSelectionModel().getSelectedItem()
						+ dogGenderChoiceBox.getSelectionModel().getSelectedItem()
						+ dogSizeChoiceBox.getSelectionModel().getSelectedItem()
						+ dogColorChoiceBox.getSelectionModel().getSelectedItem()
						+ dogAgeChoiceBox.getSelectionModel().getSelectedItem() 
						+ ownerTextField.getText().toString()
						+ emailTextField.getText().toString() 
						+ phoneTextField.getText().toString() + "2");
				insertIntoDataBase(dogBreedChoiceBox.getSelectionModel().getSelectedItem()
						, dogGenderChoiceBox.getSelectionModel().getSelectedItem()
						, dogSizeChoiceBox.getSelectionModel().getSelectedItem()
						, dogColorChoiceBox.getSelectionModel().getSelectedItem()
						, dogAgeChoiceBox.getSelectionModel().getSelectedItem() 
						, ownerTextField.getText().toString()
						, emailTextField.getText().toString() 
						, phoneTextField.getText().toString()
						);
			}
		});
	}

	// Go to ListingsScene
	private void goToAdoptDogScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(donateDogScene);		
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
	
	public void insertIntoDataBase(String breed, String gender, String size, String color, 
			String age, String ownerName, String email, String phone)
	{	
		
		try
		{
			System.out.println("Connecting to jdbc.");
			
			//Needed to connect to mysql
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Congrats, connection to jdbc works.");
			//Connect to MySQL database ( url, username, password)
			Connection login = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root","root", "admin");
			
			java.sql.Statement stmt = null;
			ResultSet rs = null;

			try {

				    int a = login.createStatement().executeUpdate("use pawprint;", 1);
				    int b = login.createStatement().executeUpdate("insert into dog value"
				    		+ "(\"" + breed 
				    		+ "\", \"" + gender
				    		+ "\", \"" + size
				    		+ "\", \"" + color
				    		+ "\"," + Integer.parseInt(age)
				    		+ ", \"" + ownerName
				    		+ "\", \"" + email
				    		+ "\"," + Integer.parseInt(phone)
				    		+ ");\r\n", 1);
			}
			catch (SQLException ex){
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			finally {
			    // it is a good idea to release
			    // resources in a finally{} block
			    // in reverse-order of their creation
			    // if they are no-longer needed

			    if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException sqlEx) { } // ignore

			        rs = null;
			    }

			    if (stmt != null) {
			        try {
			            stmt.close();
			        } catch (SQLException sqlEx) { } // ignore

			        stmt = null;
			    }
			}
		}
		catch (SQLException | ClassNotFoundException e)
		{
			System.out.println("Failed to connect to database.");
			e.printStackTrace();
			return;
		}
	}
}