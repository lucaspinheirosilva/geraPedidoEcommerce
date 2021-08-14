package com.vetor.gera_pedido_ecommerce.controller;
import com.vetor.gera_pedido_ecommerce.model.Resposta;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import com.vetor.gera_pedido_ecommerce.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    PedidoService service = new PedidoService();
    @Autowired
    TokenService tokenService;

    Boolean sucess;
    Resposta resp = new Resposta();


    //INDEX
    public String GetInicial() {
        return "index";
    }

}


