/*package com.yf.mail.log4jmail;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
public class Log4jMailLayout extends Layout {
	 StringBuffer sbuf; 
	 public Log4jMailLayout(){
		 sbuf=new StringBuffer(1024);
	 }
	 @Override
	public String getContentType() {
		return "text/html;charset=utf-8";
	}
	@Override
	public void activateOptions() {
	}
	@Override
	public String format(LoggingEvent event) {
		sbuf.setLength(0);  
        sbuf.append("错误等级："+event.getLevel().toString()+"<br>");  
        sbuf.append("错误原因："+event.getMessage().toString()+"<br>");  
        sbuf.append("错误的类："+event.getLocationInformation().getClassName()+"<br>");  
        sbuf.append("错误方法："+event.getLocationInformation().getMethodName()+"<br>");  
        sbuf.append("错误位置："+event.getLocationInformation().getLineNumber()+"行");  
        return sbuf.toString();  
	}
	@Override
	public boolean ignoresThrowable() {
		return false;
	}
	*//**
	 * @author 李听
	 * 
	 *//*
}
*/