package modules;


//To Read and Write In textfile
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//For java SQL and MySQL connection
import java.sql.*;
import com.mysql.cj.xdevapi.Statement;

import javafx.animation.AnimationTimer;
//For JavaFx tableview
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

@SuppressWarnings("unused")
public class GUI extends BorderPane {

	private static int invoiceNumber = readInvoiceFile();
	
	// Scenes sceneAdjust
	private static VBox loginScene;

	private final int HEIGHT = 640;
	private final int WIDTH = 640;
	
	private adoptOrDonate choose1Scene = new adoptOrDonate();;
	private adoptDog adoptDogScene = new adoptDog();;
	private donateDog donateDogScene = new donateDog();;
	private invoiceDisplay finishedTransaction = new invoiceDisplay();;
	private resultDisplay adoptList = new resultDisplay();;
	
	// Setting for main program window
	public GUI() {
		this.setMaxSize(WIDTH, HEIGHT);
		this.setMinSize(WIDTH, HEIGHT);
		this.getStyleClass().add("body");
		
		createLogin();
		
		//adoptDog addition constructor
		goToLoginScene(adoptDogScene.backToLoginButton);
		goToAdoptDogResultScene(adoptDogScene.goToListButton);
		
		//adoptOrDonate addition constructor
		goToDonateDogScene(choose1Scene.donateSceneButton);
		goToAdoptDogScene(choose1Scene.adoptSceneButton);
		goToLoginScene(choose1Scene.backToLoginButton);
		
		//donateDog addition constructor
		goToLoginScene(donateDogScene.backToLoginButton1);
		goToInvoiceScene(donateDogScene.goToInvoiceButton1, "donate", donateDogScene.dogBreedComboBox1.getSelectionModel().getSelectedItem(),
				donateDogScene.dogGenderComboBox1.getSelectionModel().getSelectedItem(),
				donateDogScene.dogSizeComboBox1.getSelectionModel().getSelectedItem(),
				donateDogScene.dogColorComboBox1.getSelectionModel().getSelectedItem(),
				donateDogScene.dogAgeComboBox1.getSelectionModel().getSelectedItem(), donateDogScene.ownerTextField1.getText().toString(),
				donateDogScene.emailTextField1.getText().toString(), donateDogScene.phoneTextField1.getText().toString());
		
		//invoiceDisplay addition constructor
		goToLoginScene(finishedTransaction.backToLoginButton);
		
		//resultDisplay addition constructor
		goToLoginScene(adoptList.backToLoginButton2);
		goToInvoiceScene(adoptList.goToInvoiceButton2,"adopt","","","","","","","", "");
		
		
		
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
		
Rectangle r = new Rectangle(5,5,10,10);
		
		AnimationTimer t = new AnimationTimer() {
			@Override
			public void handle(long now) 
				{
				r.setRotate(r.getRotate() + 5);
				r.setFill(Color.POWDERBLUE);
				}
			};//End AT
			t.start();

		Label username = new Label("\tUser Name: ");
		username.setStyle("labels");
		Label helpBox = new Label("\tForgot your Password? ");
		helpBox.setUnderline(true);
		Label signUp = new Label("\tNew User? Join Here! ");
		TextField inputName = new TextField();
		inputName.setPromptText("Username");
		ImageView lockIcon = new ImageView();
		ImageView guestIcon = new ImageView();
		ImageView helpIcon = new ImageView();
		
//		http://i65.tinypic.com/vr3ku0.png (Lock Symbol)
//		http://i68.tinypic.com/2wec87o.png (Question Mark)
//		http://i66.tinypic.com/jtqjwy.png (User?)
//		http://i66.tinypic.com/k0frdw.png (Paw)
		Image lock = new Image("http://i65.tinypic.com/vr3ku0.png",50.0,30.0,true,true);
		lockIcon.setImage(lock);
		
		Image help = new Image("http://i68.tinypic.com/2wec87o.png",30.0,30.0,true,true);
		helpIcon.setImage(help);
		
		Image guest = new Image("http://i66.tinypic.com/jtqjwy.png",50.0,30.0,true,true);
		guestIcon.setImage(guest);
		
		Label testingLabel = new Label("TEST");

		Label password = new Label("\tPassword: ");
		TextField inputPass = new TextField();
		inputPass.setPromptText("Password");

		gridPane.add(username, 0, 0);
		gridPane.add(guestIcon,0,0);
//		gridPane.add(r,8,8);
		gridPane.add(inputName, 1, 0);
		gridPane.add(password, 0, 1);
		gridPane.add(inputPass, 1, 1);
		gridPane.add(lockIcon, 0, 1);
		gridPane.add(helpBox, 1, 3);
		gridPane.add(helpIcon, 1	, 3);
		gridPane.add(signUp, 1, 4);
		gridPane.add(r, 1, 4);

		Button loginButton = new Button("Login");
		loginButton.setStyle("button");
		goTochoose1Scene(loginButton);

		loginScene.getChildren().addAll(gridPane, loginButton);

		this.setCenter(loginScene);
	}
	
