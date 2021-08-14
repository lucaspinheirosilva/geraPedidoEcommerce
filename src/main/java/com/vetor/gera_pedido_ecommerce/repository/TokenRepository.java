package com.vetor.gera_pedido_ecommerce.repository;

import com.vetor.gera_pedido_ecommerce.model.token.Token;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TokenRepository {

    private final EntityManager em;


    public TokenRepository(EntityManager em) {
        this.em = em;
    }
    List<Token> listToken = new ArrayList<>();
    public List<Token> localizarToken() {
        TypedQuery<Token> tq;
        try {
            String query = "SELECT t FROM token as t WHERE t.isAtivo='S' ";
            tq = em.createQuery(query, Token.class);

            listToken = tq.getResultList();
        } catch (Exception e) {
            Token token = new Token();
            token.setGrupo("ERRO DE CONEXAO, Contate do Admin!!");
            listToken.add(token);
        }
        return listToken;
    }

    public List<Token> localizarPorGrupo(String grupo) {
        TypedQuery<Token> tq;
        try {
            String query = "SELECT t FROM token as t WHERE t.grupo = :grupo ";
            tq = em.createQuery(query, Token.class);
            tq.setParameter("grupo", grupo);

            listToken=tq.getResultList();
        }catch (Exception e){
            Token token = new Token();
            token.setGrupo("ERRO DE CONEXAO, Contate do Admin!!");
            listToken.add(token);
        }

        return listToken;
    }
}
