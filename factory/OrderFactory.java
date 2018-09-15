package factory;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import model.Order;
import model.OrderItem;

public class OrderFactory implements OrderCreator {

	public OrderFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Order create(Calendar date, List<OrderItem> itemList) {
		return new Order(UUID.randomUUID().toString(),date,itemList);
	}

	@Override
	public Order create(String id, Calendar date, List<OrderItem> itemList) {
		return new Order(id,date,itemList);
	}

	@Override
	public Order create(int day, int month, int year, List<OrderItem> itemList) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH,day);
		date.set(Calendar.MONTH,month);
		date.set(Calendar.YEAR,year);
		return new Order(UUID.randomUUID().toString(),date,itemList);
	}

	@Override
	public Order create(String id, int day, int month, int year, List<OrderItem> itemList) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH,day);
		date.set(Calendar.MONTH,month);
		date.set(Calendar.YEAR,year);
		return new Order(id,date,itemList);
	}

}
