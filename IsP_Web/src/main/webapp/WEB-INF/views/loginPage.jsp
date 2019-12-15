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
<title>Welcome to IsPMA | Access my account </title>
</head>
<body>

<h2>Welcome IsPMA ! We provide Lightning-fast service</h2>

<br><strong>${inValidMessage}</strong>

<form action="login" method="post">
Username <input type="email" name="username" required="required"><br><br>
Password <input type="password" name="password" required="required"><br><br>
<input type="radio" name="role" value="customer" checked="checked"> I am a customer <input type="radio" name="role" value="admin"> I am an employee<br><br>
<input type="submit" value="Submit">
</form>
<br>




 New Customer? Don't have a username? <a href='createCustomerPage'> Create one</a><br><br>
 <a href='home'>Browse without login</a>
 

</body>
</html>