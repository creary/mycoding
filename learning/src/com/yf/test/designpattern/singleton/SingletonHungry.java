package com.yf.test.designpattern.singleton;

public class SingletonHungry {
	private static SingletonHungry singleton=new SingletonHungry();
	private SingletonHungry(){
		System.out.println("Singleton construct ");
	}
	public static synchronized SingletonHungry getInstance(){
		
		return singleton;
	}

}
