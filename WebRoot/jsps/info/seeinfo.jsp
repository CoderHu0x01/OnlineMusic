<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'seeInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#divMain h5{
			margin-top:0px;
			border: 1px solid gray;
			background-color: gray;
		}
	</style>
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			/*
			为试听按钮添加点击事件,控制音乐播放
			*/
			$("#auditionBtn").click(function(){
				var audioPath=$(this).val();
				$("#audio").attr("src","${pageContext.request.contextPath }"+audioPath).attr("controls","controls").trigger("play");
			});
		});
	</script>
  </head>
  
  <body>

   	<div id="divMain">
   		<h5>查看短消息</h5>
   		<table align="center" height="250px;">
   			<tr>
   				<td style="text-align: center;color: blue;border: 1px solid blue;">唱的不错</td>
   			</tr>
	   		<tr>
   				<td>他(她)给您留言:<span id="spanContent"></span></td>
   			</tr>
   			<tr>
   				<td>
   					您可以点击下面的播放器进行试听!
   					<br>
   					<input type="image" id="auditionBtn" name="path" value="" src="<c:url value='/images/shiting.PNG'/>">
					<br>
					<audio id="audio">
						您的浏览器不支持该音频！
					</audio>
   				</td>
   			</tr>
   			<tr height="100px">
   				<td style="text-align: center;"><input type="button" value="退出" class="btnSeeCancel"></td>
   			</tr>
   		</table>
   		
   	</div>
  </body>
</html>
