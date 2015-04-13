package com.yf.test.designpattern.singleton;

public class SingletonLazyDouCheck {
	
	private SingletonLazyDouCheck(){
		System.out.println("SingletonLazyDouCheck construct ");
	}
	private static SingletonLazyDouCheck instance=null;
	
	public static SingletonLazyDouCheck getInstance(){
		
		if(instance==null){
			synchronized (SingletonLazyDouCheck.class) {
				SingletonLazyDouCheck temp=instance;
				if(temp==null){
					temp=new SingletonLazyDouCheck();
					instance=temp;
				}
			}
		}
		return instance;
	}
}
