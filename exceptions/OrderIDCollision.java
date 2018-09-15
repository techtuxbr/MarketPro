package exceptions;

public class OrderIDCollision extends Exception{
    public OrderIDCollision() {
        super();
    }

    public OrderIDCollision(String s) {
        super(s);
    }

    public OrderIDCollision(String s, Throwable throwable) {
        super(s, throwable);
    }

    public OrderIDCollision(Throwable throwable) {
        super(throwable);
    }

    protected OrderIDCollision(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
