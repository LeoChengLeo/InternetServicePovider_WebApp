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
<title>Update Package Page</title>
</head>
<body>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <a href="${contextPath}/admin">Admin Home</a>
 <br>
 
 
 
<p><Strong>Performance Starter</Strong> <a href="${contextPath}/admin/servicePackage/update?packageID=1">Edit</a></p>
 
 <dir>
  <li>Up to 5 devices at a time</li>
  <li>Download speed up to 20 Mbps</li>
  <li>$29.99/month</li>
</dir>


<p><Strong>Performance Plus</Strong> <a href="${contextPath}/admin/servicePackage/update?packageID=2">Edit</a></p>

<dir>
  <li>Up to 5 devices at a time</li>
  <li>Stream top shows & movies</li>
  <li>Only $32.99/month</li>
</dir>
 
 
 

<p><Strong>Performance Pro</Strong> <a href="${contextPath}/admin/servicePackage/update?packageID=3">Edit</a></p>
 
<dir>
  <li>Up to 8 devices at a time</li>
  <li>HD included</li>
  <li>Only $32.99/month</li>
  <li>Download speed up to 160 Mbps</li></li>
</dir>


</body>
</html>