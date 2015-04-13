package com.yf.test.designpattern.singleton;

public class SingletonDoubleCheckVali {
		private SingletonDoubleCheckVali(){
			System.out.println("SingletonLazyDouCheck construct ");
		}
		private static volatile SingletonDoubleCheckVali instance=null;
		
		public static SingletonDoubleCheckVali getInstance(){
			
				if(instance==null){
					synchronized (SingletonDoubleCheckVali.class) {
						if(instance==null){
							instance=new SingletonDoubleCheckVali();
							}
						}
					}
				return instance;	
			}
	}
