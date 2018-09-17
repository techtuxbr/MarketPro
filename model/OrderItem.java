package model;

import java.io.Serializable;

public class OrderItem implements Serializable{
	
	private Item item;
	private float total;
	private	int amount;
	private float price;
	private String itemName;
	private String itemBarcode;

	public OrderItem(Item item,int amount) {
		this.item = item;
		this.total = item.getPrice() * amount;
		this.amount = amount;
		this.price = item.getPrice();
		this.itemName = item.getName();
		this.itemBarcode = item.getBarCode();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemBarcode() {
		return itemBarcode;
	}

	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}
}