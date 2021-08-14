//BUSCA CODIGO DE BARRAS POR GRUPO
$("#grupo").change(function () {
	$.ajax({
		url: '/ws/produto/listaprodutos/' + $(this).val(),
		dataType: 'json',
		success: function (response) {

            var retorno
			var dataSet = [];
			var tamanho = response.length;

			$("#produtos").empty();

			for (var i = 0; i < tamanho; i++) {
				retorno = response[i]["retorno"]
			}
			if (retorno != null) {
				$("#erro").empty();
				$("#erro").append("<p>" + retorno + "</p>")
			}
			else {

                $("#produtos").append("<tr><th>" + 'ID' + "</th><th>" + 'PRODUTO' + "</td><th>" + 'VALOR' + "</th></tr>");
			response.forEach(element => {
				var codprod = element.cod_produto
				var prod = element.nome_produto
				var valor = element.vl_venda_vista
				var valorMasked = valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
				$("#produtos").append("<tr><td>" + codprod + "</td><td>" + prod + "</td><td>" + valorMasked + "</td></tr>");
			});

			}









		}
	});
});