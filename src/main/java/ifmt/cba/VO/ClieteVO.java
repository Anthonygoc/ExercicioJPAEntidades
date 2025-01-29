
package ifmt.cba.VO;

import jakarta.persistence.*;

@Entity
@Table (name = "Cliente")
public class ClieteVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private float limiteCredito;

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
