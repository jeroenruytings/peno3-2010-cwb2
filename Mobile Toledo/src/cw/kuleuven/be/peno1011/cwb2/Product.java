package cw.kuleuven.be.peno1011.cwb2;

public class Product {
	
	private String name;
	private int price;
	
	public Product(String name, int price){
		setName(name);
		setPrice(price);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}	
	

}
