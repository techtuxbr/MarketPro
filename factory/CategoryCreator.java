package factory;

import model.Category;
import model.SaleType;

public interface CategoryCreator {

	public Category create(String name, SaleType saleType);
	public Category create(String id, String name, SaleType saleType);
	
}
