package com.vetor.gera_pedido_ecommerce.model.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCliente {

    //DADOS DO PEDIDO
    private String cpf_cnpj;
    private String nome_cliente;
    private String data_nasc_abe;
    private String fisica_juridica;

}
