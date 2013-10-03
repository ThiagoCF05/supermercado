<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<html lang="en">
<jsp:include page="../menu/header.jsp" />
<body>
	<div class="container">
		<jsp:include page="../menu/topMenu.jsp" />
		<h1>Adicionar Categoria</h1>
		<form:form commandName="categoria" method="POST"
			action="/walcupom/categorias/add">
			<div class="row">
				<div class="span3">
					<div class="control-group string required">
						<label class="string required control-label"
							for="nome"><abbr title="required">*</abbr>
							Nome</label>
						<div class="controls">
							<form:input path="nome"
								cssClass="string required span2" size="50" />
						</div>
					</div>
				</div>
			</div>
			<div class="control-group string required">
				<label class="string required control-label" for="dataCriacao"><abbr
					title="required">*</abbr> Descrição</label>
				<div class="controls">
					<textarea id="descricao" rows="2" cols="20"></textarea>
				</div>
			</div>
			<div class="form-actions">
				<input id="buttonCreate" class="btn btn-primary" name="commit"
					type="submit" value="Criar Categoria"> <a
					class="btn btn-danger" href="<c:url value="/categorias/list"/>">Cancelar</a>
			</div>
		</form:form>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
</body>
</html>