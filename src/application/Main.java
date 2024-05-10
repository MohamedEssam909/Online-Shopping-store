package application;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





public class Main extends Application {
	private Customer customer=new Customer();
    private List<Product> productsInCart = new ArrayList<>();
    public List<Product> productsInWishlist =customer.getWishList();
    private VBox cartItemsVBox;
    private VBox wishlistItemsVBox;
    static List<Product> inStock;
    private List<Product> searchedProducts;
    private List<Product> categorizedProducts;
    Stage mainStage;
    private Circle[] ratingCircles=new Circle[5];
    private int maxRating = 5;
    private int currentRating = 0;
    private int finalRating;
 

    @Override
    public void start(Stage primaryStage) {
    	//Title
        primaryStage.setTitle("Online Shopping Store");
        //Main Stage
         this.mainStage=primaryStage;
         
        // Shop products data with image file paths
         Product[] available= {
        		 
        		new Shirt("shirt",200,"S","red","cotton","img\\shirt.jpg",Category.Clothing),
 				new Shirt("shirt",250,"M","red","cotton","img\\shirt2.jpg",Category.Clothing),
 				new Shirt("shirt",300,"L","red","cotton","img\\shirt3.jpg",Category.Clothing),
 					
 				new Jeans("jeans",300,"S","Blue","cotton","img\\jeans.jpg",Category.Clothing),
 				new Jeans("jeans",350,"M","Blue","cotton","img\\jeans1.jpg",Category.Clothing),
 				new Jeans("jeans",400,"L","Blue","cotton","img\\jeans2.jpg",Category.Clothing),
 					
 				new Jeans("jeans",300,"M","Blue","cotton","img\\p1.jpg",Category.Clothing),
 				new Jeans("jeans",350,"M","Blue","cotton","img\\p2.jpg",Category.Clothing),
 				new Jeans("jeans",400,"M","Blue","cotton","img\\p3.jpg",Category.Clothing),
 					
 				new Tshirt("T-shirt",100,"S","Yellow","cotton","img\\tshirt.jpg",Category.Clothing),
 				new Tshirt("T-shirt",150,"M","Yellow","cotton","img\\tshirt1.jpg",Category.Clothing),
 				new Tshirt("T-shirt",170,"L","Yellow","cotton","img\\tshirt2.jpg",Category.Clothing),
 				
 				new Tshirt("T-shirt",100,"M","Black","cotton","img\\t4.jpg",Category.Clothing),
 				new Tshirt("T-shirt",150,"M","Black","cotton","img\\t5.jpg",Category.Clothing),
 				new Tshirt("T-shirt",170,"M","Black","cotton","img\\t6.jpg",Category.Clothing),
 					
 				new Short("Short",50,"S","Black","cotton","img\\short.jpg",Category.Clothing),
 				new Short("Short",70,"M","Black","cotton","img\\short2.jpg",Category.Clothing),
 				new Short("Short",100,"L","Black","cotton","img\\short3.jpg",Category.Clothing),
        		 
 				
 				new TBook("as good as dead", 125,"T-Book","HOLLY JACKSON","img\\agad.jpg",Category.Book),
				new TBook("the mountain is you", 225,"T-Book","brianna wiest","img\\tmiu.jpg",Category.Book),
				new TBook("this is how you heal", 120,"T-Book","brianna wiest","img\\tihuh.jpg",Category.Book),
				new TBook("the comfort book", 150,"T-Book","matt haig","img\\tcb.jpg",Category.Book),
				
				new TBook("The subtle art of not giving a f", 600,"T-Book","HOLLY JACKSON","img\\bbb1.jpg",Category.Book),
				new TBook("The psychology of money", 360,"T-Book","brianna wiest","img\\bbb2.jpg",Category.Book),
				new TBook("Rich dad poor dad", 450,"T-Book","brianna wiest","img\\bbb3.jpg",Category.Book),
				new TBook("The island of missing trees", 190,"T-Book","matt haig","img\\bbb4.jpg",Category.Book),
				
				new EBook("stop overthinking", 143,"E-Book","nick trenton","img\\so.jpg",Category.Book),
				new EBook("atomic habits", 125,"E-Book","james clear","img\\ah.jpg",Category.Book),
				new EBook("harry potter set", 630,"E-Book","J K Rowling","img\\hps.jpg",Category.Book),
				
				
				
				new VanGogh("STARRY NIGHT",1000,"32*32","img\\sn.jpg",Category.Paintings),
				new VanGogh("CAFÉ TERRACE AT NIGHT",700,"32*32","img\\ctan.jpg",Category.Paintings),
				new VanGogh("THE OLD TOWER IN THE FIELDS",750,"32*32","img\\totitf.jpg",Category.Paintings),
				new VanGogh("FARMHOUSE IN PROVENCE",600,"32*32","img\\fip.jpg",Category.Paintings),
				new VanGogh("THE PAINTER OF SUNFLOWERS",400,"32*32","img\\tpotsf.jpg",Category.Paintings),
				
				
				new Nature("Water Lilies and Japanese Bridge",730,"32*32","img\\pai1.jpg",Category.Paintings),
				new Nature("Le Jardin de l'artiste à Giverny",200,"32*32","img\\pai2.jpg",Category.Paintings),
				new Nature("Tiger in a Tropical Storm",350,"32*32","img\\pai3.jpg",Category.Paintings),
				new Nature("Beauty of Nature",500,"32*32","img\\pai4.jpg",Category.Paintings),
				new Nature("The Hay Wain",700,"32*32","img\\pai5.jpg",Category.Paintings),
				
				new LandScape("Collection 1",300,"32*32","img\\ln1.jpg",Category.Paintings),
				new LandScape("Collection 2",300,"32*32","img\\ln2.jpg",Category.Paintings),
				new LandScape("Collection 3",300,"32*32","img\\ln3.jpg",Category.Paintings),
				new LandScape("Collection 4",300,"32*32","img\\ln4.jpg",Category.Paintings),
				new LandScape("Collection 5",300,"32*32","img\\ln5.jpg",Category.Paintings),
 				
 		
        		new BM("BM",2012,250000,3000,280000,"img\\\\BM1.jpg",Category.Cars),
				new BM("BM",2017,300000,4200,320000,"img\\\\BM2.jpg",Category.Cars),
				new BM("BM",2022,900000,9000,940000,"img\\\\BM3.jpg",Category.Cars),
				new BM("BM",2024,2000000,20000,2300000,"img\\\\BM4.jpg",Category.Cars),
				new BM("BM",2012,250000,3000,280000,"img\\\\BM5.jpg",Category.Cars),
					
				new Mercedes("Mercedes",2008,500000,4000,520000,"img\\\\MER1.jpg",Category.Cars),
				new Mercedes("Mercedes",2019,700000,9000,710000,"img\\\\MER2.jpg",Category.Cars),
				new Mercedes("Mercedes",2022,1000000,30000,1030000,"img\\\\MER3.jpg",Category.Cars),
					
				new Toyota("Toyota",2004,300000,4200,310000,"img\\\\tot1.jpg",Category.Cars),
				new Toyota("Toyota",2013,650000,8000,660000,"img\\\\tot2.jpg",Category.Cars),
				new Toyota("Toyota",2021,730000,10300,735000,"img\\tot3.jpg",Category.Cars),
					
				new Audi("Audi",2023,2500000,9600,2550000,"img\\audi1.jpg",Category.Cars),
				new Audi("Audi",2018,600000,3100,610000,"img\\audi2.jpg",Category.Cars),
				
				
					
				new TV("TV", 17149,"Samsung","4k UHD smart TV",2,"img\\tv.jpg",Category.Electronics),
				new TV("TV", 21047,"LG","4k UHD TV",4,"img\\tv1.jpg",Category.Electronics),
				new TV("TV", 99999,"TOSHIBA","HD TV",3,"img\\tv2.jpg",Category.Electronics),
				new TV("TV", 7099,"SONY","HD smart LED TV",2,"img\\tv3.jpg",Category.Electronics),
					
				new Mobile("Mobile", 669999,"Apple","iPhone 15 pro max",2,"img\\m1.jpg",Category.Electronics),
				new Mobile("Mobile", 8666,"Samsung","Galaxy A24 Dual sim",5,"img\\m2.jpg",Category.Electronics),	
				new Mobile("Mobile", 9075,"OPPO","A78 Dual sim 8GB",4,"img\\m3.jpg",Category.Electronics),
					
				new Laptop("Laptops",28999,"HP","15.6 inch AMD",1,"img\\l1.jpg",Category.Electronics),
				new Laptop("Laptops", 229999,"Dell","15.6 inch i5-1135G7",3,"img\\l2.jpg",Category.Electronics),
				new Laptop("Laptops", 45950,"Apple","macbook air MGN63 13",1,"img\\l3.jpg",Category.Electronics),	
					
				new Desk("desk",870,"wood","white",true,"img\\d2.jpg",Category.Furniture),
				
				new Chair("chair",689,"khorshed","gray",false,"img\\c1.jpg",Category.Furniture),
				new Chair("chair",130,"polypropylene","red",false,"img\\c2.jpg",Category.Furniture),
				
				new Bookcase("bookcase",399,"wood","white",true,"img\\bb1.jpg",Category.Furniture),
				new Bookcase("bookcase",790,"wood","black",true,"img\\bb2.jpg",Category.Furniture),
				
				
				new Bracelet("bracelet", 445,"Zinc alloy","4 pcs charm bracelet","img\\b1.jpg",Category.Jewelry),
				new Bracelet("bracelet", 700,"Silver","3Diamonds bracelet","img\\b2.jpg",Category.Jewelry),
				new Bracelet("bracelet", 1500,"gold","dar locket bracelet","img\\b3.jpg",Category.Jewelry),
				
				new Ring("ring", 299,"Silver","butterfly ring with sparkly","img\\ring.jpg",Category.Jewelry),
				new Ring("ring", 350,"gold plated","ring with sparkling zicron","img\\r2.jpg",Category.Jewelry),
				
				new Necklace("necklace", 200,"gold plated","flag of palestine necklace","img\\n1.jpg",Category.Jewelry),
				new Necklace("necklace", 500,"Silver","pendant necklace","img\\n2.jpg",Category.Jewelry)
		};
         
        
         //Discount
         for(int i=0;i<10;i++) {
        	 double x=available[i].offerDiscount(20);
             available[i].setPrice(x);
             available[i].setName(available[i].getName()+" (20% off)");
         }
         
         
        //setting Shop System instock Products
        Main.inStock = Arrays.asList(available);
        ShopSystem.addToStock(inStock);
        
        
        
        // Main layout
        BorderPane borderPane = new BorderPane();
        
        
        
        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        header.getChildren().add(new Label("Welcome "+this.customer.getuserName()+" to Our Store"));
        
        
        
        
        
        // Search field
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        
        
        // Search button
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-font-size: 13px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
        searchButton.setOnAction(event -> {
        	this.searchedProducts=Product.searchByKeyword(searchField.getText(),inStock);
            updateProductGrid_search(this.searchedProducts);
            if (this.searchedProducts.isEmpty()) {
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opss");
                alert.setHeaderText(null);
                alert.setContentText("No Results Found");
                alert.showAndWait();
            }
        
        		
        });
        
        
  
        //Search field Box
        HBox searchBox = new HBox(searchField, searchButton);
        searchBox.setPadding(new Insets(10, 0, 0, 10));
        searchBox.setSpacing(5);
       
        

        
        
        
 
        // Categories ComboBox
        ComboBox<Category> categoryComboBox = new ComboBox<>(FXCollections.observableArrayList(Category.values()));
        categoryComboBox.setPromptText("Select Category");
        categoryComboBox.setOnAction(event -> {
            Category selectedCategory = categoryComboBox.getValue();
            if (selectedCategory != null) {
               this.categorizedProducts= filterProductsByCategory(selectedCategory, available);
            }
        });

        
      //Categories field Box
        HBox categoryBox = new HBox();
        categoryBox.getChildren().add(categoryComboBox);
        categoryBox.setPadding(new Insets(10, 0, 0, 10));
        borderPane.setLeft(categoryBox);

        
        
        
        
        
        
     // Sort ComboBox two types of sorting --> Name or Price
        ComboBox<String> sortByComboBox = new ComboBox<>(FXCollections.observableArrayList("Price", "Name"));
        sortByComboBox.setPromptText("Sort by");
        sortByComboBox.setOnAction(event -> {
            String selectedSortOrder = sortByComboBox.getValue();
            if (selectedSortOrder != null) {
            	List<Product> sortedProducts=Product.sortBy(this.categorizedProducts, selectedSortOrder);
            	updateProductGrid(sortedProducts);
            }
        });

        
      //Sort field Box
        HBox sortBox = new HBox();
        sortBox.getChildren().add(sortByComboBox);
        sortBox.setPadding(new Insets(10, 0, 0, 10));
        
        

       // Log Out button
        Button logOutButton = new Button("Log Out");
        logOutButton.setStyle("-fx-font-size: 13px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
        logOutButton.setOnAction(event -> { 
        	//Before logging out save wishlist to user
        	this.customer.setWishList(productsInWishlist);
        	loggingOut(primaryStage);
        });
      
        //log Out box
        HBox logOutBox=new HBox();
        logOutBox.getChildren().add(logOutButton);
        logOutBox.setPadding(new Insets(10,0,0,10));
        
        
        
     //Setting main layout top part   
     borderPane.setTop(new HBox(header,logOutBox,searchBox,sortBox));
        
        
        
        
        
        
        // Product grid
        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(10));
        productGrid.setVgap(10);
        productGrid.setHgap(20);
        productGrid.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

        //Products showing in grid
        int columnIndex = 0;
        int rowIndex = 0;
        for (Product product : available) {
            ProductItem productItem = new ProductItem(product);
            productGrid.add(productItem, columnIndex, rowIndex);
            columnIndex++;
            //6 products per row
            if (columnIndex == 6) {
                columnIndex = 0;
                rowIndex++;
            }
        }

        
        
        
        //Scrolling Down
        
        // Wrap the product grid in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(productGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Responsive layout
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        borderPane.setCenter(scrollPane);

        
        
        
        // Cart 
        VBox cartVBox = new VBox();
        cartVBox.setPadding(new Insets(10));
        cartVBox.setSpacing(20);
        cartVBox.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        
        Label cartLabel = new Label("Shopping Cart");
        
        //Items in cart VBox
        cartItemsVBox = new VBox();
        cartItemsVBox.setSpacing(5);
        cartVBox.getChildren().addAll(cartLabel, cartItemsVBox);

        
        
        
        
        
        //Wish List
        VBox wishlistVBox = new VBox();
        wishlistVBox.setPadding(new Insets(10));
        wishlistVBox.setSpacing(10);
        wishlistVBox.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Label wishlistLabel = new Label("Wishlist");
        
      //Items in WishList VBox
        wishlistItemsVBox = new VBox();
        wishlistItemsVBox.setSpacing(5);
        wishlistVBox.getChildren().addAll(wishlistLabel, wishlistItemsVBox);
        

        
        
      //Setting main layout Right part
        borderPane.setRight(new VBox(cartVBox, wishlistVBox));
        borderPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
       
        
        
        //invoked to show previous wish list of customer
        //no matter he logs out or not, his wishList will always be saved
        updateWishlistItems();
        
        
        
        //Main Page SHOW
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();   
        
    }
    
    
    
