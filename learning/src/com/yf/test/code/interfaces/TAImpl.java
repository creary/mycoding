package com.yf.test.code.interfaces;

public interface TAImpl extends TA {
	/**
	 * 对接口TA的扩展 TAImpl是新的接口 并且不会影响TA的功能实现
	 * 新的功能只需要实现TAImpl就可以了
	 */

	void getB();
	
}
