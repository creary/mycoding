package com.yf.test.base.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRemove {
/**
 * list 元素的删除问题：
 */
	public static void main(String[] args) {/*
//list 删除元素 下标移动 没有达到预定效果
		List<Object> arrL=new ArrayList<Object>();
		arrL.add(1);
		arrL.add(2);
		arrL.add(2);
		arrL.add(3);
		arrL.add(4);
		for(int i=0;i<arrL.size();i--){
			if(arrL.get(i).equals(2)){
				arrL.remove(i);
			}
			//由于删除一个2之后元素下标移动 所有没有达到想要的效果 输出结果是 1   2   3    4 
			System.out.println(arrL.get(i));
		}
	*/
		
		List<Object> arrL=new ArrayList<Object>();
		arrL.add(1);
		arrL.add(2);
		arrL.add(2);
		arrL.add(3);
		arrL.add(4);
		Iterator<Object> iter=	arrL.iterator();
		while(iter.hasNext()){
			Object i=iter.next();
			if(i.equals(2)){
				iter.remove();
			}
		}
		for (Object object : arrL) {
			System.out.println(object);
		}
	}
}
