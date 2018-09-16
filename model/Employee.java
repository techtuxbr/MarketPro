package model;

public class Employee extends User {
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String username, String password) {
		super(name, username, password, 0);
		// TODO Auto-generated constructor stub
	}

	public Employee(String id, String name, String username, String password) {
		super(id, name, username, password, 0);
		// TODO Auto-generated constructor stub
	}
}