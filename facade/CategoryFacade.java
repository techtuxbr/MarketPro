package facade;

import java.util.ArrayList;
import java.util.List;

import exceptions.CategoryIDCollision;
import exceptions.CategoryNotFound;
import exceptions.NullData;
import exceptions.UsernameCollision;
import helper.FileHelper;
import helper.HashHelper;
import model.Category;
import model.SaleType;
import model.User;
import observable.CategoriesListObserver;
import singleton.CategoriesListSingleton;
import singleton.UserListSingleton;

public class CategoryFacade {
	
	public static CategoriesListObserver observable;
	
	public static void allocateCategories(){
		FileHelper<Category> fh = new FileHelper<Category>("categories.bin");
		CategoriesListSingleton.getInstance().setCategories(fh.readData());
		observable = new CategoriesListObserver(CategoriesListSingleton.getInstance());
	}
	
	public static void register(Category c) throws CategoryIDCollision, NullData{
		if(c == null) {
			throw new NullData("A categoria que voc� est� tentando criar � nula");
		}
		
		Category cTemp = CategoriesListSingleton.getInstance().getCategoryByID(c.getId());
		if(cTemp == null) {
			CategoriesListSingleton.getInstance().insert(c);
		}else {
			throw new CategoryIDCollision("A categoria que voc� est� tentando criar tem o mesmo ID de uma categoria j� cadastrada\nInforma��es:\n Categoria cadastrada: " + cTemp.toString() + " Categoria � cadastrar: " + c.toString());
		}
	}
	
	public static Category get(String id) throws NullData {
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		return CategoriesListSingleton.getInstance().getCategoryByID(id);
	}

	public static Category getByName(String name) throws NullData {
		if(name == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		return CategoriesListSingleton.getInstance().getCategoryByName(name);
	}
	
	public static Category remove(String id) throws NullData{
		Category cTemp;
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		cTemp = CategoriesListSingleton.getInstance().getCategoryByID(id);
		CategoriesListSingleton.getInstance().removeByID(id);
		return cTemp;
	}
	
	public static void update(String id, String name, SaleType saleType) throws NullData, CategoryNotFound {
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}else if(name == null) {
			throw new NullData("O seu parametro name � nulo");
		}else if(saleType == null) {
			throw new NullData("O seu parametro SaleType � nulo");
		}
		if(CategoriesListSingleton.getInstance().getCategoryByID(id) == null) {
			throw new CategoryNotFound("Categoria n�o existente, imposs�vel atualizar! verifique o ID!");
		}else {
			CategoriesListSingleton.getInstance().updateByID(id, name, saleType);
		}
	}
	
	public static List<Category> getList(){
		return CategoriesListSingleton.getInstance().getCategories();
	}
	
	public static void printList() {
		CategoriesListSingleton.getInstance().getCategories().forEach(element -> {
			System.out.println(element.toString());
		});
	}
}
