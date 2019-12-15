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

<title>Active Service</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 
 
 
<table border=1>

<tr>
<th>Active IP</th> <th>Service Address</th> <th>CustomerProfile</th> <th>Network Specification</th>
</tr>

<c:forEach var="address" items="${addressList}">
<tr>
     <td align="center">${address.bind_IPv4.client_IPv4}</td>
     <td align="center">${address.street}, ${address.city.cityName}, MA, ${address.zipcode} </td>
     <td align="center">
     <a href="${contextPath}/admin/customerProfile?id=${address.serviceDetail.customer.customerID}">
     ${address.serviceDetail.customer.firstName}  ${address.serviceDetail.customer.lastName}
     </a>   
     </td>
         
     <td align="center">
     <a href="${contextPath}/admin/netSpec?ip=${address.bind_IPv4.client_IPv4}">
     check Specification
     </a>   
     </td>
   

</tr>
</c:forEach>

</table>
 
<h3><a href="${contextPath}/admin/network?id=${netID}"><Strong>Back to network detail</Strong></a></h3>


</body>
</html>