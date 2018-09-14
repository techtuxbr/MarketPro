package observable;

import java.util.Observable;
import java.util.Observer;

import helper.FileHelper;
import model.Category;
import model.User;

public class CategoriesListObserver implements Observer{
	
	private CategoriesListObservable observable; 
	
	public CategoriesListObserver(CategoriesListObservable observable) {
		this.observable = observable;
		this.observable.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		FileHelper<Category> fh = new FileHelper<Category>("categories.bin");
		fh.writeData(this.observable.getCategories());
	}

}
