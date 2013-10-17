<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
		<div class="container">
			<jsp:include page="../menu/topMenu.jsp" />	
			<h1 style="text-align:center">Adiciona Produto</h1>
			<c:url value="/produtos/add" var="addProdutos" />
			<c:url value="/produtos/list" var="listProdutos" />
			<form:form commandName="produto" method="POST" 
			action="${addProdutos }">
				<div class="row">
					<div class="span2">
						<div class="control-group string required">
							<label class="string required control-label" for="codigoBarra"><abbr title="required">*</abbr> Código de Barras</label>
							<div class="controls">
								<form:input path="codigoBarra" value="${ produto.codigoBarra }" cssClass="string required span2" size="100" />
								<form:hidden path="user" value="${ username }"  />
								<form:hidden path="id" value="${ produto.id }"  />
							</div>
						</div>
					</div>
					<div class="span2">
						<div class="control-group string required">
							<label class="string required control-label" for="tipoCodigoBarra"><abbr title="required">*</abbr> Tipo de Código de Barra</label>
							<div class="controls">
								<form:input path="tipoCodigoBarra" value="${ produto.tipoCodigoBarra }" cssClass="string required span2" size="9" />
							</div>
						</div>
					</div>
					<div class="span2">
						<div class="control-group string required">
							<label class="string required control-label" for="formato"><abbr title="required">*</abbr> Formato</label>
							<div class="controls">
								<form:input path="formato" value="${ produto.formato }" cssClass="string required span2" size="9" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span6">
						<div class="control-group string required">
							<label class="string required control-label" for="nome"><abbr title="required">*</abbr> Nome</label>
							<div class="controls">
								<form:input path="nome" value="${ produto.nome }" cssClass="string required span6" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span6">
						<div class="control-group string required">
							<label class="string required control-label" for="nomeTecnico"><abbr title="required">*</abbr> Nome Técnico</label>
							<div class="controls">
								<form:input path="nomeTecnico" value="${ produto.nomeTecnico }" cssClass="string required span6" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span4">
						<div class="control-group string required">
							<label class="string required control-label" for="marca"><abbr title="required">*</abbr> Marca</label>
							<div class="controls">
								<form:input path="marca" value="${ produto.marca }" cssClass="string required span4" size="50" />
							</div>
						</div>
					</div>
					<div class="span2">
						<div class="control-group string required">
							<label class="string required control-label" for="peso"><abbr title="required">*</abbr> Peso</label>
							<div class="controls">
								<form:input path="peso" value="${ produto.peso }" cssClass="string required span2" size="10" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span6">
						<div class="control-group string required">
							<label class="string required control-label" for="categoria"><abbr title="required">*</abbr> Categoria</label>
							<div class="controls">
								<form:input path="categoria" value="${ produto.categoria }" cssClass="string required span3" size="50" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="form-actions">
					<input class="btn btn-primary" name="commit" type="submit" value="Criar Produto">
					<a class="btn btn-danger" href="${listProdutos }">Cancelar</a> 
				</div>
			</form:form>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script>
		$("#closeMessage").click(function(event){
			event.preventDefault();
			$("#alertMessage").slideToggle();
		});
		
		
	</script>
	</body>
</html>