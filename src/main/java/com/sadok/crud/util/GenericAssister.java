package com.sadok.crud.util;

import java.util.stream.Stream;

public class GenericAssister {
	
	private void GenericAssiter() {
		
	}
	
	public static boolean isNotStringEmpty(String str) {
		return str !=null && str.trim().length()!=0;
	}
	
	public static boolean isStringEmpty(String str) {
		return str ==null || str.trim().length()==0;
	}
	
	public static boolean isAnyStringEmpty(String... strs) {
		for(String str:strs) {
			if(isStringEmpty(str)) {
				return true;
			}
		}
		return false;
	}

}
