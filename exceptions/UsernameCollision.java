package exceptions;

public class UsernameCollision extends Exception{
	public UsernameCollision() {super();}
	public UsernameCollision(String message) { super(message); }
	public UsernameCollision(String message, Throwable cause) { super(message, cause); }
	public UsernameCollision(Throwable cause) { super(cause);}
}
