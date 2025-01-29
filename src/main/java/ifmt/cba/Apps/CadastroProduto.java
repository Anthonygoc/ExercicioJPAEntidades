package ifmt.cba.Apps;

import ifmt.cba.VO.ProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroProduto {

    public static void main(String[] args) {

        ProdutoVO produto = new ProdutoVO();
        produto.setNome("Abacaxi");
        produto.setPrecoVenda(10);
        produto.setCodigo(352);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Testando");

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
        em.close();

    }
}
