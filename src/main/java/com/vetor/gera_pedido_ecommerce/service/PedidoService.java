package com.vetor.gera_pedido_ecommerce.service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import lombok.extern.java.Log;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;


@Service
@Log
public class PedidoService {
    final String criarPedidoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/pedido";
    final String listarProdutoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/produtos";
    final String tokenAcesso = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMVUNBLTU0MzkiLCJleHAiOjE5NDIxNjI0MzUsInJvbCI6WyJST0xFX1VTRVIiXX0.fdtnDViKiT1xDtxOR0wM0xHzgEfkvtUKkD9Ab7tKSx-saQ-pB5iFFl4lu_j46Yxz81cIEKziJzTsB9s_IsEUog";


    public PedidoModel enviar(PedidoModel pedido) {

        return pedido;
    }


    public ProdutoModel listaProdutos() throws URISyntaxException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenAcesso);

        HttpEntity<ProdutoModel> entity = new HttpEntity(headers);

        URI uri = new URI(listarProdutoURL);


        //pega a resposta e transporma em ResponseEntity<String>
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());


        //Transforma a var RESPONSE na classe PRODUTOMODEL
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);


        ProdutoModel produtoModel = mapper.readValue(response.getBody(),ProdutoModel.class);


        System.out.println(produtoModel);


        return null;
    }



}

