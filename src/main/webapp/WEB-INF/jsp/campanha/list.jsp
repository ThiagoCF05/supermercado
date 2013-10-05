<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
	<div class="container">
		<jsp:include page="../menu/topMenu.jsp" />
		<div style="text-align: center">
			<h1>Campanhas</h1>
			<div class="row">
				<div class="span2">
					<input type="hidden" value="todas"> <select id="type">
						<option value="todas">Todas</option>
						<option value="aberta">Abertas</option>
						<option value="encerradas">Encerradas</option>
					</select>
				</div>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>
						<a href="<c:url value="/campaigns/add"/>"><i class="icon-plus"></i></a>
						Tipo	
					</th>
					<th>Data de Início</th>
					<th>Data de Encerramento</th>
					<th>Criado em</th>
					<th>Quantidade de Cupons</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${campanhas }" var="campanha">
					<tr>
						<td>${campanha.tipo }</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
								value="${campanha.dataInicio }" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
								value="${campanha.dataEncerramento }" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
								value="${campanha.dataCriacao }" /></td>
						<td>${campanha.quantidadeCupons }</td>
						<td><input type="hidden" value="${campanha.id }" /> 
						<a href="<c:url value="/campaigns/view/${campanha.id }"/>">
							<i class="icon-zoom-in"></i>
						</a> 
						<a id="${campanha.id }" href="javascript:void(0)" onclick="removeCampanha('${campanha.id}');">
							<i class="icon-remove"></i>
						</a>
						<img alt="Waiting" src="<c:url value="/resources/img/ajax-loader.gif"/>" width="20%"
							height="20%" style="display: none"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pager">
			<li class="disabled"><a
				href="<c:url value="/${pageInfo.firstPage}"/>"> &laquo; &nbsp;
					Primeira </a></li>
			<li><a href="<c:url value="/${pageInfo.previousPage}"/>">
					&lt; &nbsp; Anterior </a></li>
			<li><a href="<c:url value="/${pageInfo.nextPage}"/>">
					Próxima &nbsp; &gt; </a></li>
			<li><a href="<c:url value="/${pageInfo.lastPage}"/>">
					Última &nbsp; &raquo; </a></li>
		</ul>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script type='text/javascript' src='<c:url value="/resources/js/listCampanha.js"/>'></script>
	</body>
</html>