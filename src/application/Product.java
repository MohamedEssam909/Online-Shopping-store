package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Main.Category;

public abstract class Product implements Comparable<Product> {
	 private String name;
	 private double price;
	 private String imagePath;
	 private Category category;
	 private static String sortType;
	 public static ArrayList<String> reviews=new ArrayList<String>();

	 
public Product()  {
		 
	 }
	 
	 public Product(String n, double p,String imagePath,Category category){
	 this.name=n;
	 this.price=p;
	 this.imagePath = imagePath;
	 this.category = category;
	 }
	 
	 
	 
	 
	 
	 public void setName(String name) {
		this.name = name;
	}

	public static ArrayList<String> getReviews() {
		return reviews;
	}

	public static void setReviews(ArrayList<String> reviews) {
		Product.reviews = reviews;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getName() {
		 return this.name;
		 }
		 
		 public double getPrice() {
		 return this.price;
		 }
		 public String getImagePath() {
			 return this.imagePath;
		 }
		 public Category getCategory() {
	         return category;
	     }

	
	 
	 
	 
	 
	 @Override
	 public String toString() {
	 return "Product: "+this.name+"\nPrice: "+this.price;
	 }
	 
	 
	 
	 
	 
	 @Override
	 public boolean equals(Object o){
	 if(this==o)
	 return true;
	 if(!(o instanceof Product))
	 return false;
	 Product i = (Product)o;
	 return(this.price==i.price && this.name.equals(i.name));
	 }
	 
	 
	 
	 
	 
	 
	 public double offerDiscount(int percent){
	return this.price -= this.price * percent/100;
	 }
	 
	 
	 
	 
	 //Used by sort 
	 @Override
	 public int compareTo(Product p) {
		 if(Product.sortType.equals("Price"))
		 {
		 if(this.getPrice()==p.getPrice())
			 return 0;
		 else if(this.getPrice()>p.getPrice())
			 return 1;
		 else
			 return -1;	 
		 }
		 else if (Product.sortType.equals("Name"))
             return this.getName().compareTo(p.getName());
		 else 
		 {
			 System.out.println("INVALID Sort Type, No sort is done");
			 return 404;
		 }
	 }
	 
	 
	//abstract as each category has its own option
	 abstract boolean shippableORnot();
	 
	 
	 
	
	 //used in search option in main Page
	 public static List<Product> searchByKeyword(String query,List<Product> inStock) {
		 List<Product> filteredProducts = inStock.stream().filter(product -> product.getName().toLowerCase().contains(query.toLowerCase().trim())).toList();
		return filteredProducts; 
	 }
	 
	 
	 
	 
	 public abstract void buyProduct();
	 public abstract void addNewProduct();
	 
	 
	 protected abstract boolean isReturnable();
	 protected abstract double restockingFees();

	 
	 
	private static void returningReasons(String s) {
		 reviews.add(s);
	 }
	 
	 
	
	 protected abstract void returnProduct();
	 
	 
	 
	 
	 public static void returnProd(Product o,String s) {
		 if(o.isReturnable()) {
			 System.out.println("Restocking Fees will be: "+o.restockingFees());
			 o.returnProduct();
			 returningReasons(s);
		 }
		 else
			 System.out.println("The Product is not Returnable");
	 }
	 
	 
	 
	 
	 //Advanced Sort Method that takes sort type also 
	 public static List<Product> sortBy(List<Product> available_list, String s) {
		 Product.sortType=s;
		 Collections.sort(available_list);
		 return available_list;
		 	 
	 }
}







//Child Classes of Product

	abstract class Car extends Product{
	 private int numOfKilos;
	 private int officialPrice;
	 private int year;
	 //static ArrayList<Car> inStock=new ArrayList<Car>();
	 
	 
	 public Car() {
		 
	 }
	 
	 public Car(String n,int y, int p, int k, int oP,String imagePath,Category category){
	super(n,p,imagePath,category);
	 this.numOfKilos=k;
	 this.officialPrice=oP;
	 this.year=y; 
		 
	 }
	 
	 public int getKilos() {
	 return this.numOfKilos;
	 }
	 
	 public int getOfficialPrice() {
	 return this.officialPrice;
	 }
	 
	 
	 @Override 
	 public String toString(){
	 return super.toString()+"\nRelease Year: "+this.year+"\nKilometers: "+this.numOfKilos+"\nOfficial price:"+this.officialPrice+"\n.............\n";
	 }
	 
	 
	 
	 @Override
	 public boolean equals(Object o){
	 if(!(o instanceof Car))
	 return false;
	 Car c = (Car)o;
	 return (super.equals(o) && this.numOfKilos==c.numOfKilos && 
	this.officialPrice==c.officialPrice);
	 }
	 
	 
	 
	 
	 @Override
	 boolean shippableORnot() {return false;}

	 
	
