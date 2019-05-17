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
    
    <title>My JSP 'musicbox.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/musicbox.css'/>">
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/jaudio.js'/>"></script>
	<script type="text/javascript">
		
	</script>
  </head>
  
  <body>
    <div class='jAudio--player'>

<audio></audio>

<div class='jAudio--ui'>

  <div class='jAudio--thumb'></div>

  <div class='jAudio--status-bar'>

	<div class='jAudio--details'></div>
	<div class='jAudio--volume-bar'></div>

	<div class='jAudio--progress-bar'>
	  <div class='jAudio--progress-bar-wrapper'>
		<div class='jAudio--progress-bar-played'>
		  <span class='jAudio--progress-bar-pointer'></span>
		</div>
	  </div>
	</div>

	<div class='jAudio--time'>
	  <span class='jAudio--time-elapsed'>00:00</span>
	  <span class='jAudio--time-total'>00:00</span>
	</div>

  </div>

</div>


<div class='jAudio--playlist'>
</div>

<div class='jAudio--controls'>
  <ul>
	<li><button class='btn' data-action='prev' id='btn-prev'><span></span></button></li>
	<li><button class='btn' data-action='play' id='btn-play'><span></span></button></li>
	<li><button class='btn' data-action='next' id='btn-next'><span></span></button></li>
  </ul>
</div>

</div>
    
    <script type="text/javascript">
    	var t = {
			playlist:[
			<c:forEach items="${listMusic }" var="music">
				{
				  file: "<c:url value='${music.path }'/>",
				  thumb: "images/jaudioimg/01.jpg",
				  trackName: "${music.mname }",
				  trackArtist: "${music.singer }",
				  trackAlbum: "${music.album }",
				},
			</c:forEach>
			],
			autoPlay:true
	}

$(".jAudio--player").jAudio(t);
    </script>
  </body>
</html>
