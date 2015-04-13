package com.yf.test.designpattern.retional;

public class Dependence {
	/**
	 * 依赖关系的定义为：对于两个相对独立的对象，当一个对象负责构造另一个对象的实例，或者依赖另一个对象的服务时，这两个对象之间主要体现为依赖关系。
	 * 定义比较晦涩难懂，但在java中的表现还是比较直观的：类A当中使用了类B，其中类B是作为类A的方法参数、方法中的局部变量、或者静态方法调用。
	 * 
	 */
	public void read(Book b){
		
		
	}
	
}
class Book{}