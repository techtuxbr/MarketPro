package singleton;

import java.util.List;

import model.User;
import observable.UserListObservable;

public class UserListSingleton extends UserListObservable{
	
	private static UserListSingleton instance;
	private UserListSingleton() {
		super();
	}
	
	public static UserListSingleton getInstance() {
		if(instance == null) {
			instance = new UserListSingleton();
			return instance;
		}
		
		return instance;
	}
	
}
