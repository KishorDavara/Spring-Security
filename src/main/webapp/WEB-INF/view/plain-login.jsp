<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Login Page</title>
<style>
 .failure {
   background-color:#ffe6e6; 
   color:#ff0000;
 }
</style>
</head>
<body>
<h3> Custom Login Page</h3>

<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
   <c:if test="${param.error != null }">
     <span class="failure">
     You may have entered invalid username/password.</span>
   </c:if>
   <p>
    User Name: <input type="text" name="username" />
   </p>
   
   <p>
    Password: <input type="password" name="password" />
   </p>
   
   <input type="submit" value="Login" />
   
</form:form>
</body>
</html>