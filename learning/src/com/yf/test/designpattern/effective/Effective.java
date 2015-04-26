package com.yf.test.designpattern.effective;

public class Effective {
	
	
	/**
	 * Effect java 考虑用静态方法代替构造方法
	 * 把boolean一个基本类型的值转换为Boolean的引用                 
	 * @param b true或者false
	 * @return Boolean类型的值
	 */
	public static Boolean valueOf(boolean b){
		return b ? Boolean.TRUE :Boolean.FALSE;
	}
	/**
	 * 所有的都是对象Object  Object中的hashcode()方法和equals()方法
	 *  equals在没有重写的情况下和==是一样的，对于值类型，比较的是值，对于引用类型则比较的是对象的首地址。
	 *    hashCode我们一般很少直接使用，它返回的是一个int值，在HashMap中对对象进行存储时，它会调用hashCode方法来比较两个对象是否相等。查询对象的时候也会调用hashCode以提高查询效率。
      一般来说equals方法比较相等，则hashCode一定相等，反过来不一定成立，因为具有相同的hashCode不一定是相同的对象。一个好的hashCode函数应该能做到为不同的对象产生不相等的hash值。
      
      如果我们对equals方法进行重写时，一般强烈建议对hashCode方法重写，以保证相同的对象返回相同的hash值，不同的对象返回不同的hash值。因为我们在使用HashMap、HashSet的时候会使用hashCode和equals来判断存入的是否是同一个对象。如果不重写hashCode，那么会继承Object中的，它返回的是一个对象的地址，对于两个对象，这个地址是永远不会相等。如果hashCode都不相等，就不会再调用equals方法进行比较了。
      当从HashSet集合中查找某个对象时，java系统首先会调用对象的hashCode()方法来获得该对象的哈希码，然后根据哈希码找到对应的存储区域，最后取得该存储区域内的每个元素与该对象进行equals方法比较。这样就不用遍历集合中的所有元素就可以得到结论，可见HashSet集合具有很好的对象检索性能。
 	  1、简单说==是判断变量指向的对象的内存地址是否相同     
      2、引用变量对应的内存地址中存放的是所引用对象的内存的首地址   
      3、equals方法是用于比较两个独立对象的内容是否相同，如果一个类没有覆写父类或Object类的equals，那这个equals方法和==没什么区别；如果覆写了父类或Object类的equals，那它按我们要求去实现，如：按人的编号、姓名首字母、年龄进行比较。

      equals覆写步骤：
      1) 用==比较两对象    
      2) 判断被比较对象是否为null    
      3) 不为null, 判断被比较对象是不是和比较对象类型相同或其子类，并进行类型转换(因为传进来的都是Object类型)      
      4) 进行属性比较
	 * @author mycode
	 *
	 */
	class object extends Object{
		
	}
	
	

	
	
	
	
	
	

}
