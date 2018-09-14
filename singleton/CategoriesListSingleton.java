package singleton;

import observable.CategoriesListObservable;

public class CategoriesListSingleton extends CategoriesListObservable {

	private static CategoriesListSingleton instance;
	private CategoriesListSingleton() {
		super();
	}
	
	public static CategoriesListSingleton getInstance() {
		if(instance == null) {
			instance = new CategoriesListSingleton();
			return instance;
		}
		
		return instance;
	}
}
