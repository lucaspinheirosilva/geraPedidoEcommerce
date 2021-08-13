package com.vetor.gera_pedido_ecommerce.model.produtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class ProdutoModel {

    //RETORNO DE LISTA DE PRODUTOS

    private Integer cod_produto;
    private Integer cod_fornecedor;
    private Integer cod_departamento;
    private Integer cod_grupo;
    private Integer cod_subgrupo;
    private Integer cod_secao;
    private Integer cod_estacao;
    private Integer cod_estilo;

    private String nome_produto;
    private String dsc_produto_web;
    private Boolean is_ativo;
    private Boolean is_fora_linha;
    private String ncm;
    private Integer ordenacao;
    private String dsc_marca;
    private String dsc_modelo;
    private String dsc_referencia;

    private Float vl_custo_liquido;
    private Float vl_venda_vista;
    private Float vl_venda_prazo;

    private Float peso_bruto;
    private Float peso_liquido;
    private Float altura;
    private Float largura;
    private Float comprimento;
    private Boolean pronta_entrega;

    private String retorno;


}

