<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		#divForm a{
			font-size: 12px;
		}
		
		#divForm form{
			margin-left: 30px;
			padding-top: 50px;
		}
		
		#divForm{
			width: 100%;
			height: 300px;
			background-image: url("<c:url value='/images/img11.jpg'/>");
			background-size:80% 200px;
			background-repeat: no-repeat;	
		}

		.labelClass{
			color:red;
			/*border:1px solid red;*/
			font-size: 8px;
		}
		
	</style>
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/user/login.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			
		});
		
		/*
		显示遮幕层
		*/
		function regist(param,diglogName){
			parent.showMask(param,diglogName);//调用父窗体中的函数
		}
		
	</script>
  </head>
  
  <body>
  <div id="divForm">
	    <form action="<c:url value='/UserServlet'/>" method="post">
	    <input type="hidden" name="method" value="login">
	    	<label id="msg" style="font-size: 10px;color:red">${msg }</label><br>
	    	用户名:<br>
	    	<input type="text" class="input" id="loginname" name="loginname" value="${formUser.username }">
	    	<label class="labelClass" id="loginnameError">${errors.username }</label><br>
	    	密码:<br>
	    	<input type="password" class="input" id="loginpass" name="loginpass">
	    	<label class="labelClass" id="loginpassError">${errors.password }</label><br>
	    	<a href="javascript:regist('','registDialog');">我要注册</a>
	    	<input type="image" src="<c:url value='/images/login.PNG'/>" id="submit" width="50px" height="30px">
	    </form>
    </div>
  </body>
</html>
