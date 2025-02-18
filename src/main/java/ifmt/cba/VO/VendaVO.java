package ifmt.cba.VO;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "venda")
public class VendaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_venda", nullable = false)
    private Date dataVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_codigo", nullable = false)
    private ClienteVO cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_codigo", nullable = false)
    private VendedorVO vendedor;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVendaVO> itensVenda = new ArrayList<>();

    // Métodos de acesso
    public int obterCodigo() {
        return codigo;
    }

    public void definirCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date obterDataVenda() {
        return dataVenda;
    }

    public void definirDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ClienteVO obterCliente() {
        return cliente;
    }

    public void definirCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    public VendedorVO obterVendedor() {
        return vendedor;
    }

    public void definirVendedor(VendedorVO vendedor) {
        this.vendedor = vendedor;
    }

    public List<ItemVendaVO> obterItensVenda() {
        return itensVenda;
    }

    public void definirItensVenda(List<ItemVendaVO> itens) {
        this.itensVenda = itens;
    }

    public void adicionarItem(ItemVendaVO item) {
        item.setVenda(this);
        this.itensVenda.add(item);
    }
}
