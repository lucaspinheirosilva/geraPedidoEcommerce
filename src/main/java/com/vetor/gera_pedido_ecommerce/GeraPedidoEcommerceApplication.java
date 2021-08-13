package com.vetor.gera_pedido_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class GeraPedidoEcommerceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GeraPedidoEcommerceApplication.class, args);
	}

}
