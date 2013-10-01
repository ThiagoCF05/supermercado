<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="../menu/header.jsp" />

<body>
	<h1 id="banner">User</h1>
	<hr/>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>Email</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${user.id }</td>
				<td>${user.userName }</td>
				<td>${user.email }</td>
				<td>${user.password }</td>
			</tr>
		</tbody>
	</table>
	<p>Authenticated User</p>

</body>
</html>