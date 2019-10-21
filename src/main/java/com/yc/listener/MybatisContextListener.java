package com.yc.listener;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebListener("*")
public class MybatisContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("进入监听器，实例化SerlvetContext对象。。。");
		
		InputStream is = null;
		SqlSession sqlsession = null;
		
		try {
			// 读配置文件
			is = Resources.getResourceAsStream("mybatis-config.xml");
			// 获取SqlSessionFactory
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			// 从SqlSessionFactory中获取SqlSession
			sqlsession = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 将sqlsession存到ServletContext中
		sce.getServletContext().setAttribute("sqlsession", sqlsession);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
