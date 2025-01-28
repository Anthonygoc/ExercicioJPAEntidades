package ifmt.cba.VO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroGrupo {

    public static void main(String[] args) {

        GrupoProdutoVO grupo = new GrupoProdutoVO();
        grupo.setNome("bolacha");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Testando");

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(grupo);
        em.getTransaction().commit();
        em.close();

    }
}
