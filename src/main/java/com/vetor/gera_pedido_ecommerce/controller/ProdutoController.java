package com.vetor.gera_pedido_ecommerce.controller;

import com.vetor.gera_pedido_ecommerce.model.Resposta;
import com.vetor.gera_pedido_ecommerce.model.produtos.CodigoBarrasModel;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import com.vetor.gera_pedido_ecommerce.model.token.Token;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import com.vetor.gera_pedido_ecommerce.service.ProdutoService;
import com.vetor.gera_pedido_ecommerce.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller

@RequestMapping("/ws/produto")
public class ProdutoController {

    @Autowired
    TokenService tokenService;
    @Autowired
    ProdutoService produtoService;

    //LISTAR TODOS OS PRODUTOS (POR NOME)
    @GetMapping("/listAll")
    public String listaProduto(Model model) {

        List<ProdutoModel> list = new ArrayList<>();

        List<Token> listToken = tokenService.listarToken();
        model.addAttribute("grupoSelecionado", listToken);
        return "listarProdutos";
    }
    //LISTA TODOS OS CODIGOS DE BARRAS
    @GetMapping("/listacodbarras/{grupo}")
    @ResponseBody
    public List<CodigoBarrasModel> listaCodigoBarras(@PathVariable("grupo") String grupo){

        return produtoService.listarCodigoBarras(grupo);
    }

    //LISTA TODOS OS PRODUTOS

    @GetMapping("/listaprodutos/{grupo}")
    @ResponseBody
    public List<ProdutoModel>listProduto(@PathVariable("grupo")String grupo){

        List<ProdutoModel> listProd = produtoService.listaProdutos(grupo);
        return listProd;

    }

}