	// Go to LoginScene
	public void goToLoginScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(loginScene);
			eraseBothForms();
		});
	}
	
	// Go to choose1Scene
	public void goTochoose1Scene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(choose1Scene.chooseScene);
		});
	}

	
	// Go to DonateDogScene
	public void goToDonateDogScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(donateDogScene.donateDogScene);
		});
	}
	
	// Go to InvoiceScene
	public void goToInvoiceScene(Button button, String choice,
			String breed, String gender, String size, String color, 
			String age, String ownerName, String email, String phone) 
	{
		if (choice == "donate") {
			finishedTransaction.donateResultTextField.setText("");
			button.setOnAction(e -> {
				if (donateDogScene.dogBreedComboBox1.getSelectionModel().getSelectedItem() == ""
						|| donateDogScene.dogGenderComboBox1.getSelectionModel().getSelectedItem() == ""
						|| donateDogScene.dogSizeComboBox1.getSelectionModel().getSelectedItem() == ""
						|| donateDogScene.dogColorComboBox1.getSelectionModel().getSelectedItem() == ""
						|| donateDogScene.dogAgeComboBox1.getSelectionModel().getSelectedItem() == ""
						|| donateDogScene.ownerTextField1.getText().toString() == "" 
						|| donateDogScene.emailTextField1.getText().toString() == ""
						|| donateDogScene.phoneTextField1.getText().toString() == "") {
					Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
					alert0.setTitle("Errors!!!");
					alert0.setHeaderText(null);
					alert0.setContentText("All input fields must be filled.");
					alert0.showAndWait();

				} else {
					String phoneString = donateDogScene.phoneTextField1.getText().toString();
					String invoice = "Invoice: " + "\t\t\t" + String.format("%010d%n", invoiceNumber) + "Dog Breed:" + "\t\t\t"
							+ donateDogScene.dogBreedComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Gender:" + "\t\t"
							+ donateDogScene.dogSizeComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Gender:" + "\t\t"
							+ donateDogScene.dogGenderComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Color:" + "\t\t\t"
							+ donateDogScene.dogColorComboBox1.getSelectionModel().getSelectedItem() + "\n" + "Dog Age:" + "\t\t\t"
							+ donateDogScene.dogAgeComboBox1.getSelectionModel().getSelectedItem() + " Years Old" + "\n"
							+ "Owner Name" + "\t\t" + donateDogScene.ownerTextField1.getText().toString() + "\n" + "Email:" + "\t\t\t"
							+ donateDogScene.emailTextField1.getText().toString() + "\n" + "Owner's Phone #:" + "\t"
							+ "(" + phoneString.substring(0,3) + ")-" + phoneString.substring(3,6) + "-" + phoneString.substring(6);

					boolean phonePass = isRealPhoneNum(donateDogScene.phoneTextField1.getText().toString());
					boolean emailPass = isValidEmail(donateDogScene.emailTextField1.getText().toString());
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
						finishedTransaction.donateResultTextField.setText(invoice);
						this.setCenter(finishedTransaction.donateInvoiceScene);
						insertIntoDataBase(donateDogScene.dogBreedComboBox1.getSelectionModel().getSelectedItem(),
								donateDogScene.dogGenderComboBox1.getSelectionModel().getSelectedItem(),
								donateDogScene.dogSizeComboBox1.getSelectionModel().getSelectedItem(),
								donateDogScene.dogColorComboBox1.getSelectionModel().getSelectedItem(),
								donateDogScene.dogAgeComboBox1.getSelectionModel().getSelectedItem(),
								donateDogScene.ownerTextField1.getText().toString(), 
								donateDogScene.emailTextField1.getText().toString(),
								donateDogScene.phoneTextField1.getText().toString());
						
						overWriteInvoice(invoiceNumber);
						invoiceNumber++;
					}
				}
			});
		}
		else if (choice == "adopt")
		{

			button.setOnAction(e -> {
				Dog k9 = (Dog) adoptList.adoptDogTableView.getSelectionModel().getSelectedItem();
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

				finishedTransaction.donateResultTextField.setText(invoice);
				
				deleteFromDataBase(k9.getBreed(), k9.getGender(), k9.getSize(), k9.getColor(), 
						k9.getAge(),k9.getOwnerName(),k9.getEmail(),k9.getPhone());
				
				this.setCenter(finishedTransaction.donateInvoiceScene);
				
				overWriteInvoice(invoiceNumber);
				invoiceNumber++;
			});
		}
	}
	
	// Go to AdoptDogScene
	public void goToAdoptDogScene(Button button) {
		button.setOnAction(e -> {
			this.setCenter(adoptDogScene.adoptDogScene);		
		});
	}

	//Go to AdoptDogResultScene
	public void goToAdoptDogResultScene(Button button) {
		button.setOnAction(e -> {
			selectFromDatabase(adoptDogScene.dogBreedComboBox2.getSelectionModel().getSelectedItem()
					, adoptDogScene.dogGenderCombobox2.getSelectionModel().getSelectedItem()
					, adoptDogScene.dogSizeComboBox2.getSelectionModel().getSelectedItem()
					, adoptDogScene.dogColorComboBox2.getSelectionModel().getSelectedItem()
					, adoptDogScene.dogAgeComboBox2.getSelectionModel().getSelectedItem() 
					);
			this.setCenter(adoptList.adoptDogResultScene);		
		});
	}
	
	//Erase adopt and donate form
	public void eraseBothForms() {
		donateDogScene.dogBreedComboBox1.getSelectionModel().selectFirst();
		donateDogScene.dogGenderComboBox1.getSelectionModel().selectFirst();
		donateDogScene.dogSizeComboBox1.getSelectionModel().selectFirst();
		donateDogScene.dogColorComboBox1.getSelectionModel().selectFirst();
		donateDogScene.dogAgeComboBox1.getSelectionModel().selectFirst();
		donateDogScene.ownerTextField1.setText("");
		donateDogScene.emailTextField1.setText("");
		donateDogScene.phoneTextField1.setText("");

		adoptDogScene.dogBreedComboBox2.getSelectionModel().selectFirst();
		adoptDogScene.dogGenderCombobox2.getSelectionModel().selectFirst();
		adoptDogScene.dogSizeComboBox2.getSelectionModel().selectFirst();
		adoptDogScene.dogColorComboBox2.getSelectionModel().selectFirst();
		adoptDogScene.dogAgeComboBox2.getSelectionModel().selectFirst();
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
	@SuppressWarnings("unused")
	public static void insertIntoDataBase(String breed, String gender, String size, String color, 
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
	@SuppressWarnings("unused")
	public void selectFromDatabase(String breed, String gender, String size, String color, 
			String age)
	{	
		adoptList.dogObservableList.clear();
		int numberOfFilters = 0;
		numberOfFilters = numberOfFilters();
		
		try
		{
			System.out.println("Connecting to jdbc.");
			System.out.println(numberOfFilters + "from select");
			System.out.println(adoptDogScene.dogAgeComboBox2.getSelectionModel().getSelectedItem());
			
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
				if (adoptDogScene.dogSizeComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " size = " + "\'" + size + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}

				}
				if (adoptDogScene.dogBreedComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " breed = " + "\'" + breed + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}
				}
				if (adoptDogScene.dogGenderCombobox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " gender = " + "\'" + gender + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}
				}
				if (adoptDogScene.dogColorComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
					command += " color = " + "\'" + color + "\'";
					numberOfFilters--;
					if (numberOfFilters <= 0) {
						command += ";";
					} else {
						command += " AND";
					}
				}
				if (adoptDogScene.dogAgeComboBox2.getSelectionModel().getSelectedIndex() != 0 && numberOfFilters !=0) {
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
						adoptList.dogObservableList.add(new Dog(rs.getString("breed"),rs.getString("gender"),rs.getString("size"),rs.getString("color"),
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
	@SuppressWarnings("unused")
	public void deleteFromDataBase(String breed, String gender, String size, String color, 
			String age, String ownerName, String email, String phone)
	{	
		Dog k9 = (Dog) adoptList.adoptDogTableView.getSelectionModel().getSelectedItem();
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
	public static HBox emptyHBoxPrinter() {
		HBox emptyBox = new HBox(8);
		emptyBox.setAlignment(Pos.BASELINE_CENTER);
		emptyBox.getChildren().addAll(new Label());
		emptyBox.setPrefHeight(100000);
		emptyBox.setVisible(false);
		return emptyBox;
	}

	// Return empty Vertical Box
	public static VBox emptyVBoxPrinter() {
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

		Label title = new Label();
		// title.getStyleClass().add("title");

		Label description = new Label();
		//INSERT PAW HERE
		BackgroundImage myBI = new BackgroundImage(new Image("http://i66.tinypic.com/k0frdw.png",
				 30, 30, false, true),
				 BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				 BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

				 loginVBox.setBackground(new Background(myBI));

		
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
	public static String[] readDogBreedsIntoArray(String filename)
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
		if(adoptDogScene.dogSizeComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("size");
			System.out.println(adoptDogScene.dogSizeComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(adoptDogScene.dogBreedComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("breed");
			System.out.println(adoptDogScene.dogBreedComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(adoptDogScene.dogGenderCombobox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("gender");
			System.out.println(adoptDogScene.dogGenderCombobox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(adoptDogScene.dogColorComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("color");
			System.out.println(adoptDogScene.dogColorComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		if(adoptDogScene.dogAgeComboBox2.getSelectionModel().isSelected(0) == false)
		{
			System.out.println("age");
			System.out.println(adoptDogScene.dogAgeComboBox2.getSelectionModel().getSelectedIndex());
			numOfFilters++;
		}
		System.out.println(numOfFilters + "from function");
		return numOfFilters;
	}
	
	public static boolean isRealPhoneNum(String num)
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
  
  public static void print(Node node) {
      System.out.println("Creating a printer job...");

      PrinterJob job = PrinterJob.createPrinterJob();
      if (job != null && job.showPrintDialog(node.getScene().getWindow())){
          boolean success = job.printPage(node);
          if (success) {
              job.endJob();
          }
      }
    }
  
	public static int readInvoiceFile() {
		int setInvoiceNumber = 0;

		FileReader fileReader = null;
		try {
			fileReader = new FileReader("src/Invoice.txt");
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
	
	public static void overWriteInvoice(int Invoice)
	{
		File fold=new File("src/Invoice.txt");
		fold.delete();
		File fnew=new File("src/Invoice.txt");
		String source = Integer.toString(invoiceNumber);
//		System.out.println(source);

		try {
		    FileWriter f2 = new FileWriter(fnew, false);
		    f2.write(source);
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}        
	}
	
}

