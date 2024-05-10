package application;

import java.util.ArrayList;
import java.util.List;

class Customer{
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String address;
	private String phoneNumber;
	private int numberOfOrder=1;
	private double allOrdersPrice=0;
	private List <Product> wishList=new ArrayList<>();
	
	
	public double getAllOrdersPrice() {
		return allOrdersPrice;
	}

	public void setAllOrdersPrice(double allOrdersPrice) {
		this.allOrdersPrice = allOrdersPrice;
	}



	Customer(){
		
	}
	
	Customer(String fullName,String email,String address,String phoneNumber){
		if (fullName ==null || email ==null || address ==null || phoneNumber ==null)
			throw new NullPointerException();
		
		
		this.fullName=fullName;
		this.email=email;
		this.address=address;
		this.phoneNumber=phoneNumber;
		
	}
	 String getFullName() {
    	 return this.fullName;
     }
	 String getAddress() {
    	 return this.address;
     }
	 String getPhoneNumber() {
    	 return this.phoneNumber;
     }
     String getEmail() {
    	 return this.email;
     }
     
 	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}    
     void setUserName(String userName) {
    	 this.userName=userName;
     }
     String getuserName() {
    	 return this.userName;
     }
	
     
     public int getNumberOfOrder() {
		return numberOfOrder;
	}
     public void setNumberOfOrder(int numberOfOrder) {
    	 this.numberOfOrder=numberOfOrder;
     }


	public void setWishList(List<Product> wishList) {
		
			this.wishList = wishList;
		
		
	}
	
    List <Product> getWishList() {
      	 return this.wishList;
       }
   	
    //Displaying in console
	void wishList() {
    	 for(int i=0;i<getWishList().size();i++)
    	 { System.out.println(i+1 +" item in your wishList is"+getWishList().get(i));}
         }
	
	
	@Override
	 public String toString() {
	 return "Full Name: "+this.fullName+" Email: "+this.email+" Address: "+this.address+" Phone Number: "+this.phoneNumber;
	 }	

}





