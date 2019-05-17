package com.scme.onlinemusic.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseServlet用来作为其它Servlet的父类， 处理页面的请求方法
 * 
 * @author HGD
 *
 */
public class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 处理编码
		 */
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 获取页面的请求方法名称
		String methodName = req.getParameter("method");
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new RuntimeException("请求方法名称不能为空!");
		}

		/*
		 * 通过反射调用当前方法
		 */
		Method method = null;
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("您调用的方法" + methodName + "不存在！");
		}

		/*
		 * 执行当前方法
		 */
		try {
			String path = (String) method.invoke(this, req, resp);
			/*
			 * 切割当前方法的返回值字符串(如f:/main.jsp),并进行相应操作
			 */
			if (path != null && !path.trim().isEmpty()) {// 非空判断
				if (path.contains(":")) {
					// 获取:的索引
					int index = path.indexOf(":");
					// 切割前缀(如 f:)
					String head = path.substring(0, index);
					// 切割出url路径(如 /main.jsp)
					String url = path.substring(index + 1);
					if (head.equalsIgnoreCase("f")) {// 转发
						req.getRequestDispatcher(url).forward(req, resp);
					} else if (head.equalsIgnoreCase("r")) {// 重定向
						resp.sendRedirect(url);
					} else {
						System.out.println("不支持当前操作！！！！");
					}
				} else {// 不写前缀默认转发
					req.getRequestDispatcher(path).forward(req, resp);
				}
			}
		} catch (Exception e) {
			System.out.println("您调用的方法内部出现了异常！");
			throw new RuntimeException(e);
		}
	}
}
