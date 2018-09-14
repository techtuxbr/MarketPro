package exceptions;

public class WrongUsername extends Exception{
	public WrongUsername() {super();}
	public WrongUsername(String message) { super(message); }
	public WrongUsername(String message, Throwable cause) { super(message, cause); }
	public WrongUsername(Throwable cause) { super(cause);}
}
