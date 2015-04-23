package com.yf.test.designpattern.effective;

import org.junit.Test;

public class Lesson1 {
	/**
	 * 创建对象：
	 * 1.new Person（）这种是通过对象的共有的构造方法来创建对象
	 * 2.使用类的静态方法返回对象的实例  Person.newInstance();
	 * 			(Effect java中说明用静态方法代替共有构造方法来创建对象）
	 */
@Test
public void effect1(){
		Lesson1 le1=ObjectFactory.getInstance();

	}
}
