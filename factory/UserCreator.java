package factory;

import model.User;

public interface UserCreator {
	public User createAdmin(String name, String username, String password);
	public User createAdmin(String id,String name, String username, String password);
	public User createEmployee(String name, String username, String password);
	public User createEmployee(String id,String name, String username, String password);
}
