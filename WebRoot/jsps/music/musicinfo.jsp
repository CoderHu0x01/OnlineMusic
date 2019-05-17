<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'musicinfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#divMain {
	width: 400px;
	height: 200px;
	margin: 100px auto auto auto;
}

#divMain h2 {
	margin: 50px auto auto 50px;
}

#divMain table{
	margin-top: 50px;
}

#divMain table textarea{
	overflow-y: scroll;
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
			$("#audio").attr("src",audioPath).attr("controls","controls").trigger("play");
		});
		
		
			//异步加载歌曲类型
		$.ajax({
			url:"/OnlineMusic/MusicServlet",
			data:{
				method:"loadMusicType"
			},
			type:"POST",
			dataType:"json",
			async:false,
			cache:false,
			success:function (result){
				//执行从服务端响应的json串
				var arrMusicType=eval(result);
				//获取下拉框
				var sel=$("#tSel");
				for(var i=0;i<arrMusicType.length;i++){
					sel.append("<option value='"+arrMusicType[i].tid+"'>"+arrMusicType[i].tname+"</option>");
				}
			}
		});
	});
</script>

</head>

<body>
	<h2>第一步上传音乐(上传音乐)</h2>
	<div id="divMain">
		<p>您的音乐已上传,您可以点击下面的试听按钮进行试听!</p>
		<input type="image" id="auditionBtn" name="path" value="<c:url value='${path }'/>" src="<c:url value='/images/shiting.PNG'/>">
		<br>
		<audio id="audio">
			您的浏览器不支持该音频！
		</audio>
		
		<form action="<c:url value="/MusicServlet?method=add"/>" method="post">
		<input type="hidden" name="path" value="${path }">
		<table height="200px">
			<tr>
				<td>歌曲名称</td>
				<td><input type="text" name="mname"></td>
			</tr>
			<tr>
				<td>歌曲类型</td>
				<td>
					<select id="tSel" name="tid">
					</select>
				</td>
			</tr>
			<tr>
				<td>歌&nbsp;&nbsp;手</td>
				<td><input type="text" name="singer"></td>
			</tr>
			<tr>
				<td>所属专辑</td>
				<td><input type="text" name="album"></td>
			</tr>
			<tr>
				<td>简介</td>
				<td>
					<textarea rows="6" cols="" name="summary"></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="提交" id="submit">
		</form>
	</div>
</body>
</html>
