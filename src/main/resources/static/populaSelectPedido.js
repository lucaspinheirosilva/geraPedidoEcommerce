	//BUSCA CODIGO DE BARRAS POR GRUPO
$("#grupo").change(function(){
        		$.ajax({
    		    url: '/listacodbarras/'+$(this).val(),
    			dataType: 'json',
    			success: function(response){

    			var tamanho = response.length;
    			var retorno;
    			$("#codbarras").empty();

    			for(var i=0;i<tamanho;i++){
    			retorno =response[i]["retorno"]
    			}

    			if(retorno!=null){
    					$("#erro").empty();
    			$("#erro").append("<p>"+retorno+"</p>")
    			}
    			else{
    			for( var i = 0; i<tamanho; i++){
    			    $("#erro").empty();
                    var codbarras = response[i]['cod_barra'];
                    $("#codbarras").append("<option value='"+codbarras+"'>"+codbarras+"</option>");
                    }
                 }


    				$("#valorprod").focus();
    			}
    		});
    	});