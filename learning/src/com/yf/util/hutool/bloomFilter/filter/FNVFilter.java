package com.yf.util.hutool.bloomFilter.filter;


import com.yf.util.hutool.Hashs;

public class FNVFilter extends AbstractFilter {

	public FNVFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public FNVFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return Hashs.FNVHash(str);
	}

}
