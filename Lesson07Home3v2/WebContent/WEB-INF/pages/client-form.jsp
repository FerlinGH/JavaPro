<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Client Form</title>
</head>
<body>
	<h2>Spring Bank - Client Form</h2>

	<form:form action="save" modelAttribute="client" method="POST">
		<form:hidden path="id" />
		<table>
			<tbody>
				<tr>
					<td>First Name:</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Save"></td>
				</tr>
			</tbody>
		</table>
	</form:form>

	<p>
		<a href="${pageContext.request.contextPath }/client/list">Back to
			List</a>
	</p>
</body>
</html>