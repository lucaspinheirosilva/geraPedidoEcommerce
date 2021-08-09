package com.vetor.gera_pedido_ecommerce;

import com.vetor.gera_pedido_ecommerce.model.Hash_Map;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoCliente;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoPagamento;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import com.vetor.gera_pedido_ecommerce.service.PedidoService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;

@SpringBootTest
public class TestPedido {

    @Autowired
    PedidoService service;

    @Test
    public void metodoPost() throws JSONException {
        String criarPedidoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/pedido";
        String tokenAcesso = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMVUNBLTU0MzkiLCJleHAiOjE5NDIxNjI0MzUsInJvbCI6WyJST0xFX1VTRVIiXX0.fdtnDViKiT1xDtxOR0wM0xHzgEfkvtUKkD9Ab7tKSx-saQ-pB5iFFl4lu_j46Yxz81cIEKziJzTsB9s_IsEUog";

        //Instancia os objetos
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + tokenAcesso);

        PedidoProduto pedidoProduto = new PedidoProduto();
        PedidoModel pedidoModel = new PedidoModel();
        PedidoCliente pedidoCliente = new PedidoCliente();
        PedidoPagamento pedidoPagamento = new PedidoPagamento();

        /**https://www.baeldung.com/java-org-json***/
        JSONObject pedidosJSONobject = new JSONObject();
        JSONObject clienteJSONObject = new JSONObject();

        JSONObject produtoJSONObject = new JSONObject();
        JSONArray produtoJSONArry = new JSONArray();

        JSONObject pagamentoJSONObject = new JSONObject();
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
        //Cria um Json de ClientePedido
        pedidosJSONobject.put("cliente", clienteJSONObject);//Injeta o JSON do cliente no PEDIDO

        produtoJSONArry.put(produtoJSONObject);
        pedidosJSONobject.put("produtos", produtoJSONArry);//Injeta o JSON do produto no PEDIDO

        pagamentoJSONArray.put(pagamentoJSONObject);
        pedidosJSONobject.put("pagamentos", pagamentoJSONArray);//Injeta o JSON do pagamento no PEDIDO

        System.out.println(pedidosJSONobject);


    }

    @Test
    public void testeAleatorio() {

    }
}
