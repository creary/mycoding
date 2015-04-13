package com.yf.test.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;

import com.yf.util.hutool.db.ds.DruidDS;

/**
 * @author 
 *
 */
public class  DBTest{
	
	
	
	@Test
	public void testArr(){
		DataSource ds=DruidDS.getDataSource();
		System.out.println(ds);
		try {
	Connection ce=	ds.getConnection();
		System.out.println(ce);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testapacheDB(){
	try {
		DataSource ds=	 DruidDS.getDataSource();
		Connection conn=	ds.getConnection();
		QueryRunner qr=new QueryRunner();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
		
	}
	
	
	
}
