<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image Container</title>
</head>
<body>
	<c:choose>
		<c:when test="${!photos.isEmpty() }">
			<form name="input" action="deleteSelected" method="POST">
				<table>
					<tr>
						<th></th>
						<th>Image ID</th>
						<th>Actions</th>
					</tr>
					<c:forEach var="tempPhoto" items="${photos }">
						<!--  construct the "Show" link  -->
						<c:url var="showLink" value="/show/${tempPhoto.getKey() }" />

						<!--  construct the "Delete" link  -->
						<c:url var="deleteLink" value="/delete/${tempPhoto.getKey() }" />

						<tr>
							<td><input type="checkbox" name="id"
								value="${tempPhoto.getKey() }"></td>
							<td>${tempPhoto.getKey() }</td>
							<td><a href="${showLink }">Show</a> | <a
								href="${deleteLink }">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="Delete selected images">
			</form>
		</c:when>
		<c:otherwise>
			<h2>Your container is empty, add some photos first!</h2>
		</c:otherwise>
	</c:choose>
	<p>
		<a href="${pageContext.request.contextPath }/">Go Back</a>
	</p>

</body>
</html>