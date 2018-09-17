package observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import factory.ItemFactory;
import model.Brand;
import model.Category;
import model.Item;
import model.SaleType;

public class ItemListObservable extends Observable {

	private List<Item> items = new ArrayList<Item>();
	private ItemFactory itemFactory = new ItemFactory();
		
	public void setItems(List<Item> items) {
		if(this.items != null) {
			this.items = items;
		}else {
			return;
		}
	}
	
	public List<Item> getItems() {
		List<Item> tempItems = new ArrayList<Item>();
		for(int i = 0; i < this.items.size();i++) {
			Item iTemp = this.items.get(i);
			Item it;
			it = this.itemFactory.create(iTemp.getName(),iTemp.getBarCode(),iTemp.getBrand(),iTemp.getInStock(),iTemp.getPrice(),iTemp.getType());
			tempItems.add(it);
		}
		return tempItems;
	}
	
	public List<Item> getItemsByBrand(String brand) {
		List<Item> items = new ArrayList<Item>();
		for(int i = 0; i < this.items.size();i++) {
			if(this.items.get(i).getBrand().getName().equalsIgnoreCase(brand)) {
				items.add(this.items.get(i));
			}
		}
		return items;
	}

	public Item getItemByBarcode(String barcode) {
		for(int i = 0; i < this.items.size();i++) {
			if(this.items.get(i).getBarCode().equals(barcode)) {
				return this.items.get(i);
			}
		}
		return null;
	}

	public void decrementStock(String barcode,int amount){
		Item i = this.getItemByBarcode(barcode);
		if(i != null){
			i.setInStock(i.getInStock()-amount);
			setChanged();
			notifyObservers();
		}
	}

	public void incrementStock(String barcode,int amount){
		Item i = this.getItemByBarcode(barcode);
		if(i != null){
			i.setInStock(i.getInStock()+amount);
			setChanged();
			notifyObservers();
		}
	}
	
	public void removeByBarcode(String barcode) {
			Item i = this.getItemByBarcode(barcode);
			if(i != null) {
				this.items.remove(this.items.indexOf(i));
		        setChanged();
		        notifyObservers();	
			}
	}
	
	public void updateByBarcode(String barcode, String name, Brand brand, int inStock, float price, Category type) {
		Item i = this.getItemByBarcode(barcode);
		if(i != null) {
			Item iTemp = this.items.get(this.items.indexOf(i));
			iTemp.setName(name);
			iTemp.setBrand(brand);
			iTemp.setInStock(inStock);
			iTemp.setPrice(price);
			iTemp.setType(type);
	        setChanged();
	        notifyObservers();	
		}
	}
	
	public void insert(Item iTemp) {
		Item i;
		if(iTemp != null) {
			i = this.itemFactory.create(iTemp.getName(),iTemp.getBarCode(),iTemp.getBrand(),iTemp.getInStock(),iTemp.getPrice(),iTemp.getType());
			this.items.add(i);
			setChanged();
			notifyObservers();
		}
	}
	
	public void clear() {
		this.items.clear();
		setChanged();
		notifyObservers();
	}

}
