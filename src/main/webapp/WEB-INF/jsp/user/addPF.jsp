<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />
	<body>
		<div class="container">
			<jsp:include page="../menu/topMenu.jsp" />
			<h1>Cadastro</h1>
			<input type="hidden" id="tipoPessoa" value="${tipoPessoa }" />
			<select id="pessoa">
				<option id="fisica" value="fisica">Pessoa Física</option>
				<option id="juridica" value="juridica">Pessoa Jurídica</option>
			</select>
			
			<br />
			<div id="form">
				<c:url value="/users/addPF/pf?documento=${pessoaJuridica.CNPJ }" var="addUserPF"/>
				<form:form commandName="pessoaFisica" method="POST" 
			action="${addUserPF }">
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="firstName"><abbr title="required">*</abbr> Username</label>
								<div class="controls">
									<form:input path="userName" value="${user.userName }"  readonly="true"  cssClass="string required span3" size="50" />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="email"><abbr title="required">*</abbr> Email</label>
								<div class="controls">
									<form:input path="email" value="${user.email }"  readonly="true"  cssClass="string required span3" size="50" />
									<form:hidden path="id" value="${user.id }" />
									<form:hidden path="password" value="${user.password }" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="firstName"><abbr title="required">*</abbr> Primeiro Nome</label>
								<div class="controls">
									<form:input path="firstName" value="${pessoaFisica.firstName }"  cssClass="string required span3" size="50" />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="lastName"><abbr title="required">*</abbr> Último Nome</label>
								<div class="controls">
									<form:input path="lastName" value="${pessoaFisica.lastName }" cssClass="string required span3" size="50" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="CPF"><abbr title="required">*</abbr> CPF</label>
								<div class="controls">
									<form:input path="CPF" onKeyPress="MascaraCPF(pessoaFisica.CPF);" value="${pessoaFisica.CPF }" cssClass="string required span2" size="50" />
									<form:hidden path="idPF" value="${pessoaFisica.idPF }"  />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="bornDate"><abbr title="required">*</abbr> Data de Nascimento</label>
								<div class="controls">
									<fmt:formatDate pattern="dd/MM/yyyy" var="bornDateFormated" value="${pessoaFisica.bornDate }" />
									<form:input path="bornDate" value="${bornDateFormated }" onKeyPress="MascaraData(pessoaFisica.bornDate);" cssClass="string required span2" size="50" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="cidade"><abbr title="required">*</abbr> Cidade</label>
								<div class="controls">
									<form:input path="cidade" value="${pessoaFisica.cidade }" cssClass="string required span2" size="50" />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="estado"><abbr title="required">*</abbr> Estado</label>
								<div class="controls">
									<form:input path="estado" value="${pessoFisica.estado }" cssClass="string required span2" size="50" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-actions">
						<input class="btn btn-primary" name="commit" type="submit" value="OK">
					</div>
				</form:form>
			</div>
			<div id="aux" style="display:none">
				<c:url value="/users/addPF/pj?documento=${pessoaFisica.CPF }" var="addUserPF"/>
				<form:form commandName="pessoaJuridica" method="POST" 
			action="${addUserPF }">
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="firstName"><abbr title="required">*</abbr> Username</label>
								<div class="controls">
									<form:input path="userName" value="${user.userName }"  readonly="true"  cssClass="string required span3" size="50" />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="email"><abbr title="required">*</abbr> Email</label>
								<div class="controls">
									<form:input path="email" value="${user.email }"  readonly="true"  cssClass="string required span3" size="50" />
									<form:hidden path="id" value="${user.id }" />
									<form:hidden path="password" value="${user.password }" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="nomeFantasia"><abbr title="required">*</abbr> Nome Fantasia</label>
								<div class="controls">
									<form:input path="nomeFantasia" value="${pessoaJuridica.nomeFantasia }"  cssClass="string required span3" size="50" />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="razaoSocial"><abbr title="required">*</abbr> Razão Social</label>
								<div class="controls">
									<form:input path="razaoSocial" value="${pessoaJuridica.razaoSocial }" cssClass="string required span3" size="50" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="CNPJ"><abbr title="required">*</abbr> CNPJ</label>
								<div class="controls">
									<form:input path="CNPJ" onKeyPress="MascaraCNPJ(pessoaJuridica.CNPJ);" value="${pessoaJuridica.CNPJ }" cssClass="string required span2" size="50" />
									<form:hidden path="idPJ" value="${pessoaJuridica.idPJ }"  />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="site"><abbr title="required">*</abbr> Site</label>
								<div class="controls">
									<form:input path="site" value="${pessoaJuridica.site }" cssClass="string required span2" size="50" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="cidade"><abbr title="required">*</abbr> Cidade</label>
								<div class="controls">
									<form:input path="cidade" value="${pessoaJuridica.cidade }" cssClass="string required span2" size="50" />
								</div>
							</div>
						</div>
						<div class="span3">
							<div class="control-group string required">
								<label class="string required control-label" for="estado"><abbr title="required">*</abbr> Estado</label>
								<div class="controls">
									<form:input path="estado" value="${pessoaJuridica.estado }" cssClass="string required span2" size="50" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-actions">
						<input class="btn btn-primary" name="commit" type="submit" value="OK">
					</div>
				</form:form>
			</div>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script type="text/javascript" src="<c:url value="/resources/js/mascarasValidacao.js"/>"></script> 
	<script>
		$(document).ready(function(){
			var tipoPessoa = $("#tipoPessoa").attr("value");
			
			if(tipoPessoa == "fisica"){
				$("#fisica").attr('selected', true);
				$("#juridica").attr('selected', false);
			}
			else{
				$("#fisica").attr('selected', false);
				$("#juridica").attr('selected', true);
				
				var aux = $("#aux").html();
				 var form = $("#form").html();
				 $("#aux").html("");
				 $("#aux").html(form);
				 
				 $("#form").html("");
				 $("#form").html(aux);
			}
		});
		$("#pessoa").change(function(event){
			 var aux = $("#aux").html();
			 var form = $("#form").html();
			 $("#aux").html("");
			 $("#aux").html(form);
			 
			 $("#form").html("");
			 $("#form").html(aux);

		});
	</script>
	</body>
</html>