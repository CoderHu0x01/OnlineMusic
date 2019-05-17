package com.scme.onlinemusic.info.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scme.onlinemusic.info.pojo.Info;
import com.scme.onlinemusic.info.serviceimpl.InfoServiceImpl;
import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.utils.BaseServlet;
import com.scme.onlinemusic.utils.CommonUtils;

public class InfoServlet extends BaseServlet {
	private InfoServiceImpl infoService = new InfoServiceImpl();

	/**
	 * 异步统计消息数量
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxCountInfoNum(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("sessionUser");
		if (user != null) {
			int countNum = infoService.ajaxCountInfoNum(user.getUid());
			resp.getWriter().print(countNum);
		}
		return null;
	}

	/**
	 * 添加短消息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 将消息内容封装到javabean中
		 */
		Info info = new Info();
		// 补全javabean中的信息
		info.setIid(CommonUtils.uuid());
		info.setSender(req.getParameter("sendUid"));
		info.setTitle(req.getParameter("title"));
		info.setContent(req.getParameter("content"));
		/*
		 * 设置发送时间
		 */
		info.setSendTime(new Date());
		/*
		 * 从session域中获取当前登录用户
		 */
		User user = (User) req.getSession().getAttribute("sessionUser");
		info.setUser(user);
		// 设置音乐编号
		Music music = new Music();
		music.setMid(req.getParameter("sendMid"));
		info.setMusic(music);
		/*
		 * 调用service层完成内容添加
		 */
		infoService.addInfo(info);
		return "f:/jsps/main.jsp";
	}

	/**
	 * 加载当前用户的所有短消息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取当前用户
		User user = (User) req.getSession().getAttribute("sessionUser");
		/*
		 * 调用service按照编号查找当前用户的所有短消息
		 */
		List<Info> listInfo = infoService.loadInfo(user.getUid());
		req.setAttribute("listInfo", listInfo);
		return "f:/jsps/info/infomanage.jsp";
	}

	/**
	 * 删除短消息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取多个消息编号的字符串
		String iids = req.getParameter("iids");
		/*
		 * 调用service完成多条消息的删除
		 */
		infoService.deleteByIids(iids);

		return loadInfo(req, resp);
	}
}
