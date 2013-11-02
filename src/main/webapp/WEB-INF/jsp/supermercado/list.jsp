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
			<div class="row" style="margin-top:10px">
				<div class="span1">
					<select id="searchType">
						<option value="bairro">Bairro</option>
						<option value="cidade">Cidade</option>
						<option value="rede">Rede</option>
					</select>
				</div>
				<div class="offset2 span9">
					<input type="hidden" id="type" value="bairro" />
					<form id="byCidade" method="GET" class="form-search" style="display:none">
						<select id="cidade">
							<c:forEach items="${cidades }" var="cidade">
								<option value="${cidade }">${cidade }</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn">
					  	<a class="search" href="<c:url value="/supermercados/list?type=cidade&cidade="/>"> Buscar</a>
					  </button>
					</form>
					
					<form id="byBairro" method="GET" class="form-search">
						<select id="bairro">
							<c:forEach items="${bairros }" var="bairro">
								<option value="${bairro }">${bairro }</option>
							</c:forEach>
						</select>
					  <button type="submit" class="btn">
					  	<a class="search" href="<c:url value="/supermercados/list?type=bairro&bairro="/>"> Buscar</a>
					  </button>
					</form>
					
					<form id="byRede" method="GET" class="form-search" style="display:none">
						<select id="rede">
							<c:forEach items="${redes }" var="rede">
								<option value="${rede }">${rede }</option>
							</c:forEach>
						</select>
					  <button type="submit" class="btn">
					  	<a class="search" href="<c:url value="/supermercados/list?type=rede&rede="/>"> Buscar</a>
					  </button>
					</form>
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
								<a href="<c:url value="/supermercados/view/${supermercado.id}" />">
									<i class="icon-zoom-in"></i>
								</a>
								<a id="${supermercado.id }" href="javascript:void(0)" onclick="removeEndereco('${supermercado.id}');">
									<i class="icon-remove"></i>
								</a>
								<img alt="Waiting" src="<c:url value="/resources/img/ajax-loader.gif"/>" width="20%" height="20%" style="display: none">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul class="pager">
				<li><a
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
	<script type="text/javascript" src="<c:url value="/resources/js/mascarasValidacao.js"/>"></script> 
	<script>
		$(document).ready(function(){
			var e = document.getElementById("bairro");
			var valor = e.options[e.selectedIndex].value;
			
			var link = $("#bairro").parent().children("button").children("a");
			
			link.attr("href", "/supermercados/list?type=bairro&bairro=" + encodeURI(valor));
			
			e = document.getElementById("cidade");
			valor = e.options[e.selectedIndex].value;
			
			link = $("#cidade").parent().children("button").children("a");
			
			link.attr("href", "/supermercados/list?type=cidade&cidade=" + encodeURI(valor));
		});
		
		$("#searchType").change(function(event){
			event.preventDefault();
			var e = document.getElementById("searchType");
			var valor = e.options[e.selectedIndex].value;
			
			if(valor == "bairro"){
				$("#byBairro").css("display", "block");
				$("#byCidade").css("display", "none");
				$("#byRede").css("display", "none");
				$("#type").attr("value", "bairro");
			}
			else if(valor == "cidade"){
				$("#byBairro").css("display", "none");
				$("#byCidade").css("display", "block");
				$("#byRede").css("display", "none");
				$("#type").attr("value", "cidade");
			}
			else{
				$("#byBairro").css("display", "none");
				$("#byCidade").css("display", "none");
				$("#byRede").css("display", "block");
				$("#type").attr("value", "rede");
			}
		});
		
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
		
		$("#bairro").change(function(event){
			var e = document.getElementById("bairro");
			var valor = e.options[e.selectedIndex].value;
			
			var link = $("#bairro").parent().children("button").children("a");
			
			link.attr("href", "/supermercados/list?type=bairro&bairro=" + encodeURI(valor));
		});
		
		$("#cidade").change(function(event){
			var e = document.getElementById("cidade");
			var valor = e.options[e.selectedIndex].value;
			
			var link = $("#cidade").parent().children("button").children("a");
			
			link.attr("href", "/supermercados/list?type=cidade&cidade=" + encodeURI(valor));
		});
		
		$("#rede").change(function(event){
			var e = document.getElementById("rede");
			var valor = e.options[e.selectedIndex].value;
			
			var link = $("#rede").parent().children("button").children("a");
			
			link.attr("href", "/supermercados/list?type=rede&rede=" + encodeURI(valor));
		});
		
		
	</script>
	</body>
</html>