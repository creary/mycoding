package com.yf.test.method.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class Shiro {

	
	@Test
	public void testShiro(){
		/*获得初始化inisecuritManager工厂 */
		Factory<SecurityManager>  factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager sm=	 factory.getInstance();
		SecurityUtils.setSecurityManager(sm);
		
		Subject  su=SecurityUtils.getSubject();
		UsernamePasswordToken spwt=new UsernamePasswordToken("user","liting");
		su.login(spwt);
		
		
		
		
		
	}

}
