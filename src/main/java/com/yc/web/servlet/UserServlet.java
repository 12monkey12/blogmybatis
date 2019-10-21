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

import com.yc.bean.User;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	
	SqlSession sqlsession = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入UserServlet的post方法。。。");
		//因为我们有过滤器，因此这里不需要考虑编码了
		PrintWriter out = resp.getWriter();
		String op = req.getParameter("op");
		
		sqlsession = (SqlSession) this.getServletContext().getAttribute("sqlsession");
		
		if ( "reg".equals(op) ) {
			reg(req, resp, out);
		} else if ( "login".equals(op) ) {
			login(req, resp, out);
		} else if ( "logout".equals(op) ) {
			logout(req, resp, out);
		} else if ( "getAllUser".equals(op) ) {
			getAllUser(req, resp, out);
		}
	}

	//获取所有用户
	private void getAllUser(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
		List<User> users = sqlsession.selectList("mapper.UserMapper.getAllUser");
		req.getSession().setAttribute("users", users);
		out.print(1);
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
		req.getSession().removeAttribute("user");
		out.print(0);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
		System.out.println("进入登录方法。。。");
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		// 传递多个参数，用map来封装
		Map<String, String> map = new HashMap<String, String>();
		map.put("uname", uname);
		map.put("pwd", pwd);
		
		User user = sqlsession.selectOne("mapper.UserMapper.login", map);
		
		if ( user == null ) {
			out.print(0);
		} else {
			//存起来
			req.getSession().setAttribute("user", user);
			out.print(1);
		}
	}

	private void reg(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {

		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		// 传递多个参数，用map来封装
		Map<String, String> map = new HashMap<String, String>();
		map.put("uname", uname);
		map.put("pwd", pwd);
		
		int result = sqlsession.insert("mapper.UserMapper.reg", map);
		
		out.print(result);
	}
	
	
}
