package application;


public interface PaymentStrategy {
	//in both method pay method make sure to display total price and set all user order price
	void pay(double totalPrice);
}
