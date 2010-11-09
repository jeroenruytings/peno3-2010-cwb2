package cw.kuleuven.be.peno1011.cwb2;

import java.util.List;

public class Catalogue {
	
	private List <Product> producten;
	
	
	public Catalogue(List <Product> producten) 
	
	{
		this.producten = producten;
	}

	public List<Product> getCatalogueNr()
	{
		return producten;
	}
	
}
