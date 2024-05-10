package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Order implements Shippable{
		
	//Order has these Data fields to make it easy to store data in shop System
        private Customer customer;
		private List<Product> cart=new ArrayList<>();
		private double totalPrice;
		private LocalDate date;
		private PaymentStrategy method;
		
		
		Order(){}
			
		
		Order(List<Product> cart,Customer customer,PaymentStrategy method ){
			this.cart=cart;
			this.date=LocalDate.now();
			this.customer=customer;
			this.method=method;
		}
		
		
		
		public void setCart(List<Product> cart) {
			this.cart=cart;
		}
		public List<Product> getCart(){
			return this.cart;
		} 

		
		
		
		public Customer getCustomer() {
			return customer;
		}
		public LocalDate getDate() {
			return date;
		}

		
		
		public void setDate(LocalDate date) {
			this.date = date;
		}

		
		
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		
		

		void setTotalPrice(double totalPrice) {
			this.totalPrice=totalPrice;
			
		}
		double getTotalPrice() {
			return this.totalPrice;
			
		}
		

		@Override
		 public String toString() {
			String s="";
		 for(int i=0 ;i<cart.size();i++)
		 {
			 s+=cart.get(i).toString();
		 }
		 return s+customer.toString()+"\n............."+"\nTotal Price= "+this.totalPrice;
		 
		 }
		
		
		
		//Checking if product is in our stock or not
		boolean available() { 
			boolean x=false;
			for(int i=0;i<cart.size();i++){
				for(int j=0;j<Main.inStock.size();j++){
					if ((cart.get(i).equals(Main.inStock.get(j)))){ 
						x=true;
					break;
					}
					}
			    	}

			return x;
			}
		
		
		
	
		//Used in shipping part in OrderPage
		public boolean canBeShipped() {
			boolean x=true;
			for(int i=0;i<cart.size();i++)
			{
				if(cart.get(i).shippableORnot()==false)
				{
					x=false;
					break;
				}
			}
			return x;
		};
		
		
		
		//Used in shipping part in OrderPage
		@Override
		public String freeShipping() {
			if (this.getTotalPrice()>1000) 
				return new String("0EGP");	
			else {
				this.setTotalPrice(this.getTotalPrice()+30);
				return new String("30EGP\n");
			}
		}
		
		
		

		
		
		
		
	

	
		
		void pay(PaymentStrategy x) {
			x.pay(totalPrice);
			
		}
		

		


}
	
	
	
	
	
	