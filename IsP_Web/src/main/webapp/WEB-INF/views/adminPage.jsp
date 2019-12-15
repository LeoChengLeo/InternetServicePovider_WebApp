<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>AdminPage</title>
</head>
<body>



<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<h1>Welcome IsPMA Employee Home Page</h1>

<h2>Employee Profile</h2>

${sessionScope.AuthEmployee.employeeID}<br>
${sessionScope.AuthEmployee.firstName} ${sessionScope.AuthEmployee.lastName}<br>
${sessionScope.AuthEmployee.phoneNumber}<br>
<a href="${contextPath}/logout">Logout</a><br>


<br><br>

<a href="${contextPath}/admin/searchPage"><Strong>Search Customer Service Status</Strong></a> <br><br>


<a href="${contextPath}/admin/infr"><Strong>Network Infrastructure</Strong></a> <br>








</body>
</html>