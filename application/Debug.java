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
import facade.CategoryFacade;
import facade.ItemFacade;
import facade.UserFacade;
import helper.AuthHelper;
import helper.FileHelper;
import model.Admin;
import model.Employee;
import model.Kilogram;
import model.Liter;
import model.Unity;
import singleton.BrandListSingleton;
import singleton.CategoriesListSingleton;
import singleton.ItemListSingleton;
import singleton.LoggedUserSingleton;
import singleton.UserListSingleton;
import helper.HashHelper;
import observable.BrandListObservable;
import observable.BrandListObserver;
import observable.CategoriesListObserver;
import observable.ItemListObserver;
import observable.UserListObserver;
import factory.BrandFactory;
import factory.CategoryFactory;
import factory.ItemFactory;
import facade.BrandFacade;
public class Debug {

	public static void main(String args[]) {
		
		
		
		/*
		Employee e = new Employee("Victor Lima","vlima","123456");
		Admin a = new Admin("Lima Victor","lvictor","231232");
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(e);
		users.add(a);
		*/
		
		UserFacade.allocateUsers();
		UserListObserver userListObserver = new UserListObserver(UserListSingleton.getInstance());
		CategoryFacade.allocateCategories();
		CategoriesListObserver categoriesListObserver = new CategoriesListObserver(CategoriesListSingleton.getInstance());
		BrandFacade.allocateBrands();
		BrandListObserver brandListObserver = new BrandListObserver(BrandListSingleton.getInstance());
		ItemFacade.allocateItems();
		ItemListObserver itemListObserver = new ItemListObserver(ItemListSingleton.getInstance());
		
		/*
		ItemFactory bf = new ItemFactory();
		
		
		ItemListSingleton.getInstance().insert(bf.create("Pipoca", null , 10, 3, null));
		ItemListSingleton.getInstance().insert(bf.create("Melancia", null , 102, 15, null));
		ItemListSingleton.getInstance().insert(bf.create("Feijão", null , 103, 3, null));
		ItemListSingleton.getInstance().insert(bf.create("Doritos", null , 104, 3, null));
		ItemListSingleton.getInstance().insert(bf.create("Coca-cola", null , 150, 5, null));
		
				
		System.out.println("LISTA DE Produtos");
		ItemListSingleton.getInstance().getItems().forEach(element -> {
			System.out.println(element.toString());
		});
		System.out.println("FIM LISTA DE Produtos");
		
		
		
		/*
		BrandFactory bf = new BrandFactory();
		
		BrandListSingleton.getInstance().clear();
		BrandListSingleton.getInstance().insert(bf.create("Santa clara"));
		BrandListSingleton.getInstance().insert(bf.create("Tio joao"));
		BrandListSingleton.getInstance().insert(bf.create("Garoto"));
		BrandListSingleton.getInstance().insert(bf.create("Nestlê"));
		BrandListSingleton.getInstance().insert(bf.create("Unilever"));
		
	
		System.out.println("LISTA DE Marcas");
		BrandListSingleton.getInstance().getBrands().forEach(element -> {
			System.out.println(element.toString());
		});
		System.out.println("FIM LISTA DE MARCAS");
		*/
		
		/*	
		CategoryFactory categoryFactory = new CategoryFactory();
		//CategoriesListSingleton.getInstance().clear();
		//CategoriesListSingleton.getInstance().insert(categoryFactory.create("Sucos", new Liter()));
		
		System.out.println("LISTA DE CATEGORIAS");
			CategoriesListSingleton.getInstance().getCategories().forEach(element -> {
				System.out.println(element.toString());
			});
		System.out.println("FIM LISTA DE CATEGORIAS");
		*/
		
		/*
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
					if(!AuthHelper.isAdmin()) {
						int type;
						System.out.println("Criar usuario: ");
						System.out.println("Digite o tipo do usuário 0 ou 1: ");
						type = s.nextInt();
						
						if(type == 1) {
							Admin user = new Admin();
							System.out.println("Digite o nome do usuario: ");
							user.setName(s.nextLine());
							System.out.println("Digite o username: ");
							s.nextLine();
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
	*/
	}
}
