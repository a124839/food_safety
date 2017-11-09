package com.ichinait.food.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.codec.Hex;

public class DigestUtil {
	private static final String MD5="md5";
	
	
	public static String md5(String in){
		
		try {
			MessageDigest digest = MessageDigest.getInstance(MD5);
			digest.update(in.getBytes());
			byte[] bitAry = digest.digest();
			return new String(Hex.encode(bitAry));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtil.md5("123"));
	}
	

}
