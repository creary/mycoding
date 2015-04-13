package com.yf.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class MailUtil {
	
/**
 * 说明: 基于Acache的simplemail来写的
 * @param host 如:smtp.163.com 或者 smtp.gmail.com
 * @param name 用户名: l1334889580@163.com 
 * @param password eg:密码
 * @param port 发送邮件端口 SSL 协议使用:465 其他使用25
 * @param isSSL 是否使用SSl协议 
 * @param from 发送邮件的人
 * @param to 发送给谁
 * @param content 发送内容
 * @param subject 邮件标题
 */
	public static void sendMail(String host,String name,String password,int port,boolean isSSL,String from,String to,String content,String subject) {
		try {
			Email email = new SimpleEmail();
			email.setHostName(host);
			email.setSmtpPort(port);
			email.setAuthenticator(new DefaultAuthenticator(name, password));
			email.setSSLOnConnect(isSSL);
			email.setFrom(from);
			email.setSubject(subject);
			email.setMsg(content);
			email.addTo(to);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
}
