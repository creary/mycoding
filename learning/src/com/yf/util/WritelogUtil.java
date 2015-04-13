package com.yf.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 书写日志信息到指定的文件中
 * @author liting
 * @since 2012-10-17 15:15:24
 */
public class WritelogUtil {

	private static String rootPath =( null!= ResourcesUtil.getVbyKey("log")) ? ResourcesUtil.getVbyKey("log") :"D:"  ; 
	
	/**
	 * 将信息写到文件中
	 * @param msg
	 */
	public static void writeMsgToFile(String msg) {
		//删除之前的文件
		delOldFile();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(getFileName(),true);
			Date today = new Date();
			String time = String.valueOf(today.getHours()) + ":" + String.valueOf(today.getMinutes()) + " " + String.valueOf(today.getSeconds());
			fileWriter.write("#" + time + "# [" + msg + "]" + "\r\n");
			fileWriter.flush();
		} catch (IOException e) {
			System.out.println("### 写日志到文件异常 ### >>> " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("### 关闭写日志的流异常 ### >>> " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	/**
	 * 删除之前的日志文件
	 * 
	 */
	private static void delOldFile() {
		Date today = new Date();
		int month = today.getMonth()+1;
		month = month - 2;
		if(month == -1)	month = 11;
		if(month == 0)	month = 12;
		String delPath = rootPath + String.valueOf(month) + "\\";
		File folder = new File(delPath);
		if(folder.exists()) {
			File[] files = folder.listFiles();
			for(int i=0; i<files.length; i++) {
				files[i].delete();
			}
		}
	}
	
	/**
	 * 获取要保存的文件
	 * @return fileName
	 */
	private static String getFileName() {
		Date today = new Date();
		String fileName = StringUtil.isValidObj(ResourcesUtil.getVbyKey("filename")) ? ResourcesUtil.getVbyKey("filename")+".log" :(String.valueOf((today.getYear()+1900)) + String.valueOf((today.getMonth()+1)) + String.valueOf(today.getDate()) + ".log");
		
		
		//创建目录
		File folder = new File(rootPath +((StringUtil.isValidObj(ResourcesUtil.getVbyKey("filename"))) ? ResourcesUtil.getVbyKey("filename") : "log") + "\\");
		if(!folder.exists()) {
			folder.mkdirs();
		}
		//创建文件
		File file = new File(fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("### 新建日志文件异常 ### >>> " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		fileName = rootPath +((StringUtil.isValidObj(ResourcesUtil.getVbyKey("filename"))) ? ResourcesUtil.getVbyKey("filename") : "log") + "\\" + fileName;
		
		return fileName;
	}
	
	/**
	 * 测试使用的main方法
	 */
	public static void main(String[] args) {
		//getFileName();
		String testString = "写日志咯：71FABB7890D2CC0D267FBD84F409618C0303BC597B9244C324947BDE4B1C0B4CB08C33FC461F7BADD088535DAE42D8D7D06F4134E442D9D1CE3A0F9B3EDD64337A2D18CE34FCDC137B7CBD84F409618C03038FEAEC79F79C2F58BD84F409618C03038FEAEC79F79C2F58BD84F409618C03038FEAEC79F79C2F581790ACB3C178641D14D8C09905BC52CF1C8249B12F2EDE5AC3C8FAF2FD8A686E";
		writeMsgToFile(testString);
		//delOldFile();
	}

}
