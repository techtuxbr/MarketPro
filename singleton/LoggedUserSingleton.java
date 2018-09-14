package singleton;
import model.User;

public class LoggedUserSingleton extends User{
	private static LoggedUserSingleton instance;
	private LoggedUserSingleton() {
		super();
	}
	public static LoggedUserSingleton getInstance() {
		if(instance == null) {
			instance = new LoggedUserSingleton();
			instance.setId(null);
			return instance;
		}
		
		return instance;
	}
}
