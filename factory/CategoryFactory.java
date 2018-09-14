package factory;

import java.util.UUID;

import model.Category;
import model.SaleType;

public class CategoryFactory implements CategoryCreator{

	@Override
	public Category create(String name, SaleType saleType) {
		return new Category(UUID.randomUUID().toString(),name,saleType);
	}

	@Override
	public Category create(String id, String name, SaleType saleType) {
		return new Category(id,name,saleType);
	}

}
