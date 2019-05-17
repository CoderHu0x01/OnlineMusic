<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'userinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#divInfo{
			width: 80%;
			height: 300px;
			background-image: url("<c:url value='/images/img11.jpg'/>");
			background-size:800% 200px;
			background-repeat: no-repeat;
			text-align: center;
			font-size: 13px;
			line-height: 30px;	
		}
	</style>
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			setInterval(function() {
				/*
				异步更新短消息数量
				*/
				$.ajax({
					url:"/OnlineMusic/InfoServlet",
					data:{
						method:"ajaxCountInfoNum"
					},
					type:"POST",
					dataType:"json",
					async:false,
					cache:false,
					success:function(result){
						$("#countNum").text(result);
					}
				});
			}, 500)
		});	
	</script>
  </head>
  
  <body>
    <div id="divInfo">
	    <div id="divCotent">
	    	<span>${sessionUser.username }</span>，欢迎回来!<br>
	    	<span>您有</span><span id="countNum">0</span><span>封未读信息请</span><span><a href="<c:url value="/InfoServlet?method=loadInfo"/>" target="left">查看</a></span><br>
	    	
	    	<span>播放我上次创建的</span><span><a href="<c:url value="/MusicBoxServlet?method=loadPlayer"/>" target="left">[播放列表]</a></span>
	    	
	    	<p>如果您有音乐分享,您可以点我进行</p><span><a href="<c:url value='/jsps/music/upload.jsp'/>" target="left">[上传音乐]</a></span><br>
	    	<span>退出请点</span><span><a href="<c:url value="/UserServlet?method=quit"/>">[注销登录]</a></span>
	    </div>
    </div>
  </body>
</html>
