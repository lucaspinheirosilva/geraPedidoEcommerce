//BUSCA CODIGO DE BARRAS POR GRUPO
$("#grupo").change(function () {
	$.ajax({
		url: '/ws/produto/listaprodutos/' + $(this).val(),
		dataType: 'json',
		success: function (response) {

			var dataSet = [];


			var tamanho = response.length;
			var retorno

			$("#produtos").empty();

			for (var i = 0; i < tamanho; i++) {
				retorno = response[i]["retorno"]
			}
			if (retorno != null) {
				$("#erro").empty();
				$("#erro").append("<p>" + retorno + "</p>")
			}
			else {
				$("#erro").empty();
				retorno ="AGUARDE...."
				$("#erro").append("<p>" + retorno + "</p>")
				for (var i = 0; i < tamanho; i++) {
					var codprod = response[i]['cod_produto'];
					var prod = response[i]['nome_produto'];
					var valor = response[i]['vl_venda_vista'];
					$("#produtos").append("<tr><td>" + codprod + "</td><td>" + prod + "</td><td>" + valor + "</td></tr>");
				}
				$("#erro").empty();

			}







		}
	});
});