package com.vetor.gera_pedido_ecommerce.controller;

import com.vetor.gera_pedido_ecommerce.model.produtos.CodigoBarrasModel;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    PedidoService service;

    //LISTA TODOS OS CODIGOS DE BARRAS
    @RequestMapping(value = "/listacodbarras/{grupo}")
    @GetMapping()
    public List<CodigoBarrasModel> listaCodigoBarras(@PathVariable("grupo") String grupo){

        return service.listarCodigoBarras(grupo);
    }

    //LISTA TODOS OS PRODUTOS
    @RequestMapping(value = "/listaprodutos/{grupo}")
    @GetMapping()
    public List<ProdutoModel>listProduto(@PathVariable("grupo")String grupo){

        List<ProdutoModel> listProd = service.listaProdutos(grupo);
        return listProd;

    }
}
