package facade;

import java.util.List;

import exceptions.CategoryIDCollision;
import exceptions.InvalidNegativeValue;
import exceptions.ItemNotFound;
import exceptions.NullData;
import exceptions.ProductIDCollision;
import helper.FileHelper;
import model.Brand;
import model.Category;
import model.Item;
import model.SaleType;
import observable.ItemListObserver;
import singleton.CategoriesListSingleton;
import singleton.ItemListSingleton;

public class ItemFacade {
	private static ItemListObserver observer;
	public static void allocateItems() {
		FileHelper<Item> fh = new FileHelper<Item>("items.bin");
		ItemListSingleton.getInstance().setItems(fh.readData());
		observer = new ItemListObserver(ItemListSingleton.getInstance());
	}
	
	public static void register(Item i) throws ProductIDCollision, NullData{
		if(i == null) {
			throw new NullData("O item que voc� est� tentando criar � nulo");
		}
		Item iTemp = ItemListSingleton.getInstance().getItemByBarcode(i.getBarCode());
		if(iTemp == null) {
			ItemListSingleton.getInstance().insert(i);
		}else {
			throw new ProductIDCollision("O produto que voc� est� tentando criar tem o mesmo ID de um produto j� cadastrado\nInforma��es:\n Produto cadastrado: " + iTemp.toString() + " P � cadastrar: " + i.toString());
		}
	}
	
	public static Item get(String id) throws NullData {
		if(id == null) {
			throw new NullData("O seu parametro id é nulo");
		}
		return ItemListSingleton.getInstance().getItemByBarcode(id);
	}

	public static void decrementStock(String barcode, int amount){
		ItemListSingleton.getInstance().decrementStock(barcode,amount);
	}

	public static void incrementStock(String barcode,  int amount){
		ItemListSingleton.getInstance().incrementStock(barcode,amount);
	}

	public static List<Item> getByBrand(String brand) throws NullData {
		if(brand == null) {
			throw new NullData("O seu parametro id é nulo");
		}
		return ItemListSingleton.getInstance().getItemsByBrand(brand);
	}
	
	public static Item remove(String id) throws NullData{
		Item iTemp;
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}
		iTemp = ItemListSingleton.getInstance().getItemByBarcode(id);
		ItemListSingleton.getInstance().removeByBarcode(id);
		return iTemp;
	}
	
	public static void update(String id, String name, Brand brand,int inStock, float price, Category type) throws NullData, ItemNotFound, InvalidNegativeValue{
		if(id == null) {
			throw new NullData("O seu parametro id � nulo");
		}else if(name == null) {
			throw new NullData("O seu parametro name � nulo");
		}else if(type == null) {
			throw new NullData("O seu parametro Categoria � nulo");
		}else if(inStock < 0) {
			throw new InvalidNegativeValue("O valor do estoque n�o pode ser negativo");
		}else if(price < 0) {
			throw new InvalidNegativeValue("O valor do pre�o n�o pode ser negativo");
		}
		
		if(ItemListSingleton.getInstance().getItemByBarcode(id) == null) {
			throw new ItemNotFound("Produto n�o existente, imposs�vel atualizar! verifique o ID!");
		}else {
			ItemListSingleton.getInstance().updateByBarcode(id, name, brand, inStock, price, type);
		}
	}
	
	public static List<Item> getList(){
		return ItemListSingleton.getInstance().getItems();
	}
	
	public static void printList() {
		ItemListSingleton.getInstance().getItems().forEach(element -> {
			System.out.println(element.toString());
		});
	}
}
