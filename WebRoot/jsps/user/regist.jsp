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
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		
		.content {
			text-align:center;
			font:bold 15px/90px Verdana, Geneva, sans-serif;
		}
		
		.bottom {
			margin-top: 10px;
			margin-left: 60px;
		}
		.bottom .btn1 {
			border:none;
			width: 63px;
			height: 29px;
			cursor:pointer;
			margin-right: 50px;
		}
		
		.labelClass{
			color:red;
			/*border:1px solid red;*/
			padding:0 10 0 15;
			font-size: 12px;
			background-image: url("images/error.png");
			background-repeat: no-repeat;
			background-position:-5px -2px;
			background-size: 18px 20px;
		}
	</style>
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/user/regist.js'/>"></script>
	
	<script type="text/javascript">
		$(function(){
			
		});
	</script>
	
  </head>
  
  <body>
       <div class="content">
       <h3>用户注册</h3>
	  <form id="formRegist" action="<c:url value='/UserServlet?method=regist'/>" method="post">
		  	<table height="200px" style="margin-left: 30px">
		  		<tr>
		  			<td>用户名:</td>
		  			<td><input type="text" class="inputClass" id="username" name="username"></td>
		  			<td><label class="labelClass" id="usernameError">${errors.username }</label></td>
		  		</tr>
		  		<tr>
		  			<td>用户密码:</td>
		  			<td><input type="password" class="inputClass" id="password" name="password"></td>
		  			<td><label class="labelClass" id="passwordError">${errors.password }</label></td>
		  		</tr>
		  		<tr>
		  			<td>确认密码:</td>
		  			<td><input type="password" class="inputClass" id="repass" name="repass"></td>
		  			<td><label class="labelClass" id="repassError">${errors.repass }</label></td>
		  		</tr>
		  	</table>
	  	</form>
	  </div>
	  <div class="bottom">
	    <input type="submit" value="注册" class="btn1"  id="btnSure" >
	    <input type="button" value="取消" class="btn1" id="btnCancel"/>
	  </div>
  </body>
</html>
