<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transaction Confirmation</title>
</head>
<body>
	<h2>Spring Bank - Transaction Confirmation</h2>
	<div align="center">
		<h4>Your transaction is confirmed:</h4>
		<table>
			<tbody>
				<tr>
					<td>Sender:</td>
					<td><c:out value="${sender.lastName}, ${sender.firstName}"></c:out>
						(id = <c:out value="${sender.id}"></c:out> )</td>
				</tr>
				<tr>
					<td>Amount to send:</td>
					<td><c:out value="${transaction.amount }"></c:out></td>
				</tr>
				<tr>
					<td>Sender's currency:</td>
					<td><c:out value="${transaction.senderCurrency }"></c:out></td>
				</tr>
				<tr>
					<td>Recipient:</td>
					<td><c:out
							value="${recipient.lastName}, ${recipient.firstName}"></c:out>
						(id = <c:out value="${recipient.id}"></c:out> )</td>
				</tr>
				<tr>
					<td>Recipient's currency:</td>
					<td><c:out value="${transaction.recipientCurrency }"></c:out></td>
				</tr>
				<tr>
					<td>Expected conversion rate:</td>
					<td><c:out value="${transaction.rate }"></c:out></td>
				</tr>
				<tr>
					<td>Estimated total:</td>
					<td><c:out value="${transaction.total }"></c:out></td>
				</tr>
			</tbody>
		</table>
		<br>
		<form:form action="perform" modelAttribute="transaction" method="POST">
			<form:hidden path="id" />
			<form:hidden path="senderId" />
			<form:hidden path="recipientId" />
			<form:hidden path="senderCurrency" />
			<form:hidden path="recipientCurrency" />
			<form:hidden path="amount" />
			<form:hidden path="rate" />
			<form:hidden path="total" />

			<input type="submit" value="Perform Transaction" />
			<input type="button" value="Cancel"
				onclick="window.location.href='${pageContext.request.contextPath }/client/list';" />
		</form:form>
	</div>
</body>
</html>