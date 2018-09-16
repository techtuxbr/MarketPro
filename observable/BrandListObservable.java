package observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import factory.BrandFactory;
import model.Brand;
import model.SaleType;

public class BrandListObservable extends Observable {
	private List<Brand> brands = new ArrayList<Brand>();
	private BrandFactory brandFactory = new BrandFactory();
		
	public void setBrands(List<Brand> brand) {
		if(this.brands.size() < 1) {
			this.brands = brand;
		}else {
			return;
		}
	}
	
	public List<Brand> getBrands() {
		List<Brand> tempBrand = new ArrayList<Brand>();
		for(int i = 0; i < this.brands.size();i++) {
			Brand bTemp = this.brands.get(i);
			Brand b;
			b = this.brandFactory.create(bTemp.getId(), bTemp.getName());
			tempBrand.add(b);
		}
		return tempBrand;
	}
	
	public Brand getBrandByID(String id) {
		for(int i = 0; i < this.brands.size();i++) {
			if(this.brands.get(i).getId().equals(id)) {
				return this.brands.get(i);
			}
		}
		return null;
	}

	public Brand getBrandByName(String name) {
		for(int i = 0; i < this.brands.size();i++) {
			if(this.brands.get(i).getName().equalsIgnoreCase(name)) {
				return this.brands.get(i);
			}
		}
		return null;
	}
	
	public void removeByID(String id) {
			Brand b = this.getBrandByID(id);
			if(b != null) {
				this.brands.remove(this.brands.indexOf(b));
		        setChanged();
		        notifyObservers();	
			}
	}
	
	public void updateByID(String id, String name) {
		Brand b = this.getBrandByID(id);
		if(b != null) {
			Brand bTemp = this.brands.get(this.brands.indexOf(b));
			bTemp.setName(name);
	        setChanged();
	        notifyObservers();	
		}
	}
	
	public void insert(Brand bTemp) {
		Brand b;
		if(bTemp != null) {
			b = this.brandFactory.create(bTemp.getId(),bTemp.getName());
			this.brands.add(b);
			setChanged();
			notifyObservers();
		}
	}
	
	public void clear() {
		this.brands.clear();
		setChanged();
		notifyObservers();
	}
}
