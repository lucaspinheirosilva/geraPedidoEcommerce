package com.vetor.gera_pedido_ecommerce.anotacoes;

public class Anotacoes {

    //PRINT DO OBJETOS PRODUTOS
   /* System.out.println("cod_produto="+cod_produto);
            System.out.println("cod_fornecedor="+cod_fornecedor);
            System.out.println("cod_departamento="+cod_departamento);
            System.out.println("cod_grupo="+cod_grupo);
            System.out.println("cod_subgrupo="+cod_subgrupo);
            System.out.println("cod_secao="+cod_secao);
            System.out.println("cod_estacao="+cod_estacao);
            System.out.println("cod_estilo="+cod_estilo);
            System.out.println("nome_produto="+nome_produto);

            System.out.println("dsc_produto_web="+dsc_produto_web);
            System.out.println("is_ativo="+is_ativo);
            System.out.println("is_fora_linha="+is_fora_linha);
            System.out.println("ncm="+ncm);
            System.out.println("ordenacao="+ordenacao);
            System.out.println("dsc_marca="+dsc_marca);
            System.out.println("dsc_modelo="+dsc_modelo);
            System.out.println("dsc_referencia="+dsc_referencia);

            System.out.println("vl_custo_liquido="+vl_custo_liquido);
            System.out.println("vl_venda_vista="+vl_venda_vista);
            System.out.println("vl_venda_prazo="+vl_venda_prazo);

            System.out.println("peso_bruto="+peso_bruto);
            System.out.println("peso_liquido="+peso_liquido);
            System.out.println("altura="+altura);
            System.out.println("largura="+largura);
            System.out.println("comprimento="+comprimento);
            System.out.println("pronta_entrega="+pronta_entrega);
            System.out.println("---------------------FIM DO PRODUTO NUMERO "+(i+1)+" de "+array.length()+" --------------------");*/


    //SETTER DO OBJETO PEDIDO
    /* pedido.setData_pedido("2021-07-19 12:00:00");
        pedido.setObservacoes("");
        pedido.setTotal_produtos(199.20F);
        pedido.setTotal_frete(0.00F);
        pedido.setTotal_seguro(0.00F);
        pedido.setTotal_desconto(0.00F);
        pedido.setTotal_outro(0.00F);
        pedido.setQtd_produtos(1);
        pedido.setLogradouro("Rua Sei la oque");
        pedido.setNumero("222");
        pedido.setComplemento("");
        pedido.setBairro("Perdidos");
        pedido.setCidade("Curitiba");
        pedido.setEstado("PR");
        pedido.setCep("83301131");
        pedido.setReferencia("");
        pedido.setPrazo_entrega(10);
        pedido.setOrigem("Ecommerce(Tray)");

        pedido.setNome_cliente("Cliente Teste 19/07/");
        pedido.setCpf_cnpj("06404140955");
        pedido.setData_nasc_abe("1993-07-19 12:00:00");
        pedido.setFisica_juridica("F");

        pedido.setCod_barras("9000000100008");
        pedido.setDesconto(0.00F);
        pedido.setFrete(0.00F);
        pedido.setOutros(0.00F);
        pedido.setQuantidade(1);
        pedido.setSeguro(0.00F);
        pedido.setValor(199.20F);

        pedido.setData_pagamento("2020-04-01 00:00:00");
        pedido.setInf_pagamento("Cartao Xerecard");
        pedido.setStatus("E");
        pedido.setNumero_parcelas(3);
        pedido.setTipo_pagamento("C");
        pedido.setValor_pagamento(199.20F);
        pedido.setBandeira("XERECARD");
        pedido.setToken("nao_precisa");*/


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



//TODO: implementar o campo Codigo de barras, para buscar altomatico
//TODO> Colocar os campos REQUIRED novamente
//TODO> (FEITO)-melhorar a mensagem final ao cadastrar o pedido
// TODO: (FEITO)-implementar o busca CEP