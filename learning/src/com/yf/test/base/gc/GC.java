package com.yf.test.base.gc;

import java.io.IOException;

import org.junit.Test;

public class GC {
	//垃圾回收：内存泄露---无法进行垃圾回收的无用对象 过多会造成内存泄露
		/**
		 * 
		* @Title: testGc 
		* @Description: TODO(避免内存泄露 避免短对象 被长对象引用) 
		* @param @throws Exception    设定文件  @test 注解必须加上
		* @return void    返回类型  junit测试没有返回对象
		* @throws
		 */
	@Test
	public void testGc() throws Exception{
		System.in.read();
		
	}
	
	

}
