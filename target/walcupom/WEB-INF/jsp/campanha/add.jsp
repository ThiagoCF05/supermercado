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
		
		<!--  Links -->
		<c:url value="/files/upload" var="upload" />
		<c:url value="/campaigns/add" var="addCampaigns" />
		<!-- ******* -->
		
		<h1>Upload</h1>
		<form:form commandName="form" method="POST"
			action="${upload }" enctype="multipart/form-data">
			<div class="row">
				<div class="span6">
					<div class="control-group string required">
						<label class="string required control-label" for="image"><abbr
							title="required">*</abbr> Imagem</label>
						<div class="controls">
							<input name="image" type="file" />
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<div class="row">
			<div class="span2">
				<input class="btn btn-primary" onclick="uploadJqueryForm()"
					type="submit" value="Upload">
			</div>
			<div class="span2">
				<div class="progress">
					<div class="bar"></div>
					<div class="percent">0%</div>
				</div>
			</div>
		</div>
		<h1>Adicionar Campanha</h1>
		<form:form commandName="campanha" method="POST"
			action="${addCampaigns }">
			<div class="row">
				<div class="span3">
					<div class="control-group string required">
						<label class="string required control-label" for="tipo"><abbr
							title="required">*</abbr> Tipo Campanha</label>
						<div class="controls">
							<form:select path="tipo">
								<form:option value="produto_gratis">Produto Grátis</form:option>
								<form:option value="desconto_protudo">Desconto em Produto</form:option>
								<form:option value="entrada_gratis">Entrada Grátis</form:option>
								<form:option value="desconto_entrada">Desconto Entrada</form:option>
							</form:select>
							<form:hidden path="user" value="${campanha.user.id }" />
							<input type="hidden" id="imagens[0]" name="imagens[0]" value="" />
						</div>
					</div>
				</div>
				<div class="span3">
					<div class="control-group string required">
						<label class="string required control-label"
							for="quantidadeCupons"><abbr title="required">*</abbr>
							Quantidade de Cupons</label>
						<div class="controls">
							<form:input path="quantidadeCupons"
								cssClass="string required span2" size="50" />
						</div>
					</div>
				</div>
			</div>
			<div class="control-group string required">
				<label class="string required control-label" for="dataCriacao"><abbr
					title="required">*</abbr> Data de Início</label>
				<div class="controls">
					<form:hidden path="dataInicio" />
					<input id="data" type="text"
						class="string required span2 datepicker" /> <select id="horas"
						class="span1">
						<c:forEach items="${horas }" var="hora">
							<option value="${hora }">${hora }</option>
						</c:forEach>
					</select> <select class="span1" id="ampm">
						<option value="am">AM</option>
						<option value="pm">PM</option>
					</select> <select id="minutos" class="span1">
						<c:forEach items="${minutos }" var="minuto">
							<option value="${minuto }">${minuto }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group string required">
				<label class="string required control-label" for="dataCriacao"><abbr
					title="required">*</abbr> Data de Encerramento</label>
				<div class="controls">
					<form:hidden path="dataEncerramento" />
					<input id="dataEnc" type="text"
						class="string required span2 datepicker" /> <select
						id="horasEncerramento" class="span1">
						<c:forEach items="${horas }" var="hora">
							<option value="${hora }">${hora }</option>
						</c:forEach>
					</select> <select class="span1" id="ampmEncerramento">
						<option value="am">AM</option>
						<option value="pm">PM</option>
					</select> <select id="minutosEncerramento" class="span1">
						<c:forEach items="${minutos }" var="minuto">
							<option value="${minuto }">${minuto }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="span3">
					<div class="control-group string required">
						<label class="string required control-label" for="limiteAceite"><abbr
							title="required">*</abbr> Limite de Aceite</label>
						<div class="controls">
							<select id="minutosLimiteAceite" class="span1">
								<c:forEach items="${minutos }" var="minuto">
									<option value="${minuto }">${minuto }</option>
								</c:forEach>
							</select> <select disabled class="span2">
								<option>Minutos</option>
							</select>
							<form:hidden path="limiteAceite" />
						</div>
					</div>
				</div>
				<div class="span3">
					<div class="control-group string required">
						<label class="string required control-label" for="limiteUso"><abbr
							title="required">*</abbr> Limite para Uso</label>
						<div class="controls">
							<select id="horasLimiteUso" class="span1">
								<c:forEach items="${limiteUso }" var="hora">
									<option value="${hora }">${hora }</option>
								</c:forEach>
							</select> <select disabled class="span2">
								<option>Horas</option>
							</select>
							<form:hidden path="limiteUso" />
						</div>
					</div>
				</div>
			</div>

			<div class="control-group string required">
				<label class="string required control-label" for="enderecos"><abbr
					title="required">*</abbr> Enderecos</label>
				<div class="controls">
					<form:select path="enderecos" multiple="multiple">
						<c:forEach items="${enderecosUsuario }" var="endereco">
							<form:option value="${endereco.id }">${endereco.rua } , ${endereco.numero }</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="control-group string required">
				<label class="string required control-label" for="distancia"><abbr
					title="required">*</abbr> Distância</label>
				<div class="controls">
					<form:select path="distancia">
						<c:forEach items="${distancias }" var="distancia">
							<option value="${distancia }">${distancia }</option>
						</c:forEach>
					</form:select> <select disabled class="span2">
						<option>Kilômetros</option>
					</select>
				</div>
			</div>
			<div class="form-actions">
				<input id="buttonCreate" class="btn btn-primary" name="commit"
					type="submit" value="Criar Campanha"> <a
					class="btn btn-danger" href="<c:url value="/campaigns/list"/>">Cancelar</a>
			</div>
		</form:form>
	</div>
	<jsp:include page="../menu/includeScripts.jsp" />
	<script src="http://malsup.github.com/jquery.form.js"></script>
	<script>
		function uploadJqueryForm(){
			var bar = $('.bar');
			var percent = $('.percent');
			var status = $('#status');
		   $("#form").ajaxForm({
			   beforeSend: function() {
			        status.empty();
			        var percentVal = '0%';
			        bar.width(percentVal)
			        percent.html(percentVal);
			    },
			    uploadProgress: function(event, position, total, percentComplete) {
			        var percentVal = percentComplete + '%';
			        bar.width(percentVal)
			        percent.html(percentVal);
			    },
			    complete: function(xhr) {
			        status.html(xhr.responseText);
			    },
			   success:function(data) { 
				   if(data.substring(0,6) == "Imagem"){
					  $("#msg").html("");
					  $("#msg").html(data);
				      $("#alertMessage").slideDown();
				   }
				   else{
					   $("#imagens\\[0\\]").attr("value", data);
					   $("#msg").html("");
					   $("#msg").html("Upload de Imagem concluído com sucesso.");
				       $("#alertMessage").slideDown();
				   }
			    },
			    dataType:"text"
			  }).submit();
		}
	
		$(function() {
			$("#closeMessage").click(function(event){
				event.preventDefault();
			    $("#alertMessage").slideUp();
			  });
	  	});
		
	  $(function() {
	    $( ".datepicker" ).datepicker();
	  });
	  
	  $("#buttonCreate").click(function(event){
		  var fileName = $('#imagens\\[0\\]').attr("value");
		  if(!fileName){
			  event.preventDefault();
			  $("#msg").html("");
			  $("#msg").html("Para inserção da campanha, é necessário fazer o upload do layout do cupom.");
		      $("#alertMessage").slideDown();
		  }
		  else{
			  var dataInicio = getDataInicio();
			  getLimiteAceite();
			  getLimiteUso();
			  var dataEncerramento = getDataEncerramento(); 
			  
			  var aux = dataInicio.split(" ");
			  var aux1 = aux[0].split("/");
			  var aux2 = aux[1].split(":");
			  dataInicio = new Date(aux1[2], parseInt(aux1[0])-1, aux1[1], parseInt(aux2[0]), parseInt(aux2[1]), parseInt(aux2[2]));
			  
			  aux = dataEncerramento.split(" ");
			  aux1 = aux[0].split("/");
			  aux2 = aux[1].split(":");
			  dataEncerramento = new Date(aux1[2], parseInt(aux1[0])-1, aux1[1], parseInt(aux2[0]), parseInt(aux2[1]), parseInt(aux2[2]));
			  
			  if(dataEncerramento <= dataInicio){
				  event.preventDefault();
				  $("#msg").html("");
				  $("#msg").html("A Data de Encerramento deve ser maior que a Data de Início da Campanha");
			      $("#alertMessage").slideDown();
			  }
			  var dataAtual = new Date();
			  if(dataInicio < dataAtual){
				  event.preventDefault();
				  $("#msg").html("");
				  $("#msg").html("A Data de Início deve ser maior que a Data Atual");
			      $("#alertMessage").slideDown();
			  }
			  var quantidadeCupons = $("#quantidadeCupons").val();
			  if(quantidadeCupons < 1){
				  event.preventDefault();
				  $("#msg").html("");
				  $("#msg").html("Favor inserir um número válido de cupons a serem distribuídos");
			      $("#alertMessage").slideDown();
			  }
		  }
	  });
	  
	  function getDataInicio(){
		  var e = document.getElementById("horas");
		  var horas = e.options[e.selectedIndex].value;
		  e = document.getElementById("minutos");
		  var minutos = e.options[e.selectedIndex].value;
		  e = document.getElementById("ampm");
		  var ampm = e.options[e.selectedIndex].value;
		  var data = document.getElementById("data").value;
		  
		  var date = data + " ";
		  
		  if(ampm == "pm"){
			  horas = parseInt(horas);
			  horas = horas + 12;
			  horas = horas.toString();
			  if(horas == "24")
				  horas = "01";
		  }
		  date = date + horas + ":" + minutos + ":00";
		  document.getElementById("dataInicio").setAttribute("value", date);
		  return date;
	  }
	  
	  function getDataEncerramento(){
		  var e = document.getElementById("horasEncerramento");
		  var horas = e.options[e.selectedIndex].value;
		  e = document.getElementById("minutosEncerramento");
		  var minutos = e.options[e.selectedIndex].value;
		  e = document.getElementById("ampmEncerramento");
		  var ampm = e.options[e.selectedIndex].value;
		  var data = document.getElementById("dataEnc").value;
		  
		  var date = data + " ";
		  
		  if(ampm == "pm"){
			  horas = parseInt(horas);
			  horas = horas + 12;
			  horas = horas.toString();
			  if(horas == "24")
				  horas = "01";
		  }
		  date = date + horas + ":" + minutos + ":00";
		  document.getElementById("dataEncerramento").setAttribute("value", date);
		  return date;
	  }
	  
	  function getLimiteAceite(){
		  var e = document.getElementById("minutosLimiteAceite");
		  var minutos = e.options[e.selectedIndex].value;
		  minutos += " minutos";
		  document.getElementById("limiteAceite").setAttribute("value", minutos);
	  }
	  
	  function getLimiteUso(){
		  var e = document.getElementById("horasLimiteUso");
		  var horas = e.options[e.selectedIndex].value;
		  horas += " horas";
		  document.getElementById("limiteUso").setAttribute("value", horas);
	  }
	  
	</script>
</body>
</html>