    //invoked when LogOut button is pressed, goes to login page
    private void loggingOut(Stage primaryStage) {
    	primaryStage.close();
    	Login l=new Login();
    	Stage stage=new Stage();
    	l.start(stage);
		
	}


    //Main constructor, connects user who signed in or up with the main page
    //the same user in all pages
	public Main(Customer customer) {
        this.customer=customer;
    }
	
	
	
    public static void main(String[] args) {
        launch(args);
    }

    
    
    
    
    
     //Inner Class=> as we don't use it except in Main class 
    // Product item view class
    class ProductItem extends VBox {
        public ProductItem(Product product) {
            setPadding(new Insets(10));
            setSpacing(5);
            setStyle("-fx-border-color: #424242; -fx-border-width: 3px; -fx-border-radius: 30px;");
            ImageView imageView = new ImageView(new Image(new File(product.getImagePath()).toURI().toString()));
            imageView.setFitWidth(155);
            imageView.setFitHeight(150);
            Label nameLabel = new Label(product.getName());
            Label priceLabel = new Label(String.valueOf(product.getPrice())+"EGP");
            
            //Add to cart button
            Button addToCartButton = new Button("Add to Cart");
            addToCartButton.setStyle("-fx-font-size: 13px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
            addToCartButton.setOnAction(event -> {
                productsInCart.add(product);
                updateCartItems();
            });
            
          //Add to WishList button
            Button addToWishlistButton = new Button("Add to Wishlist");
            addToWishlistButton.setStyle("-fx-font-size: 13px; -fx-background-color: #435F54; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
            addToWishlistButton.setOnAction(event -> {
                productsInWishlist.add(product);
                customer.setWishList(productsInWishlist);
                updateWishlistItems();
            });
            
            //Review Button
            Button rateButton = new Button("Rate Product");
            rateButton.setStyle("-fx-font-size: 13px; -fx-background-color: #FFA500; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
            rateButton.setOnAction(e -> showRating(product));
            
            getChildren().addAll(imageView, nameLabel, priceLabel, addToCartButton, addToWishlistButton,rateButton);
        }
    }

    
    
    
    //Rating Methods


    private void showRating(Product product) {
        this.finalRating=0;
    	// Create circles for rating
        for (int i = 0; i < maxRating; i++) {
            final int index = i; // Create final variable for index
            Circle circle = new Circle(15);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);
            circle.setOnMouseClicked(event -> handleRating(index + 1));
            ratingCircles[i] = circle;
        }

        HBox ratingBox = new HBox(10);
        ratingBox.getChildren().addAll(ratingCircles);

        ShopSystem.ratings.add(product.getName());
        ShopSystem.ratings.add(String.valueOf(finalRating));
        
        Scene ratingScene = new Scene(ratingBox);
        Stage ratingStage = new Stage();
        ratingStage.setScene(ratingScene);
        ratingStage.setTitle("Rate Product");
        ratingStage.show();
        
    }

    private void handleRating(int rating) {
        // Update the rating circles based on the selected rating
        currentRating = rating;
        for (int i = 0; i < maxRating; i++) {
            if (i < currentRating) {
                ratingCircles[i].setFill(Color.GREEN); // Fill circles up to the selected rating
                this.finalRating+=1;
            } else {
                ratingCircles[i].setFill(Color.WHITE); // Reset color for circles beyond the selected rating
            }
        }
    
       
    }

    
    
    
    
    
 // Update product grid
    private void updateProductGrid_search(List<Product> products) {
        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(10));
        productGrid.setVgap(10);
        productGrid.setHgap(20);
        productGrid.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        int columnIndex = 0;
        int rowIndex = 0;
        for (Product product : products) {
            ProductItem productItem = new ProductItem(product);
            productGrid.add(productItem, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex == 6) {
                columnIndex = 0;
                rowIndex++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(productGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        BorderPane borderPane = (BorderPane) cartItemsVBox.getScene().getRoot();
        borderPane.setCenter(scrollPane);
        
    }

    
    
    
    

 // Update cart items
    private void updateCartItems() {
        double totalPrice = 0;

        // Clear existing items
        cartItemsVBox.getChildren().clear();
        
        // Add current items
        for (Product product : productsInCart) {
            Label productLabel = new Label(product.getName());
            Label productPrice = new Label(String.valueOf(product.getPrice())+"EGP");
            
            // Button to remove item from cart
            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-font-size: 9px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
            removeButton.setOnAction(event -> {
                productsInCart.remove(product);
                updateCartItems();
            });
            
            HBox itemBox = new HBox(productLabel, productPrice, removeButton);
            itemBox.setSpacing(5);
            cartItemsVBox.getChildren().add(itemBox);
            
            totalPrice += product.getPrice();
        }

        
        //Showing the user Total Price of his cart
        Label totalPriceLabel = new Label("Total Price= " + String.valueOf(totalPrice) + "EGP");
        totalPriceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        
        // Button to clear the entire cart
        Button clearCartButton = new Button("Clear Cart");
        clearCartButton.setStyle("-fx-font-size: 13px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
        clearCartButton.setOnAction(event -> {
            productsInCart.clear();
            updateCartItems();
        });
        
        
        //Order button Travels to Ordering Page
        Button order= new Button("order");
        order.setStyle("-fx-font-size: 13px; -fx-background-color: #435F54; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
        order.setOnAction(e-> {
        	
        	//Closing Main Page
        	this.mainStage.close();
        	
        	//Sending Cart, Customer's info, Date of order to OrderPage
        	openOrderPage(productsInCart,customer,LocalDate.now());	
        });
        
        cartItemsVBox.getChildren().addAll(totalPriceLabel, clearCartButton,order);
    }

    
    
    
    
    //invoked to open order Page while Sending Cart, Customer's info, Date of order to OrderPage
    private void openOrderPage(List <Product> productsInCart,Customer customer,LocalDate date) {
	
    	OrderPage o= new OrderPage(productsInCart,customer,date);
    	Stage orderStage=new Stage();
    	o.start(orderStage);
	
	
}



	// Update wish list items
    private void updateWishlistItems() {
        // Clear existing items
        wishlistItemsVBox.getChildren().clear();
        // Add current items
        for (Product product : productsInWishlist) {
            Label productLabel = new Label(product.getName());
            
            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-font-size: 9px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-border-radius: 60px;");
            removeButton.setOnAction(event -> {
            	productsInWishlist.remove(product);
            	updateWishlistItems();
            });
            
            HBox h=new HBox(productLabel,removeButton);
            wishlistItemsVBox.getChildren().addAll(h);
        }
    }

    
    
    
    
    // Filter products by category
    private List<Product> filterProductsByCategory(Category category, Product[] products) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                filteredProducts.add(product);
            }
        }
        updateProductGrid(filteredProducts);
        
        //Returning Filtered list here is important as it is used with sort option
		return filteredProducts;
    }

    
    
    
    // Update product grid based on filtered products
    private void updateProductGrid(List<Product> filteredProducts) {
        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(10));
        productGrid.setVgap(10);
        productGrid.setHgap(20);
        productGrid.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

        
        

        int columnIndex = 0;
        int rowIndex = 0;
        for (Product product : filteredProducts) {
            ProductItem productItem = new ProductItem(product);
            productGrid.add(productItem, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex == 6) {
                columnIndex = 0;
                rowIndex++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(productGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        BorderPane borderPane = (BorderPane) cartItemsVBox.getScene().getRoot();
        borderPane.setCenter(scrollPane);
    }

    
    
    // Product categories defined in an enum
    //enum is a special type that represents a group of constants (unchangeable values).
    //enumerations=>specifically listed.
    enum Category {
        Cars,
        Clothing,
        Paintings,
        Electronics,
        Furniture,
        Book,
        Jewelry
    }
}
