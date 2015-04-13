package com.yf.util.hutool.bloomFilter.filter;


import com.yf.util.hutool.Hashs;

/**
 * 默认Blimport com.yf.util.hutool.Hashs;
oolly
 *
 */
public class DefaultFilter extends AbstractFilter {

	public DefaultFilter(long maxValue, int MACHINENUM) {
		super(maxValue, MACHINENUM);
	}
	
	public DefaultFilter(long maxValue) {
		super(maxValue);
	}
	
	@Override
	public long hash(String str) {
		return Hashs.javaDefaultHash(str) % size;
	}
}
