package singleton;

import observable.OrderListObservable;

public class OrderListSingleton extends OrderListObservable {
    private static OrderListSingleton instance;

    private OrderListSingleton() {
        super();
    }

    public static OrderListSingleton getInstance() {
        if(instance == null){
            instance = new OrderListSingleton();
            return instance;
        }
        return instance;
    }

}
