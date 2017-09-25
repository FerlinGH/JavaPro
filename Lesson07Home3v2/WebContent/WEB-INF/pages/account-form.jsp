<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>
</head>
<body>

	<h2>Spring Bank - Account Form</h2>

	<c:choose>
		<c:when test="${currencyOptions.isEmpty() }">
			<h2>You already have all possible accounts!</h2>
			<p>
		</c:when>
		<c:otherwise>

			<form:form action="add" modelAttribute="account" method="POST">
				<input type="hidden" name="clientId" value="${clientId}" />
				Select your currency:
					<form:select path="currency">
					<form:options items="${currencyOptions}"></form:options>
				</form:select>
				<br>
				<br>
				Fund your account:
				<form:input path="amount" />
				<br>
				<br>
				<input type="submit" value="Create Account" />

			</form:form>
		</c:otherwise>
	</c:choose>

</body>
</html>