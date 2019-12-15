<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>SearchResultPage</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2><a href="${contextPath}/admin"><Strong>Admin Home</Strong></a></h2>



<h1>Search Result:</h1>



<table>

<tr>
<th>Status</th> <th>Customer Profile</th> <th>Service Address</th> <th>Zipcode</th> <th>BindIPv4</th> <th>Network</th> <th>NetSpec</th>
</tr>

<c:forEach var="customer" items="${customers}">
<tr>
     <td  align="center">Activated</td>
     <td  align="center"> <a href="${contextPath}/admin/customerProfile?id=${customer.customerID}">${customer.firstName}  ${customer.lastName}</a> </td>
     <td  align="center">${customer.serviceDetail.address.street} </td>   
     <td  align="center">${customer.serviceDetail.address.zipcode} </td>
     <td  align="center">${customer.serviceDetail.address.bind_IPv4.client_IPv4}</td>
     <td  align="center">${customer.serviceDetail.address.bind_IPv4.cityNetwork.network}</td>
     <td  align="center"> <a href="${contextPath}/admin/netSpec?ip=${customer.serviceDetail.address.bind_IPv4.client_IPv4}">check Specification</a> </td>
    
     
</tr>
</c:forEach>

</table>

<br>
<a href="${contextPath}/admin/searchPage">Back to Search Page</a>




</body>
</html>