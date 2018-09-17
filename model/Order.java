package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Order implements Serializable {
	
	private String id;
	private Calendar date;
	private List<OrderItem> itemList;
	private float total;
	
	public Order(String id, Calendar date, List<OrderItem> itemList) {
		super();
		this.id = id;
		setDate(date);
		setItemList(itemList);
		this.total = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(!id.isEmpty())this.id = id;
		else this.id = "default";
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	public float getTotal() {
		float total = 0;
		for(int i = 0; i < this.itemList.size();i++){
			total += itemList.get(i).getTotal();
		}
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}