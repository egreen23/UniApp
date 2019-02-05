<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id="users-list">

	<c:choose> 
		<c:when test="${empty users} ">
			<p> non esistono utenti </p>
		</c:when>
		<c:otherwise>
			<c:forEach items="${users}" var="user">
				<div>
					<c:out value="${user.nome}"/> <c:out value="${user.cognome}"/>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>

</div>

</body>
</html>