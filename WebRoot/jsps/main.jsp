<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->	
	<style type="text/css">
		body{
			background-image: url("./images/img01.jpg");
			background-repeat: repeat-x;
		}
		
		/*
		遮罩层
		*/
		.mask { 
		    background-color:gray;
		    left:0;
		    position:absolute;
		    top:0;
		    z-index:100;
		    filter:alpha(opacity=30);
		   	opacity:0.3;
		   	width: 100%;
		   	height: 1000px;
		   	display: none;
		}
		
		/*
		弹出窗体
		*/
		.registDialog,.sendInfoDialog,.seeInfoDialog {
			background-color:white;
			width: 480px;
			height:500px;
			position: absolute;
			z-index: 101;
			border:1px solid black;
			display: none;
		}

	</style>
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.min.js'/>"></script>
	<script type="text/javascript">
		$(function(){	
			//单击提示框的关闭图片和取消按钮
			$("#btnCancel").click(function(){
				$(".registDialog").hide();
				$("div.mask").hide(); 
			});
			
			//发送短信息的取消按钮
			$(".btnSendCancel").click(function(){
				$(".sendInfoDialog").hide();
				$("div.mask").hide();
			});
			
			//发送短信息的取消按钮
			$(".btnSeeCancel").click(function(){
				$(".seeInfoDialog").hide();
				$("div.mask").hide(); 
			});
		
			//单击提示框的确定
			$("#btnSure").click(function(){
				//$("#"+delUlId).css("display","none");
				$("#formRegist").submit();//提交表单
				$(".registDialog").css("display","none");
				$(".mask").css("display","none");
		  	});
		  	
		  	/*
		  	点击发送短消息确认按钮时
		  	*/
		  	$(".btnSend").click(function(){
				//$("#"+delUlId).css("display","none");
				$("#sendForm").submit();//提交表单,完成消息发送
				$(".dialog").css("display","none");
				$(".mask").css("display","none");
		  	});
		});
		
		
		/*
		显示遮幕层
		*/
		function showMask(param,dialogName){		
			//为发送短消息表单设置隐藏值
			setVal(param);
			//为查看短消息表单设置参数值
			seeInfoVal(param);
			
			$(".mask").show();
			//设置提示框位置
			setDialog(dialogName);
			/*
			显示提示框
			*/
			$("."+dialogName).show();
		}
		
		
		// 设置对话框的位置
		function setDialog(dialogName){
			var $wObj=$(window);
			var $dObj=$("div."+dialogName);
			var widW=$wObj.width();
			var widH=$wObj.height();
			var diaW=$dObj.width(); 
			var diaH=$dObj.height();
			var left=(widW-diaW)/2;
			var top=(widH-diaH)/2;
			$dObj.css({"left":left,"top":top});
		}

		/*
		为发消息表单设置参数值
		*/
		function setVal(param){
			var arrStr=param.split(",");
			$("#hiddenUid").val(arrStr[0]);
			$("#sendName").val(arrStr[1]);
			$("#hiddenMid").val(arrStr[2]);
		}
		
		/*
		为查看短消息表单设置参数值
		*/
		function seeInfoVal(param){
			var arrStr=param.split(",");
			$("#spanContent").text(arrStr[0]);
			$("#auditionBtn").val(arrStr[1]);
		}
	</script>
  </head>
  
  <body>
    <table width="100%">
    	<tr>
    		<td><iframe scrolling="no" frameborder="0" src="<c:url value='/jsps/top.jsp'/>" width="100%" height="150px"></iframe></td>
    	</tr>
    	<tr>
    		<td><iframe scrolling="no" frameborder="0" src="<c:url value='/jsps/content.jsp'/>" width="90%"  height="800px" style="margin-left: 80px"></iframe></td>
    	</tr>
    </table>
    
    <div class="mask"></div>
    	
    	 <!-- 注册弹出窗体 -->
	    <div class="registDialog">
	  		<%@include file="/jsps/user/regist.jsp" %>
		</div>
		
		<!-- 发送短消息弹出窗体 -->
		<div class="sendInfoDialog">
	  		<%@include file="/jsps/info/sendinfo.jsp" %>
		</div>
		
		<!-- 查看短消息弹出窗体 -->
		<div class="seeInfoDialog">
	  		<%@include file="/jsps/info/seeinfo.jsp" %>
		</div>
  </body>
</html>
