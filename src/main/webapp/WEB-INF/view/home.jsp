<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
</head>
<body>
	<p>Welcome to Spring Security Home Page!</p>
	<hr>
	   <!-- display username and role -->
	   <p>
	   User: <security:authentication property="principal.username" />
	   <br><br>
	   Role: <security:authentication property="principal.authorities" />
	   </p>
	   
	   
		<!-- Add a link to point to /leaders ... this is for the managers -->
	   <security:authorize access="hasRole('MANAGER')">	
			<p>
				<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
				(Only for Manager peeps)
			</p>
		</security:authorize>	
	
		<!-- Add a link to point to /systems ... this is for the admins -->	
		<security:authorize access="hasRole('ADMIN')">  
			<p>
				<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
				(Only for Admin peeps)
			</p>
		</security:authorize>
	<hr>

	<!-- Logout handling -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout" />

	</form:form>
</body>
</html>