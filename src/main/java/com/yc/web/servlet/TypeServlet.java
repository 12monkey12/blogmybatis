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

import com.yc.bean.Type;

@WebServlet("/typeServlet")
public class TypeServlet extends HttpServlet {
	
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
		
		sqlsession = (SqlSession) this.getServletContext().getAttribute("sqlsession");
		
		if ( "getAllType".equals(op) ) {
			getAllType(req, resp, out);
		} else if ( "updateType".equals(op) ) {
			updateType(req, resp, out);
		}
	}

	private void updateType(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
		String tname = req.getParameter("tname");
		String tid = req.getParameter("tid");
		
		// 传递多个参数，用map来封装
		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		map.put("tid", tid);
		
		int result = sqlsession.update("mapper.TypeMapper.updateType", map);
		//要记得重新存一遍
		if ( result > 0 ) {
			List<Type> types = sqlsession.selectList("mapper.TypeMapper.getAllType");
			req.getSession().setAttribute("types", types);
		}
		out.print(result);
	}

	private void getAllType(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
		List<Type> types = sqlsession.selectList("mapper.TypeMapper.getAllType");
		req.getSession().setAttribute("types", types);
		out.print(1);
	}
	
	
}