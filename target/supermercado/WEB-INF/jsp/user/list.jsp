<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
	<jsp:include page="../menu/header.jsp" />

	<body>
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Username</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id }</td>
						<td>${user.userName }</td>
						<td><c:url value="/users/view/${user.userName }">View</c:url></td>
					</tr>
				
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="../menu/includeScripts.jsp" />
	</body>
</html>