package ifmt.cba.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendedor")
public class VendedorVO extends PessoaFisicaVO {

    @Column(name = "per_comissao")
    private float percentualComissao;

    public float obterPercentualComissao() {
        return percentualComissao;
    }

    public void definirPercentualComissao(float percentualComissao) {
        this.percentualComissao = percentualComissao;
    }
}
