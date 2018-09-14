package model;

import java.util.UUID;

public class Brand {
	private String id = UUID.randomUUID().toString();
	private String name;
	
	public Brand(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
}
