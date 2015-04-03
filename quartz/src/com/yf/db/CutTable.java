package com.yf.db;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yf.util.DBUtil;
import com.yf.util.DBUtilApache;
import com.yf.util.ResourcesUtil;

public class CutTable {
		private static Logger log=LoggerFactory.getLogger(CutTable.class);
		private static Connection conn=DBUtil.openConnection(ResourcesUtil.getVbyKey("driverClassName"),ResourcesUtil.getVbyKey("url"),ResourcesUtil.getVbyKey("username"),ResourcesUtil.getVbyKey("password"));
		public  static boolean isTabHere(String dbname,String taname){
		boolean s=true;
		Object[] queryArray = DBUtilApache.queryArray(conn, "select `TABLE_NAME` from `INFORMATION_SCHEMA`.`TABLES` where `TABLE_SCHEMA`='"+dbname+"' and `TABLE_NAME`='"+taname+"' ", null);
		if(queryArray.length<0){
				s=false;
		}
		log.info("数据库"+dbname+"  ： "+s +" : "+taname);
		return s;
	}
}
