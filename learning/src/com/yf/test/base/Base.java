package com.yf.test.base;

import java.io.IOException;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;

import com.yf.util.DBUtilApache;
import com.yf.util.hutool.Log;
import com.yf.util.mail.MailUtil;
import com.yf.util.net.IpUtil;
import com.yf.util.quartz.QuartzManager;
import com.yf.util.quartz.job.QuartzUtils;
import com.yf.util.quartz.jobean.ScheduleJob;

public class Base {
	
	static Logger log=Log.get();
	@Test
	public void effectjava(){
		String a="abc";
		String b="abcde";
		//问 a是否是b的字串
		System.out.println(b.contains(a));;//如果返回true就表示b中含有a 也就是a是b的子串
	}
	
	/**
	 * 累加算法：
	 * 实现求sum
	 * sum = 1+2+3+4......+n;
	 */
	public static long cycle(long value){
		long sum=0;
		for(long i=1,v=value;i<v;i++){
			sum+=i;
		}
		return sum;
	}
	/**
	 * 高斯算法实现数字累加：
	 * @param value  1到n  value就是n
	 *  * 实现求sum
	 * sum = 1+2+3+4......+n;
	 * @return  sum  
	 */
	public static long gaosi(long value){
		long sum=0;
		sum=(value+1)*value/2;
		return sum;
	 }
	@Test
	public void testFormat(){
		SimpleDateFormat sdf=new SimpleDateFormat("YYYMMdd");
		System.out.println(sdf.format(new Date()));
	}
	@Test
	public void testZhuanyi(){
		System.out.println(" 我是 \n 回车");
		System.out.println(" 我是 \t 水平制表符");
		System.out.println(" 我是 \b 空格");
		System.out.println(" 我是 \r 换行");
		System.out.println(" 我是 \f 换页");
	}
	
	@Test
	public void selectTest(){
		System.out.println(DBUtilApache.queryArrayList(DBUtilApache.openConn("mysql", "localhost", "3306", "test", "root", "ifidc2403"), "select * from bpbaselogtbl",null).size());;
	}
	
	@Test
	public void updateTest(){
		System.out.println(DBUtilApache.update(DBUtilApache.openConn("mysql", "localhost", "3306", "test", "root", "ifidc2403"), "INSERT into sys_icon(mark,type) value ('11','22');",null));;
	}
	
	@Test
	public void iptest(){
		try {
			System.out.println(IpUtil.getRealIp());
			System.out.println(IpUtil.getAddress());
			Calendar cal=Calendar.getInstance();
			System.out.println(cal.get(Calendar.YEAR)+"_"+Calendar.MONTH);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTime(){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy_MM_dd");
		System.out.println(sd.format(new Date()));//data类型转化String类型
		try {
			System.out.println(sd.parse("2014_11_11")) ;//String类型转化为Data类型
			Calendar cal=Calendar.getInstance();
			cal.setTime(sd.parse("2014_11_11"));
			} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testImag(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(new Date()));
	}
	@Test
	public void testMail(){
		try {
			MailUtil.sendMail("1152695512@qq.com", "主题", "<html></html>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testQuartz() throws SchedulerException{
		QuartzUtils qu=new QuartzUtils();
		List<ScheduleJob> ls=qu.getAllJob();
		for (ScheduleJob iterable_element : ls) {
			System.out.println(iterable_element);
		}
	}
	
	@Test
	public void testM(){
	int m=0;
	for (int i = 0; i < 100; i++) {
		m=m++;
	}
	System.out.println(m);
	}
	@Test
	public void testAbstract(){
		
	}
}

