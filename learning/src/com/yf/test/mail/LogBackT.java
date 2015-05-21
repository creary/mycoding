package com.yf.test.mail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackT {
	final static Logger log= LoggerFactory.getLogger(LogBackT.class);
		@Test
		public void testLogBack(){
			log.info("Test logback 测试");
		}
		
		@Test
		public void testLogback(){
			log.error("测试发送邮件");
		}
		
}
