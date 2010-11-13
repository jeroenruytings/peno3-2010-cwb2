package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.List;

public class Catalogue {
	
	private List <Product> products;
	
	
	public Catalogue(List <Product> products) {
		setProducts(products);
	}

	
	public void setProducts(List <Product> products) {
		this.products = products;
	}


	public List <Product> getProducts() {
		return products;
	}
	
}
