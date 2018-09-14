package singleton;

import observable.BrandListObservable;

public class BrandListSingleton extends BrandListObservable {

	private static BrandListSingleton instance;
	private BrandListSingleton() {
		super();
	}
	
	public static BrandListSingleton getInstance() {
		if(instance == null) {
			instance = new BrandListSingleton();
			return instance;
		}
		return instance;
	}
}
