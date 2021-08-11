package com.vetor.gera_pedido_ecommerce.model.produtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodigoBarrasModel {

    private String cod_barra;
    private int cod_produto;
    private int cod_cor_1;
    private int cod_cor_2;
    private int cod_cor_3;
    private int cod_tamanho;
    private boolean integracao_ativa;
    private boolean variacao_principal;
    private String ean;
    private float qnt_estoque_disponivel;
}
