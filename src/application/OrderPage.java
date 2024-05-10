package application;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;

public class OrderPage extends Application {
	Order myOrder=new Order();
    PaymentStrategy method;
    HBox itemBox;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Your Cart");
        
        //Calculating Total Price
        totalPrice();
        
        
        // Creating VBox to hold all cart items
        Label YourCart=new Label("CheckOut");
        YourCart.setStyle("-fx-font-weight: bold; -fx-font-size: 60px;");
        YourCart.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
        VBox itemsVBox = new VBox();
        itemsVBox.setAlignment(Pos.CENTER);
        itemsVBox.getChildren().add(YourCart);
        
        
        //User info
        Label name=new Label("Name: "+this.myOrder.getCustomer().getFullName());
        name.setStyle("-fx-font-size: 32px;");
        
        Label phoneNum=new Label("Phone Number: "+this.myOrder.getCustomer().getPhoneNumber());
        phoneNum.setStyle("-fx-font-size: 32px;");
        
        Label email=new Label("Email: "+this.myOrder.getCustomer().getEmail());
        email.setStyle("-fx-font-size: 32px;");
        
        Label address=new Label("Address: "+ this.myOrder.getCustomer().getAddress());
        address.setStyle("-fx-font-size: 32px;");
        
        Label numOfOrders=new Label("This is your: #"+ this.myOrder.getCustomer().getNumberOfOrder()+" Order");
        numOfOrders.setStyle("-fx-font-size: 32px;");
        
        VBox userInfo=new VBox(20);
        userInfo.setAlignment(Pos.CENTER);
        userInfo.setSpacing(5);
        userInfo.setPadding(new Insets(10));
        
        userInfo.getChildren().addAll(name,phoneNum,email,address,numOfOrders);
        
        // Adding cart items to VBox
        int productNumber=1;
        for (Product product : this.myOrder.getCart()) {
            Label productNameLabel = new Label((productNumber++)+"- "+product.getName());
            productNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;");
            Label productPriceLabel = new Label(String.valueOf(product.getPrice())+"EGP");
            productPriceLabel.setStyle("-fx-font-size: 32px;");

            HBox productBox = new HBox(productNameLabel, productPriceLabel);
            productBox.setAlignment(Pos.CENTER);
            productBox.setSpacing(5);
            productBox.setPadding(new Insets(10));
            
            itemsVBox.getChildren().add(productBox);
        }
        
        // Date label
        Label dateLabel = new Label("Date: " + this.myOrder.getDate());
        dateLabel.setStyle("-fx-font-size: 36px;");
        // Date of Arrival label
        Label dateofArrivalLabel = new Label("Date of Arrival: " + this.myOrder.getDate().plusWeeks(2));
        dateofArrivalLabel.setStyle("-fx-font-size: 36px;");
        
        //Shipping Label
        Label shippingLabel=new Label("Shipping Fees: "+myOrder.freeShipping());
        shippingLabel.setStyle("-fx-font-size: 36px;");
        
        // Total Price label
        Label totalPriceLabel = new Label("Total Price: " + this.myOrder.getTotalPrice()+"EGP");
        totalPriceLabel.setStyle("-fx-font-size: 36px;");
        
        // Order button
        Button orderButton = new Button("Place Order");
        orderButton.setStyle("-fx-font-size: 25px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
        orderButton.setOnAction(event -> {
 
        	
        	if (myOrder.available()){
        		//setting number of order of customer
        		this.myOrder.getCustomer().setNumberOfOrder(this.myOrder.getCustomer().getNumberOfOrder()+1);
                //saving order's info in shopSystem
        		ShopSystem.dataProcessing(myOrder);
        		
        		if(!(myOrder.canBeShipped())) {
        			Alert alert1=new Alert(Alert.AlertType.WARNING);
        				alert1.setTitle("opps");
        			     alert1.setContentText("Your order contains something that cannot be delivered. Please contact us for pickup");
        			     alert1.showAndWait();
        		}
        	     
        		Alert alert2=new Alert(Alert.AlertType.CONFIRMATION);
        		alert2.setTitle("Done");
        	     alert2.setContentText("Thank you for choosing us <3");
        	     alert2.showAndWait();
        		}
        	
        	
        	
        	
        primaryStage.close();
        //Open the main page
        //Send the same Customer data to main Page
        Main mainPage = new Main(this.myOrder.getCustomer());
       Stage mainStage = new Stage();
       mainPage.start(mainStage);
    	});

        // VBox to hold total price and date
        VBox infoVBox = new VBox(shippingLabel,totalPriceLabel,dateLabel,dateofArrivalLabel);
        infoVBox.setAlignment(Pos.CENTER);
        infoVBox.setSpacing(10);

        // Payment method ComboBox
        ComboBox<String> paymentComboBox = new ComboBox<>(FXCollections.observableArrayList("Cash", "Credit"));
        paymentComboBox.setPromptText("payment method");
        paymentComboBox.setPrefWidth(250);
        paymentComboBox.setPrefHeight(25);
        paymentComboBox.setOnAction(event -> {
            String method = paymentComboBox.getValue();
            
            if (method=="Credit") {
            
            	    CreditCard Credit= new CreditCard(myOrder.getCustomer(),myOrder);
            	    Stage Credit1 = new Stage();
            	    Credit.start(Credit1);
            	    }
            	    
           if (method=="Cash") {
           			CashStategy cash=new CashStategy(myOrder.getCustomer()) ;
           			cash.pay(myOrder.getTotalPrice());}
        });
        
        
        
        
        // VBox to hold infoVBox ,orderButton
        VBox v = new VBox(20);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(userInfo,infoVBox,paymentComboBox,orderButton);
        v.setPadding(new Insets(20));

        //HBox to hold all elements
        HBox root = new HBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(itemsVBox,v);
        root.setPadding(new Insets(20));
        
        
        // Set background color
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(backgroundFill));

        
        
        //scene and show the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Order");
        primaryStage.setMaximized(true);
        primaryStage.show();
        Alert();
    }






//Save data in order, to save this order later in SHOP SYSTEM
public OrderPage(List <Product> productsInCart,Customer customer,LocalDate date) {
	this.myOrder.setCart(productsInCart);  
	this.myOrder.setCustomer(customer);
	this.myOrder.setDate(date);
}


//Method to calculate total price of USER Order
void totalPrice() {
	double tPrice=0;
for(int i=0 ;i<this.myOrder.getCart().size();i++)
{
	tPrice+=this.myOrder.getCart().get(i).getPrice();
	
}
this.myOrder.setTotalPrice(tPrice);

}




//Shipping Alerts
public void Alert() {
    if(myOrder.freeShipping().equals("0EGP")) {
    	 Alert alert=new Alert(Alert.AlertType.INFORMATION);
	     alert.setTitle("Congratulations");
	     alert.setContentText("Your order exceeded 1000 pounds. You will have free shipping");
	     alert.showAndWait();
    }
    else {
		 Alert alert=new Alert(Alert.AlertType.INFORMATION);
	     alert.setTitle("Shipping");
	     alert.setContentText("Shipping Fees Added");
	     alert.showAndWait();
    }
    }




public static void main(String[] args) {
    launch(args);
}
}