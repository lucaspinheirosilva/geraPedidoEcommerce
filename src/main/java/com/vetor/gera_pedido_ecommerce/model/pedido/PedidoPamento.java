package com.vetor.gera_pedido_ecommerce.model.pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoPamento {

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
