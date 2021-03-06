package com.yf.test.designpattern.effective;

public class EffectJAVA {
	/**
	 * 考虑用静态工厂代替构造器;
	 * 创建对象的方式：
	 * 	1.使用类的共有构造器
	 * 	2.使用类的静态方法返回类的实例
	 * 静态方法的有点
	 * 		【1.方法名可以自定义{类的共有构造方法必须和类名相同}】
	 * 		2.构造方法每一次调用都会去创建一个对象 【静态方法只在第一次调用的时候创建对象】
	 * 		3.静态方法可以返回类型的任何子类对象
	 * public static Father newInstance(String type) {  
        if (type.equals("ChildA")) { // 根据类型判断返回那个子类对象  
            return new ChildA();  
        } else {  
            return new ChildB();  
        }  
    	}  
    4. 静态工厂方法在创建参数化类型实例的时候，可以使代码变得更加简洁。
    使用静态工厂方法创建对象有什么缺点?
    缺点1: 如果一个类只能通过静态工厂方法来获得实例，那么该类的构造函数就不能是共有的或受保护的，那么该类就不会有子类，即该类不能被继承。单例模式中首先要私有化构造器。
       缺点2：静态工厂方法和其他静态方法从名字上看无法区分，所以我们可以约定静态工厂方法名字使用valueOf或者getInstance。
④. 什么时候用静态工厂方法，什么时候用构造器呢?
    静态工厂方法和共有构造器各有用处，我们需要理解他们各自的长处，静态工厂通常更加合适，因此切忌第一反应就是提供公有的构造器，而不先考虑静态工厂。
	 */

	
	
	
}
