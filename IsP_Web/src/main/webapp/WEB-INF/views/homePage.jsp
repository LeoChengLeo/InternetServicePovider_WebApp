<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>IsPMA Internet</title>
</head>
<body>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 
 
 
 
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="${contextPath}/home">IsPMAHome</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
    
    
 <c:choose>
    <c:when test="${sessionScope.Authenticated=='true'}">
        
      
     
      <li class="nav-item">
         <a class="nav-link" href="${contextPath}/customer/serviceDetail/get">Hello ${sessionScope.AuthCustomer.firstName}</a>
      </li>
      
      
      <li class="nav-item">
        <a class="nav-link" href="${contextPath}/logout">Logout</a>
      </li>
       
    </c:when>    
    <c:otherwise>
      <li class="nav-item">
        <a class="nav-link" href="${contextPath}/loginPage">Sign in</a>
      </li>
    </c:otherwise>
</c:choose>
    
       
    </ul>
  </div>  
</nav>
 
 
 
<h2><strong>${updateMessage}</strong></h2>
 

<div class="jumbotron text-center">
  <h1>Welcome to IsPMA</h1>
  <h2>The Internet Plan:
Lightning-fast service,
no bundles, tricks
or extra fees.</h2> 
</div>
  
<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <h3><a href="${contextPath}/servicePackage?packageID=1"><Strong>Performance Starter</Strong></a></h3>
            <dir>
  			<li>Up to 5 devices at a time</li>
  			<li>Download speed up to 20 Mbps</li>
  			<li>$29.99/month</li>
			</dir>
    </div>
    <div class="col-sm-4">
     <h3><a href="${contextPath}/servicePackage?packageID=2"><Strong>Performance Plus</Strong></a></h3>
      <dir>
  <li>Up to 5 devices at a time</li>
  <li>Stream top shows & movies</li>
  <li>Only $32.99/month</li>
</dir>
 
    </div>
    <div class="col-sm-4">
      <h3><a href="${contextPath}/servicePackage?packageID=3"><Strong>Performance Pro</Strong></a></h3>
<dir>
  <li>Up to 8 devices at a time</li>
  <li>HD included</li>
  <li>Only $32.99/month</li>
  <li>Download speed up to 160 Mbps</li></li>
</dir>
    </div>
  </div>
</div>
 
 

 

</body>
</html>