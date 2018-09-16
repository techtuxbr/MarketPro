package model;

import java.io.Serializable;

public class OrderItem implements Serializable{
	
	private Item item;
	private float total;
	private	float amount;
	
	public OrderItem(Item item, float total, float amount) {
		super();
		setItem(item);
		setTotal(total);
		setAmount(amount);
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
		if (total>0)this.total = total;
		else this.total = 0;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		if (amount>0) this.amount = amount;
		else this.amount = 0;
	}

}