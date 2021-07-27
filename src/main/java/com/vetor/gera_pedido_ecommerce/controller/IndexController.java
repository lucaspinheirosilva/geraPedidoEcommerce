package com.vetor.gera_pedido_ecommerce.controller;

import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;


@Controller
public class IndexController {

    @Autowired
    PedidoService service = new PedidoService();

    @RequestMapping(value = "/")
    @GetMapping()
    public String GetInicial() {
        return "index";
    }

    @RequestMapping("/listAll")
    @GetMapping
    public String produto(Model model) throws URISyntaxException {
        model.addAttribute("listaProdutos", service.listaProdutos());
        return "listaProdudos";
    }

    @GetMapping("/add")
    public String pedidoFormulario(Model model){
        model.addAttribute("pedido",new PedidoModel());
        return "pedidoFormulario";
    }

    @PostMapping("/criarpedido")
    public String criarPedido(@Valid PedidoModel pedidoModel, BindingResult result) {
        if (result.hasErrors()){
            return "pedidoFormulario";
        }
        service.enviar(pedidoModel);
        return "redirect:/";

        //TODO:parei em 17:39 min
       //TODO:  https://www.youtube.com/watch?v=cVikO4bF_fg&list=PLfIr3ukPHb1Q8UoxKpH1syc45i0RR4Tqu&index=3


    }

}
