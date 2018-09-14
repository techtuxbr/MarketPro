package singleton;

import observable.ItemListObservable;

public class ItemListSingleton extends ItemListObservable {

	private static ItemListSingleton instance;
	private ItemListSingleton() {
		super();
	}
	
	public static ItemListSingleton getInstance() {
		if(instance == null) {
			instance = new ItemListSingleton();
			return instance;
		}
		return instance;
	}
}
