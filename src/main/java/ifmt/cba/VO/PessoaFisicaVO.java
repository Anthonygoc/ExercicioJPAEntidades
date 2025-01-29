package ifmt.cba.VO;

import jakarta.persistence.*;

@Entity
@Table (name = "PessoaFisica")
public class PessoaFisicaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String CPF;
    private String RG;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }
}
