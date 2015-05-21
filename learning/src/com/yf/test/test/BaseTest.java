package com.yf.test.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.mysql.jdbc.BufferRow;
import com.yf.util.ImageUtil;
import com.yf.util.byteUtil;

public class BaseTest {
	/**
	 * testD(java中Calendar 获取当前日期的前几天后几天的处理)
	 * 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	@Test
	public void testD() {
		System.out.println(new Date().toString());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println(new SimpleDateFormat("yyy-MM-dd").format(cal
				.getTime()));
		System.out
				.println(new SimpleDateFormat("yyy-MM-dd").format(new Date()));
	}

	/**
	 * testExec(java 调用系统的cmd命令：) 注意在：输入流的时候要进行转码否则会有中文乱码 BufferedReader br=new
	 * BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"));
	 * 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	@Test
	public void testExec() {
		Runtime run = Runtime.getRuntime();
		try {
			Process p = run.exec("ipconfig");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream(), "gbk"));
			StringBuilder sb = new StringBuilder();
			String inin;
			while (null != (inin = br.readLine())) {
				sb.append(inin).append("\n");
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStrToByte() {
		// String
		// s="18029365184,150128104941,1128677630,231330817,90,13300,0,0,500,0,0";
		// byte[] by= s.getBytes();
		byte[] b = new byte[] { 0x06, 0x00, 0x00, 0x00, 0x2A, 0x00, 0x2A, 0x00,
				0x50, 0x00, 0x10, 0x00, 0x00, 0x00, 0x30, 0x00, 0x30, 0x00,
				0x30, 0x00, 0x30, 0x00, 0x30, 0x00, 0x30, 0x00, 0x30, 0x00,
				0x34, 0x00, 0x3C, 0x00, 0x00, 0x00, 0x31, 0x00, 0x38, 0x00,
				0x39, 0x00, 0x32, 0x00, 0x32, 0x00, 0x31, 0x00, 0x37, 0x00,
				0x37, 0x00, 0x30, 0x00, 0x35, 0x00, 0x31, 0x00, 0x2D, 0x00,
				0x32, 0x00, 0x30, 0x00, 0x31, 0x00, 0x34, 0x00, 0x30, 0x00,
				0x37, 0x00, 0x31, 0x00, 0x39, 0x00, 0x31, 0x00, 0x33, 0x00,
				0x32, 0x00, 0x38, 0x00, 0x33, 0x00, 0x36, 0x00, 0x2E, 0x00,
				0x6A, 0x00, 0x70, 0x00, 0x67, 0x00, (byte) 0xD8, 0x1D, 0x03,
				0x00 };
		ImageUtil.writerImageTOdisk(b, "d:\\a.jpg");
	}

	@Test
	public void byteFile() {
		try {
			byte[] b = byteUtil.getByte(new File(
					"D:\\cache\\windows\\Desktop\\a.JPG"));
			String s = new String(b, "UTF-8");
			String s1 = new String(b, "ISO8859-1");
			ImageUtil.writerImageTOdisk(s1.getBytes(),
					"D:\\cache\\windows\\Desktop\\c.JPG");
			for (byte c : s.getBytes()) {
				System.out.println(c);
				;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 当期时间戳：
	 * 时间戳：
	 * 	　时间戳是自 1970 年 1 月 1 日（00:00:00 GMT）以来的秒数。它也被称为 Unix 时间戳（Unix Timestamp）。
	 * Unix时间戳(Unix timestamp)，或称Unix时间(Unix time)、POSIX时间(POSIX time)，
	 * 是一种时间表示方式，定义为从格林威治时间1970年01月01日00时00分00秒起至现在的总秒数。Unix时间戳不仅被使用在Unix系统、类Unix系统中，也在许多其他操作系统中被广泛采用。
	 * 		
	 * @return String
	 */
	public long timeStamp_System_currentTimeMillis(){
		return System.currentTimeMillis();
	}
	/**
	 * 获取时间戳
	 * @return
	 */
	public long timeStamp_Calendar(){
		return Calendar.getInstance().getTimeInMillis();
	}
	/**
	 * 获取时间戳
	 * @return long 类型
	 */
	public long timeStamp_Date(){
		return new Date().getTime();
	}
	
	
	
}
