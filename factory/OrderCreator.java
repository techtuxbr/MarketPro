package factory;

import java.util.Calendar;
import java.util.List;

import model.Order;
import model.OrderItem;

public interface OrderCreator {
	public Order create(Calendar date, List<OrderItem> itemList);
	public Order create(String id, Calendar date, List<OrderItem> itemList);
	public Order create(int day,int month,int year, List<OrderItem> itemList);
	public Order create(String id,int day,int month,int year, List<OrderItem> itemList);
}