	 @Override
		public boolean isReturnable() {
	      	return false;
		}
	 
	 
		@Override
		public double restockingFees() {
			return 0;
		}
		
		
		@Override
		public void returnProduct() {
			
		}

	}
	
	
//Child Classes of Car
	
	
	class BM extends Car{
		static int inStock=0;
		

		BM(){}
		BM(String n,int y, int p, int k, int oP,String imagePath,Category category){super(n,y,p,k,oP,imagePath,category);}
		
		public static int getInStock() {
			return inStock;
		}
		
		
		public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
		
		
		public void buyProduct() {
			Main.inStock.remove(this);
			inStock--;
		}
		
		
	}	
	
	
	
	class Mercedes extends Car{
		static int inStock=0;
		Mercedes(){}
		Mercedes(String n,int y, int p, int k, int oP,String imagePath,Category category){super(n,y,p,k,oP,imagePath,category);}
		
		public static int getInStock() {
			return inStock;
		}
		public void addNewProduct() {
			Main.inStock.add(this);
			inStock++;
			}
		public void buyProduct() {
			Main.inStock.remove(this);
				inStock--;
			}
	}	
	
	
	
	class Toyota extends Car{
		static int inStock=0;
		Toyota(){}
		Toyota(String n,int y, int p, int k, int oP,String imagePath,Category category){super(n,y,p,k,oP,imagePath, category);}
		
		public static int getInStock() {
			return inStock;
		}
		public void addNewProduct() {
			Main.inStock.add(this);
			inStock++;
			}
		public void buyProduct() {
			Main.inStock.remove(this);
				inStock--;
			}
		
	}	
	
	
	
	class Audi extends Car{
		static int inStock=0;
		Audi(){}
		Audi(String n,int y, int p, int k, int oP,String imagePath,Category category){super(n,y,p,k,oP,imagePath, category);}
		public static int getInStock() {
			return inStock;
		}
		public void addNewProduct() {
			Main.inStock.add(this);
			inStock++;
			}
		public void buyProduct() {
			Main.inStock.remove(this);
				inStock--;
			}
	}
	
	
	
//Child of Product	
	
abstract class ClothingProduct extends Product {
	private String size;
	private String color;
	private String material;
	//static ArrayList<ClothingProduct> inStock=new ArrayList<ClothingProduct>();
	public ClothingProduct() {
		
		
	}
	
	public ClothingProduct(String n, int p,String size,String color,String material,String imagePath,Category category) {
		super(n, p,imagePath,category);
		this.size=size;
		this.material=material;
		this.color=color;
	}
	public String getSize() {
		return this.size;
	}
	public String getColor() {
		return this.color;
	}
	public String getMaterial() {
		return this.material;
	}
	@Override
	 boolean shippableORnot() {return true;}
	
