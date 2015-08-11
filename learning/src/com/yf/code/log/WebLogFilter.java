package com.yf.code.log;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebLogFilter<E> implements Filter  {
static final Logger log=LoggerFactory.getLogger(WebLogFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req=(HttpServletRequest) request;
			String encoding=req.getCharacterEncoding();
			String localIP=	req.getLocalAddr();//本地ip
			int serverPort= req.getLocalPort();//本地端口号 ：tomcat端口号
			Locale	local= req.getLocale();//用户的本地环境
			String context=req.getContentType();//context路径
			String method=req.getMethod();//是POST还是GET
			String portal=req.getProtocol();//传输协议 ：http 还是https
			String queryS=req.getQueryString();//查询字符串
			String usrip=req.getRemoteAddr();//用户ip
			int port=req.getRemotePort();//远程端口
			String remoteUser=req.getRemoteUser();//远程用户
			String sessionId=req.getRequestedSessionId();//客户端session id
			String url=req.getRequestURI();//用户请求的URL
			String schema=req.getScheme();//协议头：如:http
			String serername=req.getServerName();//服务器名称
			int serport=req.getServerPort();//服务器端口
			String servletPath=req.getServletPath();//servlet路径
			log.info("encoding"+encoding);
			log.info("localIP"+localIP);
			log.info("serverPort"+serverPort);
			log.info("local"+local);
			log.info("context"+context);
			log.info("method"+method);
			log.info("portal"+portal);
			log.info("queryS"+queryS);
			log.info("usrip"+usrip);
			log.info("port"+port);
			log.info("remoteUser"+remoteUser);
			log.info("sessionId"+sessionId);
			log.info("url"+url);
			log.info("schema"+schema);
			log.info("serername"+serername);
			log.info("serport"+serport);
			log.info("servletPath"+servletPath);
			chain.doFilter(request, response);
	}
	//过滤器 拦截器 监听器 服务器启动开始 服务关闭关闭  只初始化一次
	@Override
	public void destroy() {
		
	}
}
