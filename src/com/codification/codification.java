package com.codification;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class codification {

	
private String md5(String plaintext){
	 String hashtext = "";
	try {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
	    hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
	} catch (NoSuchAlgorithmException e) {
		hashtext = null;
	//	e.printStackTrace();
	}
	return hashtext;
}
	


public  String cd_prs(String str){
	String code = md5(str);
	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		code = (""+Integer.toHexString((int)(f_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase();
	}
	return code;
}


}
