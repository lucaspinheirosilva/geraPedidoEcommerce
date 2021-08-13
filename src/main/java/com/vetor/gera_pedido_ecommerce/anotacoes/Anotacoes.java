package com.vetor.gera_pedido_ecommerce.anotacoes;

public class Anotacoes {

    //ESTRUTURA DO get PRODUTOS ECOMMERCE
    /*
     */


    //ESTRUTURA DO post PEDIDO
   /* {
        "data_pedido" : "2020-04-01 00:00:00",
            "observacoes" : "INTEGRACAO DO PEDIDO",
            "total_produtos" : 100.00,
            "total_frete" : 0.00,
            "total_seguro" : 0.00,
            "total_desconto" : 0.00,
            "total_outro" : 0.00,
            "qtd_produtos" : 2.00,
            "logradouro" : "RUA X",
            "numero" : "SEM NUMERO",
            "complemento" : "CASA 1",
            "bairro" : "CENTRO",
            "cidade" : "CURITIBA",
            "estado" : "PR",
            "cep" : "80300000",
            "referencia" : "",
            "prazo_entrega" : 5,
            "origem" : "MERCADO LIVRE/B2W/AMAZON/ETC...",

            "cliente" : {
        "cpf_cnpj" : "02964489904",
                "nome_cliente" : "CLIENTE TESTE",
                "data_nasc_abe": "2020-04-01 00:00:00",
                "fisica_juridica" : "F"
    },

        "produtos" :
	[
        {
            "cod_barras" : "9000000100008",
                "valor" : 70.00,
                "frete" : 0.00,
                "seguro" : 0.00,
                "desconto" : 0.00,
                "outros" : 0.00,
                "quantidade" : 1.00
        },
        {
            "cod_barras" : "9000000200036",
                "valor" : 30.00,
                "frete" : 0.00,
                "seguro" : 0.00,
                "desconto" : 0.00,
                "outros" : 0.00,
                "quantidade" : 1.00
        }
	],

        "pagamentos" :
        [
        {
            "tipo_pagamento" : "C",
                "valor_pagamento" : 100.00,
                "token" : "",
                "data_pagamento" : "2020-04-01 00:00:00",
                "status" : "E",
                "inf_pagamento" : "CARTAO VISA",
                "numero_parcelas" : 1,
                "bandeira" : "VISA"
        }
        ]
    }*/
}


//TODO> Colocar os campos REQUIRED novamente

//TODO> (FEITO)Implementar o mesmo retorno de erro da tela de pedidos(os listar o token) na listagem de produtos
//TODO> (FEITO)arrumar a função de listar produtos
//TODO> (FEITO)-melhorar a mensagem final ao cadastrar o pedido
//TODO: (FEITO)-implementar o busca CEP
//TODO: (FEITO)implementar o campo Codigo de barras, para buscar altomatico
//TODO: (FEITO)Implementar retorno de erro caso tenha algum problema ao LISTAR O TOKEN ao selecionar o GRUPO