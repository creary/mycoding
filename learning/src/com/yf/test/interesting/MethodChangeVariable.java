package com.yf.test.interesting;

public class MethodChangeVariable {

		public int a=1;
		public static void main(String[] args) {
			MethodChangeVariable ms=new MethodChangeVariable();
			System.out.println(new MethodChangeVariable().mem(ms).a);
			
		}
	
		public MethodChangeVariable mem(MethodChangeVariable m){
			m.a=2;
			return m;
		}
	
		
}
