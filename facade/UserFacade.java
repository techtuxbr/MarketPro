package facade;

import java.util.List;

import helper.AuthHelper;
import helper.FileHelper;
import helper.HashHelper;
import model.Admin;
import model.User;
import model.Employee;
import model.SaleType;
import model.User;
import observable.UserListObserver;
import singleton.UserListSingleton;
import singleton.CategoriesListSingleton;
import singleton.LoggedUserSingleton;
import singleton.UserListSingleton;
import exceptions.CategoryNotFound;
import exceptions.NullData;
import exceptions.UserNotFound;
import exceptions.UsernameCollision;
import exceptions.WrongPassword;
import exceptions.WrongUsername;

public class UserFacade{
	public static UserListObserver observer;
	
	public static void allocateUsers(){
		FileHelper<User> fh = new FileHelper<User>("users.bin");
		UserListSingleton.getInstance().setUsers(fh.readData());
		observer =  new UserListObserver(UserListSingleton.getInstance());
	}
	
	public static void registerUser(User u) throws UsernameCollision{
		List<User> uList = UserListSingleton.getInstance().getUsers();
		for(int i = 0; i < uList.size();i++) {
			if(uList.get(i).getUsername().equals(u.getUsername())) {
				throw new UsernameCollision("O nome de usu�rio j� est� em uso");
			}
		}
		u.setPassword(HashHelper.hashInMD5(u.getPassword()));
		UserListSingleton.getInstance().insert(u);
	}
	
	public static void authUser(String username, String password) throws WrongPassword, WrongUsername{
		List<User> uList = UserListSingleton.getInstance().getUsers();
		User user = null;
		for(int i = 0; i < uList.size();i++) {
			if(uList.get(i).getUsername().equals(username)) {
				user = uList.get(i);
			}
		}
		if(user == null) {
			throw new WrongUsername("Nome de usuario incorreto");
		}
		if(HashHelper.comparePlainTextToMD5Hash(password, user.getPassword())) {
			AuthHelper.authenticate(user);
		}else {
			throw new WrongPassword("Senha incorreta!");
		}
	}
	
	public static User get(String id) throws NullData {
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		return UserListSingleton.getInstance().getUserByID(id);
	}
	
	public static User remove(String id) throws NullData{
		User uTemp;
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		uTemp = UserListSingleton.getInstance().getUserByID(id);
		UserListSingleton.getInstance().removeByID(id);
		return uTemp;
	}
	
	public static void update(String id, String name, String username, String password, int type) throws NullData, UserNotFound{
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}else if(name == null) {
			throw new NullData("O seu parametro name � nulo");
		}else if(username == null) {
			throw new NullData("O seu parametro username � nulo");
		}else if(password == null) {
			throw new NullData("O seu parametro password � nulo");
		}
		if(UserListSingleton.getInstance().getUserByID(id) == null) {
			throw new UserNotFound("Usu�rio n�o existente, imposs�vel atualizar! verifique o ID!");
		}else {
			UserListSingleton.getInstance().updateByID(id, name, username, password, type);
		}
	}
	
	public static List<User> getList(){
		return UserListSingleton.getInstance().getUsers();
	}

	public static boolean isAdmin(){
		return AuthHelper.isAdmin();
	}

	public static boolean isLogged(){
		return AuthHelper.isLogged();
	}

	public static void printList() {
		UserListSingleton.getInstance().getUsers().forEach(element -> {
			System.out.println(element.toString());
		});
	}
}
