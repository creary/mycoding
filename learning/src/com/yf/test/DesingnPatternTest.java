package com.yf.test;

import org.junit.Test;

import com.yf.util.ResourcesUtil;
import com.yf.util.SingletonUtil;

public class DesingnPatternTest {

	
	@Test
	public void singleTonLazyTest(){
		/**
		 * 多次调用(多次new 也只会生成一个对象)
		 */
//		SingletonLazy singletonLazy=SingletonLazy.getInstance();
//		System.out.println(singletonLazy);
//		SingletonLazy singletonLazy1=SingletonLazy.getInstance();
//		System.out.println(singletonLazy1);
		
//		System.out.println(sUtil.getFileInputS());
		
		System.out.println(ResourcesUtil.getVbyKey("test"));
		
	}
}