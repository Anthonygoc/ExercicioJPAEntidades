package ifmt.cba.Apps;

import ifmt.cba.VO.ProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class CadastroProduto {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {

            emf = Persistence.createEntityManagerFactory("Testando");
            em = emf.createEntityManager();

            ProdutoVO produto = new ProdutoVO();
            String nome = JOptionPane.showInputDialog("Digite o nome do produto");
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto"));


            produto.setNome(nome);
            produto.setCodigo(codigo);

            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();

            System.out.println("Produto cadastrado com sucesso!");

        } catch (Exception ex) {
            System.out.println("Inclusão não realizada: " + ex.getMessage());
            ex.printStackTrace();

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
