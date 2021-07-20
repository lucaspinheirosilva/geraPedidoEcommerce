package com.vetor.gera_pedido_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.vetor.gera_pedido_ecommerce.controller"})
public class GeraPedidoEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeraPedidoEcommerceApplication.class, args);
	}

}
