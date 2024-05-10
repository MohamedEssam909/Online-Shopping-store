package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreditCard extends Application {
	
	    private TextField NameField;
	    private TextField cardNumberField;
	    private TextField cvvField;
	    private TextField dateOfExpiryField;
	    Customer customer;
	    Order myOrder;

	public CreditCard(Customer customer ,Order myOrder) {
		this.customer=customer;
		this.myOrder=myOrder;
		}



	@Override
    public void start(Stage primaryStage) {
        
    	// Create sign up page components
        Label titleLabel = new Label("Credit Card");
        titleLabel.setStyle("-fx-font-size: 42px; -fx-font-weight: bold;");
        
        NameField = new TextField();
        NameField.setPromptText("Name:");
        NameField.setMaxWidth(500);
        NameField.setPrefHeight(45);
        NameField.setStyle("-fx-font-size: 18px;");
        
        cardNumberField = new TextField();
        cardNumberField.setPromptText("Card Number:");
        cardNumberField.setMaxWidth(500);
        cardNumberField.setPrefHeight(45);
        cardNumberField.setStyle("-fx-font-size: 18px;");
        
        cvvField = new TextField();
        cvvField.setPromptText("cvv:");
        cvvField.setMaxWidth(500);
        cvvField.setPrefHeight(45);
        cvvField.setStyle("-fx-font-size: 18px;");
        
        dateOfExpiryField = new TextField();
        dateOfExpiryField.setPromptText("Date Of Expiry:");
        dateOfExpiryField.setMaxWidth(500);
        dateOfExpiryField.setPrefHeight(45);
        dateOfExpiryField.setStyle("-fx-font-size: 18px;");
       

        // save Button            
            Button saveButton = new Button("save");
            saveButton.setOnAction(e -> creditInfo(primaryStage));
            saveButton.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
            saveButton.setMaxWidth(200);
            saveButton.setPrefHeight(50);
        

     
        // Create layout for credit page
        VBox CreditCard = new VBox(20);
        CreditCard.setPadding(new Insets(20));
        CreditCard.setAlignment(Pos.CENTER); // Center the components
        CreditCard.getChildren().addAll(titleLabel, NameField,cardNumberField,cvvField,dateOfExpiryField,saveButton);
        CreditCard.setMargin(saveButton, new Insets(20, 0, 0, 0)); // Add margin to the save button

        // Set background color
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        CreditCard.setBackground(new Background(backgroundFill));

        // Set up scene and show the stage
        Scene scene = new Scene(CreditCard);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Credit Card");

        primaryStage.setMaximized(true);
     // Show the stage
        primaryStage.show();
    }

 //save info in class Credit card 
    private void creditInfo(Stage primaryStage) {
        String name = NameField.getText();
        String cardNumber = cardNumberField.getText();
        String cvv = cvvField.getText();
        String dateOfExpiry = dateOfExpiryField.getText();
     
        //if user did not write any required data throw Exception
        
        try {
        	 if(!valid())throw new NullPointerException();
        	 if(!(cardNumber.matches("\\d+"))) throw new  IllegalArgumentException("1");
        	 if(!(cvv.matches("\\d+"))) throw new  IllegalArgumentException("2");
        	 if(!(dateOfExpiry.matches("\\d+"))) throw new  IllegalArgumentException("3");
     
        CreditCardStrategy x=new CreditCardStrategy(name,cardNumber,cvv,dateOfExpiry,this.customer);
        x.pay(myOrder.getTotalPrice());
        }
        catch(NullPointerException c){//make sure user white all data or try again
        	TextField[] arr= {NameField,cardNumberField,cvvField,dateOfExpiryField};
        	Handling.nullPointerException_handler(arr);
    		showErrorDialog("complete all fields");
        }
        
        catch(IllegalArgumentException c){
        	if ((c.getMessage()).equals("1"))
        	Handling.illegalArgumentException_handler(cardNumberField);
        	if ((c.getMessage()).equals("2"))
            Handling.illegalArgumentException_handler(cvvField);
        	if ((c.getMessage()).equals("3"))
            Handling.illegalArgumentException_handler(dateOfExpiryField);
            	
        	showErrorDialog("only numbers are allowed");
        }
        if(valid())
        	{showSignUP("save successfully");
        	order(primaryStage,name);}
    }


    private void order(Stage primaryStage, String username) {
        
        primaryStage.close();
       
    }

    private void showSignUP(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
//WARNING to write all data
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //check null object
    private boolean valid()
    {if (NameField.getText().isEmpty()||cardNumberField.getText().isEmpty()||cvvField.getText().isEmpty()||dateOfExpiryField.getText().isEmpty())
    	return false;
    
        return true;
    }

   
}
