package observable;

import java.util.Observable;
import java.util.Observer;
import helper.FileHelper;
import model.User;

public class UserListObserver implements Observer{

	private UserListObservable userList;
	
	public UserListObserver(UserListObservable userList) {
		this.userList = userList;
		this.userList.addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		//System.out.println("Algo aconteceu na lista");
		FileHelper fh = new FileHelper<User>("users.bin");
		fh.writeData(((UserListObservable)this.userList).getUsers());
	}
}
