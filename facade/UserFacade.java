package facade;

import java.util.List;

import helper.AuthHelper;
import helper.FileHelper;
import helper.HashHelper;
import model.Admin;
import model.Employee;
import model.User;
import singleton.LoggedUserSingleton;
import singleton.UserListSingleton;
import exceptions.UsernameCollision;
import exceptions.WrongPassword;
import exceptions.WrongUsername;

public class UserFacade{
	
	public static void allocateUsers(){
		FileHelper<User> fh = new FileHelper<User>("users.bin");
		UserListSingleton.getInstance().setUsers(fh.readData());
	}
	
	public static void registerUser(User u) throws UsernameCollision{
		List<User> uList = UserListSingleton.getInstance().getUsers();
		for(int i = 0; i < uList.size();i++) {
			if(uList.get(i).getUsername().equals(u.getUsername())) {
				throw new UsernameCollision("O nome de usuário já está em uso");
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
}
