//To Read and Write In textfile
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// For java SQL and MySQL connection
import java.sql.*;
import com.mysql.cj.xdevapi.Statement;

// For JavaFx tableview
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

	private int invoiceNumber = readInvoiceFile();
	
	// Scenes sceneAdjust
	private VBox loginScene;
	private VBox chooseScene;
	private VBox donateDogScene;
	private VBox donateInvoiceScene;
	private VBox adoptDogScene;
	private VBox adoptDogResultScene;
	private final int HEIGHT = 640;
	private final int WIDTH = 640;
	
	//dogDonateSceneObjects
	Label dogBreedLabel1 = new Label("Dog Breed");
	ComboBox<String> dogBreedComboBox1 = new ComboBox<>();
	Label dogGenderLabel1 = new Label("Dog Gender");
	ComboBox<String> dogGenderComboBox1 = new ComboBox<>();
	Label dogSizeLabel1 = new Label("Dog Size");
	ComboBox<String> dogSizeComboBox1 = new ComboBox<>();
	Label dogColorLabel1 = new Label("Dog Color");
	ComboBox<String> dogColorComboBox1 = new ComboBox<>();
	Label dogAgeLabel1 = new Label("Dog Age");
	ComboBox<String> dogAgeComboBox1 = new ComboBox<>();
	Label ownerLabel1 = new Label("Owner Name");
	TextField ownerTextField1 = new TextField();
	Label emailLabel1 = new Label("E-mail");
	TextField emailTextField1 = new TextField();
	Label phoneLabel1 = new Label("Phone");
	TextField phoneTextField1 = new TextField();

	//dogInvoiceObjects
	TextArea donateResultTextField = new TextArea();
	
	//dogAdoptSceneObjects
	Label dogSizeLabel2 = new Label("Dog Size");
	ComboBox<String> dogSizeComboBox2 = new ComboBox<>();
	Label dogBreedLabel2 = new Label("Dog Breed");
	ComboBox<String> dogBreedComboBox2 = new ComboBox<>();
	Label dogGenderLabel2 = new Label("Dog Gender");
	ComboBox<String> dogGenderCombobox2 = new ComboBox<>();
	Label dogColorLabel2 = new Label("Dog Color");
	ComboBox<String> dogColorComboBox2 = new ComboBox<>();
	Label dogAgeLabel2 = new Label("Dog Age");
	ComboBox<String> dogAgeComboBox2 = new ComboBox<>();
	
	//dogAdoptResultObjects
	ComboBox<String> adoptDogListComboBox = new ComboBox<>();
	TableView adoptDogTableView = new TableView();
	TableColumn breedColumn = new TableColumn("Breed");
	TableColumn genderColumn = new TableColumn("Gender");
	TableColumn sizeColumn = new TableColumn("Size");
	TableColumn colorColumn = new TableColumn("Color");
	TableColumn ageColumn = new TableColumn("Age");
	TableColumn ownerNameColumn = new TableColumn("Owner");
	TableColumn emailColumn = new TableColumn("Email");
	TableColumn phoneColumn = new TableColumn("Phone");
	ObservableList<Dog> dogObservableList = FXCollections.observableArrayList();
	Label adoptResultLabel = new Label("Search Results");
	
	// Setting for main program window
	public GUI() {
		this.setMaxSize(WIDTH, HEIGHT);
		this.setMinSize(WIDTH, HEIGHT);
		this.getStyleClass().add("body");
		createLogin();
		createChooseScene();
		createDonateDogScene();
		createInvoiceScene();
		createAdoptDogScene();
		createAdoptDogResultScene();
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
	public void createDonateDogScene() {
		donateDogScene = new VBox();
		
		///1st Row
		//Breed
		dogBreedLabel1.setPrefWidth(100);
		dogBreedComboBox1.getItems().addAll("");
		String [] dogBreeds = readDogBreedsIntoArray("src\\DogBreeds.txt");
		for(String breed: dogBreeds)
		{
			dogBreedComboBox1.getItems().add(breed);
		}
		dogBreedComboBox1.setPrefWidth(125);
		dogBreedComboBox1.getSelectionModel().selectFirst();
		dogBreedComboBox1.setVisibleRowCount(5);
		//Gender
		dogGenderLabel1.setPrefWidth(100);
		dogGenderComboBox1.getItems().addAll("","Male","Female");
		dogGenderComboBox1.setPrefWidth(125);
		dogGenderComboBox1.getSelectionModel().selectFirst();
		dogGenderComboBox1.setVisibleRowCount(5);
		//Size
		dogSizeLabel1.setPrefWidth(100);
		dogSizeComboBox1.getItems().addAll("","Small","Medium","Large");
		dogSizeComboBox1.setPrefWidth(125);
		dogSizeComboBox1.getSelectionModel().selectFirst();
		dogSizeComboBox1.setVisibleRowCount(5);
		//Color
		dogColorLabel1.setPrefWidth(100);
		dogColorComboBox1.getItems().addAll("","Brown","Red","Gold","Yellow","Cream","Black","Blue","Gray","White");	
		dogColorComboBox1.setPrefWidth(125);
		dogColorComboBox1.getSelectionModel().selectFirst();
		dogColorComboBox1.setVisibleRowCount(5);
		
		HBox dogBreedGenderSizeColorHBox = new HBox();
		dogBreedGenderSizeColorHBox.getChildren().addAll(dogBreedLabel1, dogBreedComboBox1, emptyVBoxPrinter(),  
				dogGenderLabel1, dogGenderComboBox1, emptyVBoxPrinter(), 
				dogSizeLabel1, dogSizeComboBox1, emptyVBoxPrinter(), 
				dogColorLabel1, dogColorComboBox1);
		dogBreedGenderSizeColorHBox.setAlignment(Pos.BASELINE_CENTER);
	
		/// 2nd Row
		//Age
		dogAgeLabel1.setPrefWidth(100);
		dogAgeComboBox1.getItems().add("");
		for (int i = 1; i < 21; i++)
		{
			dogAgeComboBox1.getItems().add(Integer.toString(i));
		}
		dogAgeComboBox1.getSelectionModel().select("");
		dogAgeComboBox1.setPrefWidth(125);
		dogAgeComboBox1.getSelectionModel().selectFirst();
		dogAgeComboBox1.setVisibleRowCount(5);
		//OwnerName
		ownerLabel1.setPrefWidth(100);
		ownerTextField1.setPrefWidth(125);
		//Email
		emailLabel1.setPrefWidth(100);
		emailTextField1.setPrefWidth(125);
		//Phone
		phoneLabel1.setPrefWidth(100);
		phoneTextField1.setPrefWidth(125);
		HBox dogAgeOwnerEmailPhoneHBox = new HBox();
		dogAgeOwnerEmailPhoneHBox.getChildren().addAll(dogAgeLabel1,dogAgeComboBox1,emptyVBoxPrinter(), 
				ownerLabel1,ownerTextField1,emptyVBoxPrinter(), 
				emailLabel1,emailTextField1,emptyVBoxPrinter(), 
				phoneLabel1,phoneTextField1);
		dogAgeOwnerEmailPhoneHBox.setAlignment(Pos.BASELINE_CENTER);
		
		//CheckPoint
		System.out.println(dogAgeComboBox1.getSelectionModel().getSelectedItem().toString()+dogBreedComboBox1.getSelectionModel().getSelectedItem().toString()+
				dogGenderComboBox1.getSelectionModel().getSelectedItem().toString()+dogColorComboBox1.getSelectionModel().getSelectedItem().toString()+
				dogAgeComboBox1.getSelectionModel().getSelectedItem().toString()+ownerTextField1.getText().toString()+
				emailTextField1.getText().toString()+phoneTextField1.getText().toString()+"here");

		/// 3rd Row Buttons
		// Button and Label for going back to loginScene
		Label backToLoginLabel1 = new Label("Back To Login");
		Button backToLoginButton1 = new Button("Click Here!!");
		goToLoginScene(backToLoginButton1);
		// Button and Label for going to next Scene
		Label goToInvoiceLabel1 = new Label("Go to Invoice");
		Button goToInvoiceButton1 = new Button("Click Here!!");
		goToInvoiceScene(goToInvoiceButton1, "donate",
				dogBreedComboBox1.getSelectionModel().getSelectedItem(),dogGenderComboBox1.getSelectionModel().getSelectedItem(),
				dogSizeComboBox1.getSelectionModel().getSelectedItem(),dogColorComboBox1.getSelectionModel().getSelectedItem(),
				dogAgeComboBox1.getSelectionModel().getSelectedItem(),ownerTextField1.getText().toString(),
				emailTextField1.getText().toString(),phoneTextField1.getText().toString());
		HBox buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(emptyVBoxPrinter(),backToLoginLabel1, backToLoginButton1, 
				emptyVBoxPrinter(), goToInvoiceLabel1, goToInvoiceButton1, emptyVBoxPrinter());
		buttonsHBox.setAlignment(Pos.BASELINE_CENTER);

		
		
		// All that is displayed is here
		donateDogScene.getChildren().addAll(emptyHBoxPrinter(), dogBreedGenderSizeColorHBox, emptyHBoxPrinter(), dogAgeOwnerEmailPhoneHBox,
				emptyHBoxPrinter(), buttonsHBox, emptyHBoxPrinter());
		donateDogScene.setAlignment(Pos.CENTER);
		donateDogScene.setAlignment(Pos.TOP_CENTER);
		
	}

	// Create Scene 3 (Dog Invoice)
	public void createInvoiceScene() {
		donateInvoiceScene = new VBox();

		donateResultTextField.setEditable(false);
		donateResultTextField.setPrefHeight(550);
		donateResultTextField.setPrefRowCount(9);
		donateResultTextField.setPrefColumnCount(2);
		donateResultTextField.setPrefWidth(100);
		Button backToLoginButton = new Button("Back To Login");
		backToLoginButton.setAlignment(Pos.CENTER);
		goToLoginScene(backToLoginButton);
		Button printInvoiceButton = new Button("Print Invoice");
		printInvoiceButton.setAlignment(Pos.CENTER);
		printInvoiceButton.setOnAction(e -> print(donateResultTextField));
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(emptyVBoxPrinter(), backToLoginButton, emptyVBoxPrinter(), printInvoiceButton, emptyVBoxPrinter());
		buttonsHBox.setAlignment(Pos.CENTER);

		donateInvoiceScene.getChildren().addAll(donateResultTextField,buttonsHBox);
	}
	
	// Create Scene 4 (Adopt Dog Module)
	public void createAdoptDogScene() {
		adoptDogScene = new VBox();
		
		//Size
		dogSizeComboBox2.getItems().addAll("","Small","Medium","Large");
		dogSizeComboBox2.setVisibleRowCount(5);
		dogSizeComboBox2.getSelectionModel().select(0);
		
		//Breed
		dogBreedComboBox2.getItems().addAll("");
		String [] dogBreeds = readDogBreedsIntoArray("src\\DogBreeds.txt");
		for(String breed: dogBreeds)
		{
			dogBreedComboBox2.getItems().add(breed);
		}
		dogBreedComboBox2.setVisibleRowCount(5);
		dogBreedComboBox2.getSelectionModel().select(0);
		
		HBox dogSizeAndBreedHBox = new HBox();
		dogSizeAndBreedHBox.getChildren().addAll( dogSizeLabel2, dogSizeComboBox2, emptyVBoxPrinter(),
				dogBreedLabel2,  dogBreedComboBox2, emptyVBoxPrinter());
		dogSizeAndBreedHBox.setAlignment(Pos.BASELINE_CENTER);
		
		//Gender
		dogGenderCombobox2.getItems().addAll("","Male","Female");
		dogGenderCombobox2.setVisibleRowCount(5);
		dogGenderCombobox2.getSelectionModel().select(0);
		
		//Color
		dogColorComboBox2.getItems().addAll("","Brown","Red","Gold","Yellow","Cream","Black","Blue","Gray","White");
		dogColorComboBox2.setVisibleRowCount(5);
		dogColorComboBox2.getSelectionModel().select(0);
		
		//Age
		dogAgeComboBox2.getItems().add("");
		for (int i = 1; i < 21; i++)
		{
			dogAgeComboBox2.getItems().add(Integer.toString(i));
		}
		dogAgeComboBox2.getSelectionModel().select("");
		dogAgeComboBox2.setVisibleRowCount(5);
		dogAgeComboBox2.getSelectionModel().select(0);
		HBox dogGenderAndColorAndAgeHBox = new HBox();
		dogGenderAndColorAndAgeHBox.getChildren().addAll(dogGenderLabel2, dogGenderCombobox2, emptyVBoxPrinter(), 
				dogColorLabel2, dogColorComboBox2, emptyVBoxPrinter(),
				dogAgeLabel2, dogAgeComboBox2, emptyVBoxPrinter());
		dogGenderAndColorAndAgeHBox.setAlignment(Pos.BASELINE_CENTER);

		
		
		// Button and Label for going back to loginScene
		Label loginSceneLabel = new Label("Back To Login");
		Button backToLoginButton = new Button("Click Here!!");
		goToLoginScene(backToLoginButton);
		// Button and Label for going to next Scene
		Label goToListLabel = new Label("Go to List");
		Button goToListButton = new Button("Click Here!!");
		goToAdoptDogResultScene(goToListButton);
		HBox buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(loginSceneLabel, backToLoginButton, 
				emptyVBoxPrinter(), goToListLabel, goToListButton, emptyVBoxPrinter());
		buttonsHBox.setAlignment(Pos.BASELINE_CENTER);

		
		// All that is displayed is here
		adoptDogScene.getChildren().addAll(emptyHBoxPrinter(), dogSizeAndBreedHBox,
				emptyHBoxPrinter(), dogGenderAndColorAndAgeHBox,
				emptyHBoxPrinter(), buttonsHBox, emptyHBoxPrinter());
		adoptDogScene.setAlignment(Pos.CENTER);
		adoptDogScene.setAlignment(Pos.TOP_CENTER);
	}	

	// Create Scene 5 (Adopt a Dog Result Module)
	public void createAdoptDogResultScene()
	{


		adoptDogResultScene = new VBox();
		
		breedColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("breed"));
		genderColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("gender"));
		sizeColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("size"));
		colorColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("color"));
		ageColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("age"));
		ownerNameColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("ownerName"));
		emailColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("email"));
		phoneColumn.setCellValueFactory(
                new PropertyValueFactory<Dog, String>("phone"));
		
		Label backToLoginLabel2 = new Label("Back To Login");
		Button backToLoginButton2 = new Button("Click Here!!");
		goToLoginScene(backToLoginButton2);
		Label goToInvoiceLabel2 = new Label("Go to Invoice");
		Button goToInvoiceButton2 = new Button("Click Here!!");
		goToInvoiceScene(goToInvoiceButton2,"adopt","","","","","","","", "");
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(emptyVBoxPrinter(),backToLoginLabel2, backToLoginButton2, 
				emptyVBoxPrinter(), goToInvoiceLabel2, goToInvoiceButton2, emptyVBoxPrinter());
		buttonsHBox.setAlignment(Pos.BASELINE_CENTER);
		
		adoptDogTableView.setItems(dogObservableList);
		adoptDogTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		adoptDogTableView.getColumns().addAll(breedColumn,genderColumn,sizeColumn,colorColumn,ageColumn,ownerNameColumn,emailColumn,phoneColumn);
		adoptDogResultScene.setSpacing(5);
		adoptDogResultScene.setPadding(new Insets(10,0,0,10));
		adoptDogResultScene.getChildren().addAll(adoptResultLabel,adoptDogTableView,buttonsHBox);
	}

	// Go to LoginScene
	public void goToLoginScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(loginScene);
			eraseBothForms();
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
	
	// Go to InvoiceScene
	private void goToInvoiceScene(Button button, String choice,
			String breed, String gender, String size, String color, 
			String age, String ownerName, String email, String phone) 
	{
		if (choice == "donate") {
			donateResultTextField.setText("");
			button.setOnAction(e -> {
				if (dogBreedComboBox1.getSelectionModel().getSelectedItem() == ""
						|| dogGenderComboBox1.getSelectionModel().getSelectedItem() == ""
						|| dogSizeComboBox1.getSelectionModel().getSelectedItem() == ""
						|| dogColorComboBox1.getSelectionModel().getSelectedItem() == ""
						|| dogAgeComboBox1.getSelectionModel().getSelectedItem() == ""
						|| ownerTextField1.getText().toString() == "" 
						|| emailTextField1.getText().toString() == ""
						|| phoneTextField1.getText().toString() == "") {
					Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
					alert0.setTitle("Errors!!!");
					alert0.setHeaderText(null);
					alert0.setContentText("All input fields must be filled.");
					alert0.showAndWait();

				} else {
					String phoneString = phoneTextField1.getText().toString();
					String invoice = "Invoice: " + "\t\t\t" + String.format("%010d%n", invoiceNumber) + "Dog Breed:" + "\t\t\t"
							+ dogBreedComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Gender:" + "\t\t"
							+ dogSizeComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Gender:" + "\t\t"
							+ dogGenderComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Color:" + "\t\t\t"
							+ dogColorComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Age:" + "\t\t\t"
							+ dogAgeComboBox1.getSelectionModel().getSelectedItem() + " Years Old" + "\n"
							+ "Owner Name" + "\t\t" + ownerTextField1.getText().toString() + "\n" + "Email:" + "\t\t\t"
							+ emailTextField1.getText().toString() + "\n" + "Owner's Phone #:" + "\t"
							+ "(" + phoneString.substring(0,3) + ")-" + phoneString.substring(3,6) + "-" + phoneString.substring(6);

					boolean phonePass = isRealPhoneNum(phoneTextField1.getText().toString());
					boolean emailPass = isValidEmail(emailTextField1.getText().toString());
					if (phonePass == false)
					{
						Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
						alert1.setTitle("Errors!!!");
						alert1.setHeaderText(null);
						alert1.setContentText("Please enter a real phone number.\nPlease enter a 10 digit phone number");
						alert1.showAndWait();
					}
					if (emailPass == false)
					{
						Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
						alert2.setTitle("Errors!!!");
						alert2.setHeaderText(null);
						alert2.setContentText("Please enter a valid email address.");
						alert2.showAndWait();
					}

					if (emailPass == true && phonePass == true) {
						donateResultTextField.setText(invoice);
						this.setCenter(donateInvoiceScene);
						insertIntoDataBase(dogBreedComboBox1.getSelectionModel().getSelectedItem(),
								dogGenderComboBox1.getSelectionModel().getSelectedItem(),
								dogSizeComboBox1.getSelectionModel().getSelectedItem(),
								dogColorComboBox1.getSelectionModel().getSelectedItem(),
								dogAgeComboBox1.getSelectionModel().getSelectedItem(),
								ownerTextField1.getText().toString(), emailTextField1.getText().toString(),
								phoneTextField1.getText().toString());
						
						overWriteInvoice(invoiceNumber);
						invoiceNumber++;
					}
				}
			});
		}
		else if (choice == "adopt")
		{

			button.setOnAction(e -> {
				Dog k9 = (Dog) adoptDogTableView.getSelectionModel().getSelectedItem();
				String phoneString = k9.getPhone().toString();
				System.out.println(k9.getBreed());
				System.out.println(k9.getGender());
				System.out.println(k9.getSize());
				System.out.println(k9.getColor());
				System.out.println(k9.getAge());
				System.out.println(k9.getOwnerName());
				System.out.println(k9.getEmail());
				System.out.println(k9.getPhone());
				
				String invoice = "Invoice: " + "\t\t\t" + String.format("%010d%n", invoiceNumber) + "Dog Breed:" + "\t\t\t"
						+ k9.getBreed() + "\n" + "Dog Gender:" + "\t\t"
						+ k9.getGender() + "\n" + "Dog Size:" + "\t\t\t"
						+ k9.getSize() + "\n" + "Dog Color:" + "\t\t\t"
						+ k9.getColor() + "\n" + "Dog Age:" + "\t\t\t"
						+ k9.getAge() + " Years Old" + "\n" + "Owner Name" + "\t\t" 
						+ k9.getOwnerName() + "\n" + "Email:" + "\t\t\t"
						+ k9.getEmail() + "\n" + "Owner's Phone #:" + "\t"
						+ "(" + phoneString.substring(0,3) + ")-" + phoneString.substring(3,6) + "-" + phoneString.substring(6);

				donateResultTextField.setText(invoice);
				
				deleteFromDataBase(k9.getBreed(), k9.getGender(), k9.getSize(), k9.getColor(), 
						k9.getAge(),k9.getOwnerName(),k9.getEmail(),k9.getPhone());
				
				this.setCenter(donateInvoiceScene);
				
				overWriteInvoice(invoiceNumber);
				invoiceNumber++;
			});
		}
	}
	
	// Go to AdoptDogScene
	private void goToAdoptDogScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(adoptDogScene);		
		});
	}

	//Go to AdoptDogResultScene
	private void goToAdoptDogResultScene(Button button) {
		button.setOnAction(e -> {
			selectFromDatabase(dogBreedComboBox2.getSelectionModel().getSelectedItem()
					, dogGenderCombobox2.getSelectionModel().getSelectedItem()
					, dogSizeComboBox2.getSelectionModel().getSelectedItem()
					, dogColorComboBox2.getSelectionModel().getSelectedItem()
					, dogAgeComboBox2.getSelectionModel().getSelectedItem() 
					);
			this.setCenter(adoptDogResultScene);		
		});
	}
	
	//Erase adopt and donate form
	private void eraseBothForms() {
		dogBreedComboBox1.getSelectionModel().selectFirst();
		dogGenderComboBox1.getSelectionModel().selectFirst();
		dogSizeComboBox1.getSelectionModel().selectFirst();
		dogColorComboBox1.getSelectionModel().selectFirst();
		dogAgeComboBox1.getSelectionModel().selectFirst();
		ownerTextField1.setText("");
		emailTextField1.setText("");
		phoneTextField1.setText("");

		dogBreedComboBox2.getSelectionModel().selectFirst();
		dogGenderCombobox2.getSelectionModel().selectFirst();
		dogSizeComboBox2.getSelectionModel().selectFirst();
		dogColorComboBox2.getSelectionModel().selectFirst();
		dogAgeComboBox2.getSelectionModel().selectFirst();
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
	
	// Insert new Dog profile into database
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
				    		+ "\"," + Long.parseLong(phone)
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

	// Select statement on Database from Adopting Dog
	public void selectFromDatabase(String breed, String gender, String size, String color, 
			String age)
	{	
		dogObservableList.clear();
		int numberOfFilters = 0;
		numberOfFilters = numberOfFilters();
		
		try
		{
			System.out.println("Connecting to jdbc.");
			System.out.println(numberOfFilters + "from select");
			System.out.println(dogAgeComboBox2.getSelectionModel().getSelectedItem());
			
			//Needed to connect to mysql
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Congrats, connection to jdbc works.");
			//Connect to MySQL database ( url, username, password)
			Connection login = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root","root", "admin");
			
			java.sql.Statement stmt = null;
			ResultSet rs = null;
			String command = "select * from dog";
			if (numberOfFilters > 0)
			/*
			 * dogSizeComboBox2.getSelectionModel().getSelectedIndex() != 0 ||
			 * dogBreedComboBox2.getSelectionModel().getSelectedIndex() != 0 ||
			 * dogGenderCombobox2.getSelectionModel().getSelectedIndex() != 0 ||
			 * dogColorComboBox2.getSelectionModel().getSelectedIndex() != 0 ||
			 * dogAgeComboBox2.getSelectionModel().getSelectedIndex() != 0)
			 */
			{
				command += " where";
				if (dogSizeComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " size = " + "\'" + size + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}

				}
				if (dogBreedComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " breed = " + "\'" + breed + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}
				}
				if (dogGenderCombobox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " gender = " + "\'" + gender + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}
				}
				if (dogColorComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " color = " + "\'" + color + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}
				}
				if (dogAgeComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " age = " + "\'" + age + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}
				}
			}
			else
			{
				command += ";";
			}
			/*" breed = "  	+ "\'" 	+ breed 	+ "\'"  + " AND" +
			" gender = " 	+ "\'"  	+ gender	+ "\'" 	+ " AND" +
			" size = " 		+ "\'"  	+ size 		+ "\'" 	+ " AND" +
			 " color = " 	+ "\'"  	+ color	 	+ "\'" 	+ " AND" +
			" age = " 		+ Integer.parseInt(age) 	+ ";"
			;*/
			System.out.println(command);

			try {

				    int a = login.createStatement().executeUpdate("use pawprint;", 1);
				    stmt = login.createStatement();
				    rs = login.createStatement().executeQuery(command);


				    if (stmt.execute(command)) {
				        rs = stmt.getResultSet();
				    }

				    // Now do something with the ResultSet ....
					System.out.println();
					
					while (rs.next())
					{
						dogObservableList.add(new Dog(rs.getString("breed"),rs.getString("gender"),rs.getString("size"),rs.getString("color"),
								rs.getString("age"),rs.getString("ownerName"),rs.getString("email"),rs.getString("phone")));
						String result = rs.getString("breed") + " " + rs.getString("gender") + " " + rs.getString("size") + " " + rs.getString("color") + " " + 
								rs.getString("age") + " " + rs.getString("ownerName") + " " + rs.getString("email") + " " + rs.getString("phone");
						
						System.out.println(result);
					}
					rs.close();
					System.out.println();


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
	
	// Delete new Dog profile into database
	public void deleteFromDataBase(String breed, String gender, String size, String color, 
			String age, String ownerName, String email, String phone)
	{	
		Dog k9 = (Dog) adoptDogTableView.getSelectionModel().getSelectedItem();
		System.out.println(k9.getBreed());
		System.out.println(k9.getGender());
		System.out.println(k9.getSize());
		System.out.println(k9.getColor());
		System.out.println(k9.getAge());
		System.out.println(k9.getOwnerName());
		System.out.println(k9.getEmail());
		System.out.println(k9.getPhone());
		
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
				    int b = login.createStatement().executeUpdate("delete from dog where" + 
							 " breed = "  	+ "\'" 		+ k9.getBreed() 		+ "\'"  + " AND" +
							 " gender = " 	+ "\'"  	+ k9.getGender()		+ "\'" 	+ " AND" +
							 " size = " 	+ "\'"  	+ k9.getSize() 			+ "\'" 	+ " AND" +
							 " color = " 	+ "\'"  	+ k9.getColor()	 		+ "\'" 	+ " AND" +
							 " age = " 		+ Integer.parseInt(k9.getAge()) 	+ 		  " AND" +
							 " ownerName = "+ "\'"		+ k9.getOwnerName()		+ "\'"	+ " AND" +
							 " email = "	+ "\'"		+ k9.getEmail()			+ "\'"	+ " AND" +
							 " phone = " 	+			 k9.getPhone() 	+ 			
							";", 1);
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

	// Dog class
	public class Dog {
		public String breed;
		public String gender;
		public String size;
		public String color;
		public String age;
		public String ownerName;
		public String email;
		public String phone;
		
		public Dog
		(String breed, String gender, 
				String size, String color, 
				String i, String ownerName, 
				String email,String phone)
		{
			this.breed = breed;
			this.gender = gender;
			this.size = size;
			this.color = color;
			this.age = i;
			this.ownerName = ownerName;
			this.email = email;
			this.phone = phone;
		}
		
		public String getBreed() {
			return breed;
		}
		public void setBreed(String breed) {
			this.breed = breed;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getOwnerName() {
			return ownerName;
		}
		public void setOwnerName(String ownerName) {
			this.ownerName = ownerName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
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
		emptyBox.setPrefWidth(50);
		emptyBox.setVisible(false);
		return emptyBox;
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

	// Set Scene 1 Background picture
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
	
	// Read in list of dog breeds into array
	public String[] readDogBreedsIntoArray(String filename)
	    {
	        FileReader fileReader = null;
			try {
				fileReader = new FileReader(filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        List<String> lines = new ArrayList<String>();
	        String line = null;
	         
	        try {
				while ((line = bufferedReader.readLine()) != null) 
				{
				    lines.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        return lines.toArray(new String[lines.size()]);
	    } 

	// Get the number of filters given by user
	public int numberOfFilters()
	{
		int numOfFilters = 0;
		if(dogSizeComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("size");
			System.out.println(dogSizeComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(dogBreedComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("breed");
			System.out.println(dogBreedComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(dogGenderCombobox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("gender");
			System.out.println(dogGenderCombobox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(dogColorComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("color");
			System.out.println(dogColorComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(dogAgeComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("age");
			System.out.println(dogAgeComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		System.out.println(numOfFilters + "from function");
		return numOfFilters;
	}
	
	public boolean isRealPhoneNum(String num)
	{
		boolean numIsDigit = true;
		System.out.println(num);
	    System.out.println(Long.parseLong(num));

		try{
		    long number = Long.parseLong(num);
		    if ( Long.toString(number).length() == 10) {
		        System.out.println("Valid phone number!");
		    }
		    else {
		        System.out.println("Invalid Phone!");
		    }
		}
		catch (NumberFormatException ex) {
			System.out.println("Didn't work");
			numIsDigit = false;
			}
	    return numIsDigit;
	}
	
    public static boolean isValidEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    
    private void print(Node node) {
        System.out.println("Creating a printer job...");

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(node.getScene().getWindow())){
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
      }
    
	public int readInvoiceFile() {
		int setInvoiceNumber = 0;

		FileReader fileReader = null;
		try {
			fileReader = new FileReader("src\\Invoice.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;

		try {
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] i = lines.toArray(new String[lines.size()]);

		if (i.length == 1) {
			setInvoiceNumber = Integer.parseInt(i[0]);
			System.out.println(setInvoiceNumber);
		} else {
			System.out.println(88888888);
		}

		return setInvoiceNumber;
	}
	
	public void overWriteInvoice(int Invoice)
	{
		File fold=new File("src\\Invoice.txt");
		fold.delete();
		File fnew=new File("src\\Invoice.txt");
		String source = Integer.toString(invoiceNumber);
		System.out.println(source);

		try {
		    FileWriter f2 = new FileWriter(fnew, false);
		    f2.write(source);
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}        
	}
}
