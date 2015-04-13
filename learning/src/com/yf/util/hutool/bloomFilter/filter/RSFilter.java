package com.yf.util.hutool.bloomFilter.filter;


import com.yf.util.hutool.Hashs;

public class RSFilter extends AbstractFilter {

	public RSFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public RSFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return Hashs.RSHash(str) % size;
	}

}
