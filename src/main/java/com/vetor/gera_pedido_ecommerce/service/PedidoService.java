package com.vetor.gera_pedido_ecommerce.service;
import com.vetor.gera_pedido_ecommerce.model.Hash_Map;
import com.vetor.gera_pedido_ecommerce.model.Resposta;
import com.vetor.gera_pedido_ecommerce.model.token.Token;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoCliente;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoPagamento;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Service
@Slf4j
public class PedidoService {
    @Autowired
    TokenService tokenService;

    EndPoints endPoints = new EndPoints();

    //envia(faz o POST) do objeto PedidoCliente para o endPoint
    public Resposta enviar(@Valid PedidoModel pedidoModel,
                           @Valid PedidoCliente pedidoCliente,
                           @Valid PedidoProduto pedidoProduto,
                           @Valid PedidoPagamento pedidoPagamento,
                           @Valid Token token) throws URISyntaxException {

        List<Token> listaGrupos = new ArrayList<>();
        try {
            listaGrupos = tokenService.listarPorGrupo(token.getGrupo());

            for (Token listaGrupo : listaGrupos) {
                System.out.println(listaGrupo);

                token.setId(listaGrupo.getId());
                token.setGrupo(listaGrupo.getGrupo());
                token.setToken(listaGrupo.getToken());
                token.setIsAtivo(listaGrupo.getIsAtivo());
                token.setNome(listaGrupo.getNome());
            }
        } catch (Exception e) {
            token.setGrupo("ERRO!!"+e.getMessage());
            listaGrupos.add(token);
        }


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getToken());


        URI uri = new URI(endPoints.criarPedidoURL);

        JSONObject pedidosJSONobject = new JSONObject();
        JSONObject clienteJSONObject = new JSONObject();
        JSONObject produtoJSONObject = new JSONObject();
        JSONObject pagamentoJSONObject = new JSONObject();

        JSONArray produtoJSONArry = new JSONArray();
        JSONArray pagamentoJSONArray = new JSONArray();

        Hash_Map hashMap = new Hash_Map();

        //FAZ A ITERAÇÃO DO MAP E POPULA O JSON COM A kEY E vALUE DO MAP
        Iterator<Map.Entry<String, Object>> itPedido = hashMap.mapPedido(pedidoModel).entrySet().iterator();
        while (itPedido.hasNext()) {
            Map.Entry<String, Object> entryPedido = itPedido.next();
            pedidosJSONobject.put(entryPedido.getKey(), entryPedido.getValue());
        }
        Iterator<Map.Entry<String, Object>> itCliente = hashMap.mapCliente(pedidoCliente).entrySet().iterator();
        while (itCliente.hasNext()) {
            Map.Entry<String, Object> entryCliente = itCliente.next();
            clienteJSONObject.put(entryCliente.getKey(), entryCliente.getValue());
        }
        Iterator<Map.Entry<String, Object>> itProduto = hashMap.mapProduto(pedidoProduto).entrySet().iterator();
        while (itProduto.hasNext()) {
            Map.Entry<String, Object> entryProduto = itProduto.next();
            produtoJSONObject.put(entryProduto.getKey(), entryProduto.getValue());
        }
        Iterator<Map.Entry<String, Object>> itPagamento = hashMap.mapPagamento(pedidoPagamento).entrySet().iterator();
        while (itPagamento.hasNext()) {
            Map.Entry<String, Object> entryPagamento = itPagamento.next();
            pagamentoJSONObject.put(entryPagamento.getKey(), entryPagamento.getValue());
        }

        //Começa a criar o JSON
        pedidosJSONobject.put("cliente", clienteJSONObject);//Injeta o JSON do cliente no PEDIDO

        produtoJSONArry.put(produtoJSONObject);
        pedidosJSONobject.put("produtos", produtoJSONArry);//Injeta o JSON do produto no PEDIDO

        pagamentoJSONArray.put(pagamentoJSONObject);
        pedidosJSONobject.put("pagamentos", pagamentoJSONArray);//Injeta o JSON do pagamento no PEDIDO

        System.out.println(pedidosJSONobject);

        HttpEntity<String> entity = new HttpEntity(pedidosJSONobject.toString(), headers);

        //pega a resposta e transforma em ResponseEntity<String>
        Resposta resposta = new Resposta();


        String erroTryCatch = "";
        try {
            resposta = restTemplate.postForObject(uri, entity, Resposta.class);


        } catch (Exception e) {
            log.error(e.getMessage());
            erroTryCatch = e.getMessage();
        }

        assert resposta != null;
        if (resposta.getMensagem() != null) {
            System.out.println(resposta.getMensagem());
            System.out.println("STATUS: " + resposta.getStatus());
            System.out.println("CODIGO PEDIDO: " + resposta.getCodigo_pedido());


            return resposta;
        }

        resposta.setMensagem(erroTryCatch);
        return resposta;

    }


}

