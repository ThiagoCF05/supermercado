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
			<table class="table table-hover">
				<thead>
					<tr>
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
					<c:forEach items="${enderecos }" var="endereco">
						<tr>
							<td>${endereco.cep }</td>
							<td>${endereco.rua }</td>
							<td>${endereco.avenida }</td>
							<td>${endereco.numero }</td>
							<td>${endereco.cidade }</td>
							<td>${endereco.estado }</td>
							<td>
								<input type="hidden" value="${endereco.id }" /> 
								<a class="remove" href="">
									<i class="icon-remove"></i>
								</a> 
								<img alt="Waiting" src="/walcupom/resources/img/ajax-loader.gif" width="20%" height="20%" style="display: none">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form:form commandName="endereco" method="POST" 
			action="/walcupom/enderecos/add">
				<div class="row">
					<div class="span4">
						<div class="control-group string required">
							<label class="string required control-label" for="cep"><abbr title="required">*</abbr> CEP</label>
							<div class="controls">
								<form:input path="cep" cssClass="string required span3" onKeyPress="MascaraCep(endereco.cep);" size="10" />
								<form:hidden path="user" value="${ username }"  />
							</div>
						</div>
					</div>
					<div class="span2">
						<div class="control-group string required">
							<label class="string required control-label" for="numero"><abbr title="required">*</abbr> Número</label>
							<div class="controls">
								<form:input path="numero" cssClass="string required span2" size="5" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span6">
						<div class="control-group string required">
							<label class="string required control-label" for="rua"><abbr title="required">*</abbr> Rua</label>
							<div class="controls">
								<form:input path="rua" cssClass="string required span6" size="50" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span4">
						<div class="control-group string required">
							<label class="string required control-label" for="avenida"><abbr title="required">*</abbr> Bairro</label>
							<div class="controls">
								<form:input path="avenida" cssClass="string required span4" size="50" />
							</div>
						</div>
					</div>
					<div class="span2">
						<div class="control-group string required">
							<label class="string required control-label" for="bloco"><abbr title="required">*</abbr> Bloco</label>
							<div class="controls">
								<form:input path="bloco" cssClass="string required span2" size="10" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span4">
						<div class="control-group string required">
							<label class="string required control-label" for="cidade"><abbr title="required">*</abbr> Cidade</label>
							<div class="controls">
								<form:input path="cidade" cssClass="string required span3" size="50" />
							</div>
						</div>
					</div>
					<div class="span2">
						<div class="control-group string required">
							<label class="string required control-label" for="estado"><abbr title="required">*</abbr> Estado</label>
							<div class="controls">
								<form:input path="estado" cssClass="string required span1" size="2" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="form-actions">
					<input class="btn btn-primary" name="commit" type="submit" value="Criar Endereço">
					<a class="btn btn-danger" href="<c:url value="/"/>">Cancel</a> 
				</div>
			</form:form>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script type="text/javascript" src="/walcupom/resources/js/mascarasValidacao.js"></script> 
	<script>
		$(document).ready(function(){
			$("#cep").blur(function(){
				var validado = ValidaCep(endereco.cep)
				if(validado == false){
					$('#rua').attr("value", "");
					$('#avenida').attr("value", "");
					$('#cidade').attr("value", "");
					$('#estado').attr("value", "");
					$("#msg").html("CEP Inválido");
			        $("#alertMessage").slideDown();
				}
				else{
					var site = "http://cep.republicavirtual.com.br/web_cep.php?formato=json&cep=" + $("#cep").val();
					$.ajax({
						url : site,
						type : 'GET',
						dataType: 'json',
						success : function(data){
							if(data.resultado == 1){
								$('#rua').attr("value", "");
		                        $('#rua').attr("value", data.tipo_logradouro  + " " + data.logradouro);
		                        $('#avenida').attr("value", "");
		                        $('#avenida').attr("value", data.bairro);
		                        $('#cidade').attr("value", "");
		                        $('#cidade').attr("value", data.cidade);
		                        $('#estado').attr("value", "");
		                        $('#estado').attr("value", data.uf);
		                    }
						},
						error: function(xhr, textStatus, errorThrown){
							$('#rua').attr("value", "");
							$('#avenida').attr("value", "");
							$('#cidade').attr("value", "");
							$('#estado').attr("value", "");
			            }  
					});
				}
			});
		});
		$("#closeMessage").click(function(event){
			event.preventDefault();
			$("#alertMessage").slideToggle();
		});
		
		$(".remove").click(function(event){
			event.preventDefault();
			var tabela = $(this).closest("td");
			var id = tabela.children("input").attr("value");
			$(this).fadeOut();
			tabela.children("img").fadeIn();
			
			$.ajax({
			     url: "/walcupom/enderecos/delete/" + id,
			     type: 'GET',
			     context: document.getElementById("msg"),
			     success: function(data){
			    	 $("#msg").html(data);
			         $("#alertMessage").slideDown();
			         tabela.closest("tr").fadeOut();
			     },
			     dataType: 'text'
			 }); 
		});
		
		
	</script>
	</body>
</html>