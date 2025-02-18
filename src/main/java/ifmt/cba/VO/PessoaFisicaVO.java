package ifmt.cba.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisicaVO extends PessoaVO {

    @Column(name = "rg")
    private String numeroRg;

    @Column(name = "cpf")
    private String numeroCpf;

    public String obterRg() {
        return numeroRg;
    }

    public void definirRg(String rg) {
        this.numeroRg = rg;
    }

    public String obterCpf() {
        return numeroCpf;
    }

    public void definirCpf(String cpf) {
        this.numeroCpf = cpf;
    }
}
