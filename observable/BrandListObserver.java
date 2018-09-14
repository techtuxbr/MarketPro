package observable;

import java.util.Observable;
import java.util.Observer;

import helper.FileHelper;
import model.Brand;
import model.Category;

public class BrandListObserver implements Observer {

	private BrandListObservable observable; 
	
	public BrandListObserver(BrandListObservable observable) {
		this.observable = observable;
		this.observable.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Algo aconteceu na lista");
		FileHelper<Brand> fh = new FileHelper<Brand>("brands.bin");
		fh.writeData(this.observable.getBrands());
	}

}
