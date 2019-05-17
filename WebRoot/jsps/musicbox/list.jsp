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
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		table{
			text-align: center;
		}
	</style>
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript">
		$(function(){
		/*
		为试听按钮添加点击事件,控制音乐播放
		*/
			$(".auditionBtn").each(function(){
				$(this).click(function(){
					var audioPath=$(this).val();
						$(this).next().attr("src",audioPath).attr("controls","controls").trigger("play");
				});
			});
			
			/*
			为全选按钮添加点击事件
			*/
			$("#checkedAll").click(function(){
				//获取当前复选框选中状态
				var bool=$(this).is(":checked");
				//让其他复选框与全选同步
				checkedAll(bool);
			});
			
			/*
				为每个复选框添加点击事件,判断其是否被选中
			*/
			$(".chkBtn").click(function(){
				//获取所有复选框个数
				var chkCount=$(".chkBtn").length;
				//获取被选中复选框个数
				var chkedCount=$(".chkBtn:checked").length;
				
				if(chkCount==chkedCount){//若全被选中
					//选中全选按钮
					$("#checkedAll").attr("checked",true);
				}else{//否则撤销全选
					$("#checkedAll").attr("checked",false);
				}
			});
			
		});
		
		/*
		设置全部复选框选中状态
		*/
		function checkedAll(bool){
			$(".chkBtn").attr("checked",bool);
		}
		
		/*
		为下拉框中的选项执行相应操作
		*/
		function chooseRun(){
			var arrMid=new Array();//存储所以被选中的音乐编号
			var valType=$(".sel").val();//获取下拉框中的值
			if(valType=="播放"){
				//设置提交表单中访问方法的名称
				$("#method").val("player");
			}else if(valType=="删除"){
				//设置提交表单中访问方法的名称
				$("#method").val("deletes");
			}
			
			//获取所以被选中的下拉框,并遍历
			$(".chkBtn:checked").each(function(){
				//获取当前选中的音乐编号
				var mid=$(this).val();
				arrMid.push(mid);
			});
			//添加到隐藏表单中
			$("#mids").val(arrMid.toString());
		}
	</script>
  </head>
  
  <body>
    <h1>我的音乐盒</h1>
    <form action="<c:url value="/MusicBoxServlet"/>" method="post">
    	<input type="hidden" name="method" value="" id="method">
		<input type="hidden" name="mids" value="" id="mids">    	
    <table width="500px" align="center" height="250px">
    	<tr>
    		<td>ID</td>
    		<td>歌曲</td>
    		<td>歌手</td>
    		<td>试听</td>
    		<td><input type="checkbox" id="checkedAll">全选</td>
    	</tr>
    	<c:forEach items="${listMusic }" var="musicbox" varStatus="varStatus">
    	<tr>
    		<td>${varStatus.index+1 }</td>
    		<td>${musicbox.music.mname }</td>
    		<td>${musicbox.music.singer }</td>
    		<td>
    			<input type="image" class="auditionBtn" value="<c:url value='${musicbox.music.path }'/>" src='<c:url value="/images/shiting.PNG"/>' onclick="return false">
    			<audio class="audio">
						您的浏览器不支持该音频！
				</audio>
    		<td>
    		<td><input type="checkbox" class="chkBtn" value="${musicbox.music.mid }" name="chkBtn"></td>
    	</tr>
    	</c:forEach>
    	<tr>
    		<td></td>
    		<td></td>
    		<td>
    		选中项:
    			<select name="choose" class="sel">
    				<option value="播放">播放</option>
    				<option value="删除">删除</option>
    			</select>
    		</td>
    		<td>
    			<input type="submit" value="提交" onclick="chooseRun();">
    		</td>
    	</tr>
    </table>
    
    </form>
  </body>
</html>
