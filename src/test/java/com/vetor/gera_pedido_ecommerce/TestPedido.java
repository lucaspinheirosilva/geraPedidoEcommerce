package com.vetor.gera_pedido_ecommerce;

import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestPedido {

    @BeforeClass
    static void executarAntesDeTodosMétodosDeTeste() throws JSONException {
        String criarPedidoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/pedido";
        String tokenAcesso = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMVUNBLTU0MzkiLCJleHAiOjE5NDIxNjI0MzUsInJvbCI6WyJST0xFX1VTRVIiXX0.fdtnDViKiT1xDtxOR0wM0xHzgEfkvtUKkD9Ab7tKSx-saQ-pB5iFFl4lu_j46Yxz81cIEKziJzTsB9s_IsEUog";


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + tokenAcesso);
        JSONObject pedidoJsonObject = new JSONObject();


        List<PedidoProduto> ListpedidoProduto = new ArrayList<>();
        PedidoProduto pedidoProduto = new PedidoProduto();
        PedidoModel pedidoModel = new PedidoModel();

        JSONObject json = new JSONObject();
        JSONObject pedidosJSONobject = new JSONObject();
        JSONArray array = new JSONArray();

        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("observacoes", pedidoModel.getData_pedido());
        pedidosJSONobject.put("total_produtos", pedidoModel.getData_pedido());
        pedidosJSONobject.put("total_frete", pedidoModel.getData_pedido());
        pedidosJSONobject.put("total_seguro", pedidoModel.getData_pedido());
        pedidosJSONobject.put("total_desconto", pedidoModel.getData_pedido());
        pedidosJSONobject.put("total_outro", pedidoModel.getData_pedido());
        pedidosJSONobject.put("qtd_produtos", pedidoModel.getData_pedido());
        pedidosJSONobject.put("logradouro", pedidoModel.getData_pedido());
        pedidosJSONobject.put("numero", pedidoModel.getData_pedido());
        pedidosJSONobject.put("complemento", pedidoModel.getData_pedido());
        pedidosJSONobject.put("bairro", pedidoModel.getData_pedido());
        pedidosJSONobject.put("cidade", pedidoModel.getData_pedido());
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());

        pedidoProduto.setCod_barras("654654654655");
        pedidoProduto.setValor(10.99F);
        pedidoProduto.setFrete(10.99F);
        pedidoProduto.setSeguro(10.99F);
        pedidoProduto.setDesconto(10.99F);
        pedidoProduto.setOutros(10.99F);
        pedidoProduto.setQuantidade(2);

        ListpedidoProduto.add(pedidoProduto);
        pedidoModel.setProdutoList(ListpedidoProduto);

        pedidoJsonObject.put("ff", pedidoModel.getProdutoList());


    }

}
