package facade;

import helper.FileHelper;
import model.Item;
import singleton.ItemListSingleton;

public class ItemFacade {
	public static void allocateItems() {
		FileHelper<Item> fh = new FileHelper<Item>("items.bin");
		ItemListSingleton.getInstance().setItems(fh.readData());
	}
}
