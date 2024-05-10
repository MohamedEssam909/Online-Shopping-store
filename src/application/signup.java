package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class signup extends Application {
	
	    private TextField fullNameField;
	    private TextField userName;
	    private TextField emailField;
	    private TextField addressField;
	    private TextField phoneField;
	    private PasswordField passwordField;
	    Customer newCustomer;
	 
	    
	    
    @Override
    public void start(Stage primaryStage) {
        
    	// Create sign up page components
        Label titleLabel = new Label("sign Up");
        titleLabel.setStyle("-fx-font-size: 42px; -fx-font-weight: bold;");
        
        fullNameField = new TextField();
        fullNameField.setPromptText("Full Name:");
        fullNameField.setMaxWidth(500);
        fullNameField.setPrefHeight(45);
        fullNameField.setStyle("-fx-font-size: 18px;");
        
        userName = new TextField();
        userName.setPromptText("User Name:");
        userName.setMaxWidth(500);
        userName.setPrefHeight(45);
        userName.setStyle("-fx-font-size: 18px;");
        
        emailField = new TextField();
        emailField.setPromptText("email:");
        emailField.setMaxWidth(500);
        emailField.setPrefHeight(45);
        emailField.setStyle("-fx-font-size: 18px;");
        
        addressField = new TextField();
        addressField.setPromptText("Address:");
        addressField.setMaxWidth(500);
        addressField.setPrefHeight(45);
        addressField.setStyle("-fx-font-size: 18px;");
        
        phoneField = new TextField();
        phoneField.setPromptText("Phone Number:");
        phoneField.setMaxWidth(500);
        phoneField.setPrefHeight(45);
        phoneField.setStyle("-fx-font-size: 18px;");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(500);
        passwordField.setPrefHeight(45);
        passwordField.setStyle("-fx-font-size: 18px;");
       

        // sign Up Button            
            Button signUpButton = new Button("sign up");
            signUpButton.setOnAction(e -> attemptSignUp(primaryStage));
            signUpButton.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
            signUpButton.setMaxWidth(200);
            signUpButton.setPrefHeight(50);
        

     
        //VBox of all components
        VBox sign = new VBox(20);
        sign.setPadding(new Insets(20));
        sign.setAlignment(Pos.CENTER); // Center all components
        sign.getChildren().addAll(titleLabel, fullNameField,userName,emailField,addressField,phoneField, passwordField,signUpButton);
        VBox.setMargin(signUpButton, new Insets(20, 0, 0, 0)); // Add margin to the sign Up button

        
        //background color
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        sign.setBackground(new Background(backgroundFill));

        
    
        Scene scene = new Scene(sign);
        primaryStage.setScene(scene);
        primaryStage.setTitle("sign up");
        primaryStage.setMaximized(true);
     
        
        primaryStage.show();
    }

 //Method communicates with Shop System to save data
    private void attemptSignUp(Stage primaryStage) {
        String fullName = fullNameField.getText();
        String UserName = userName.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        
      //if user did not write any required data throw Exception
        
        try {
        	 if(!valid())throw new NullPointerException();
        	 if(!(phone.matches("\\d+"))) throw new  IllegalArgumentException("2");
       //A new customer so save his info
        this.newCustomer=new Customer(fullName,email,address,phone);
        this.newCustomer.setUserName(UserName);
        this.newCustomer.setPassword(password);
        
        //Saving his data
        ShopSystem.customers.add(this.newCustomer);
        
        }
        catch(NullPointerException c){
        	TextField[] arr= {fullNameField,userName,emailField,addressField,phoneField,passwordField};
        	Handling.nullPointerException_handler(arr);
        	showErrorDialog("complete all fields");
        }
        catch(IllegalArgumentException c){
        	Handling.illegalArgumentException_handler(phoneField);
        	showErrorDialog("only numbers are allowed");
        }
        if(valid())
        	{showSignUP("Registered successfully, Welcome");
        	
        	//Sending the new customer info to Main Page to use his Data
            returnToMain(primaryStage,this.newCustomer);}
    }


    
    private void returnToMain(Stage primaryStage,Customer customer) {
        // Close the Sign Up page
        primaryStage.close();
        // Open the main page and Send the new customer info to Main Page to use his Data
        Main mainPage = new Main(customer);
        Stage mainStage = new Stage();
        mainPage.start(mainStage);
    }

    
    //Confirmation Alert method
    private void showSignUP(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
  //Error Alert method
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean valid()
    {if (fullNameField.getText().isEmpty()||emailField.getText().isEmpty()||addressField.getText().isEmpty()||phoneField.getText().isEmpty()||passwordField.getText().isEmpty())
    	return false;
    
        return true;
    }
    
    
   public static void main(String[] args) {
        launch(args);
   }
}