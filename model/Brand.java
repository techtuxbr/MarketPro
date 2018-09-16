package model;

import java.io.Serializable;
import java.util.UUID;

public class Brand implements Serializable{
	private String id;
	private String name;
	
	public Brand(String id,String name) {
		super();
		this.id = id;
		setName(name);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(!name.isEmpty())this.name = name;
		else this.name = "default";
	}
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + "]";
	}
	
	
}