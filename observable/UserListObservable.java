package observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import factory.UserFactory;
import model.User;

public class UserListObservable extends Observable{
	
	private List<User> users = new ArrayList<User>();
	private UserFactory userFactory = new UserFactory();
		
	public void setUsers(List<User> users) {
		if(this.users != null) {
			this.users = users;
		}else {
			return;
		}
	}
	
	public List<User> getUsers() {
		List<User> tempUsers = new ArrayList<User>();
		for(int i = 0; i < users.size();i++) {
			User uTemp = users.get(i);
			User u;
			if(uTemp.getType() == 0) {
				u = this.userFactory.createEmployee(uTemp.getId(),uTemp.getName(),uTemp.getUsername(),uTemp.getPassword());
			}else {
				u = this.userFactory.createAdmin(uTemp.getId(),uTemp.getName(),uTemp.getUsername(),uTemp.getPassword());
			}
			tempUsers.add(u);
		}
		return tempUsers;
	}
	
	public User getUserByID(String id) {
		for(int i = 0; i < users.size();i++) {
			if(users.get(i).getId().equals(id)) {
				return users.get(i);
			}
		}
		return null;
	}

	public User getUserByUsername(String username) {
		for(int i = 0; i < users.size();i++) {
			if(users.get(i).getUsername().equalsIgnoreCase(username)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public void removeByID(String id) {
			User u = this.getUserByID(id);
			if(u != null) {
				users.remove(users.indexOf(u));
		        setChanged();
		        notifyObservers();	
			}
	}
	
	public void updateByID(String id,String name, String username, String password, int type) {
		User u = this.getUserByID(id);
		if(u != null) {
			User uTemp = users.get(users.indexOf(u));
			uTemp.setName(name);
			uTemp.setUsername(username);
			uTemp.setPassword(password);
			uTemp.setType(type);
	        setChanged();
	        notifyObservers();	
		}
	}
	
	public void insert(User uTemp) {
		User u;
		if(uTemp != null) {
			if(uTemp.getType() == 0) {
				u = this.userFactory.createEmployee(uTemp.getId(),uTemp.getName(),uTemp.getUsername(),uTemp.getPassword());
			}else {
				u = this.userFactory.createAdmin(uTemp.getId(),uTemp.getName(),uTemp.getUsername(),uTemp.getPassword());
			}
			users.add(u);
			setChanged();
			notifyObservers();
		}
	}
	
	public void clear() {
		this.users.clear();
		setChanged();
		notifyObservers();
	}
}
