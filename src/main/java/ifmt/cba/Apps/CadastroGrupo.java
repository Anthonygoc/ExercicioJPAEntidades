package ifmt.cba.Apps;

import ifmt.cba.VO.GrupoProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroGrupo {

    public static void main(String[] args) {

        GrupoProdutoVO grupo = new GrupoProdutoVO();
        grupo.setNome("automoveis");
        grupo.setCodigo(498);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Testando");

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(grupo);
        em.getTransaction().commit();
        em.close();

    }
}
