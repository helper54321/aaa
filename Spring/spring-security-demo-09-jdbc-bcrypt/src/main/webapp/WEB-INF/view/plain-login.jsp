<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Login Page</title>

<style>
	.failed{
		color: red;
	}

</style>
</head>
<body>

	<h3>My Custom Login Page</h3>
	
	<!-- A DemoSecurityConfig fájlban a loginProcessingUrl-nél ezt adtuk meg, ezért kell oda küldeni -->
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
	
		<!--
			 Check for login error 
			 Sikertelen bejelentkezési kísérlet esetén a link végén megjelenik a ?error paraméter rész
			 Itt megnézzük, hogy ez megtörtént-e (tehát nem null). Ha igen a megadott hibaszöveget megjelenítjük
		 -->
		 <c:if test="${param.error != null}">
		 	<i class="failed">Sorry! You entered invalid username/password.</i>
		 </c:if>
		 
		
		
		<!-- Egyedül a name résznél megadott értékek kötelező elnevezések, többi résznél bármit megadhatunk -->
		<p>
			User name: <input type="text" name="username" />
		</p>
		
		<p>
			Password: <input type="password" name="password" />
		</p>
		
		<input type="submit" value="Login">
	</form:form>
	
</body>
</html>