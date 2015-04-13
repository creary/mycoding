package com.yf.test.designpattern.effective;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {
	
	public static Lesson1 getInstance(){
		
		return new Lesson1();
	}

private static	Map<String, List<String>> map=new HashMap<String, List<String>>();
	public static <V, K> HashMap<K, V> newInstance(){
		return new HashMap<K, V>();
	}
	
	
}
