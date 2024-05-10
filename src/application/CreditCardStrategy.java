package application;

public class CreditCardStrategy implements PaymentStrategy {
	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;
	private Customer customer;
	public CreditCardStrategy() {}
	
	public CreditCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry,Customer customer) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.dateOfExpiry = dateOfExpiry;
		this.customer=customer;
	}
	
	
	public String getName() {
		return name;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public String getCvv() {
		return cvv;
	}


	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	
	@Override 
	public void pay(double x) {
		System.out.println(x+" paid with Credit Card");
		customer.setAllOrdersPrice(customer.getAllOrdersPrice()+x);
	}
}


class CashStategy implements PaymentStrategy{	
	Customer customer;
	public CashStategy() {}
	
	public CashStategy(Customer customer) {
		this.customer=customer;
	}
	@Override 
	public void pay(double x) {
		System.out.println(x+" payment in cash");
		customer.setAllOrdersPrice(customer.getAllOrdersPrice()+x);
	}
}