package observable;

import java.util.Observable;
import java.util.Observer;

import helper.FileHelper;
import model.Category;
import model.Item;

public class ItemListObserver implements Observer {

	private ItemListObservable observable;
	
	public ItemListObserver(ItemListObservable observable) {
		this.observable = observable;
		this.observable.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		FileHelper<Item> fh = new FileHelper<Item>("items.bin");
		fh.writeData(this.observable.getItems());
	}

}
