package com.yf.util.hutool.bloomFilter.filter;


import com.yf.util.hutool.Hashs;


public class TianlFilter extends AbstractFilter {

	public TianlFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public TianlFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return Hashs.tianlHash(str) % size;
	}

}
