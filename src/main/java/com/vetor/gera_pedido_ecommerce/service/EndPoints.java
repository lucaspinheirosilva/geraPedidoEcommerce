package com.vetor.gera_pedido_ecommerce.service;

import lombok.Getter;

@Getter
public class EndPoints {
    final String criarPedidoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/pedido";
    final String listarProdutoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/produtos";
    final String listarCodigoBarrasURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/codigosbarra";


}
