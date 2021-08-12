	//BUSCA CODIGO DE BARRAS POR GRUPO
$("#grupo").change(function(){
        		$.ajax({
    		    url: '/listaprodutos/'+$(this).val(),
    			dataType: 'json',
    			success: function(response){

    			 var dataSet = [];
    			/* $.each(response, function(index,data){
                       dataSet.push([data.cod_produto,data.nome_produto,data.vl_venda_vista]);
                     });
                      $('#produtos').DataTable({
                           data: dataSet,
                           columns: [
                             { title: 'cod' },
                             { title: 'Nome Produto' },
                             { title: 'Valor' }
                           ]
                         });*/


    			var len = response.length;
    			$("#produtos").empty();
    			 for( var i = 0; i<len; i++){
                    var codprod = response[i]['cod_produto'];
                    var prod = response[i]['nome_produto'];
                    var valor = response[i]['vl_venda_vista'];
                    $("#produtos").append("<tr><td>"+codprod+"</td><td>"+prod+"</td><td>"+valor+"</td></tr>");
    			 }


    			}
    		});
    	});