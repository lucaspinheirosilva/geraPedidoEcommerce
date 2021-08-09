package com.vetor.gera_pedido_ecommerce.repository;

import com.vetor.gera_pedido_ecommerce.model.token.Token;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TokenRepository {

    private EntityManager em;

    public TokenRepository(EntityManager em) {
        this.em = em;
    }
    public List<Token>localizarToken(){
        String query = "SELECT t FROM token as t WHERE t.isAtivo='S' ";
        TypedQuery<Token> tq = em.createQuery(query,Token.class);

        return tq.getResultList();
    }

    public List<Token> localizarPorGrupo(String grupo) {
        String query = "SELECT t FROM token as t WHERE t.grupo = :grupo ";
        //String query = "SELECT t FROM token as t WHERE t.grupo = 'LUCA-5439'";
        TypedQuery<Token> tq = em.createQuery(query,Token.class);
        tq.setParameter("grupo",grupo);
        return tq.getResultList();
    }
}
