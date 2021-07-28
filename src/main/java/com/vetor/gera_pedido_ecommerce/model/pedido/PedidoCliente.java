package com.vetor.gera_pedido_ecommerce.model.pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoCliente {

    //DADOS DO Cliente
    private String cpf_cnpj;
    private String nome_cliente;
    private String data_nasc_abe;
    private String fisica_juridica;


}
