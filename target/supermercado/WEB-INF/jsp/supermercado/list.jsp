<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
		<div class="container">
			<jsp:include page="../menu/topMenu.jsp" />	
			<h1 style="text-align:center">Endereços</h1>
			<c:url value="/supermercados/add" var="addSupermercado" />
			<div class="row">
				<div class="span6">
					<a href="${addSupermercado }">Adiciona Supermercado</a>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Nome do Estabelecimento</th>
						<th>Latitude</th>
						<th>Longitude</th>
						<th>CEP</th>
						<th>Rua</th>
						<th>Bairro</th>
						<th>Número</th>
						<th>Cidade</th>
						<th>Estado</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${supermercados }" var="supermercado">
						<tr>
							<td>${supermercado.nomeEstabelecimento }</td>
							<td>${supermercado.latitude }</td>
							<td>${supermercado.longitude }</td>
							<td>${supermercado.cep }</td>
							<td>${supermercado.rua }</td>
							<td>${supermercado.bairro }</td>
							<td>${supermercado.numero }</td>
							<td>${supermercado.cidade }</td>
							<td>${supermercado.estado }</td>
							<td>
								<input type="hidden" value="${supermercado.id }" /> 
								<a id="${supermercado.id }" href="javascript:void(0)" onclick="removeEndereco('${supermercado.id}');">
									<i class="icon-remove"></i>
								</a>
								<img alt="Waiting" src="<c:url value="/resources/img/ajax-loader.gif"/>" width="20%" height="20%" style="display: none">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script type="text/javascript" src="<c:url value="/resources/js/mascarasValidacao.js"/>"></script> 
	<script>
			
		$("#closeMessage").click(function(event){
			event.preventDefault();
			$("#alertMessage").slideToggle();
		});
		
		function removeEndereco(id){
			var confirma = confirm("Tem certeza que deseja remover o Supermercado?");
			if(confirma == true){
				event.preventDefault();
				var tabela = $("#"+id).closest("td");
				$("#"+id).fadeOut();
				tabela.children("img").fadeIn();
				
				$.ajax({
				     url: "/supermercados/delete/" + id,
				     type: 'GET',
				     context: document.getElementById("msg"),
				     success: function(data){
				    	 $("#msg").html(data);
				         $("#alertMessage").slideDown();
				         tabela.closest("tr").fadeOut();
				     },
				     dataType: 'text'
				 }); 
				
			}
		}
		
		
	</script>
	</body>
</html>