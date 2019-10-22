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
	   User: <security:authentication property="principal.username" />
	   <br>
	   Role: <security:authentication property="principal.authorities" />
	<hr>

	<!-- Logout handling -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout" />

	</form:form>
</body>
</html>