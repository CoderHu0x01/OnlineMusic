<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>上传音乐</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#divMain{
			width: 300px;
			height: 200px;
			margin:100px auto auto auto;
		}
		
		h2{
			margin: 50px auto auto 50px;
		}
		
		#errorLab{
			color:red;
			font-size: 12px;
		}
	</style>
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
	<script type="text/javascript">
		$(function(){
		
		});
	</script>
  </head>
  
  <body>
  <h2>第一步上传音乐(上传音乐)</h2>
  	<div id="divMain">
  	<label id="errorLab">${msg }</label>
  	<br>
  	    请上传音乐:
  		<form action="<c:url value='/MusicServlet?method=upload'/>" method="post" enctype="multipart/form-data">
	    	<input type="file" name="uploadFile" id="file" value="浏览"><br><br>
	    	<input type="submit" value="下一步" style="margin-left: 70px">
    	</form>
    </div>
  </body>
</html>
