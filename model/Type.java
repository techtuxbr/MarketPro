package model;

import java.util.UUID;

public class Type {
	private String id = UUID.randomUUID().toString();
	private String name;
	private SaleType saleType;
	
	public Type(String name, SaleType saleType) {
		super();
		this.name = name;
		this.saleType = saleType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

}
