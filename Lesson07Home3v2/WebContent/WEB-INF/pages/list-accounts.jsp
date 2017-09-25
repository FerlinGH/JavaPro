<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client Accounts</title>
</head>
<body>
	<h2>Spring Bank - Client's Accounts</h2>
	<h4>
		Current accounts of
		<c:out value="${client.lastName}, ${client.firstName}"></c:out>
		(id =
		<c:out value="${client.id}"></c:out>
		):
	</h4>
	<c:url var="newAccountLink" value="new">
		<c:param name="clientId" value="${client.id }" />
	</c:url>
	<input type="button" value="New Account"
		onclick="window.location.href='${newAccountLink }';" />
	<c:choose>
		<c:when test="${accounts.isEmpty() }">
			<h3>You don't have any accounts yet, go ahead and create one!</h3>
		</c:when>
		<c:otherwise>
			<c:url var="newTransactionLink" value="/transaction/create">
				<c:param name="clientId" value="${client.id }" />
			</c:url>
			<input type="button" value="New Transaction"
				onclick="window.location.href='${newTransactionLink }';" />
			<br>
			<br>
			<div align="left">
				<table width="400" border="1">
					<thead>
						<tr>
							<th>ID</th>
							<th>Currency</th>
							<th>Amount</th>
							<th>Fund Account</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="account" items="${accounts }">
							<tr>
								<td align="center">${account.id }</td>
								<td align="center">${account.currency }</td>
								<td align="center">${account.amount }</td>
								<td align="center">
									<form action="fund" method="POST">
										<input type="hidden" value="${client.id}" name="clientId" />
										<input type="hidden" name="accountId" value="${account.id }" />
										<input type="text" name="fundAmount" /> <input type="submit"
											value="Fund">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<form action="evaluate" method="GET">
					<input type="hidden" value="${client.id}" name="clientId">
					<input type="submit" value="Evaluate Checkout" />
				</form>
				<strong> <c:out value="${evaluationMessage }" />
				</strong>
			</div>
		</c:otherwise>
	</c:choose>

</body>
</html>