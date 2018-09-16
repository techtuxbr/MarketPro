package model;

import java.io.Serializable;

public class SaleType implements Serializable{
	protected int id;
	protected String name;
	protected String acronym;
	
	public SaleType(int id, String name, String acronym) {
		super();
		this.id = id;
		setName(name);
		setAcronym(acronym);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(!(id<1 || id>3))this.id = id;
		else this.id = -1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(!name.isEmpty())this.name = name;
		else this.name = "default";
	}
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		if(!acronym.isEmpty())this.acronym = acronym;
		else this.acronym = "default";
	}
	
}