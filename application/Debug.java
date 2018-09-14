package application;

import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Scanner;
import java.util.Set;

import exceptions.UsernameCollision;
import exceptions.WrongPassword;
import exceptions.WrongUsername;
import facade.UserFacade;
import helper.AuthHelper;
import helper.FileHelper;
import model.Admin;
import model.Employee;
import singleton.LoggedUserSingleton;
import singleton.UserListSingleton;
import helper.HashHelper;
import observable.UserListObserver;
public class Debug {

	public static void main(String args[]) {
		Employee e = new Employee("Victor Lima","vlima","123456");
		Admin a = new Admin("Lima Victor","lvictor","231232");
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(e);
		users.add(a);
		
		UserFacade.allocateUsers();
		UserListObserver userListObserver = new UserListObserver(UserListSingleton.getInstance());
		
		int op = 1;
		do {
			System.out.println("Teste sistema de usuarios: ");
			System.out.println("1 - Logar");
			System.out.println("2 - Exibir usuário logado");	
			System.out.println("3 - Cadastrar usuario");
			System.out.println("4 - Listar contas");
			System.out.println("5 - Limpar lista");
			Scanner s = new Scanner(System.in);
			op = s.nextInt();
			
			switch(op) {
				case 1:
					try {
						UserFacade.authUser("usuario", "senha");
					} catch (WrongPassword | WrongUsername e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					break;
				case 2:
					if(AuthHelper.isLogged()) {
						System.out.println("Username: " + LoggedUserSingleton.getInstance().getUsername() + " Password:"+ LoggedUserSingleton.getInstance().getPassword());
					}else {
						System.out.println("Nenhum usuario Logado");
					}
					break;
				case 3:
					if(AuthHelper.isAdmin()) {
						int type;
						System.out.println("Criar usuario: ");
						System.out.println("Digite o tipo do usuário 0 ou 1: ");
						type = s.nextInt();
						
						if(type == 1) {
							Admin user = new Admin();
							System.out.println("Digite o nome do usuario: ");
							s.nextLine();
							user.setName(s.nextLine());
							System.out.println("Digite o username: ");
							user.setUsername(s.nextLine());
							System.out.println("Digite a senha: ");
							user.setPassword(s.nextLine());
							user.setType(1);
							try {
								UserFacade.registerUser(user);
							} catch (UsernameCollision e1) {
								e1.printStackTrace();
							}
						}else if(type == 0) {
							Employee user = new Employee();
							
							System.out.println("Digite o nome do usuario: ");
							user.setName(s.nextLine());
							System.out.println("Digite o username: ");
							user.setUsername(s.nextLine());
							System.out.println("Digite a senha: ");
							user.setPassword(s.nextLine());
							user.setType(0);
							try {
								UserFacade.registerUser(user);
							} catch (UsernameCollision e1) {
								e1.printStackTrace();
							}
						}	
					}
					break;
				case 4:
					UserListSingleton.getInstance().getUsers().forEach(element -> {
						System.out.println(element.toString());
					});
					break;
				case 5:
					UserListSingleton.getInstance().clear();
					break;
			}
			
		}while(op != 0);
	
			
		/*ret.forEach(element -> {
			System.out.println(element.toString());
			if(element.getType() == 1) {
				LoggedUserSingleton.getInstance().setId(element.getId());
				LoggedUserSingleton.getInstance().setName(element.getName());
				LoggedUserSingleton.getInstance().setPassword(element.getPassword());
				LoggedUserSingleton.getInstance().setType(element.getType());
				LoggedUserSingleton.getInstance().setUsername(element.getUsername());
			}
		});*/
	}
}
