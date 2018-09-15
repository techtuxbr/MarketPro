package observable;

import helper.FileHelper;
import model.Order;

import java.util.Observable;
import java.util.Observer;

public class OrderListObserver implements Observer {

    private OrderListObservable observable;

    public OrderListObserver(OrderListObservable observable){
      this.observable = observable;
      this.observable.addObserver(this);
    }
    @Override
    public void update(Observable observable, Object o) {
        FileHelper fh = new FileHelper<Order>("orders.bin");
        fh.writeData(this.observable.getOrders());
    }

}
