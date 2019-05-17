<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'nowmsg.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <meta http-equiv="refresh" content="2"> -->
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#divNowMsg {
	overflow: heidden;
	height: 300px;
	width: 80%;
	border: 1px solid red;
}
</style>
<script type="text/javascript" src="<c:url value='/jquery/jquery-3.2.1.js'/>"></script>
<script type="text/javascript">
	
	$(function() {
		
		var count = 0; //记录添加的数据
		setInterval(function() {
			$.ajax({
				url : "/OnlineMusic/MusicServlet",
				data : {
					method : "ajaxRefreshNewInfo"
				},
				type : "POST",
				dataType : "json",
				async : false,
				cache : false,
				success : function(result) {
					var arrNewInfo = eval(result);
					
					var tableInfo = $("#tableInfo");
					//遍历响应的数据
					for (var username in arrNewInfo) {
						count++;
						if (username != null && username != "") {
							//var message=new Message(username, arrNewInfo[username]);
							//arrNowMessage.push(message);
							tableInfo.append("<tr><td>" + "[" + username + "]" + "分享了歌曲" + "[" + arrNewInfo[username] + "]" + "<td></tr>");
						}
						if(count>10){//若添加记录大于10则清空table下的子元素
							tableInfo.empty();
							tableInfo.append("<td>最新消息</td>");
						}
						
					}
				}
			});
		}, 10);
		
	});
</script>
</head>

<body>
	<div id="divNowMsg">
		<table style="margin-left: 30px;" width="300px" id="tableInfo">
			<tr>
				<td>最新消息</td>
			</tr>
			<tr>
				<td id="now1"></td>
			</tr>
			
			<tr>
				<td id="now2"></td>
			</tr>
			
		</table>
	</div>
	<p>友情链接</p>
</body>
</html>
