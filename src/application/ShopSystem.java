package application;
import java.util.ArrayList;
import java.util.List;

/*
 * Our Store's Major Class 
 * it stores all data and interactions of users
 * Saves=> Customers,Orders,Stock Products,Total Budget,Ratings
 * All pages communicate with this class
 * something like a database..
 * */

//final to not make any Objects from it 
//All data are static for the same reason
final class ShopSystem  {	
	    public static List<Customer> customers=new ArrayList<Customer>();
	    public static ArrayList<Order> data=new ArrayList<Order>();
		private static double budget=0;
		public static List<Product> inOurStore=new ArrayList<Product>();
		public static List<String> ratings=new ArrayList<String>();
		

		private ShopSystem(){
		}

		public static void setBudget(double t){
			budget+=t;
		}
		public static double getBudget(){
			return budget;
		}
		
		
		//Used in OrderPage to save Data
		protected static void dataProcessing(Order myOrder) { //protected to appear in other packages
			ShopSystem.data.add(myOrder);
		 	ShopSystem.setBudget(myOrder.getTotalPrice());
		}
		
		
		
		
		//Add new Products to our stock
	public static void addToStock(List<Product> p) {
			//try {
				//if (p==null)
				//	throw new NullPointerException();
				for(int i=0;i<p.size();i++) {
					inOurStore.add(p.get(i));
				}}
			/*}
			catch(NullPointerException ex) {
				Handling.nullPointerException_handler(ex);	
			}
			catch(IllegalArgumentException ex) {
				Handling.illegalArgumentException_handler(ex);
			}
            catch(IndexOutOfBoundsException ex) {
				Handling.indexException_handler(ex);
			}
	}*/
			
			
			

		}
			
	
	