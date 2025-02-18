package ifmt.cba.VO;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="pessoa")
@Inheritance(strategy = InheritanceType.JOINED) // Utilizando a estratégia JOINED
public abstract class PessoaVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Alterando para SEQUENCE
    @Column(name = "codigo")
    private Long id;

    @Column(name = "nome")
    private String nomeCompleto;

    // Métodos de acesso
    public Long obterId() {
        return id;
    }

    public void definirId(Long id) {
        this.id = id;
    }

    public String obterNomeCompleto() {
        return nomeCompleto;
    }

    public void definirNomeCompleto(String nome) {
        this.nomeCompleto = nome;
    }
}
