package facade;

import java.util.List;

import exceptions.BrandIDCollision;
import exceptions.BrandNotFound;
import exceptions.CategoryNotFound;
import exceptions.NullData;
import helper.FileHelper;
import model.Brand;
import model.Category;
import model.SaleType;
import model.Brand;
import model.Brand;
import model.Brand;
import observable.BrandListObserver;
import singleton.BrandListSingleton;
import singleton.CategoriesListSingleton;
import singleton.BrandListSingleton;

public class BrandFacade {
	
	static BrandListObserver observer;
	
	public static void allocateBrands() {
		FileHelper<Brand> fh = new FileHelper<Brand>("brands.bin");
		BrandListSingleton.getInstance().setBrands(fh.readData());
		observer = new BrandListObserver(BrandListSingleton.getInstance());
	}
	
	public static void register(Brand b) throws BrandIDCollision, NullData{
		if(b == null) {
			throw new NullData("A marca que voc� est� tentando criar � nula");
		}
		
		Brand bTemp = BrandListSingleton.getInstance().getBrandByID(b.getId());
		if(bTemp == null) {
			BrandListSingleton.getInstance().insert(b);
		}else {
			throw new BrandIDCollision("A marca que voc� est� tentando criar tem o mesmo ID de uma marca j� cadastrada\nInforma��es:\n Marca cadastrada: " + bTemp.toString() + " Marca � cadastrar: " + b.toString());
		}
	}
	
	public static Brand get(String id) throws NullData {
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		return BrandListSingleton.getInstance().getBrandByID(id);
	}

	public static Brand getByName(String name) throws NullData {
		if(name == null) {
			throw new NullData("O seu parametro name � nulo");
		}
		return BrandListSingleton.getInstance().getBrandByName(name);
	}
	
	public static Brand remove(String id) throws NullData{
		Brand bTemp;
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		bTemp = BrandListSingleton.getInstance().getBrandByID(id);
		BrandListSingleton.getInstance().removeByID(id);
		return bTemp;
	}
	
	public static void update(String id, String name) throws NullData, BrandNotFound{
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}else if(name == null) {
			throw new NullData("O seu parametro name � nulo");
		}
		
		if(BrandListSingleton.getInstance().getBrandByID(id) == null) {
			throw new BrandNotFound("Marca n�o existente, imposs�vel atualizar! verifique o ID!");
		}else {
			BrandListSingleton.getInstance().updateByID(id, name);
		}
	}
	
	public static List<Brand> getList(){
		return BrandListSingleton.getInstance().getBrands();
	}
	
	public static void printList() {
		BrandListSingleton.getInstance().getBrands().forEach(element -> {
			System.out.println(element.toString());
		});
	}
	
}
