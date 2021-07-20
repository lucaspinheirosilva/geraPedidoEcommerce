package com.vetor.gera_pedido_ecommerce;

import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndexController {

    PedidoService service = new PedidoService();


    //*/*/*/*/*/*/*/*/*/**/*/TESTe
    PedidoModel pedido = new PedidoModel();


    @RequestMapping(value = "/")
    @GetMapping
    public String GetInicial() {
        return "Pagina Inicial";
    }

    @RequestMapping("/produto")
    @GetMapping
    public List<ProdutoModel> produto(){
        return service.listaProdutos();
    }

    @PostMapping(
            value = "/criarpedido",
            consumes = "application/json",
            produces = "application/json")
    public PedidoModel criarPedido(
            @RequestBody PedidoModel pedido) {
        pedido.setData_pedido("2021-07-19 12:00:00");
        pedido.setObservacoes("");
        pedido.setTotal_produtos(199.20F);
        pedido.setTotal_frete(0.00F);
        pedido.setTotal_seguro(0.00F);
        pedido.setTotal_desconto(0.00F);
        pedido.setTotal_outro(0.00F);
        pedido.setQtd_produtos(1);
        pedido.setLogradouro("Rua Sei la oque");
        pedido.setNumero("222");
        pedido.setComplemento("");
        pedido.setBairro("Perdidos");
        pedido.setCidade("Curitiba");
        pedido.setEstado("PR");
        pedido.setCep("83301131");
        pedido.setReferencia("");
        pedido.setPrazo_entrega(10);
        pedido.setOrigem("Ecommerce(Tray)");

        pedido.setNome_cliente("Cliente Teste 19/07/");
        pedido.setCpf_cnpj("06404140955");
        pedido.setData_nasc_abe("1993-07-19 12:00:00");
        pedido.setFisica_juridica("F");

        pedido.setCod_barras("9000000100008");
        pedido.setDesconto(0.00F);
        pedido.setFrete(0.00F);
        pedido.setOutros(0.00F);
        pedido.setQuantidade(1);
        pedido.setSeguro(0.00F);
        pedido.setValor(199.20F);

        pedido.setData_pagamento("2020-04-01 00:00:00");
        pedido.setInf_pagamento("Cartao Xerecard");
        pedido.setStatus("E");
        pedido.setNumero_parcelas(3);
        pedido.setTipo_pagamento("C");
        pedido.setValor_pagamento(199.20F);
        pedido.setBandeira("XERECARD");
        pedido.setToken("nao_precisa");

        return service.enviar(pedido);
    }

}
