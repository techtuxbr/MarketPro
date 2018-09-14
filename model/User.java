package model;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable{
	protected String id = UUID.randomUUID().toString();
	protected String name;
	protected String username;
	protected String password;
	protected int type;
	
	public User(String name, String username, String password, int type) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public User(String id, String name, String username, String password, int type) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", type="
				+ type + "]";
	}
}
