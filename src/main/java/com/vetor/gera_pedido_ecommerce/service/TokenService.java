package com.vetor.gera_pedido_ecommerce.service;

import com.vetor.gera_pedido_ecommerce.model.token.Token;
import com.vetor.gera_pedido_ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    TokenRepository repository;

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
}
