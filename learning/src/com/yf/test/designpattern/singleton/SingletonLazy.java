package com.yf.test.designpattern.singleton;

public class SingletonLazy {
	private static SingletonLazy single=null;
	private SingletonLazy(){
		System.out.println("单利模式Lazy");
	}
	public static SingletonLazy getInstance(){
		if(single==null){
			synchronized (SingletonLazy.class) {
				single=new SingletonLazy();
			}
		}
		return single;
	}
}
