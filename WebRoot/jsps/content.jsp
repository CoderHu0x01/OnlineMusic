<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'content.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			margin: 0px;
			padding: 0px;
		}
	</style>
	
	<script type="text/javascript">
		/*
		显示遮幕层
		*/
		function showMask(param,diglogName){
			parent.showMask(param,diglogName);//调用父窗体中的函数
		}
	</script>
  </head>
  
  <body style="background-color: white;">
    <table width="100%" cellspacing="0px" cellpadding="0px">
   		<tr>
   			<td><iframe scrolling="yes" src="<c:url value='/jsps/left.jsp'/>" height="780px" width="1100px" name="left"></iframe></td>
   			<td><iframe scrolling="no" src="<c:url value='/jsps/right.jsp'/>" height="780px" width="300px" name="right"></iframe></td>
   		</tr>
   	</table>
  </body>
</html>
