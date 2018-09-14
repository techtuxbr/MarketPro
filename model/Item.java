package model;

import java.io.Serializable;
import java.util.UUID;

public class Item implements Serializable{
	
	private String name;
	private String barCode = UUID.randomUUID().toString();
	private Brand brand;
	private int inStock;
	private float price;
	private Category type;
	
	public Item(String name, String barCode, Brand brand, int inStock, float price, Category type) {
		super();
		this.name = name;
		this.barCode = barCode;
		this.brand = brand;
		this.inStock = inStock;
		this.price = price;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Brand getBrand() {
		return brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public int getInStock() {
		return inStock;
	}
	
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Category getType() {
		return type;
	}
	
	public void setType(Category type) {
		this.type = type;
	}
	
	public String getBarCode() {
		return barCode;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", barCode=" + barCode + ", brand=" + brand + ", inStock=" + inStock + ", price="
				+ price + ", type=" + type + "]";
	}
	
	
	
}