	@Override 
	 public String toString() {
	 return super.toString()+ "\nSize: "+this.size+"\nMaterial: "+this.material +"\nColor: "+this.color+"\n.............\n";
	 }
	 @Override
	 public boolean equals(Object o){
	 if(!(o instanceof ClothingProduct))
	 return false;
	 ClothingProduct c = (ClothingProduct)o;
	 return (super.equals(o) && (this.color).equals(c.color) && 
	(this.material).equals(c.material) && (this.size).equals(c.size));
	 }
	 @Override
		public boolean isReturnable() {
	      	return true;
		}
		@Override
		public double restockingFees() {
			return (this.getPrice())*(20.0/100);
		}


}


class Shirt extends ClothingProduct{
	static int inStock=0;
	Shirt(){}
	Shirt(String n, int p,String size,String color,String material,String imagePath,Category category){super(n,p,size,color,material,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class Jeans extends ClothingProduct{
	static int inStock=0;
	Jeans(){}
	Jeans(String n, int p,String size,String color,String material,String imagePath,Category category){super(n,p,size,color,material,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
			Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}




class Tshirt extends ClothingProduct{
	static int inStock=0;
	Tshirt(){}
	Tshirt(String n, int p,String size,String color,String material,String imagePath,Category category){super(n,p,size,color,material,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
			Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class Short extends ClothingProduct{
	static int inStock=0;
	Short(){}
	Short(String n, int p,String size,String color,String material,String imagePath,Category category){super(n,p,size,color,material, imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}

	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}





//Child of Product	
abstract class Painting extends Product {
	private String size;

	public Painting() {
		
		
	}
	
	public Painting(String n, int p,String size,String imagePath,Category category) {
		super(n, p,imagePath,category);
		this.size=size;

	}
	public String getSize() {
		return this.size;
	}

	
	@Override
	 boolean shippableORnot() {return true;}
	
	
	@Override 
	 public String toString() {
	 return super.toString()+ "\nSize: "+this.size+"\n.............\n";
	 }
	
	
	 @Override
	 public boolean equals(Object o){
	 if(!(o instanceof ClothingProduct))
	 return false;
	 ClothingProduct c = (ClothingProduct)o;
	 return (super.equals(o) && (this.size).equals(c.getSize()));
	 }
	 
	 
	 @Override
		public boolean isReturnable() {
	      	return true;
		}
	 
	 
		@Override
		public double restockingFees() {
			return (this.getPrice())*(20.0/100);
		}



}


class VanGogh extends Painting{
	static int inStock=0;
	VanGogh(){}
	VanGogh(String n, int p,String size,String imagePath,Category category){super(n,p,size,imagePath,category);}
	
	public static int getInStock() {
		return inStock;
	}

	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class Nature extends Painting{
	static int inStock=0;
	Nature(){}
	Nature(String n, int p,String size,String imagePath,Category category){super(n,p,size,imagePath,category);}
	
	public static int getInStock() {
		return inStock;
	}

	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}




class LandScape extends Painting{
	static int inStock=0;
	LandScape(){}
	LandScape(String n, int p,String size,String imagePath,Category category){super(n,p,size,imagePath,category);}
	
	public static int getInStock() {
		return inStock;
	}

	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}




//Child of Product	
abstract class Electronics extends Product {
	private String brand;
	private String model;
	private int warranty;
	//static ArrayList<Electronics> inStock=new ArrayList<Electronics>();
	
	public Electronics() {
		
	}
	public Electronics(String n, int p,String brand,String model,int warranty,String imagePath,Category category) {
		super(n, p,imagePath, category);
		this.brand=brand;
		this.model=model;
		this.warranty=warranty;
	}

	public String getBrand() {
		return this.brand;
	}
	public String getModel() {
		return this.model;
	}
	public int getWarranty() {
		return this.warranty;
	}
	
	@Override
	 boolean shippableORnot() {
	if (this.getPrice()>30000) 
		return false;
	
	 return true;
	 }
	@Override
	 public String toString(){
		 return super.toString()+"\nBrand: "+this.brand+"\nModel: "+this.model+"\nOfficial Warranty:"+this.warranty+"\n.............\n";
		 }
	 @Override
	 public boolean equals(Object o){
	 if(!(o instanceof Electronics))
	 return false;
	 Electronics c = (Electronics)o;
	 return (super.equals(o) && (this.brand).equals(c.brand) && 
	(this.model).equals(c.model) && this.warranty==c.warranty);
	 }
	
	 @Override
		public boolean isReturnable() {
	      	return true;
		}
		@Override
		public double restockingFees() {
			return (this.getPrice())*(5.0/100);
		}
}




class TV extends Electronics{
	static int inStock=0;
	TV(){}
	TV(String n, int p,String brand,String model,int warranty,String imagePath,Category category){super(n,p,brand,model,warranty,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class Mobile extends Electronics{
	static int inStock=0;
	Mobile(){}
	Mobile(String n, int p,String brand,String model,int warranty,String imagePath,Category category){super(n,p,brand,model,warranty,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class Laptop extends Electronics{
	static int inStock=0;
	Laptop(){}
	Laptop(String n, int p,String brand,String model,int warranty,String imagePath,Category category){super(n,p,brand,model,warranty,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}




//Child of Product	
abstract class Furniture extends Product{
	private String material;
	private String color; 
	private boolean assemblyRequired;
	//static ArrayList<Furniture> inStock=new ArrayList<Furniture>();
	
public Furniture() {
		
	}
	public Furniture(String n, int p,String material,String color,boolean assemblyRequired,String imagePath,Category category) {
		super(n, p,imagePath, category);
		this.material=material;
		this.color=color;
		this.assemblyRequired=assemblyRequired;
	}
	public String getMaterial() {
		return this.material;
	}
	public String getColor() {
		return this.color;
	}
	public boolean getAssemblyRequired() {
		return this.assemblyRequired;
	}
	@Override
	 boolean shippableORnot() {if(this.assemblyRequired==true)return false;
		return true;}
	@Override
	public String toString(){
		 return super.toString()+"\nMaterial: "+this.material+"\nColor: "+this.color+"\nAssembly Required:"+this.assemblyRequired+"\n.............\n";
		 }
	@Override
	 public boolean equals(Object o){
	 if(!(o instanceof Furniture))
	 return false;
	 Furniture c = (Furniture)o;
	 return (super.equals(o) && (this.color).equals(c.color) && 
	(this.material).equals(c.material) && this.assemblyRequired==c.assemblyRequired);
	 }
	
	@Override
	public boolean isReturnable() {
      	return true;
	}
	@Override
	public double restockingFees() {
		return (this.getPrice())*(10.0/100);
	}
}


class Desk extends Furniture{
	static int inStock=0;
	Desk(){}
	Desk(String n, int p,String material,String color,boolean assemblyRequired,String imagePath,Category category){super(n,p,material,color,assemblyRequired,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class Chair extends Furniture{
	static int inStock=0;
	Chair(){}
	Chair(String n, int p,String material,String color,boolean assemblyRequired,String imagePath,Category category){super(n,p,material,color,assemblyRequired,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}




class Bookcase extends Furniture{
	static int inStock=0;
	Bookcase(){}
	Bookcase(String n, int p,String material,String color,boolean assemblyRequired,String imagePath,Category category){super(n,p,material,color,assemblyRequired,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



//Child of Product	
abstract class Book extends Product{
	private String author;
	private String type; //E-Book or T-Book
	//static ArrayList<Book> inStock=new ArrayList<Book>();
	public Book() {
		
	}
	public Book(String n, int p,String type,String author,String imagePath,Category category) {
		super(n, p,imagePath, category);
		this.type=type;
		this.author=author;
	}
	public String getAuthor() {
		return this.author;
	}
	public String getType() {
		return this.type;
	}
	@Override
	 boolean shippableORnot() {return true;}
	@Override
	public String toString(){
		 return super.toString()+"\ntype: "+this.type+"\nauthor: "+this.author+"\n.............\n";
		 }
	@Override
	 public boolean equals(Object o){
	 if(!(o instanceof Book))
	 return false;
	 Book c = (Book)o;
	 return (super.equals(o) && (this.author).equals(c.author) && 
	(this.type).equals(c.type));
	 }
	
	@Override
	public boolean isReturnable() {
      	return true;
	}
	@Override
	public double restockingFees() {
		return (this.getPrice())*(30.0/100);
	}
}



class TBook extends Book{
	static int inStock=4;
	TBook(){}
	TBook(String n, int p,String type,String author,String imagePath,Category category){super(n,p,type,author,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class EBook extends Book{
	static int inStock=0;
	EBook(){}
	EBook(String n, int p,String type,String author,String imagePath,Category category){super(n,p,type,author,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	@Override
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}





//Child of Product	
abstract class JewelryProduct extends Product {
	private String material;
	private String style;
	//static ArrayList<JewelryProduct> inStock=new ArrayList<JewelryProduct>();
	public JewelryProduct() {}
	public JewelryProduct(String n, int p,String material,String style,String imagePath,Category category) {
		super(n, p,imagePath,category);
		this.material=material;
		this.style=style;
	}
	
	public String getMaterial() {
		return this.material;
	}
	public String getStyle() {
		return this.style;
	}
	@Override
	 boolean shippableORnot() {return true;}
	@Override
	public String toString(){
		 return super.toString()+"\nmaterial: "+this.material+"\nstyle: "+this.style+"\n.............\n";
		 }
	@Override
	 public boolean equals(Object o){
	 if(!(o instanceof JewelryProduct))
	 return false;
	 JewelryProduct c = (JewelryProduct)o;
	 return (super.equals(o) && (this.material).equals(c.material) && 
	(this.style).equals(c.style));
	 }
	
	@Override
	public boolean isReturnable() {
      	return true;
	}
	@Override
	public double restockingFees() {
		return (this.getPrice())*(10.0/100);
	}
}



class Bracelet extends JewelryProduct{
	static int inStock=0;
	Bracelet(){}
	Bracelet(String n, int p,String material,String style,String imagePath,Category category){super(n,p,material,style,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
			inStock++;
			}
	public void buyProduct() {
		Main.inStock.remove(this);
				inStock--;
			}
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}



class Ring extends JewelryProduct{
	static int inStock=0;
	Ring(){}
	Ring(String n, int p,String material,String style,String imagePath,Category category){super(n,p,material,style,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
	public void addNewProduct() {
		Main.inStock.add(this);
		inStock++;
		}
	public void buyProduct() {
		Main.inStock.remove(this);
			inStock--;
		}
	public void returnProduct() {
		Main.inStock.add(this);
		inStock++;
		System.out.println(this.getName()+" product is Returned");
	}
}




class Necklace extends JewelryProduct{
	static int inStock=0;
	Necklace(){}
	Necklace(String n, int p,String material,String style,String imagePath,Category category){super(n,p,material,style,imagePath, category);}
	
	public static int getInStock() {
		return inStock;
	}
		public void addNewProduct() {
			Main.inStock.add(this);
		inStock++;
		}
		public void buyProduct() {
			Main.inStock.remove(this);
			inStock--;
		}
		public void returnProduct() {
			Main.inStock.add(this);
			inStock++;
			System.out.println(this.getName()+" product is Returned");
		}
}


