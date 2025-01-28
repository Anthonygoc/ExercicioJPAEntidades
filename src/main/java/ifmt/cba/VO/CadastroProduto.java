package ifmt.cba.VO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroProduto {

    public static void main(String[] args) {

        ProdutoVO produto = new ProdutoVO();
        produto.setNome("CARRO");
        produto.setPrecoVenda(6);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Testando");

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
        em.close();

    }
}
