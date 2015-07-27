package com.yf.code.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yf.util.ResourcesUtil;

public class TomServletContextListener implements ServletContextListener {
	
	static final Logger log=LoggerFactory.getLogger(TomServletContextListener.class);

	@Override  
    public void contextDestroyed(ServletContextEvent arg0) {
		//生成环境下  ---  不频繁重启的情况下来监听 tomcat 
		if(ResourcesUtil.getVbyKey("listenerTomcat").equals("true")){
		}
		log.error("tomcat被异常关闭");
    }  
	
    @Override  
    public void contextInitialized(ServletContextEvent arg0) {  
        System.out.println("tomcate启动成功");
    } 
    
}
