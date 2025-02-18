package ifmt.cba.VO;

import jakarta.persistence.*;

@Entity
@Table(name = "item_venda")
public class ItemVendaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int id;

    @Column(name = "quantidade", nullable = false)
    private int quantidadeItens;

    @Column(name = "preco_venda", nullable = false)
    private float precoUnitario;

    @Column(name = "per_desconto")
    private float percentualDesconto;

    @ManyToOne
    @JoinColumn(name = "produto_codigo", nullable = false)
    private ProdutoVO produtoAssociado;

    @ManyToOne
    @JoinColumn(name = "venda_codigo", nullable = false)
    private VendaVO vendaAssociada;

    // Métodos de acesso
    public int obterId() {
        return id;
    }

    public void definirId(int id) {
        this.id = id;
    }

    public int obterQuantidade() {
        return quantidadeItens;
    }

    public void definirQuantidade(int quantidade) {
        this.quantidadeItens = quantidade;
    }

    public float obterPrecoUnitario() {
        return precoUnitario;
    }

    public void definirPrecoUnitario(float preco) {
        this.precoUnitario = preco;
    }

    public float obterPercentualDesconto() {
        return percentualDesconto;
    }

    public void definirPercentualDesconto(float desconto) {
        this.percentualDesconto = desconto;
    }

    public ProdutoVO obterProdutoAssociado() {
        return produtoAssociado;
    }

    public void definirProdutoAssociado(ProdutoVO produto) {
        this.produtoAssociado = produto;
    }

    public VendaVO obterVendaAssociada() {
        return vendaAssociada;
    }

    public void definirVendaAssociada(VendaVO venda) {
        this.vendaAssociada = venda;
    }
    public float calcularValorTotal() {
        float valorSemDesconto = this.quantidadeItens * this.precoUnitario;
        return valorSemDesconto - (valorSemDesconto * (this.percentualDesconto / 100));
    }
}
