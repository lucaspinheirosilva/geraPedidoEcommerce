package com.vetor.gera_pedido_ecommerce.service;

import com.vetor.gera_pedido_ecommerce.model.Hash_Map;
import com.vetor.gera_pedido_ecommerce.model.Resposta;
import com.vetor.gera_pedido_ecommerce.model.produtos.CodigoBarrasModel;
import com.vetor.gera_pedido_ecommerce.model.token.Token;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoCliente;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoPagamento;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import com.vetor.gera_pedido_ecommerce.repository.TokenRepository;
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
import java.util.stream.Collectors;


@Service
@Slf4j
public class PedidoService {
    @Autowired
    private TokenRepository repository;


    final String criarPedidoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/pedido";
    final String listarProdutoURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/produtos";
    final String listarCodigoBarrasURL = "https://wss.mitryus.com.br/api/ecommerce/integracao/codigosbarra";


    //Lista todos os produtos que contem no EndPoint
    public List<ProdutoModel> listaProdutos() throws URISyntaxException {
        List<ProdutoModel> todosProdutos = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.setBearerAuth("teste");
         headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMVUNBLTU0MzkiLCJleHAiOjE5NDQyMzI3NjIsInJvbCI6WyJST0xFX1VTRVIiXX0.kvvO3-yk4Gcd2GIczLqxWMey-2yGq5IG1EYudGPdW9KSWVLnvtJ_emiyusxZgZ-5pnwRqxHcaSglNISJWrb3dQ");

        HttpEntity<ProdutoModel> entity = new HttpEntity(headers);
        URI uri = new URI(listarProdutoURL);

        try {
            //pega a resposta e transporma em ResponseEntity<String>
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            System.out.println("PASSOU PELO RESPONSE ENTITY---->" + response.getBody());

            //Mapeia o JSON e guarda as informaçoes nas variavies
            JSONObject object = new JSONObject(response.getBody());
            JSONArray array = object.getJSONArray("Produtos");
            for (int i = 0; i < array.length(); i++) {
                ProdutoModel produtoModel = new ProdutoModel();

                produtoModel.setCod_produto(array.getJSONObject(i).getInt("cod_produto"));
                produtoModel.setCod_fornecedor(array.getJSONObject(i).getInt("cod_fornecedor"));
                produtoModel.setCod_departamento(array.getJSONObject(i).getInt("cod_departamento"));
                produtoModel.setCod_grupo(array.getJSONObject(i).getInt("cod_grupo"));
                produtoModel.setCod_subgrupo(array.getJSONObject(i).getInt("cod_subgrupo"));
                produtoModel.setCod_secao(array.getJSONObject(i).getInt("cod_secao"));
                produtoModel.setCod_estacao(array.getJSONObject(i).getInt("cod_estacao"));
                produtoModel.setCod_estilo(array.getJSONObject(i).getInt("cod_estilo"));

                produtoModel.setNome_produto(array.getJSONObject(i).getString("nome_produto"));
                produtoModel.setDsc_produto_web(array.getJSONObject(i).getString("dsc_produto_web"));
                produtoModel.setIs_ativo(array.getJSONObject(i).getBoolean("is_ativo"));
                produtoModel.setIs_fora_linha( array.getJSONObject(i).getBoolean("is_fora_linha"));
                produtoModel.setNcm(array.getJSONObject(i).getString("ncm"));
                produtoModel.setOrdenacao(array.getJSONObject(i).getInt("ordenacao"));
                produtoModel.setDsc_marca(array.getJSONObject(i).get("dsc_marca").toString());
                produtoModel.setDsc_modelo(array.getJSONObject(i).getString("dsc_modelo"));
                produtoModel.setDsc_referencia(array.getJSONObject(i).getString("dsc_referencia"));

                produtoModel.setVl_custo_liquido(array.getJSONObject(i).getFloat("vl_custo_liquido"));
                produtoModel.setVl_venda_vista(array.getJSONObject(i).getFloat("vl_venda_vista"));
                produtoModel.setVl_venda_prazo(array.getJSONObject(i).getFloat("vl_venda_prazo"));

                produtoModel.setPeso_bruto(array.getJSONObject(i).getFloat("peso_bruto"));
                produtoModel.setPeso_liquido(array.getJSONObject(i).getFloat("peso_liquido"));
                produtoModel.setAltura(array.getJSONObject(i).getFloat("altura"));
                produtoModel.setLargura(array.getJSONObject(i).getFloat("largura"));
                produtoModel.setComprimento(array.getJSONObject(i).getFloat("comprimento"));
                produtoModel.setPronta_entrega(array.getJSONObject(i).getBoolean("pronta_entrega"));

                todosProdutos.add(produtoModel);
            }

        }catch (Exception e){
            ProdutoModel produtoModel = new ProdutoModel();
            produtoModel.setCod_produto(0);
            produtoModel.setNome_produto(e.getMessage());
            todosProdutos.add(produtoModel);
            System.out.println(e.getMessage());
        }

        //Retorna a Lista de todos os produtos
        return todosProdutos;
    }

    //Lista os Token no Banco de Dados
    public List<Token> listarToken() {
        List<Token> tokens = repository.localizarToken();

        List<Token> listAll = tokens.stream().map(Token::new).collect(Collectors.toList());

        return listAll;
    }

