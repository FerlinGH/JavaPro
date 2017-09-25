<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client Detail Form</title>
</head>
<body>
	<h2>Spring Bank - Client's Detail Form</h2>
	<form:form action="saveDetail" modelAttribute="detail" method="POST">
		<form:hidden path="id" />
		<input type="hidden" name="clientId" value="${clientId }">
		<table>
			<tbody>
				<tr>
					<td><label>Address:</label></td>
					<td><form:input path="address" /></td>
				</tr>
				<tr>
					<td><label>Card Number:</label></td>
					<td><form:input path="cardNumber" /></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input value="Save Detail" type="submit" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>

</body>
</html>