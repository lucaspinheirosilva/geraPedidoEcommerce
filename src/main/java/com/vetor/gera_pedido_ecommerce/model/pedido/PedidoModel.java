package com.vetor.gera_pedido_ecommerce.model.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoModel {

    //DADOS DO PEDIDO
    private String data_pedido;
    private String observacoes;
    private Float total_produtos;
    private Float total_frete;
    private Float total_seguro;
    private Float total_desconto;
    private Float total_outro;
    private Integer qtd_produtos;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String estado;
    private String referencia;
    private Integer prazo_entrega;
    private String origem;

    //DADOS DO CLIENTE
    private String cpf_cnpj;
    private String nome_cliente;
    private String data_nasc_abe;
    private String fisica_juridica;

    //PRODUTOS
    private String cod_barras;
    private Float valor;
    private Float frete;
    private Float seguro;
    private Float desconto;
    private Float outros;
    private Integer quantidade;

    //PAGAMENTO
    private String tipo_pagamento;
    private Float valor_pagamento;
    private String token;
    private String data_pagamento;
    private String status;
    private String inf_pagamento;
    private Integer numero_parcelas;
    private String bandeira;



}
