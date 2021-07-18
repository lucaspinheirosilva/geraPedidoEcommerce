package com.vetor.gera_pedido_ecommerce.repository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PedidoRepository {

    @GetMapping
    public String GetInicial(){
        return "Api para inserir pedido E-commerce";
    }
}
