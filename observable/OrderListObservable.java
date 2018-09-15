package observable;

import factory.BrandFactory;
import factory.OrderFactory;
import model.Order;
import model.OrderItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

public class OrderListObservable extends Observable {
    private List<Order> orders = new ArrayList<Order>();
    private OrderFactory orderFactory = new OrderFactory();

    public void setOrders(List<Order> orders) {
        if(this.orders.size() < 1) {
            this.orders = orders;
        }else {
            return;
        }
    }

    public List<Order> getOrders() {
        List<Order> tempOrder = new ArrayList<Order>();
        for(int i = 0; i < this.orders.size(); i++) {
            Order oTemp = this.orders.get(i);
            Order o;
            o = this.orderFactory.create(oTemp.getId(),oTemp.getDate(),oTemp.getItemList());
            tempOrder.add(o);
        }
        return tempOrder;
    }

    public Order getOrderByID(String id) {
        for(int i = 0; i < this.orders.size(); i++) {
            if(this.orders.get(i).getId().equals(id)) {
                return this.orders.get(i);
            }
        }
        return null;
    }

    public void removeByID(String id) {
        Order o = this.getOrderByID(id);
        if(o != null) {
            this.orders.remove(this.orders.indexOf(o));
            setChanged();
            notifyObservers();
        }
    }

    public void updateByID(String id, Calendar date, List<OrderItem> itemList) {
        Order o = this.getOrderByID(id);
        if(o != null) {
            Order oTemp = this.orders.get(this.orders.indexOf(o));
            oTemp.setDate(date);
            oTemp.setItemList(itemList);
            setChanged();
            notifyObservers();
        }
    }

    public void insert(Order oTemp) {
        Order o;
        if(oTemp != null) {
            o = this.orderFactory.create(oTemp.getId(),oTemp.getDate(),oTemp.getItemList());
            this.orders.add(o);
            setChanged();
            notifyObservers();
        }
    }

    public void clear() {
        this.orders.clear();
        setChanged();
        notifyObservers();
    }
}
