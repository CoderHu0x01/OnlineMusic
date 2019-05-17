<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'right.jsp' starting page</title>

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
	
	<c:choose>
		<c:when test="${empty sessionUser }">
			<%@include file="/jsps/user/login.jsp"%>
		</c:when>
		<c:otherwise>
			<%@include file="/jsps/user/userinfo.jsp"%>
		</c:otherwise>
	</c:choose>

	<%@include file="/jsps/message/nowmsg.jsp"%>
</body>
</html>
