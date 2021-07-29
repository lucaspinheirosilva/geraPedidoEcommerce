package com.vetor.gera_pedido_ecommerce;

import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoCliente;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoModel;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoPagamento;
import com.vetor.gera_pedido_ecommerce.model.pedido.PedidoProduto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class TestPedido {


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

        //Setter do PedidoModel
        pedidoModel.setData_pedido("2021-07-28 00:00:00");
        pedidoModel.setObservacoes("Teste");
        pedidoModel.setTotal_produtos(199.90F);
        pedidoModel.setTotal_frete(00.00F);
        pedidoModel.setTotal_seguro(00.00F);
        pedidoModel.setTotal_desconto(00.00F);
        pedidoModel.setTotal_outro(00.00F);
        pedidoModel.setQtd_produtos(1);
        pedidoModel.setLogradouro("Rua dos testes");
        pedidoModel.setNumero("270");
        pedidoModel.setComplemento("");
        pedidoModel.setBairro("JD Muçum");
        pedidoModel.setCidade("Curitiba");
        pedidoModel.setEstado("PR");
        pedidoModel.setCep("83301130");
        pedidoModel.setReferencia("");
        pedidoModel.setPrazo_entrega(20);
        pedidoModel.setOrigem("Tray");

        //setter PedidoCliente
        pedidoCliente.setCpf_cnpj("12345678910");
        pedidoCliente.setNome_cliente("Lucas Pinheiro");
        pedidoCliente.setData_nasc_abe("1993-03-24 00:00:00");
        pedidoCliente.setFisica_juridica("F");

        //setter PedidoProduto
        pedidoProduto.setCod_barras("9876564321");
        pedidoProduto.setValor(19.99F);
        pedidoProduto.setFrete(00.00F);
        pedidoProduto.setSeguro(00.00F);
        pedidoProduto.setDesconto(10.00F);
        pedidoProduto.setOutros(00.00F);
        pedidoProduto.setQuantidade(1);

        //setter PedidoPagamento
        pedidoPagamento.setTipo_pagamento("C");
        pedidoPagamento.setValor_pagamento(199.66F);
        pedidoPagamento.setToken("12122");
        pedidoPagamento.setData_pagamento("2021-07-20 00:00:00");
        pedidoPagamento.setStatus("P");
        pedidoPagamento.setInf_pagamento("CARTAO HIPERCARD");
        pedidoPagamento.setNumero_parcelas(3);
        pedidoPagamento.setBandeira("HIPERCARD");

        //Começa a criar o JSON
        pedidosJSONobject.put("data_pedido", pedidoModel.getData_pedido());
        pedidosJSONobject.put("observacoes", pedidoModel.getObservacoes());
        pedidosJSONobject.put("total_produtos", pedidoModel.getTotal_produtos());
        pedidosJSONobject.put("total_frete", pedidoModel.getTotal_frete());
        pedidosJSONobject.put("total_seguro", pedidoModel.getTotal_seguro());
        pedidosJSONobject.put("total_desconto", pedidoModel.getTotal_desconto());
        pedidosJSONobject.put("total_outro", pedidoModel.getTotal_outro());
        pedidosJSONobject.put("qtd_produtos", pedidoModel.getQtd_produtos());
        pedidosJSONobject.put("logradouro", pedidoModel.getLogradouro());
        pedidosJSONobject.put("numero", pedidoModel.getNumero());
        pedidosJSONobject.put("complemento", pedidoModel.getComplemento());
        pedidosJSONobject.put("bairro", pedidoModel.getBairro());
        pedidosJSONobject.put("cidade", pedidoModel.getCidade());
        pedidosJSONobject.put("estado", pedidoModel.getEstado());
        pedidosJSONobject.put("cep", pedidoModel.getCep());
        pedidosJSONobject.put("referencia", pedidoModel.getReferencia());
        pedidosJSONobject.put("prazo_entrega", pedidoModel.getPrazo_entrega());
        pedidosJSONobject.put("origem", pedidoModel.getOrigem());

        //Cria um Json de ClientePedido
        clienteJSONObject.put("cpf_cnpj", pedidoCliente.getCpf_cnpj());
        clienteJSONObject.put("nome_cliente", pedidoCliente.getNome_cliente());
        clienteJSONObject.put("data_nasc_abe", pedidoCliente.getData_nasc_abe());
        clienteJSONObject.put("fisica_juridica", pedidoCliente.getFisica_juridica());
        pedidosJSONobject.put("cliente", clienteJSONObject);//Injeta o JSON do cliente no PEDIDO

        produtoJSONObject.put("cod_barras", pedidoProduto.getCod_barras());
        produtoJSONObject.put("valor", pedidoProduto.getValor());
        produtoJSONObject.put("frete", pedidoProduto.getFrete());
        produtoJSONObject.put("seguro", pedidoProduto.getSeguro());
        produtoJSONObject.put("desconto", pedidoProduto.getDesconto());
        produtoJSONObject.put("outros", pedidoProduto.getOutros());
        produtoJSONObject.put("quantidade", pedidoProduto.getQuantidade());
        produtoJSONArry.put(produtoJSONObject);
        pedidosJSONobject.put("produtos", produtoJSONArry);//Injeta o JSON do produto no PEDIDO

        pagamentoJSONObject.put("tipo_pagamento", pedidoPagamento.getTipo_pagamento());
        pagamentoJSONObject.put("valor_pagamento", pedidoPagamento.getValor_pagamento());
        pagamentoJSONObject.put("token", pedidoPagamento.getToken());
        pagamentoJSONObject.put("data_pagamento", pedidoPagamento.getData_pagamento());
        pagamentoJSONObject.put("status", pedidoPagamento.getStatus());
        pagamentoJSONObject.put("inf_pagamento", pedidoPagamento.getInf_pagamento());
        pagamentoJSONObject.put("numero_parcelas", pedidoPagamento.getNumero_parcelas());
        pagamentoJSONObject.put("bandeira", pedidoPagamento.getBandeira());

        pagamentoJSONArray.put(pagamentoJSONObject);

        pedidosJSONobject.put("pagamentos", pagamentoJSONArray);


        System.out.println(pedidosJSONobject);


    }

}
