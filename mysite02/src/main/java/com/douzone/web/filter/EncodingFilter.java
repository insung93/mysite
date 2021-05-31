package com.douzone.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class EncodingFilter implements Filter {
	private String encoding;
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		System.out.println("encoding-" + encoding);
		if(encoding== null) { //default encoding character
			encoding = "UTF-8";
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* 공통적인처리 */
		request.setCharacterEncoding("UTF-8");
		
		/* request 처리 */
		
		chain.doFilter(request, response);
		
		/* response 처리 */
		
	}
	
	public void destroy() {

	}
}
