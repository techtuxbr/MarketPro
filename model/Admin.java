package model;

public class Admin extends User {

	public Admin(){
		super();
	}
	
	public Admin(String name, String username, String password) {
		super(name, username, password, 1);
		// TODO Auto-generated constructor stub
	}

	public Admin(String id, String name, String username, String password) {
		super(id, name, username, password, 1);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
