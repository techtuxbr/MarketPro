package helper;

import java.security.*;
import java.math.*;

public class HashHelper {

	public static String hashInMD5(String s) {
		   String hash = null;
	       MessageDigest m;
	       try {
	    	   m = MessageDigest.getInstance("MD5");
		       m.update(s.getBytes(),0,s.length());
		       hash = new BigInteger(1,m.digest()).toString(16);
	       } catch (NoSuchAlgorithmException e) {
	    	   e.printStackTrace();
	       }
	       return hash;
	}
	
	public static boolean comparePlainTextToMD5Hash(String plainPassword, String hash) {
		return (hashInMD5(plainPassword).equals(hash));
	}
}
