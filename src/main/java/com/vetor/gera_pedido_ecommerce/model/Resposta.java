package com.vetor.gera_pedido_ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resposta {
    private int status;
    private int codigo_pedido;
    private String mensagem;
}

