<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
		<div class="container">
			<jsp:include page="../menu/topMenu.jsp" />	
			<div style="text-align: center">
				<h1>Categorias</h1>
				<div class="row">
					<div class="span2">
						<select id="status">
							<option value="ativo">Ativa</option>
							<option value="inativo">Inativa</option>
						</select>
					</div>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>
							<a href="<c:url value="/categorias/add"/>"><i class="icon-plus"></i></a>
							Id
						</th>
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
								<c:if test="${categoria.removido == false }">
									<a id="${categoria.id }" href="javascript:void(0)" onclick="removeCategoria('${categoria.id}')">
										<i class="icon-remove"></i>
									</a>
								</c:if> 
								<img alt="Waiting" src="/walcupom/resources/img/ajax-loader.gif" width="40%" height="40%" style="display: none">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script>
		$("#closeMessage").click(function(event){
			event.preventDefault();
		    $("#alertMessage").slideUp();
		  });
	
		$("#status").change(function(event){
			var e = document.getElementById("status");
			var valor = e.options[e.selectedIndex].value;
			
			$.ajax({
				dataType : "json",
				method : "GET",
				url: "/walcupom/categorias/listJson?status=" + valor,
				success : function(data){
					$("tbody").html("");
			    	 $.each(data, function(key, value){
			    		 var linha = "<tr> <td>"+ value.id +"</td>" +
			    		 		"<td>"+ value.nome +"</td>" +
								"<td>"+value.descricao+"</td>" +
								"<td>"+value.removido+"</td>" +
								"<td>"+value.user+"</td>" +
								"<td>"+new Date(value.dataCriacao).toLocaleString()+"</td>";
						if(valor == "inativo"){
							linha += "<td></td></tr>";
						}
						else{
							linha += "<td>" +
							"<input type=\"hidden\" value=\"" +value.id +"\" />" +
									"<a class=\""+value.id+"\" href=\"javascript:void(0)\" onclick=\"removeCategoria(\'"+value.id+"\')\"><i class=\"icon-remove\"></i></a>" +
									"<img alt=\"Waiting\" src=\"/walcupom/resources/img/ajax-loader.gif\" width=\"20%\" height=\"20%\" style=\"display:none\">"+
								"</td>" +
							"</tr>";
						}
							$("tbody").append(linha);
			    	 });
				}
			});
		});
		
		function removeCategoria(id){
			event.preventDefault();
			var confirmou = confirm("Tem certeza que deseja remover a categoria?");
			if(confirmou == true){
				var tabela = $("#" + id).closest("td");
				var id = tabela.children("input").attr("value");
				$("#" + id).fadeOut();
				tabela.children("img").fadeIn();
				
				$.ajax({
				     url: "/walcupom/categorias/delete/" + id,
				     type: 'GET',
				     success: function(data){
				    	 if(data == "OK"){
				    		 $("#msg").html("Categoria removida com sucesso.");
					         $("#alertMessage").slideDown();
					         tabela.closest("tr").fadeOut();
				    	 }
				    	 else{
				    		 $("#msg").html("Não foi possível remover a Categoria.");
					         $("#alertMessage").slideDown();
				    	 }
				     },
				     dataType: 'text'
				 }); 
			}
		}
	</script>
	</body>
</html>