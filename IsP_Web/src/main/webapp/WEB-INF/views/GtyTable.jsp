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
<title>Gateway Device Table</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h2><a href="${contextPath}/admin"><Strong>Admin Home</Strong></a></h2>



<table border=1>


<tr>   <td><strong>Gateway DeviceName</strong></td>  <td>${cityGty.deviceName}</td></tr>
<tr>   <td><strong>OSPF AreaID</strong></td>  <td> ${cityGty.OSPF_areaID}</td></tr>
<tr>   <td><strong>Internal_IPv4</strong></td>  <td>${cityGty.internal_IP}</td></tr>
<tr>   <td><strong>Firmware</strong></td>  <td>${cityGty.firmware}</td></tr>
<tr>   <td><strong>Year</strong></td>  <td> ${cityGty.year}</td></tr>


</table>


<br>

<a href="${contextPath}/admin/infr">Back to table</a>





</body>
</html>