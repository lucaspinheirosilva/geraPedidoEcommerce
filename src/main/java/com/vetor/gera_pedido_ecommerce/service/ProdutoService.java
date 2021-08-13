package com.vetor.gera_pedido_ecommerce.service;

import com.vetor.gera_pedido_ecommerce.model.produtos.CodigoBarrasModel;
import com.vetor.gera_pedido_ecommerce.model.produtos.ProdutoModel;
import com.vetor.gera_pedido_ecommerce.model.token.Token;
import com.vetor.gera_pedido_ecommerce.repository.TokenRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    TokenRepository repository;

    EndPoints endPoints = new EndPoints();
    //Lista todos os produtos que contem no EndPoint
    public List<ProdutoModel> listaProdutos(String grupo){

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

        List<ProdutoModel> todosProdutos = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.setBearerAuth("teste");
        headers.setBearerAuth(token.getToken());

        HttpEntity<ProdutoModel> entity = new HttpEntity(headers);
        URI uri=null;
        try {
            uri = new URI(endPoints.listarProdutoURL);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            //pega a resposta e transporma em ResponseEntity<String>

            assert uri != null;
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
            produtoModel.setRetorno("ERRO NO TOKEN DE INTEGRAÇÃO-->"+e.getMessage());
            todosProdutos.add(produtoModel);
            System.out.println("ERRO NO TOKEN DE INTEGRAÇÃO, Contate o Administrador para a correção-->"+e.getMessage());
        }
        if (todosProdutos.size()==0){
            ProdutoModel produtoModel = new ProdutoModel();
            produtoModel.setRetorno("NENHUM PRODUTO CADASTRADO, Cadastre um produto no E-commerce antes de prosseguir!!");
            todosProdutos.add(produtoModel);
        }

        //Retorna a Lista de todos os produtos
        return todosProdutos;
    }

    //Lista todos os codigos de barras
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
        headers.setBearerAuth(token.getToken());

        HttpEntity<ProdutoModel> entity = new HttpEntity(headers);
        URI uri ;
        try {
            uri = new URI(endPoints.getListarCodigoBarrasURL());

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

                if (codigoBarrasModel.getQnt_estoque_disponivel()>0)
                {
                    listBarrasModels.add(codigoBarrasModel);
                }

            }

            System.out.println(listBarrasModels.size());

            //se for igual a 0 é pq nao tem produto cadastrado no ecommerce
            if (listBarrasModels.size()==0){
                CodigoBarrasModel codigoBarrasModel = new CodigoBarrasModel();
                codigoBarrasModel.setRetorno("NENHUM PRODUTO CADASTRADO, Cadastre um produto no E-commerce antes de prosseguir!!");
                listBarrasModels.add(codigoBarrasModel);
            }

            return listBarrasModels;

        }catch (Exception e){
            CodigoBarrasModel codigoBarrasModel = new CodigoBarrasModel();
            codigoBarrasModel.setRetorno("ERRO NO TOKEN DE INTEGRAÇÃO, Contate o Administrador para a correção-->"+e.getMessage());
            listBarrasModels.add(codigoBarrasModel);
            System.out.println(e.getMessage());

        }


        return listBarrasModels;


    }

}
