package facade;

import exceptions.NullData;
import exceptions.OrderIDCollision;
import exceptions.OrderNotFound;
import helper.FileHelper;
import helper.HashHelper;
import model.Order;
import model.OrderItem;
import observable.OrderListObserver;
import singleton.OrderListSingleton;

import java.util.Calendar;
import java.util.List;

public class OrderFacade {

    private static OrderListObserver observer;

    public static void allocateOrders(){
        FileHelper<Order> fh = new FileHelper<Order>("orders.bin");
        OrderListSingleton.getInstance().setOrders(fh.readData());
        observer = new OrderListObserver(OrderListSingleton.getInstance());
    }

    public static void register(Order o) throws OrderIDCollision, NullData {
        if(o == null) {
            throw new NullData("A ordem que você está tentando criar é nula");
        }
        Order oTemp = OrderListSingleton.getInstance().getOrderByID(o.getId());
        if(oTemp == null) {
            OrderListSingleton.getInstance().insert(o);
        }else {
            throw new OrderIDCollision("A categoria que você está tentando criar tem o mesmo ID de uma categoria já cadastrada\nInformações:\n Categoria cadastrada: " + oTemp.toString() + " Categoria à cadastrar: " + o.toString());
        }
    }

    public static Order get(String id) throws NullData {
        if(id == null) {
            throw new NullData("O seu parametro id é nulo");
        }
        return OrderListSingleton.getInstance().getOrderByID(id);
    }

    public static Order remove(String id) throws NullData{
        Order oTemp;
        if(id == null) {
            throw new NullData("O seu parametro id é nulo");
        }
        oTemp = OrderListSingleton.getInstance().getOrderByID(id);
        OrderListSingleton.getInstance().removeByID(id);
        return oTemp;
    }

    public static void update(String id, Calendar date, List<OrderItem> itemList) throws NullData, OrderNotFound {
        if(id == null) {
            throw new NullData("O seu parametro id é nulo");
        }else if(date == null) {
            throw new NullData("O seu parametro date é nulo");
        }else if(itemList == null) {
            throw new NullData("O seu parametro itemList é nulo");
        }
        if(OrderListSingleton.getInstance().getOrderByID(id) == null) {
            throw new OrderNotFound("Ordem não existente, impossível atualizar! verifique o ID!");
        }else {
            OrderListSingleton.getInstance().updateByID(id,date,itemList);
        }
    }

    public static List<Order> getList(){
        return OrderListSingleton.getInstance().getOrders();
    }

    public static void printList() {
        OrderListSingleton.getInstance().getOrders().forEach(element -> {
            System.out.println(element.toString());
        });
    }
}
