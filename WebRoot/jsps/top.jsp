<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#divTop{
			
		}
		
		#divTop h1{
			color: white;
			position: relative;
			top:80px;
			left: 150px;
		}
		
		#divTop ul{
			color: white;
			float: left;
			list-style: none;
			position: relative;
			top:20px;
			left: 1000px;
		}
		
		#divTop li{
			float: left;
			margin-left: 20px;
		}
		
		#divTop li a{
			text-decoration:none;
		}
		
	</style>
  </head>
  
  <body style="height: 500px">
    <div id="divTop">
    	<h1>OnlineMusic</h1>
    	<ul>
    		<li><a href="<c:url value='/MusicServlet?method=loadMusicShare'/>" target="left">首页</a></li>
    		<li><a href="<c:url value='/MusicBoxServlet?method=loadMusicBox'/>" target="left">音乐盒</a></li>
    		<li><a href="<c:url value='/InfoServlet?method=loadInfo'/>" target="left">短信息</a></li>
    		<li><a href="<c:url value='/jsps/music/upload.jsp'/>" target="left">分享音乐</a></li>
    		<li><a href="<c:url value='/MusicBoxServlet?method=loadPlayer'/>" target="left">播放列表</a></li>
    	</ul>
    </div>
  </body>
</html>
