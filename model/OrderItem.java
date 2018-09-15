package model;

import java.io.Serializable;

public class OrderItem implements Serializable{
	
	private Item item;
	private float total;
	private	float amount;
	
	public OrderItem(Item item, float total, float amount) {
		super();
		this.item = item;
		this.total = total;
		this.amount = amount;
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
