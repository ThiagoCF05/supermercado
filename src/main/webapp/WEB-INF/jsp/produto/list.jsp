<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
		<div class="container">
			<jsp:include page="../menu/topMenu.jsp" />	
			<h1 style="text-align:center">Produtos</h1>
			<c:url value="/produtos/add" var="addProduto" />
			<div class="row">
				<div class="span6">
					<a href="${addProduto }">Adiciona Produto</a>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Código de Barra</th>
						<th>Tipo</th>
						<th>Formato</th>
						<th>Nome</th>
						<th>Nome Técnico</th>
						<th>Marca</th>
						<th>Peso</th>
						<th>Categoria</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${produtos }" var="produto">
						<tr>
							<td>${produto.codigoBarra }</td>
							<td>${produto.tipoCodigoBarra }</td>
							<td>${produto.formato }</td>
							<td>${produto.nome }</td>
							<td>${produto.nomeTecnico }</td>
							<td>${produto.marca }</td>
							<td>${produto.peso }</td>
							<td>${produto.categoria }</td>
							<td>
								<input type="hidden" value="${produto.id }" /> 
								<a href="<c:url value="/produtos/view/${produto.id}" />">
									<i class="icon-zoom-in"></i>
								</a>
								<a id="${produto.id }" href="javascript:void(0)" onclick="removeEndereco('${produto.id}');">
									<i class="icon-remove"></i>
								</a>
								<img alt="Waiting" src="<c:url value="/resources/img/ajax-loader.gif"/>" width="50%" height="50%" style="display: none">
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
			var confirma = confirm("Tem certeza que deseja remover o Produto?");
			if(confirma == true){
				event.preventDefault();
				var tabela = $("#"+id).closest("td");
				$("#"+id).fadeOut();
				tabela.children("img").fadeIn();
				
				$.ajax({
				     url: "/produtos/delete/" + id,
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