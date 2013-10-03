<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
		<div class="container">
			<jsp:include page="../menu/topMenu.jsp" />	
			<h1 style="text-align:center">Categorias</h1>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>Descrição</th>
						<th>Removido</th>
						<th>Usuário</th>
						<th>Data de Criação</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categorias }" var="categoria">
						<tr>
							<td>${categoria.id }</td>
							<td>${categoria.nome }</td>
							<td>${categoria.descricao }</td>
							<td>${categoria.removido }</td>
							<td>${categoria.user }</td>
							<td>${categoria.dataCriacao }</td>
							<td>
								<input type="hidden" value="${categoria.id }" /> 
								<a class="remove" href="">
									<i class="icon-remove"></i>
								</a> 
								<img alt="Waiting" src="/walcupom/resources/img/ajax-loader.gif" width="20%" height="20%" style="display: none">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	</body>
</html>