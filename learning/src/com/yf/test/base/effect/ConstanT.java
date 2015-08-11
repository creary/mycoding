package com.yf.test.base.effect;

public class ConstanT {
	//私有构造方法 不允许子类进行实例化  自己实例化会跑出异常
	private ConstanT() throws Exception{
		throw new Exception();
	}
	public static void main(String[] args) throws Exception {
		System.out.println(new ConstanT());
	}
	//不允许实例化 没有子类  
	//调用他的类 不用new ConstanT对象 直接调用getCons()方法来获取Cons对象
	public static ConstanT getCons() throws Exception{
		 return  new ConstanT();
	}
}
