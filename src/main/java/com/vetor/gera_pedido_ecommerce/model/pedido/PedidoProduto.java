package com.vetor.gera_pedido_ecommerce.model.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProduto {


    //PRODUTOS
    private String cod_barras;
    private Float valor;
    private Float frete;
    private Float seguro;
    private Float desconto;
    private Float outros;
    private Integer quantidade;

}
