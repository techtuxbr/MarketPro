package exceptions;

public class OrderNotFound extends Exception{
    public OrderNotFound() {
        super();
    }

    public OrderNotFound(String s) {
        super(s);
    }

    public OrderNotFound(String s, Throwable throwable) {
        super(s, throwable);
    }

    public OrderNotFound(Throwable throwable) {
        super(throwable);
    }

    protected OrderNotFound(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