    //Lista o token por parametro GRUPO
    public List<Token>listarPorGrupo(String grupo){
         List<Token> listGrupo = repository.localizarPorGrupo(grupo).stream().map(Token::new).collect(Collectors.toList());

        return listGrupo;
    }

    //envia(faz o POST) do objeto PedidoCliente para o endPoint
    public Resposta enviar(@Valid PedidoModel pedidoModel,
                           @Valid PedidoCliente pedidoCliente,
                           @Valid PedidoProduto pedidoProduto,
                           @Valid PedidoPagamento pedidoPagamento,
                           @Valid Token token) throws URISyntaxException {

        List<Token> listaGrupos = listarPorGrupo(token.getGrupo());

        for (Token listaGrupo : listaGrupos) {
            System.out.println(listaGrupo);

            token.setId(listaGrupo.getId());
            token.setGrupo(listaGrupo.getGrupo());
            token.setToken(listaGrupo.getToken());
            token.setIsAtivo(listaGrupo.getIsAtivo());
            token.setNome(listaGrupo.getNome());
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getToken());

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
        pedidosJSONobject.put("cliente", clienteJSONObject);//Injeta o JSON do cliente no PEDIDO

        produtoJSONArry.put(produtoJSONObject);
        pedidosJSONobject.put("produtos", produtoJSONArry);//Injeta o JSON do produto no PEDIDO

        pagamentoJSONArray.put(pagamentoJSONObject);
        pedidosJSONobject.put("pagamentos", pagamentoJSONArray);//Injeta o JSON do pagamento no PEDIDO

        System.out.println(pedidosJSONobject);

        HttpEntity<String> entity = new HttpEntity(pedidosJSONobject.toString(), headers);

        //pega a resposta e transforma em ResponseEntity<String>
        Resposta resposta = new Resposta();


        String erroTryCatch="";
        try {
            resposta = restTemplate.postForObject(uri, entity, Resposta.class);


        }catch (Exception e){
            //erroTryCatch=e.getCause().toString();
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

    public List<CodigoBarrasModel> listarCodigoBarras(String grupo) {
        List<Token> tokens = repository.localizarPorGrupo(grupo).stream().map(Token::new).collect(Collectors.toList());

        Token token = new Token();
        for (Token listaGrupo : tokens) {
            System.out.println(listaGrupo);

            token.setId(listaGrupo.getId());
            token.setGrupo(listaGrupo.getGrupo());
            token.setToken(listaGrupo.getToken());
            token.setIsAtivo(listaGrupo.getIsAtivo());
            token.setNome(listaGrupo.getNome());
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<CodigoBarrasModel> listBarrasModels = new ArrayList<>();
        // headers.setBearerAuth("teste");
        headers.setBearerAuth(token.getToken());

        HttpEntity<ProdutoModel> entity = new HttpEntity(headers);
        URI uri ;
        try {
             uri = new URI(listarCodigoBarrasURL);

            //pega a resposta e transporma em ResponseEntity<String>
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            System.out.println("PASSOU PELO RESPONSE ENTITY---->" + response.getBody());


            JSONArray array = jsonObject.getJSONArray("CodigosBarra");
            for (int i = 0; i < array.length(); i++) {
                CodigoBarrasModel codigoBarrasModel = new CodigoBarrasModel();

                codigoBarrasModel.setCod_barra(array.getJSONObject(i).getString("cod_barra"));
                codigoBarrasModel.setCod_cor_1(array.getJSONObject(i).getInt("cod_cor_1"));
                codigoBarrasModel.setCod_cor_2(array.getJSONObject(i).getInt("cod_cor_2"));
                codigoBarrasModel.setCod_cor_3(array.getJSONObject(i).getInt("cod_cor_3"));
                codigoBarrasModel.setCod_produto(array.getJSONObject(i).getInt("cod_produto"));
                codigoBarrasModel.setCod_tamanho(array.getJSONObject(i).getInt("cod_tamanho"));
                codigoBarrasModel.setIntegracao_ativa(array.getJSONObject(i).getBoolean("integracao_ativa"));
                codigoBarrasModel.setVariacao_principal(array.getJSONObject(i).getBoolean("variacao_principal"));
                codigoBarrasModel.setEan(array.getJSONObject(i).getString("ean"));
                codigoBarrasModel.setQnt_estoque_disponivel(array.getJSONObject(i).getFloat("qnt_estoque_disponivel"));

                listBarrasModels.add(codigoBarrasModel);
            }

            System.out.println(listBarrasModels.size());

            if (listBarrasModels.size()==0){
                CodigoBarrasModel codigoBarrasModel = new CodigoBarrasModel();
                codigoBarrasModel.setCod_barra("NENHUM PRODUTO CADASTRADO");
                listBarrasModels.add(codigoBarrasModel);
            }

            return listBarrasModels;

        }catch (Exception e){
            CodigoBarrasModel codigoBarrasModel = new CodigoBarrasModel();
            codigoBarrasModel.setCod_barra("ERRO NO TOKEN DE INTEGRAÇÃO");
            listBarrasModels.add(codigoBarrasModel);
            System.out.println(e.getMessage());

        }


return listBarrasModels;


    }
}

