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
<title>Infr Table</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2><a href="${contextPath}/admin"><Strong>Admin Home</Strong></a></h2>


<table border=1>

<tr>
<th>CityName</th> <th>Network</th> <th>Gateway</th>
</tr>

<c:forEach var="city" items="${cityList}">
<tr>
     <td align="center">${city.cityName}</td>
     <td align="center"><a href="${contextPath}/admin/network?id=${city.cityNetwork.cityNetworkID}">${city.cityNetwork.network}</a></td>
     <td align="center"><a href="${contextPath}/admin/gateway?id=${city.cityNetwork.cityGateway.cityGatewayID}">${city.cityNetwork.cityGateway.deviceName}</a></td>
</tr>
</c:forEach>

</table>





</body>
</html>