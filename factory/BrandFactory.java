package factory;

import java.util.UUID;

import model.Brand;

public class BrandFactory implements BrandCreator {
	@Override
	public Brand create(String name) {
		return new Brand(UUID.randomUUID().toString(),name);
	}

	@Override
	public Brand create(String id, String name) {
		return new Brand(id,name);
	}
}
