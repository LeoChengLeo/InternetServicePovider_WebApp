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
<title>Select Your Deal</title>
</head>
<body>


<Strong>${errorMessage}</Strong><br>

<form:form action="create" method="post" modelAttribute="address">

City 
<select name="cityID">

<c:forEach items="${cityList}" var="city">
		<option value="${city.cityID}"> ${city.cityName}</option>
</c:forEach>

</select>
<br><br>


<form:label path="street">Street</form:label>
<form:input path="street" required="required"/><br><br>
<form:label path="zipcode">zipcode</form:label>
<form:input path="zipcode" required="required"/><br><br>



Your Deal 
<select name="packageID">
		<option value=1 <c:if test="${packageID==1}">selected </c:if> >Performance Starter</option>
		<option value=2  <c:if test="${packageID==2}">selected </c:if> >Performance Plus</option>
		<option value=3   <c:if test="${packageID==3}">selected </c:if> >Performance Pro</option>
</select>
<br><br>
<input type="submit" value="Confirm" />
</form:form>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 
<br><a href="${contextPath}/home">back to option</a>

</body>
</html>