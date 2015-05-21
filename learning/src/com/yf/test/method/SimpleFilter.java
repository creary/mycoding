package com.yf.test.method;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.yf.util.WritelogUtil;
public class SimpleFilter implements Filter {
	private String charset;
	public void destroy() {
		charset=null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(null!=charset){
			request.setCharacterEncoding(this.charset);
			response.setCharacterEncoding(this.charset);
		}
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		WritelogUtil.writeMsgToFile("SimpleFilter 的系统初始化参数是 :  "+ fConfig.getInitParameter("encode"));
		this.charset=fConfig.getInitParameter("encode");
	}
	

}
