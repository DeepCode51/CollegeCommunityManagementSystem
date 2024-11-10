package com.so.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5 {
	public static String Encrypt(String str){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] s=md.digest(str.getBytes());
			String ss="";
			String result="";
			for(int i=0;i<s.length;i++){
				ss=Integer.toHexString(s[i] & 0xff);
				if(ss.length()==1){
					result+="0"+ss;
				}else{
					result+=ss;
				}
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
