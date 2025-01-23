package ifmt.cba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ContextoPersistencia {

    public static void main(String args[]) {

        EntityManagerFactory emf = null;
        EntityManager em = null;
a
        try {
            emf = Persistence.createEntityManagerFactory("Testando");
            em = emf.createEntityManager();
            System.out.println("Contexto de persistencia criado com sucesso");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Contexto de persistencia nao foi criado - " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
