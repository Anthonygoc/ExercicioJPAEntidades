package ifmt.cba.VO;
import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class ProdutoVO {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

@ManyToOne
@JoinColumn(name = "grupo_codigo", referencedColumnName = "codigo")
GrupoProdutoVO grupoProduto;

    private String nome;
    private float PrecoVenda;

    public float getPrecoVenda() {
        return PrecoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        PrecoVenda = precoVenda;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
