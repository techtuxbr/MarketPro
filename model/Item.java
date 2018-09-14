package model;

import java.util.UUID;

public class Item {
	
	private String name;
	private String barCode = UUID.randomUUID().toString();
	private Brand brand;
	private int inStock;
	private float price;
	private Type type;
	
	public Item(String name, Brand brand, int inStock, float price, Type type) {
		super();
		this.name = name;
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
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getBarCode() {
		return barCode;
	}	
}
