package factory;

import model.Brand;

public interface BrandCreator {
	public Brand create(String name);
	public Brand create(String id, String name);
}
