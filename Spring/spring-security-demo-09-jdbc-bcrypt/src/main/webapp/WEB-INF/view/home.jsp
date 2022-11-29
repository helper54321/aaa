<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
 <!-- Bejelentkezett username, roleja, stb. megjelenítéséhez kell -->
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>luv2code Company Home Page</title>
</head>
<body>

	<h2>luv2code Company Home Page</h2>
	<hr>
	
	<p>Welcome to the luv2code company home page</p>
	
	<hr>
	
	<!-- display username and role -->
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
	
	
	
	
	<security:authorize access="hasRole('MANAGER')">
	
		<!-- Add a link to point to /leaders ... this is for the managers -->
		
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
			(Only for Manager peeps)
		</p>

	</security:authorize>	
	
	
	<security:authorize access="hasRole('ADMIN')">  

		<!-- Add a link to point to /systems ... this is for the admins -->
		
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
			(Only for Admin peeps)
		</p>
	
	</security:authorize>
	
	<hr>
	
	
	
	<!-- 
		Adding logout button.
		A /logout-hoz nem kell a controllerben kód, a háttérben elvégződik aminek kell 
	-->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>
</html>