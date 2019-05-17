<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'desc.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		.divMain{
			width:100%;
			height:275px;
			border: 1px solid aqua;
			z-index: 100;
		}
	
		.divTop{
			width:100%;
			height:100px;
			background-image: url("<c:url value='/images/img07.jpg'/>");
			background-repeat: no-repeat;
			background-size: 100% 100px;
		}
	
		.divTop span{
			position: relative;
			left: 700px;
			top:-30px;
		}
		
		.divTop h2{
			margin: 50px auto auto 100px;
		}
		
		.divTop{
			margin-top: -30px;
		}
		
		.divContent table{
			margin-left: 100px;
			margin-top: -50px;
		}
		
		
		.ulFoot li{
			float: left;
			margin-left: 50px;
		}
		
		.ulFoot{
			padding:30px;
			width:100%;
			height:20px;
			background-image: url("<c:url value='/images/img12.jpg'/>");
			background-repeat: no-repeat;
			background-size: 1500px 50px;
			border-bottom: 1px solid aqua;
		}
		
	</style>

<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
<script type="text/javascript">
	$(function(){
		
		/*
		为试听按钮添加点击事件,控制音乐播放
		*/
		$(".auditionBtn").click(function(){
			var audioPath=$(this).val();
			$(".audio").attr("src",audioPath).attr("controls","controls").trigger("play");
		});
		
		$(".ulFoot li").each(function(){
			var index=$(this).index();
			if(index==0){
				$(this).css({"list-style-image":"url('<c:url value='/images/img09.gif'/>')"});
			}else{
				$(this).css({"list-style-image":"url('<c:url value='/images/img10.gif'/>')"});
			}
		});
	});
	
	/*
	添加音乐
	*/
	function addMusic(mid){
		var flag=confirm("确认添加当前歌曲到音乐盒?");
		if(flag){
			location.href="<c:url value='/MusicBoxServlet?method=addMusic&mid="+mid+"'/>";
		}
		
	}	
		/*
		显示父窗体的遮幕层
		*/
		function sendInfo(param,dialogName){
			parent.showMask(param,dialogName);//调用父窗体中的函数
		}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty listMusic }">
			暂无任何歌曲快来分享给大家！
		</c:when>
		<c:otherwise>
			
			<!-- 所有分享信息 -->
	<c:forEach items="${listMusic }" var="musicShare">

	<div class="divMain">
	<div class="divTop">
			<h2><a href="javascript:sendInfo('${musicShare.owner.uid },${musicShare.owner.username },${musicShare.mid }','sendInfoDialog');">${musicShare.mname }</a></h2>
			<span class="spanTime"><f:formatDate value="${musicShare.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
		</div>
		<div class="divContent">
			<table>
				<tr>
					<td>试听</td>
					<td><input type="image" class="auditionBtn" src="<c:url value='/images/shiting.PNG'/>"></td>
					<audio class="audio" src="<c:url value='${musicShare.path }'/>">
						您的浏览器不支持该音频！
					</audio>
				</tr>
				<tr>
					<td>类型:</td>
					<td>${musicShare.musicType.tname }</td>
				</tr>
				<tr>
					<td>歌手:</td>
					<td>${musicShare.singer }</td>
				</tr>
				<tr>
					<td colspan="2">${musicShare.summary }</td>
					<td></td>
				</tr>
			</table>
			<ul class="ulFoot">
				<li><a href="">阅读全文</a></li>
				<li>0条评论</li>
				<li>0次点击</li>
				<li><a href="javascript:addMusic('${musicShare.mid }');">添加到我的音乐盒</a></li>
				<li>分享人<a href="">${musicShare.owner.username }</a></li>
			</ul>
		</div>
	</div>
	
	</c:forEach>
	
		</c:otherwise>
	</c:choose>
</body>
</html>
