package ifmt.cba.VO;

import jakarta.persistence.*;

@Table(name = "ItemVenda")
@Entity
public class ItemVendaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private int quantidade;

    private int precovenda;

    private float precoDesconto;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPrecovenda() {
        return precovenda;
    }

    public void setPrecovenda(int precovenda) {
        this.precovenda = precovenda;
    }

    public float getPrecoDesconto() {
        return precoDesconto;
    }

    public void setPrecoDesconto(float precoDesconto) {
        this.precoDesconto = precoDesconto;
    }
}
