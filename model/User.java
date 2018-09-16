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
		setName(name);
		setUsername(username);
		setPassword(password);
		setType(type);
	}

	public User(String id, String name, String username, String password, int type) {
		super();
		this.id = id;
		setName(name);
		setUsername(username);
		setPassword(password);
		setType(type);
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (!name.isEmpty()) this.name = name;
		else this.name = "default";
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		if (!username.isEmpty()) this.username = username;
		else this.username = "default";
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (!password.isEmpty()) this.password = password;
		else this.password = "default";
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		if (type==0 || type==1)this.type = type;
		else this.type = 0;
	}
	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		if(id != null){
			this.id = id;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", type="
				+ type + "]";
	}
}