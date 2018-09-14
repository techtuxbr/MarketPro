package exceptions;

public class WrongPassword extends Exception{
	public WrongPassword() {super();}
	public WrongPassword(String message) { super(message); }
	public WrongPassword(String message, Throwable cause) { super(message, cause); }
	public WrongPassword(Throwable cause) { super(cause);}
}
