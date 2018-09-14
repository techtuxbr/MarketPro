package facade;

import helper.FileHelper;
import model.Brand;
import model.Category;
import singleton.BrandListSingleton;

public class BrandFacade {
	
	public static void allocateBrands() {
		FileHelper<Brand> fh = new FileHelper<Brand>("brands.bin");
		BrandListSingleton.getInstance().setBrands(fh.readData());
	}
	
}
