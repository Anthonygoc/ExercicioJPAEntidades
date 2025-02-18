package ifmt.cba.VO;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "produto")
public class ProdutoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "preco_venda", nullable = false)
    private Float precoVenda;

    @ManyToOne
    @JoinColumn(name = "grupo_codigo", referencedColumnName = "codigo")
    private GrupoProdutoVO grupo;

    @ManyToMany(mappedBy = "produtos")
    private List<FornecedorVO> fornecedoresAssociados = new ArrayList<>();

    // Getters e Setters para o produto
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

    public Float obterPrecoVenda() {
        return precoVenda;
    }

    public void atribuirPrecoVenda(Float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public GrupoProdutoVO obterGrupo() {
        return grupo;
    }

    public void atribuirGrupo(GrupoProdutoVO grupo) {
        this.grupo = grupo;
    }

    // Getters e Setters para fornecedores
    public List<FornecedorVO> obterFornecedores() {
        return fornecedoresAssociados;
    }

    public void atribuirFornecedores(List<FornecedorVO> fornecedores) {
        this.fornecedoresAssociados = fornecedores;
    }

    public void adicionarFornecedor(FornecedorVO fornecedor) {
        if (fornecedor != null && !this.fornecedoresAssociados.contains(fornecedor)) {
            this.fornecedoresAssociados.add(fornecedor);
            fornecedor.adicionarProduto(this);
        }
    }

    public void removerFornecedor(FornecedorVO fornecedor) {
        if (this.fornecedoresAssociados.remove(fornecedor)) {
            fornecedor.removerProduto(this);
        }
    }
}
