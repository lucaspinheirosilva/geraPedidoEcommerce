package com.vetor.gera_pedido_ecommerce.controller;

import com.vetor.gera_pedido_ecommerce.model.Resposta;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoCliente;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoPagamento;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import com.vetor.gera_pedido_ecommerce.model.token.Token;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import com.vetor.gera_pedido_ecommerce.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ws/pedido" )
public class PedidoController {

    @Autowired
    PedidoService service = new PedidoService();
    @Autowired
    TokenService tokenService;

    Boolean sucess;
    Resposta resp = new Resposta();


    //CADASTRO DE PEDIDOS

    @GetMapping("/add")
    //@CrossOrigin(origins = "http://localhost:8080/add")
    /*https://www.onlinetutorialspoint.com/spring-boot/how-to-enable-spring-boot-cors-example-crossorigin.html*/
    public String pedidoFormulario(Model model) {
        Map<String, Object> map = new HashMap<>();
        List<Token> listToken = tokenService.listarToken();


        map.put("pedido", new PedidoModel());
        map.put("cliente", new PedidoCliente());
        map.put("produto", new PedidoProduto());
        map.put("pagamento", new PedidoPagamento());
        map.put("token", new Token());
        map.put("grupoSelecionado", listToken);

        model.addAllAttributes(map);
        return "addPedido";
    }

    @PostMapping("/criarpedido")
    public String criarPedido(ModelMap modelMap,
                              @Valid PedidoModel pedidoModel,
                              @Valid PedidoCliente pedidoCliente,
                              @Valid PedidoProduto pedidoProduto,
                              @Valid PedidoPagamento pedidoPagamento,
                              @Valid Token token) throws URISyntaxException {


        Resposta resp = service.enviar(pedidoModel, pedidoCliente, pedidoProduto, pedidoPagamento, token);
        this.resp = resp;
        //ERRO AO CRIAR PEDIDO
        if (resp.getCodigo_pedido() == 0) {
            sucess = true;
            ResponseEntity.ok().build();
            return "redirect:/mensagem";
            //SUCESSO AO CRIAR PEDIDO
        } else {
            sucess = false;
            return "redirect:/mensagem";
        }
    }

    //RETORNO DE MENSSAGEM (sucesso ou erro) DO CADASTRO DE PEDIDO
    @GetMapping("/mensagem")
    public String retorno(ModelMap modelMap) {
        String mensagemRetorno;
        String urlImagem;
        String motivoErro = "";
        if (sucess) {
            mensagemRetorno = "ERRO AO CRIAR PEDIDO, FALE COM O ADMINISTRADOR!!";
            urlImagem = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-oB_vwYAH-l74eNksTsds5uwc9SLf_gpRsQ&usqp=CAU";
            motivoErro = resp.getMensagem();


        } else {
            urlImagem = "https://static6.depositphotos.com/1080758/571/v/600/depositphotos_5713103-stock-illustration-answer22.jpg";
            mensagemRetorno = "PEDIDO CRIADO COM SUCESSO, VERIFIQUE NO Mitryus.com";

        }

        modelMap.addAttribute("resposta", mensagemRetorno);
        modelMap.addAttribute("imagem", urlImagem);
        modelMap.addAttribute("motivoErro", motivoErro);
        return "mensagem.html";
    }



}
