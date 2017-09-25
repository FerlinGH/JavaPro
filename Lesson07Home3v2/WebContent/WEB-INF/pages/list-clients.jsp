<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringBank - Show Clients</title>
</head>
<body>
	<h2>Spring Bank - Clients page</h2>
	<input type="button" value="New Client"
		onclick="window.location.href='new';" />
	<br>
	<div align="center">
		<table width="800" border="1">
			<tr>
				<th></th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Client Details</th>
				<th>Accounts</th>
			</tr>

			<c:forEach var="tempClient" items="${clients }">
				<c:url var="deleteClient" value="delete">
					<c:param name="clientId" value="${tempClient.id }" />
				</c:url>
				<tr>
					<td align="center"><a href="${deleteClient}">Delete</a></td>
					<td align="center">${tempClient.firstName }</td>
					<td align="center">${tempClient.lastName }</td>
					<td align="center">${tempClient.email }</td>
					<c:choose>
						<c:when test="${tempClient.clientDetail==null }">
							<c:url var="addLink" value="/detail/add">
								<c:param name="clientId" value="${tempClient.id }" />
							</c:url>
							<td align="center"><a href="${addLink}">Add Details</a></td>
						</c:when>
						<c:otherwise>
							<c:url var="updateLink" value="/detail/update">
								<c:param name="clientId" value="${tempClient.id }" />
							</c:url>
							<c:url var="deleteLink" value="/detail/remove">
								<c:param name="clientId" value="${tempClient.id }" />
							</c:url>
							<td align="center"><a href="${updateLink}">Update
									Details</a> | <a href="${deleteLink}">Delete Details</a></td>
						</c:otherwise>
					</c:choose>
					<c:url var="accountsManage" value="/account/list">
						<c:param name="clientId" value="${tempClient.id }" />
					</c:url>
					<td align="center"><a href="${accountsManage }">View</a></td>
				</tr>

			</c:forEach>
		</table>
	</div>

</body>
</html>