package com.yf.test.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.yf.util.ResourcesUtil;

public class SpringMail {
@Test
public void test(){
	
	System.out.println(ResourcesUtil.getVbyKey("mailhost"));
}
	
	
	@Test
	public void send(){
		JavaMailSenderImpl mailSender =new JavaMailSenderImpl();
		
		mailSender.setHost(ResourcesUtil.getVbyKey("mailhost"));
		mailSender.setPort(Integer.parseInt(ResourcesUtil.getVbyKey("mailport")));
		mailSender.setUsername(ResourcesUtil.getVbyKey("username"));
		mailSender.setPassword(ResourcesUtil.getVbyKey("password"));
		mailSender.setDefaultEncoding(ResourcesUtil.getVbyKey("encode"));
		// 配置验证
		Properties pro=new Properties();
		pro.put("mail.smtp.auth", true);
		Session session=Session.getInstance(pro);
		
		mailSender.setSession(session);
		MimeMessage mai=mailSender.createMimeMessage();
		try {
			MimeMessageHelper mailhelp=new MimeMessageHelper(mai,true);
			mailhelp.setTo(ResourcesUtil.getVbyKey("sendto"));
			mailhelp.setSubject("woshi 我是 ");
			mailhelp.setFrom(ResourcesUtil.getVbyKey("mailfrom"));
			mailhelp.setText("<font color='red'>强哥邀请你访问我的博客：http://cuisuqiang.iteye.com/！</font><br><img src='cid:myImg'>",true);
			//增加CID内容
//			ClassPathResource  clas=new ClassPathResource("D:\\c.txt");
			File clas=new File("D:\\c.txt");
			mailhelp.addInline("图片", clas);
			//增加附件
//			ClassPathResource attr=new ClassPathResource("D:\\c.jpg");
			File attr=new File("D:\\c.txt");
			mailhelp.addAttachment("c.zip",attr);
			mailSender.send(mai);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
