package helper;

import model.User;
import singleton.LoggedUserSingleton;

public class AuthHelper {
	public static boolean isLogged() {
		return !(LoggedUserSingleton.getInstance().getId() == null);
	}
	
	public static boolean isAdmin() {
		return (isLogged() && LoggedUserSingleton.getInstance().getType() == 1);
	}
	
	public static boolean isEmployee() {
		return (isLogged() && LoggedUserSingleton.getInstance().getType() == 0);
	}
	
	public static void authenticate(User user) {
		LoggedUserSingleton.getInstance().setId(user.getId());
		LoggedUserSingleton.getInstance().setName(user.getName());
		LoggedUserSingleton.getInstance().setPassword(user.getPassword());
		LoggedUserSingleton.getInstance().setType(user.getType());
		LoggedUserSingleton.getInstance().setUsername(user.getUsername());
	}
}
