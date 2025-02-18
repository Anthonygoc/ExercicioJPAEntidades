package ifmt.cba.VO;
import jakarta.persistence.*;

@Entity
@Table(name = "grupo_produto")
public class GrupoProdutoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo; // Usando Long para maior compatibilidade com PostgreSQL

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    // Métodos de acesso
    public Long obterCodigo() {
        return codigo;
    }

    public void atribuirCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String obterNome() {
        return nome;
    }

    public void atribuirNome(String nome) {
        this.nome = nome;
    }
}
