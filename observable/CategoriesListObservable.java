package observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import factory.CategoryFactory;
import model.Category;
import model.SaleType;

public class CategoriesListObservable extends Observable {
	private List<Category> categories = new ArrayList<Category>();
	private CategoryFactory categoryFactory = new CategoryFactory();
		
	public void setCategories(List<Category> categories) {
		if(this.categories != null) {
			this.categories = categories;
		}else {
			return;
		}
	}
	
	public List<Category> getCategories() {
		List<Category> tempCategories = new ArrayList<Category>();
		for(int i = 0; i < this.categories.size();i++) {
			Category cTemp = this.categories.get(i);
			Category c;
			c = this.categoryFactory.create(cTemp.getId(), cTemp.getName(),cTemp.getSaleType());
			tempCategories.add(c);
		}
		return tempCategories;
	}
	
	public Category getCategoryByID(String id) {
		for(int i = 0; i < this.categories.size();i++) {
			if(this.categories.get(i).getId().equals(id)) {
				return this.categories.get(i);
			}
		}
		return null;
	}
	
	public void removeByID(String id) {
			Category c = this.getCategoryByID(id);
			if(c != null) {
				this.categories.remove(this.categories.indexOf(c));
		        setChanged();
		        notifyObservers();	
			}
	}
	
	public void updateByID(String id, String name, SaleType saleType) {
		Category c = this.getCategoryByID(id);
		if(c != null) {
			Category cTemp = this.categories.get(this.categories.indexOf(c));
			cTemp.setName(name);
			cTemp.setSaleType(saleType);
	        setChanged();
	        notifyObservers();	
		}
	}
	
	public void insert(Category cTemp) {
		Category c;
		if(cTemp != null) {
			c = this.categoryFactory.create(cTemp.getId(),cTemp.getName(), cTemp.getSaleType());
			this.categories.add(c);
			setChanged();
			notifyObservers();
		}
	}
	
	public void clear() {
		this.categories.clear();
		setChanged();
		notifyObservers();
	}
}
