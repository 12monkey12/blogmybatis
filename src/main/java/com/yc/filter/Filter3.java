package com.yc.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.User;

@WebFilter("/back/*")
public class Filter3 implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("进入过滤器3。。。。。");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		PrintWriter out = resp.getWriter();
		if ( request.getSession().getAttribute("user") == null ) {
			//意味着你没有登录，就进到这里来了
			out.print("<script>location.href='../index.jsp'</script>");
		} else {
			User user = (User) request.getSession().getAttribute("user");
			if ( user.getIsadmin() != 1 ) {
				out.print("<script>location.href='../index.jsp'</script>");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
