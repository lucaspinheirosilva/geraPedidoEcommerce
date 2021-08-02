package com.vetor.gera_pedido_ecommerce.service;

import com.vetor.gera_pedido_ecommerce.model.Hash_Map;
import com.vetor.gera_pedido_ecommerce.model.Resposta;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoCliente;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoPagamento;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Service
public class PedidoService {
    final String criarPedidoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/pedido";
    final String listarProdutoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/produtos";
    final String tokenAcesso = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMVUNBLTU0MzkiLCJleHAiOjE5NDIxNjI0MzUsInJvbCI6WyJST0xFX1VTRVIiXX0.fdtnDViKiT1xDtxOR0wM0xHzgEfkvtUKkD9Ab7tKSx-saQ-pB5iFFl4lu_j46Yxz81cIEKziJzTsB9s_IsEUog";



    //Lista todos os produtos que contem no EndPoint
    public List<ProdutoModel> listaProdutos() throws URISyntaxException {
        List<ProdutoModel> todosProdutos = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenAcesso);

        HttpEntity<ProdutoModel> entity = new HttpEntity(headers);
        URI uri = new URI(listarProdutoURL);

        //pega a resposta e transporma em ResponseEntity<String>
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println("PASSOU PELO RESPONSE ENTITY---->" + response.getBody());


        //Mapeia o JSON e guarda as informaçoes nas variavies
        JSONObject object = new JSONObject(response.getBody());
        JSONArray array = object.getJSONArray("Produtos");
        for (int i = 0; i < array.length(); i++) {

            int cod_produto = array.getJSONObject(i).getInt("cod_produto");
            int cod_fornecedor = array.getJSONObject(i).getInt("cod_fornecedor");
            int cod_departamento = array.getJSONObject(i).getInt("cod_departamento");
            int cod_grupo = array.getJSONObject(i).getInt("cod_grupo");
            int cod_subgrupo = array.getJSONObject(i).getInt("cod_subgrupo");
            int cod_secao = array.getJSONObject(i).getInt("cod_secao");
            int cod_estacao = array.getJSONObject(i).getInt("cod_estacao");
            int cod_estilo = array.getJSONObject(i).getInt("cod_estilo");
            String nome_produto = array.getJSONObject(i).getString("nome_produto");

            String dsc_produto_web = array.getJSONObject(i).getString("dsc_produto_web");
            boolean is_ativo = array.getJSONObject(i).getBoolean("is_ativo");
            boolean is_fora_linha = array.getJSONObject(i).getBoolean("is_fora_linha");
            String ncm = array.getJSONObject(i).getString("ncm");
            int ordenacao = array.getJSONObject(i).getInt("ordenacao");
            String dsc_marca = array.getJSONObject(i).getString("dsc_marca");
            String dsc_modelo = array.getJSONObject(i).getString("dsc_modelo");
            String dsc_referencia = array.getJSONObject(i).getString("dsc_referencia");

            float vl_custo_liquido = array.getJSONObject(i).getFloat("vl_custo_liquido");
            float vl_venda_vista = array.getJSONObject(i).getFloat("vl_venda_vista");
            float vl_venda_prazo = array.getJSONObject(i).getFloat("vl_venda_prazo");

            float peso_bruto = array.getJSONObject(i).getFloat("peso_bruto");
            float peso_liquido = array.getJSONObject(i).getFloat("peso_liquido");
            float altura = array.getJSONObject(i).getFloat("altura");
            float largura = array.getJSONObject(i).getFloat("largura");
            float comprimento = array.getJSONObject(i).getFloat("comprimento");
            boolean pronta_entrega = array.getJSONObject(i).getBoolean("pronta_entrega");


            //POPULA o Objeto PRODUTO MODEL
            ProdutoModel produtoModel = new ProdutoModel();

            produtoModel.setCod_produto(cod_produto);
            produtoModel.setCod_fornecedor(cod_fornecedor);
            produtoModel.setCod_departamento(cod_departamento);
            produtoModel.setCod_grupo(cod_grupo);
            produtoModel.setCod_subgrupo(cod_subgrupo);
            produtoModel.setCod_secao(cod_secao);
            produtoModel.setCod_estacao(cod_estacao);
            produtoModel.setCod_estilo(cod_estilo);

            produtoModel.setNome_produto(nome_produto);
            produtoModel.setDsc_produto_web(dsc_produto_web);
            produtoModel.setIs_ativo(is_ativo);
            produtoModel.setIs_fora_linha(is_fora_linha);
            produtoModel.setNcm(ncm);
            produtoModel.setOrdenacao(ordenacao);
            produtoModel.setDsc_marca(dsc_marca);
            produtoModel.setDsc_modelo(dsc_modelo);
            produtoModel.setDsc_referencia(dsc_referencia);

            produtoModel.setVl_custo_liquido(vl_custo_liquido);
            produtoModel.setVl_venda_vista(vl_venda_vista);
            produtoModel.setVl_venda_prazo(vl_venda_prazo);

            produtoModel.setPeso_bruto(peso_bruto);
            produtoModel.setPeso_liquido(peso_liquido);
            produtoModel.setAltura(altura);
            produtoModel.setLargura(largura);
            produtoModel.setComprimento(comprimento);
            produtoModel.setPronta_entrega(pronta_entrega);

            todosProdutos.add(produtoModel);
        }

        //Retorna a Lista de todos os produtos
        return todosProdutos;
    }

    //envia(faz o POST) do objeto PedidoCliente para o endPoint
    public Resposta enviar(@Valid PedidoModel pedidoModel,
                              @Valid PedidoCliente pedidoCliente,
                              @Valid PedidoProduto pedidoProduto,
                              @Valid PedidoPagamento pedidoPagamento) throws URISyntaxException {


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenAcesso);

        URI uri = new URI(criarPedidoURL);

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
        //Cria um Json de ClientePedido
        pedidosJSONobject.put("cliente", clienteJSONObject);//Injeta o JSON do cliente no PEDIDO

        produtoJSONArry.put(produtoJSONObject);
        pedidosJSONobject.put("produtos", produtoJSONArry);//Injeta o JSON do produto no PEDIDO

        pagamentoJSONArray.put(pagamentoJSONObject);
        pedidosJSONobject.put("pagamentos", pagamentoJSONArray);//Injeta o JSON do pagamento no PEDIDO

        System.out.println(pedidosJSONobject);

        HttpEntity<String> entity = new HttpEntity(pedidosJSONobject.toString(), headers);

        //pega a resposta e transforma em ResponseEntity<String>
        Resposta resposta =new Resposta();

        resposta.setCodigo_pedido(0);
        resposta.setMensagem("Pedido criado com sucesso");
        resposta.setCodigo_pedido(1111);
        //resposta = restTemplate.postForObject(uri, entity, Resposta.class);

        if (resposta.getMensagem() != null) {
            System.out.println(resposta.getMensagem());
            System.out.println("STATUS: "+resposta.getStatus());
            System.out.println("CODIGO PEDIDO: "+resposta.getCodigo_pedido());


            return resposta;
        }

        return resposta;
    }

}

