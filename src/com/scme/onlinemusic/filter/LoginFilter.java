package com.scme.onlinemusic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.scme.onlinemusic.user.pojo.User;

@WebFilter(urlPatterns = { "/LoginFilter", "/jsps/info/*", "/jsps/music/*", "/jsps/musicbox/*" }, servletNames = {
		"InfoServlet", "MusicBoxServlet", "MusicServlet" })
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// 获取session中的用户
		User user = (User) req.getSession().getAttribute("sessionUser");
		if(user==null){
			req.setAttribute("msg", "您还没有登录!!请先登录！");
			req.getRequestDispatcher("/jsps/msg.jsp").forward(req, response);
		}else{
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
