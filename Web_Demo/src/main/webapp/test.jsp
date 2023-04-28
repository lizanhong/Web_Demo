<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.taorui.com/zz/x" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Timestamp t = Timestamp.valueOf("2022-10-27 8:35:11");
		pageContext.setAttribute("t", t);
		pageContext.setAttribute("t2", "2022-10-28 8:31:11");
	%>
	${x:whichDay(t)}
	${x:whichDay4String(t2)}
</body>
</html>