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
			throw new NullData("O item que você está tentando criar é nulo");
		}
		Item iTemp = ItemListSingleton.getInstance().getItemByBarcode(i.getBarCode());
		if(iTemp == null) {
			ItemListSingleton.getInstance().insert(i);
		}else {
			throw new ProductIDCollision("O produto que você está tentando criar tem o mesmo ID de um produto já cadastrado\nInformações:\n Produto cadastrado: " + iTemp.toString() + " P à cadastrar: " + i.toString());
		}
	}
	
	public static Item get(String id) throws NullData {
		if(id == null) {
			throw new NullData("O seu parametro id é nulo");
		}
		return ItemListSingleton.getInstance().getItemByBarcode(id);
	}
	
	public static Item remove(String id) throws NullData{
		Item iTemp;
		if(id == null) {
			throw new NullData("O seu parametro id é nulo");
		}
		iTemp = ItemListSingleton.getInstance().getItemByBarcode(id);
		ItemListSingleton.getInstance().removeByBarcode(id);
		return iTemp;
	}
	
	public static void update(String id, String name, Brand brand,int inStock, float price, Category type) throws NullData, ItemNotFound, InvalidNegativeValue{
		if(id == null) {
			throw new NullData("O seu parametro id é nulo");
		}else if(name == null) {
			throw new NullData("O seu parametro name é nulo");
		}else if(type == null) {
			throw new NullData("O seu parametro Categoria é nulo");
		}else if(inStock < 0) {
			throw new InvalidNegativeValue("O valor do estoque não pode ser negativo");
		}else if(price < 0) {
			throw new InvalidNegativeValue("O valor do preço não pode ser negativo");
		}
		
		if(ItemListSingleton.getInstance().getItemByBarcode(id) == null) {
			throw new ItemNotFound("Produto não existente, impossível atualizar! verifique o ID!");
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
