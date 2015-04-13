package com.yf.test.designpattern.singleton;

public class SingletonHolder {
	/**
	 * 单利内部类的实现方法
	
	 * @Title:SingletonHolder
	
	 * @Description:TODO
	 */

	private SingletonHolder(){
		System.out.println(" SingletonHolder construct ");
	}
	private static class HolerClass {
		private static SingletonHolder singHolder=new SingletonHolder();
	}
	public static SingletonHolder getInstance(){
		
		return HolerClass.singHolder;
	}
}
