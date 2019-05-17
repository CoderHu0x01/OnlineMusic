<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'sendInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		<center>
		    <h3>发送短消息</h3>
		    <form action="<c:url value="/InfoServlet?method=addInfo"/>" method="post" id="sendForm">
		    <input type="hidden" name="sendUid" value="" id="hiddenUid">
		    <input type="hidden" name="sendMid" value="" id="hiddenMid">
		    <table height="300px">
		    	<tr>
		    		<td>用户名:</td>
		    		<td><input type="text" name="sendName" id="sendName"></td>
		    	</tr>
		    	<tr>
		    		<td>标题:</td>
		    		<td><input type="text" name="title"></td>
		    	</tr>
		    	<tr>
		    		<td>留言:</td>
		    		<td><textarea rows="" cols="" name="content"></textarea></td>
		    	</tr>
		    	<tr>
		    		<td><input type="button" value="确定" class="btnSend"></td>
		    		<td><input type="button" value="取消" class="btnSendCancel"></td>
		    	</tr>
		    </table>
		    </form>
    	</center>
  </body>
</html>
