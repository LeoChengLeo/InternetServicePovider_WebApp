<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<title>Service Package Detail</title>
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

 




<h1>${servicePackage.packageName}</h1>

<h3>
<dir>
<li>DownloadSpeed Up To: ${servicePackage.downloadSpeed} Mbps</li>
<li>DownStreamSpeed: ${servicePackage.downStreamSpeed} Mbps</li>
<li>UpStreamSpeed: ${servicePackage.upStreamSpeed} Mbps</li>
<li>IndroductoryPrice:$${servicePackage.indroductoryPrice}/mon</li>
<li>ContractDuration: ${servicePackage.contractDuration} months</li>
</dir>
</h3>


<h3><a href="${contextPath}/customer/serviceDetail/createPage?packageID=${servicePackage.servicePackageID}">OrderOnline</a></h3>

</body>
</html>