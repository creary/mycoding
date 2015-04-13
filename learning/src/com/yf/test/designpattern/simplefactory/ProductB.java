package com.yf.test.designpattern.simplefactory;

public class ProductB implements IProduct {

	@Override
	public void doProduct() {
		System.out.println("创建产品B");
	}

}
