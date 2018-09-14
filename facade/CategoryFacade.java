package facade;

import java.util.ArrayList;
import java.util.List;

import exceptions.UsernameCollision;
import helper.FileHelper;
import helper.HashHelper;
import model.Category;
import model.User;
import singleton.CategoriesListSingleton;
import singleton.UserListSingleton;

public class CategoryFacade {
	public static void allocateCategories(){
		FileHelper<Category> fh = new FileHelper<Category>("categories.bin");
		CategoriesListSingleton.getInstance().setCategories(fh.readData());
	}	
}
