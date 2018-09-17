package helper;

public class NumberHelper {
    public static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
    public static boolean isFloat( String input ) {
        try {
            Float.parseFloat( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
