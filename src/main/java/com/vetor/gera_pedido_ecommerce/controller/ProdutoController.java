package com.vetor.gera_pedido_ecommerce.controller;

import com.vetor.gera_pedido_ecommerce.model.produtos.CodigoBarrasModel;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import com.vetor.gera_pedido_ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    //LISTA TODOS OS CODIGOS DE BARRAS
      @GetMapping("/listacodbarras/{grupo}")
    public List<CodigoBarrasModel> listaCodigoBarras(@PathVariable("grupo") String grupo){

        return produtoService.listarCodigoBarras(grupo);
    }

    //LISTA TODOS OS PRODUTOS

    @GetMapping("/listaprodutos/{grupo}")
    public List<ProdutoModel>listProduto(@PathVariable("grupo")String grupo){

        List<ProdutoModel> listProd = produtoService.listaProdutos(grupo);
        return listProd;

    }
}
