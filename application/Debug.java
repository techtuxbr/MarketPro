package application;

import facade.*;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Scanner;
import java.util.Set;

import javax.lang.model.element.Element;

import exceptions.BrandIDCollision;
import exceptions.CategoryIDCollision;
import exceptions.CategoryNotFound;
import exceptions.NullData;
import exceptions.UsernameCollision;
import exceptions.WrongPassword;
import exceptions.WrongUsername;
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

public class Debug {

	public static void main(String args[]) {
			CategoryFactory categoryFactory = new CategoryFactory();
			CategoryFacade.allocateCategories();			
			//CategoryFacade.printList();
			BrandFactory brandFactory = new BrandFactory();
			BrandFacade.allocateBrands();
			//BrandFacade.printList();
			ItemFactory itemFactory = new ItemFactory();
			ItemFacade.allocateItems();
			ItemFacade.printList();
			OrderFacade.allocateOrders();
	}
}
