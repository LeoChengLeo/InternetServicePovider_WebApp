<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Join IsPMA</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2><a href="${contextPath}/home">IsPMA Home</a></h2>

<Strong>${errorMessage}</Strong>

<form:form action="createCustomerAction" method="post" modelAttribute="customer">

<form:label path="userName">UserName</form:label>
<form:input type="email" path="userName" required="required"/><br><br>

<form:label path="password">Password</form:label>
<form:input path="password" required="required"/><br><br>

<form:label path="firstName">FirstName</form:label>
<form:input path="firstName" required="required"/>
 &nbsp;&nbsp;&nbsp;&nbsp;
<form:label path="lastName">LastName</form:label>
<form:input path="lastName" required="required"/><br><br>

<form:label path="phoneNumber">Mobile phone number</form:label>
<form:input path="phoneNumber" required="required"/><br><br>

<input type="submit" value="Create" />
</form:form>




</body>
</html>