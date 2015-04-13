package com.yf.test.designpattern.simplefactory;

public class Factory {
	
	
 public  IProduct  createProduct(String A){
	 
	 if(A=="A"){
		 return new  ProductA();
		 
	 }else if(A=="B"){
		 return new ProductB();
	 }
	return null;
 }
}
