package com.scme.onlinemusic.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.user.serviceimpl.UserServiceImpl;
import com.scme.onlinemusic.user.serviceimpl.exception.UserException;
import com.scme.onlinemusic.utils.BaseServlet;
import com.scme.onlinemusic.utils.CommonUtils;

public class UserServlet extends BaseServlet {
	private UserServiceImpl userService = new UserServiceImpl();

	/**
	 * 登录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 获取表单数据封装到javabean中
		 */
		User formUser = new User();
		formUser.setUsername(req.getParameter("loginname"));
		formUser.setPassword(req.getParameter("loginpass"));

		/*
		 * 校验登录表单
		 */
		Map<String, String> errors = validateLoginForm(formUser);
		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			// 保存表单数据用于回显
			req.setAttribute("formUser", formUser);
			return "f:/jsps/user/login.jsp";
		}

		/*
		 * 调用service完成登录
		 */
		try {
			User user = userService.login(formUser);
			// 登录成功,将用户信息保存到session中
			req.getSession().setAttribute("sessionUser", user);
		} catch (UserException e) {
			req.setAttribute("msg", e.getMessage());
			return "f:/jsps/user/login.jsp";
		}
		return "f:/jsps/right.jsp";
	}

	/**
	 * 校验登录表单
	 * 
	 * @param formUser
	 * @return
	 */
	private Map<String, String> validateLoginForm(User formUser) {
		Map<String, String> map = new HashMap<String, String>();

		String username = formUser.getUsername();
		if (username == null || username.trim().isEmpty()) {
			map.put("username", "用户名不能为空");
		} else if (username.length() < 3 || username.length() > 10) {
			map.put("username", "用户名长度必须在3-10位之间！");
		}

		String password = formUser.getPassword();
		if (password == null || password.trim().isEmpty()) {
			map.put("password", "密码不能为空");
		} else if (password.length() < 6 || password.length() > 12) {
			map.put("password", "密码长度必须在3-10位之间！");
		}
		return map;
	}

	/**
	 * 校验用户名是否已被注册
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateUsername(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		/*
		 * 调用service完成校验
		 */
		boolean flag = userService.ajaxValidateUsername(username);
		// 将结果响应给客户端(true表示该用户不存在,true表示用户存在)
		resp.getWriter().print(flag);
		return null;
	}

	/**
	 * 注册功能
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取表单map
		Map map = req.getParameterMap();
		// 封装到bean中
		User formUser = CommonUtils.toBean(map, User.class);
		/*
		 * 校验表单合法性
		 */
		Map<String, String> errors = validateRegistForm(formUser);
		if (errors.size() > 0) {// 若集合的长度大于0,则说明有错误
			// 保存错误集合到域中
			req.setAttribute("errors", errors);
			// 保存表单user用于回显
			return "f:/jsps/user/regist.jsp";
		}

		/*
		 * 调用service完成注册
		 */
		userService.regist(formUser);
		// 保存成功信息
		req.setAttribute("msg", "恭喜，注册成功！！");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 校验注册的合法性
	 * 
	 * @param formUser
	 * @param req
	 * @return
	 */
	private Map<String, String> validateRegistForm(User formUser) {
		Map<String, String> map = new HashMap<String, String>();

		String username = formUser.getUsername();
		if (username == null || username.trim().isEmpty()) {
			map.put("username", "用户名不能为空");
		} else if (username.length() < 3 || username.length() > 10) {
			map.put("username", "用户名长度必须在3-10位之间！");
		} else if (!userService.ajaxValidateUsername(username)) {
			map.put("username", "该用户已被注册");
		}

		String password = formUser.getPassword();
		if (password == null || password.trim().isEmpty()) {
			map.put("password", "密码不能为空");
		} else if (password.length() < 6 || password.length() > 12) {
			map.put("password", "密码长度必须在3-10位之间！");
		}

		String repass = formUser.getRepass();
		if (repass == null || repass.trim().isEmpty()) {
			map.put("repass", "确认密码不能为空！");
		} else if (!repass.equals(password)) {
			map.put("repass", "两次输入密码不一致！");
		}

		return map;
	}

	/**
	 * 注销
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		return "r:jsps/right.jsp";
	}
}
