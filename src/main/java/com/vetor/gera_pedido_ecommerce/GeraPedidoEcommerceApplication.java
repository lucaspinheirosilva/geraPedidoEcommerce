package com.vetor.gera_pedido_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"package com.vetor.gera_pedido_ecommerce.controller",
        "com.vetor.gera_pedido_ecommerce.model",
        "com.vetor.gera_pedido_ecommerce.model.pedido",
        "com.vetor.gera_pedido_ecommerce.model.produtos",
        "com.vetor.gera_pedido_ecommerce.model.token",
        "com.vetor.gera_pedido_ecommerce.repository",
        "com.vetor.gera_pedido_ecommerce.service"})
public class GeraPedidoEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeraPedidoEcommerceApplication.class, args);
    }

}
