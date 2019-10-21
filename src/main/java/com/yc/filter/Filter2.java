package com.yc.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Contents;
import com.yc.bean.Type;
import com.yc.util.CollectionsUtil;

@WebFilter("/index.jsp")
public class Filter2 implements Filter {
	
	private SqlSession sqlsession = null;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("进入过滤器2。。。。。");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		sqlsession = (SqlSession) request.getServletContext().getAttribute("sqlsession");
		System.out.println("sqlsession: " + sqlsession + "\n");
		
		if ( request.getSession().getAttribute("types") == null ) {
			List<Type> types = sqlsession.selectList("mapper.TypeMapper.getAllType");
			System.out.println("types:" + types + "\n");
			
			request.getSession().setAttribute("types", types);
			
			//获取文章
			List<Contents> contents = sqlsession.selectList("mapper.ContentMapper.getAllContents", 1);
			System.out.println("contents:" + contents + "\n");
			
			//得到总页数
			request.getSession().setAttribute("tid", 1);
			request.getSession().setAttribute("page", 1);
			request.getSession().setAttribute("pages", contents.size()%5==0?contents.size()/5:contents.size()/5+1);
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", 1);
			map.put("count", 5);
			map.put("tid", 1);
			contents = sqlsession.selectList("mapper.ContentMapper.getContentByPage", map);
			System.out.println("contents:" + contents + "\n");
			
			request.getSession().setAttribute("contents", contents);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
