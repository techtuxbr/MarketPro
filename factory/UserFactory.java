package factory;

import model.User;
import model.Admin;
import model.Employee;
import helper.HashHelper;

public class UserFactory implements UserCreator {

	@Override
	public User createAdmin(String name, String username, String password) {
		return new Admin(name,username,password);
	}

	@Override
	public User createAdmin(String id, String name, String username, String password) {
		return new Admin(id,name,username,password);
	}

	@Override
	public User createEmployee(String name, String username, String password) {
		return new Employee(name,username,password);
	}

	@Override
	public User createEmployee(String id, String name, String username, String password) {
		return new Employee(id,name,username,password);
	}
}
