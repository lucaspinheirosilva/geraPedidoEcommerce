package com.vetor.gera_pedido_ecommerce.model;

import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoCliente;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoPagamento;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;


public class Hash_Map {
    private Map<String, Object> mapPedido = new java.util.HashMap<>();
    private Map<String, Object> mapCliente = new java.util.HashMap<>();
    private Map<String, Object> mapProduto = new java.util.HashMap<>();
    private Map<String, Object> mapPagamento = new java.util.HashMap<>();


    public Map<String, Object> mapPedido(PedidoModel pedidoModel) {
//Converte a data em um data valida pela API
        pedidoModel.setData_pedido(formatarDataHora(pedidoModel.getData_pedido()));

        mapPedido.put("data_pedido", pedidoModel.getData_pedido());
        mapPedido.put("total_produtos", pedidoModel.getTotal_produtos());

        //faz verificação se os campos opcionais estao preenchidos
        Object o = pedidoModel.getObservacoes().isEmpty() ? mapPedido.put("observacoes", "") : mapPedido.put("observacoes", pedidoModel.getObservacoes());
        Object o1 = pedidoModel.getTotal_frete() == null ? mapPedido.put("total_frete", 0.00) : mapPedido.put("total_frete", pedidoModel.getTotal_frete());
        Object o2 = pedidoModel.getTotal_seguro() == null ? mapPedido.put("total_seguro", 0.00) : mapPedido.put("total_seguro", pedidoModel.getTotal_seguro());
        Object o3 = pedidoModel.getTotal_desconto() == null ? mapPedido.put("total_desconto", 0.00) : mapPedido.put("total_desconto", pedidoModel.getTotal_desconto());
        Object o4 = pedidoModel.getTotal_outro() == null ? mapPedido.put("total_outro", 0.00) : mapPedido.put("total_outro", pedidoModel.getTotal_outro());
        Object o5 = pedidoModel.getComplemento().isEmpty() ? mapPedido.put("total_outro", "") : mapPedido.put("complemento", pedidoModel.getComplemento());
        Object o6 = pedidoModel.getReferencia().isEmpty() ? "" : mapPedido.put("referencia", pedidoModel.getReferencia());

        mapPedido.put("qtd_produtos", pedidoModel.getQtd_produtos());
        mapPedido.put("logradouro", pedidoModel.getLogradouro());
        mapPedido.put("numero", pedidoModel.getNumero());
        mapPedido.put("bairro", pedidoModel.getBairro());
        mapPedido.put("cidade", pedidoModel.getCidade());
        mapPedido.put("estado", pedidoModel.getEstado());
        mapPedido.put("cep", pedidoModel.getCep());
        mapPedido.put("prazo_entrega", pedidoModel.getPrazo_entrega());
        mapPedido.put("origem", pedidoModel.getOrigem());

        return mapPedido;
    }

    public Map<String, Object> mapCliente(PedidoCliente pedidoCliente) {
        //Cria um Json de ClientePedido
        mapCliente.put("cpf_cnpj", pedidoCliente.getCpf_cnpj());
        mapCliente.put("nome_cliente", pedidoCliente.getNome_cliente());
        //Converte a data em um data valida pela API
        pedidoCliente.setData_nasc_abe(pedidoCliente.getData_nasc_abe()+"T00:00");
        pedidoCliente.setData_nasc_abe(formatarDataHora(pedidoCliente.getData_nasc_abe()));
        mapCliente.put("data_nasc_abe", pedidoCliente.getData_nasc_abe());
        mapCliente.put("fisica_juridica", pedidoCliente.getFisica_juridica());

        return mapCliente;
    }

    public Map<String, Object> mapProduto(PedidoProduto pedidoProduto) {
        mapProduto.put("cod_barras", pedidoProduto.getCod_barras());
        mapProduto.put("valor", pedidoProduto.getValor());
        mapProduto.put("quantidade", pedidoProduto.getQuantidade());
        //faz verificação se os campos opcionais estao preenchidos
        Object o = pedidoProduto.getFrete() == null ? mapProduto.put("frete", 0.00) : mapProduto.put("frete", pedidoProduto.getFrete());
        Object o1 = pedidoProduto.getSeguro() == null ? mapProduto.put("seguro", 0.00) : mapProduto.put("seguro", pedidoProduto.getSeguro());
        Object o2 = pedidoProduto.getDesconto() == null ? mapProduto.put("desconto", 0.00) : mapProduto.put("desconto", pedidoProduto.getDesconto());
        Object o3 = pedidoProduto.getOutros() == null ? mapProduto.put("outros", 0.00) : mapProduto.put("outros", pedidoProduto.getOutros());

        return mapProduto;
    }

    public Map<String, Object> mapPagamento(PedidoPagamento pedidoPagamento) {
        mapPagamento.put("tipo_pagamento", pedidoPagamento.getTipo_pagamento());
        mapPagamento.put("valor_pagamento", pedidoPagamento.getValor_pagamento());
        Object o = pedidoPagamento.getToken()==null ? mapPagamento.put("token", "tokem de teste") : mapPagamento.put("token", pedidoPagamento.getToken());
        //Converte a data em um data valida pela API
        pedidoPagamento.setData_pagamento(formatarDataHora(pedidoPagamento.getData_pagamento()));
        mapPagamento.put("data_pagamento", pedidoPagamento.getData_pagamento());
        mapPagamento.put("numero_parcelas", pedidoPagamento.getNumero_parcelas());
        mapPagamento.put("bandeira", pedidoPagamento.getBandeira());

        return mapPagamento;

    }

    private String formatarDataHora(String data) {

        String dataFormatada = "";
        SimpleDateFormat dataParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date date = dataParse.parse(data);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            dataFormatada = dateFormat.format(date);
            System.out.println(dataFormatada);
        } catch (ParseException parseException) {
            System.out.println("---------------------->>ERRO..........");
            System.out.println(parseException);
        }

        return dataFormatada;
    }
}
