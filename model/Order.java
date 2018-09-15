package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Order implements Serializable {
	
	private String id;
	private Calendar date;
	private List<OrderItem> itemList;
	
	public Order(String id, Calendar date, List<OrderItem> itemList) {
		super();
		this.id = id;
		this.date = date;
		this.itemList = itemList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
