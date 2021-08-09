package com.vetor.gera_pedido_ecommerce.model.token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grupo;
    private String nome;

   // @Column(name = "token")
    private String token;
    private String isAtivo;

    public Token(Token t) {
        this.id = t.getId();
        this.grupo = t.getGrupo();
        this.nome = t.getNome();
        this.token = t.getToken();
        this.isAtivo = t.getIsAtivo();
    }

}
