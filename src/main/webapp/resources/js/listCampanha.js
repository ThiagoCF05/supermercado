function removeCampanha(id){
	var confirma = confirm("Tem certeza que deseja remover a Campanha?");
	if(confirma == true){
		event.preventDefault();
		var tabela = $("#"+id).closest("td");
		$("#"+id).fadeOut();
		tabela.children("img").fadeIn();
		
		$.ajax({
		     url: "/walcupom/campaigns/delete/" + id,
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

$(document).ready(function(){
	$(function() {
		$("#closeMessage").click(function(event){
			event.preventDefault();
		    $("#alertMessage").slideUp();
		  });
  	});
	
	$("#type").change(function(event){
		event.preventDefault();
		var e = document.getElementById("type");
		var valor = e.options[e.selectedIndex].value;
		var linha = "";
		
		$.ajax({
		     url: "/walcupom/campaigns/listJson?type=" + valor,
		     type: 'GET',
		     success: function(data){
		    	 $("tbody").html("");
		    	 $.each(data, function(key, value){
		    		 var linha = "<tr> <td>"+ value.tipo +"</td>" +
		    		 		"<td>"+ new Date(value.dataInicio).toLocaleString() +"</td>" +
							"<td>"+new Date(value.dataEncerramento).toLocaleString()+"</td>" +
							"<td>"+new Date(value.dataCriacao).toLocaleString()+"</td>" +
							"<td>"+value.quantidadeCupons+"</td>" +
							"<td>" +
								"<input type=\"hidden\" value=\"" +value.id +"\" />" +
								"<a href=\"/walcupom/campaigns/view/"+value.id+"\"><i class=\"icon-zoom-in\"></i></a>" +
								"<a id=\""+value.id+"\" href=\"javascript:void(0)\" onclick=\"removeCampanha(\'"+value.id+"\')\"><i class=\"icon-remove\"></i></a>" +
								"<img alt=\"Waiting\" src=\"/walcupom/resources/img/ajax-loader.gif\" width=\"20%\" height=\"20%\" style=\"display:none\">"+
							"</td>" +
						"</tr>";
						$("tbody").append(linha);
		    	 });
		     },
		     dataType: 'json'
		 }); 
		
	});
	
});