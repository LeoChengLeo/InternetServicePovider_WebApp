<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<title>NetworkTable</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<h2>Network: ${network.network}</h2> 

<Strong>BroadCastAddress:</Strong> ${network.broadCastAddress} <br>

<Strong>Netmask:</Strong> ${network.netMask} <br>

<Strong>NameServer:</Strong> ${network.DNS_Server} <br>

<Strong>DHCP Server:</Strong> ${network.DHCP_Server} <br>

<a href="${contextPath}/admin/activeService?cityNetworkID=${network.cityNetworkID}"> <Strong>Number of Active IP:</Strong> ${fn:length(clientIPList)}</a> <br>
<br>
<br>
<a href="${contextPath}/admin/infr"> Back to table</a> <br>
 


</body>
</html>