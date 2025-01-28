package ifmt.cba.VO;

import jakarta.persistence.*;

@Entity
@Table(name = "GrupoProduto")
public class GrupoProdutoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
   private String nome;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
