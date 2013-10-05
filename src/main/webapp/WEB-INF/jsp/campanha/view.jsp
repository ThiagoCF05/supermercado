<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
		<div class="container">
			<jsp:include page="../menu/topMenu.jsp" />
			<div class="row-fluid">
				<div class="span3"><h3>Campanha</h3></div>
			</div>
			
			<div  class="row">
				<div class="span2">
					<button type="button" id="validar" class="btn btn-success">Validar Cupom</button>
				</div>
			</div>
			<div id="divValida" class="row" style="display:none">
				<div class="span3" >
					<hr />
					<form class="form-search">
						<input type="text" class="input-medium search-query">
						<button id="validarCupom" type="submit" class="btn">Validar</button>
					</form>
					<span id="success" class="label label-success" style="display:none">Validado</span>
					<span id="fail" class="label label-inverse" style="display:none"></span>
					<hr />
				</div>
			</div>
			
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Tipo</th>
						<th>Quantidade de Cupons</th>
						<th>Data de Início</th>
						<th>Data de Encerramento</th>
						<th>Limite de Aceite</th>
						<th>Limite de Uso</th>
						<th>Data de Criação</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							${campanha.tipo }
							<input id="campanhaId" type="hidden" value="${campanha.id }" />
						</td>
						<td>${campanha.quantidadeCupons }</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${campanha.dataInicio }" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${campanha.dataEncerramento }" /></td>
						<td>${campanha.limiteAceite }</td>
						<td>${campanha.limiteUso }</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${campanha.dataCriacao }" /></td>
					</tr>
				</tbody>
			</table>
			
			<h5>Descrição</h5>
			<p class="lead">
				${campanha.descricao }
			</p>
			
			<h5>Cupons</h5>
		</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script>
		$("#validar").click(function(){
			$("#divValida").slideToggle();
		});
		
		$("#validarCupom").click(function(event){
			var cupom = $(this).closest("input").attr("value");
			var campanha = $("#campanhaId").attr("value");
			
			$("#success").css("display", "none");
			$("#fail").css("display", "none");
			
			if(cupom != null && cupom != ""){
				$.ajax({
					url: "~/campaigns/validarCupom/" + cupom + "&" + campanha,
				    type: 'GET',
				    context: document.getElementById("msg"),
				    success: function(data){
				    	 if(data == "validado")
				    		 $("#success").css("display", "block");
				    	 else
				    		 $("#fail").css("display", "block");
				     },
				     dataType: 'text'
				});
			}
			else
				$("#fail").css("display", "block");
		});
	</script>
	</body>
</html>