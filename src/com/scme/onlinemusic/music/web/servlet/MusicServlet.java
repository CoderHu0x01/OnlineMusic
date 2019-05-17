package com.scme.onlinemusic.music.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.scme.onlinemusic.music.pojo.Music;
import com.scme.onlinemusic.music.pojo.MusicType;
import com.scme.onlinemusic.music.serviceimpl.MusicServiceImpl;
import com.scme.onlinemusic.user.pojo.User;
import com.scme.onlinemusic.utils.BaseServlet;
import com.scme.onlinemusic.utils.CommonUtils;

public class MusicServlet extends BaseServlet {
	private MusicServiceImpl musicService = new MusicServiceImpl();
	// 储存实时信息
	private Map<String, String> mapNowInfos = new HashMap<String, String>();

	/**
	 * 上传音乐
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SmartUpload smartUpload = new SmartUpload();

		// 初始化SmartUpload
		smartUpload.initialize(this.getServletConfig(), req, resp);

		try {
			// 指定允许上传的文件类型
			//smartUpload.setAllowedFilesList("mp3,wav,flac,wma");
			// 指定上传文件大小
			//smartUpload.setMaxFileSize(1024 * 1024 * 50);
			// 调用upload方法上传文件
			smartUpload.upload();

			// 获取表单提交的用于上传的所有文件的组件
			Files files = smartUpload.getFiles();
			// 遍历所有文件组件(表单)
			for (int i = 0; i < files.getCount(); i++) {
				// 获取一个form表单
				File file = files.getFile(i);
				// 获取文件名
				String fileName = file.getFileName();
				
				// 文件保存路径
				String savePath = "/upload/";
				// 保存文件到对应位置
				file.saveAs(savePath + fileName);
				// 保存文件完整路径到域中
				req.setAttribute("path", savePath + fileName);
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		} 
		return "f:/jsps/music/musicinfo.jsp";
	}

	/**
	 * 加载音乐类型
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadMusicType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MusicType> listMusicType = musicService.loadMusicType();
		Gson gson = new Gson();
		// 将集合转换成json串
		String json = gson.toJson(listMusicType);
		// 响应到客户端
		resp.getWriter().println(json);
		return null;
	}

	/**
	 * 添加音乐信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Music music = new Music();
		String mname = req.getParameter("mname");// 姓名
		String singer = req.getParameter("singer");// 歌手
		String album = req.getParameter("album");// 专辑
		String summary = req.getParameter("summary");// 简介
		String path = req.getParameter("path");// 音乐路径
		music.setMname(mname);
		music.setSinger(singer);
		music.setAlbum(album);
		music.setSummary(summary);
		music.setPath(path);
		/*
		 * 补全Music中的属性
		 */
		music.setMid(CommonUtils.uuid());
		music.setUploadTime(new Date());

		/*
		 * 获取歌曲类型id
		 */
		String tid = req.getParameter("tid");
		/*
		 * 按类型编号获取类型bean
		 */
		MusicType musicType = musicService.findByTid(tid);
		music.setMusicType(musicType);
		/*
		 * 获取用户
		 */
		User user = (User) req.getSession().getAttribute("sessionUser");
		music.setOwner(user);

		// 调用service完成音乐信息添加
		musicService.add(music);
		// 保存音乐信息到request域
		// req.setAttribute("music", music);

		/*
		 * 将最新上传的音乐信息储存到map中
		 */
		mapNowInfos.put(user.getUsername(), music.getMname());
		
		/*
		 * 跳转到音乐信息界面
		 */
		return loadMusicShare(req, resp);
	}

	/**
	 * 加载所以音乐分享信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadMusicShare(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 调用service层加载所有音乐分享信息
		 */
		List<Music> listMusic = musicService.loadMusicShare();
		req.setAttribute("listMusic", listMusic);
		return "f:/jsps/music/desc.jsp";
	}

	/**
	 * ajax刷新实时消息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxRefreshNewInfo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 将上传音乐信息的map转换成json串响应到客户端
		 */
		Gson gson = new Gson();
		String json = gson.toJson(mapNowInfos);
		resp.getWriter().print(json);
		// 清空消息map
		mapNowInfos.clear();
		return null;
	}
}
