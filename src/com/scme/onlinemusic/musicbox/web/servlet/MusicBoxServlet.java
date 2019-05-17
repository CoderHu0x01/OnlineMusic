package com.scme.onlinemusic.musicbox.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.musicbox.pojo.MusicBox;
import com.scme.onlinemusic.musicbox.serviceimpl.MusicBoxServiceImpl;
import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.utils.BaseServlet;
import com.scme.onlinemusic.utils.CommonUtils;

public class MusicBoxServlet extends BaseServlet {
	private MusicBoxServiceImpl musicBoxService = new MusicBoxServiceImpl();
	private String mids;//播放列表中的音乐编号
	/**
	 * 添加音乐到我的音乐盒
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addMusic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 补全javabean中的参数
		 */
		MusicBox musicBox = new MusicBox();
		musicBox.setBid(CommonUtils.uuid());

		Music music = new Music();
		music.setMid(req.getParameter("mid"));
		musicBox.setMusic(music);

		// 获取session中的用户
		User user = (User) req.getSession().getAttribute("sessionUser");
		musicBox.setOwner(user);
		/*
		 * 调用service完成添加
		 */
		musicBoxService.addMusic(musicBox);
		
		return "f:/jsps/left.jsp";
	}

	/**
	 * 播放音乐
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String player(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取所有播放音乐编号
		mids = req.getParameter("mids");
		
		// 调用service查询当前编号下的所有音乐
		List<Music> listMusic = musicBoxService.findMusicByMids(mids);
		req.setAttribute("listMusic", listMusic);
		return "f:/jsps/musicbox/musicbox.jsp";
	}

	/**
	 * 删除音乐盒中的音乐
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deletes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取所有播放音乐编号
		String mids = req.getParameter("mids");
		/*
		 * 调用service完成删除
		 */
		musicBoxService.deletes(mids);

		return loadMusicBox(req, resp);
	}

	/**
	 * 加载音乐盒信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadMusicBox(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 从session中获取User
		 */
		User user = (User) req.getSession().getAttribute("sessionUser");
		List<MusicBox> listMusic = musicBoxService.loadMusicBox(user.getUid());
		req.setAttribute("listMusic", listMusic);
		return "f:/jsps/musicbox/list.jsp";
	}
	
	/**
	 * 加载播放列表
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadPlayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 根据音乐编号查找出所有播放的音乐
		 */
		//校验是否存在音乐编号
		if(mids!=null&&!mids.trim().isEmpty()){
			List<Music> listMusic = musicBoxService.findMusicByMids(mids);
			req.setAttribute("listMusic", listMusic);
		}
		
		return "f:/jsps/musicbox/musicbox.jsp";
	}		
}
