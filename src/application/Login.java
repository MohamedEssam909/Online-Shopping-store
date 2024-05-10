package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/*
 * Start Point of project
 * 
 * */
public class Login extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label errorLabel;
    Customer oldCustomer;

    @Override
    public void start(Stage primaryStage) { 	
    	
    	// Create login page components
        Label titleLabel = new Label("Welcome");
        titleLabel.setStyle("-fx-font-size: 42px; -fx-font-weight: bold;");
        
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(500);
        usernameField.setPrefHeight(45);
        usernameField.setStyle("-fx-font-size: 18px;");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(500);
        passwordField.setPrefHeight(45);
        passwordField.setStyle("-fx-font-size: 18px;");
        
        
        /*CheckBox rememberMeCheckbox = new CheckBox("Remember Me");
        rememberMeCheckbox.setStyle("-fx-font-size: 18px;");
        rememberMeCheckbox.setOnAction(e ->{ 
        	if (rememberMeCheckbox.isSelected()) {
        	this.usernameField.setText(usernameField.getText());
            this.passwordField.setText(usernameField.getText());
        }});*/
        
        
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> attemptLogin(primaryStage));
        loginButton.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        loginButton.setMaxWidth(200);
        loginButton.setPrefHeight(50);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");
        errorLabel.setAlignment(Pos.CENTER);

        // Create layout for login page
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); // Center the components
        root.getChildren().addAll(titleLabel, usernameField, passwordField,loginButton, errorLabel);
        VBox.setMargin(loginButton, new Insets(20, 0, 0, 0)); // Add margin to the login button

        
        //Sign up
        
        Label signUp=new Label("Don't have an account?");
        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setOnAction(e -> openSignUpForm(primaryStage));
        signUpLink.setStyle("-fx-font-size: 18px;");
        root.getChildren().addAll(signUp,signUpLink);
        
        
        //background color
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(backgroundFill));

        // scene and showing the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        
        //to appear with maximum size
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    private void attemptLogin(Stage primaryStage) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
      //if user did not write any required data throw Exception
        
        try {
        //Checking
        if(!valid())throw new NullPointerException();
        if (isValidLogin(username, password)) {
            openMainPage(primaryStage,this.oldCustomer);
            
            
        } else {
            showErrorDialog("Invalid username or password.");
        }}
        catch(NullPointerException c) {
        	TextField[] arr= {usernameField,passwordField};
        	Handling.nullPointerException_handler(arr);
        	showErrorDialog("complete all fields");
        }
    }

    
    //function communicates with shop system
    private boolean isValidLogin(String username, String password) {
    	
    	//Already defined userName and Passwords of OWNERS
    	Customer mohamedOwner=new Customer("Mohamed Essam","me@gmail.com","67kamelsedky","01117375718");
    	Customer mennaOwner=new Customer("Menna Hussien","mh@gmail.com","kkk","0124240422");
    	Customer access=new Customer("Mdssdd","mh@dsdsm","kkk","012dssd4240422");
    	
    	mohamedOwner.setUserName("mohamed909");
    	mennaOwner.setUserName("menna13");
    	access.setUserName("1");
    	
    	mohamedOwner.setPassword("password");
    	mennaOwner.setPassword("password13");
    	access.setPassword("1");
    	
    	
    	//ADDING CUSTOMERS DATA TO SHOP SYSTEM
    	//THE IDEA IS SIMILAR TO DATABASE
    	ShopSystem.customers.add(mohamedOwner);
    	ShopSystem.customers.add(mennaOwner);
    	ShopSystem.customers.add(access);
    	
    	// Perform validation,Checking Correct User Name and Password
       boolean usernameFlag=false;
       boolean passwordFlag=false;
       int index=0;
       
       //Checking if user name Already Exists
    	for (int i=0;i<ShopSystem.customers.size();i++) {
    		if(username.equals(ShopSystem.customers.get(i).getuserName())) {
    			usernameFlag=true;
    			
    			//if it exists, it is an old customer 
    			this.oldCustomer=ShopSystem.customers.get(i);
    			
    			//saving index of this customer, to get his/her password
    			index=i;
    			break;
    		}	
    	}
    	
    	//checking his/her password with the same index
    	if(password.equals(ShopSystem.customers.get(index).getPassword())) {
    		passwordFlag=true;
    	}
    	
    	
    	//Checking both flags together
    	if((usernameFlag&&passwordFlag)==true) {
    		return true;
    	}
    	else
    		return false;

    }
    

    
    
    //Opening main store page
    private void openMainPage(Stage primaryStage, Customer customer) {
        
    	// Close the login page
        primaryStage.close();
        
        
        // Open the main page
        //Sending customer to it, to use his info
        Main mainPage = new Main(customer);
        Stage mainStage = new Stage();
        
        //setting his wishList in main page(if he has a previous one it will be shown)
        mainPage.productsInWishlist=customer.getWishList();
        
        
        mainPage.start(mainStage);
    }

    
    //Method to show alerts
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    //Open sign Up Page 
    private void openSignUpForm(Stage primaryStage) {
    	primaryStage.close();
    	signup s=new signup();
        Stage signUpStage=new Stage();
        s.start(signUpStage);
    }
    
  //check null object
    private boolean valid()
    {if (usernameField.getText().isEmpty()||passwordField.getText().isEmpty())
    	return false;
        return true;
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
