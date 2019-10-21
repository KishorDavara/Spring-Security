<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
</head>
<body>
	<p>Welcome to Spring Security Home Page!</p>
	<hr>

	<!-- Logout handling -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout" />

	</form:form>
</body>
</html>