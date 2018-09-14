package factory;

import java.util.UUID;

import model.Brand;
import model.Category;
import model.Item;

public class ItemFactory implements ItemCreator{
	@Override
	public Item create(String name, Brand brand, int inStock, float price, Category type) {
		return new Item(name,UUID.randomUUID().toString(),brand,inStock,price,type);
	}
	@Override
	public Item create(String name, String barCode, Brand brand, int inStock, float price, Category type) {
		return new Item(name,barCode,brand,inStock,price,type);
	}
}
