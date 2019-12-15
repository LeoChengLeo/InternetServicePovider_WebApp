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
<title>SearchPage</title>
</head>
<body>



<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2><a href="${contextPath}/admin"><Strong>Admin Home</Strong></a></h2>


<h2>Searching Customer Service Detail</h2>

<form action="search" method="get">



<input type="radio" name="searchBy" value="cityName" checked="checked"> Search by CityName  <br>
<input type="radio" name="searchBy" value="firstName"> Search by Customer's FirstName<br>
<input type="radio" name="searchBy" value="userName"> Search by Account's username (email) <br>
<input type="radio" name="searchBy" value="customerServiceID"> Search by ServiceID<br><br>

Enter Keyword <input type="text" name="keyword"> (cityName, fisrtName, email or ID)<br><br>


<input type="submit" value="Search Service">
</form>





</body>
</html>