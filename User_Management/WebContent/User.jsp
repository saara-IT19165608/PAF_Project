<%@page import= "com.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>User Management </h1>
<form id="formUser" name="formUser" method="post" action="User.jsp">
 User Name: 
<input id="userName" name="userName" type="text" 
 class="form-control form-control-sm">
<br> User Email: 
<input id="userEmail" name="userEmail" type="text" 
 class="form-control form-control-sm">
<br> User Password: 
<input id="userPassword" name="userPassword" type="text" 
 class="form-control form-control-sm">
<br> User NIC: 
<input id="userNIC" name="userNIC" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
</form>
<br>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divUsersGrid">

<%
 User userObj = new User(); 
 out.print(userObj.readUsers()); 
%>
</div>

</div></div></div>

</body>
</html>