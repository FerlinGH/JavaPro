<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transaction Form</title>
</head>
<body>
	<h2>Spring Bank - New Transaction Form</h2>

	<form:form action="validation" modelAttribute="transaction"
		method="POST">
		<form:hidden path="id" />
		<form:hidden path="senderId" />
		<table>
			<tbody>
				<tr>
					<td>Send from account:</td>
					<td><form:select path="senderCurrency">
							<form:options items="${availableCurrencies}"></form:options>
						</form:select></td>
				</tr>
				<tr>
					<td>Amount to send:</td>
					<td><form:input path="amount" /></td>
				</tr>
				<tr>
					<td>Enter recipient's ID:</td>
					<td><form:input path="recipientId" /></td>
				</tr>
				<tr>
					<td>Select recipient's account:</td>
					<td><form:select path="recipientCurrency">
							<form:options items="${currencies}"></form:options>
						</form:select></td>
				</tr>

			</tbody>
		</table>
		<br>
		<p>
			<input type="submit" value="Validate Transaction">
		</p>
	</form:form>
	<p>
		<strong> <font color="red"> <c:out value="${message }"></c:out></font></strong>
	</p>
</body>
</html>