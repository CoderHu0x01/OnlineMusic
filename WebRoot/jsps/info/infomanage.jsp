<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'infomanage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			/*
			设置全选按钮状态与单个按钮状态一致
			*/
			$(".selAll").click(function(){
				var bool=$(this).is(":checked");
				//同步状态
				isChecked(bool);
			});
			
			/*
			为每个按钮添加点击事件
			*/
			$(".chkBtn").click(function(){
				//获取所以按钮的个数
				var chkBtnCount=$(".chkBtn").length;
				//获取所有被选中按钮的个数
				var chkedBtnCount=$(".chkBtn:checked").length;
				if(chkBtnCount==chkedBtnCount){//若选中的个数等于所以按钮个数,则为全选
					//选中全选按钮
					$(".selAll").attr("checked",true);
				}else{//否则取消选中
					$(".selAll").attr("checked",false);
				}
			});
			
		});
		
		/*
		按钮状态
		*/
		function isChecked(bool){
			$(".chkBtn").attr("checked",bool);
		}
		
		/*
		删除选中的消息
		*/
		function deleteInfos(){
			var arrIids=new Array();//存储所有获取的消息编号
			//获取所有被选中多选框中的值
			$(".chkBtn:checked").each(function(){
				var chkedVal=$(this).val();
				arrIids.push(chkedVal);
			});
			//请求删除方法
			location="<c:url value='/InfoServlet?method=delete&iids="+arrIids+"'/>";
		}

		/*
		显示父窗体的遮幕层,查看短消息
		*/
		function seeInfo(param,dialogName){
			parent.showMask(param,dialogName);//调用父窗体中的函数
		}
	</script>
  </head>
  
  <body>
  
   	<div id="divMain">
   		<h2>我的短消息</h2>
   		
   		<table width="500px"align="center" height="150px">
   			<tr>
   				<td>发件人</td>
   				<td>时间</td>
   				<td>标题</td>
   				<td><input type="checkbox" class="selAll"></td>
   			</tr>
   			<c:forEach items="${listInfo }" var="info">
   			<tr>
   				<td>${info.user.username }</td>
   				<td><f:formatDate value="${info.sendTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
   				<td><a href="javascript:seeInfo('${info.content },${info.music.path }','seeInfoDialog')">${info.title }</a></td>
   				<td><input type="checkbox" class="chkBtn" value="${info.iid }"></td>
   			</tr>
   			</c:forEach>
   			<tr>
   				<td colspan="4" style="text-align: center;"><input type="button" value="删除选中项" onclick="deleteInfos();"></td>
   			</tr>
   		</table>
   		
   	</div>
  </body>
</html>
