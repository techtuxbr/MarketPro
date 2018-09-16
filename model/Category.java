package model;

import java.io.Serializable;
import java.util.UUID;

public class Category implements Serializable{
	private String id;
	private String name;
	private SaleType saleType;
	
	public Category(String id, String name, SaleType saleType) {
		super();
		this.id = id;
		setName(name);
		setSaleType(saleType);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(!name.isEmpty())this.name = name;
		else this.name = "default";
	}
	public SaleType getSaleType() {
		return saleType;
	}
	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
	}
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", saleType=" + saleType + "]";
	}
	

}