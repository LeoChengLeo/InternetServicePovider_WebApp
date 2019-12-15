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
<title>CustomerProfilePage</title>
</head>
<body>




<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <a href="${contextPath}/admin">Admin Home</a>
 
 
 
 <h2>Account Info</h2>
 
 ${customer.firstName} ${customer.lastName} <br>
 ${customer.userName}<br>
 ${customer.phoneNumber}<br><br>
 
 
 <h2>Service Info  <a href="${contextPath}/admin/serviceDetail/update?id=${customer.customerID}">Update</a></h2> 
 
 <strong>Service address:</strong> ${customer.serviceDetail.address.street}, ${customer.serviceDetail.address.city.cityName}, ${customer.serviceDetail.address.zipcode}<br> 
 
 <strong>The Internet Plan:</strong> ${customer.serviceDetail.servicePackage.packageName}<br>
 
 <strong>Activated Since:</strong> ${customer.serviceDetail.startServiceDate}<br>
 
 <strong>Service End Date:</strong> ${customer.serviceDetail.endServiceDate}<br>
 
 <strong>Upstream Speed:</strong>  ${customer.serviceDetail.servicePackage.upStreamSpeed} Mbps<br> 
 
 <strong>DownStream Speed:</strong> ${customer.serviceDetail.servicePackage.downStreamSpeed} Mbps<br>
 
 

 
</body>
</html>