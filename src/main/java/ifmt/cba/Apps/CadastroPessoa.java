package ifmt.cba.Apps;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroPessoa {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Testando");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();



    }

}
