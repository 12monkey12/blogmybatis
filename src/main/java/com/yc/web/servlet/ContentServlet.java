package com.yc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Contents;

@WebServlet("/contentServlet")
public class ContentServlet extends HttpServlet {
	
	SqlSession sqlsession = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//因为我们有过滤器，因此这里不需要考虑编码了
		PrintWriter out = resp.getWriter();
		String op = req.getParameter("op");
		
		System.out.println("进入ContentServlet的post方法。。。");
		sqlsession = (SqlSession) this.getServletContext().getAttribute("sqlsession");
		
		if ( "changePage".equals(op) ) {
			changePage(req, resp, out);
		} else if ( "changeType".equals(op) ) {
			changeType(req, resp, out);
		}
	}

	private void changeType(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
		System.out.println("进入改变类型方法。。。");
		int tid = Integer.parseInt(req.getParameter("tid"));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("page", 1);
		map.put("count", 5);
		map.put("tid", tid);
		
		List<Contents> list = sqlsession.selectList("mapper.ContentMapper.getContentByPage", map);
		System.out.println("contents:" + list + "\n");
		if ( list.size() > 0 ) {
			req.getSession().setAttribute("page", 1);
			req.getSession().setAttribute("tid", tid);
			int size = sqlsession.selectList("mapper.ContentMapper.getAllContents",tid).size();
			System.out.println("size:" + size + "\n");
			req.getSession().setAttribute("pages", size%5==0?size/5:size/5+1);
			req.getSession().setAttribute("contents", list);
			
			out.print(1);
		} else {
			out.print(0);
		}
	}

	private void changePage(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
		System.out.println("进入到分页方法。。。");
		int page = Integer.parseInt(req.getParameter("page"));
		//重新将当前页，存到session中
		req.getSession().setAttribute("page", page);
		int tid = Integer.parseInt(req.getSession().getAttribute("tid") + "");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("page", page);
		map.put("count", 5);
		map.put("tid", tid);
		
		List<Contents> list = sqlsession.selectList("mapper.ContentMapper.getContentByPage", map);
		System.out.println("list:" + list + "\n");
		if ( list.size() > 0 ) {
			req.getSession().setAttribute("contents", list);
			
			out.print(1);
		} else {
			out.print(0);
		}
	}
}
