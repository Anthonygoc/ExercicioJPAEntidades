package ifmt.cba.VO;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "fornecedor")
public class FornecedorVO extends PessoaJuridicaVO {

    @ManyToMany
    @JoinTable(
            name = "fornecedor_produto",
            joinColumns = @JoinColumn(name = "fornecedor_codigo"),
            inverseJoinColumns = @JoinColumn(name = "produto_codigo")
    )
    private List<ProdutoVO> produtosList = new ArrayList<>();

    public List<ProdutoVO> getProdutosList() {
        return produtosList;
    }

    public void setProdutosList(List<ProdutoVO> produtosList) {
        this.produtosList = produtosList;
    }

    public void adicionarProduto(ProdutoVO produto) {
        if (produto != null && !this.produtosList.contains(produto)) {
            this.produtosList.add(produto);
        }
    }

    public void removerProduto(ProdutoVO produto) {
        this.produtosList.remove(produto);
    }
}
