package com.yf.test;

import com.yf.db.CutTable;
import com.yf.util.DateUtil;
import com.yf.util.ResourcesUtil;

public class DBTest {
public static void main(String[] args) {
	
	System.out.println(ResourcesUtil.getVbyKey("username"));
System.out.println(CutTable.isTabHere("test", "bpbaselogtbl"));
System.out.println(DateUtil.getCurrentTabDay());
}
}
