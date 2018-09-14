package factory;

import model.Brand;
import model.Category;
import model.Item;

public interface ItemCreator {
	public Item create(String name, Brand brand, int inStock, float price, Category type);
	public Item create(String name, String barCode, Brand brand, int inStock, float price, Category type);
}
