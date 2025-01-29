package ifmt.cba.Apps;
import ifmt.cba.VO.GrupoProdutoVO;
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

            // Captura os dados do produto
            String nome = JOptionPane.showInputDialog("Digite o nome do produto");
            float precoVenda = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor do produto"));
            GrupoProdutoVO grupo = new GrupoProdutoVO();
            grupo.setNome(nome);
            // Configura os dados no objeto produto
            produto.setNome(nome);
            produto.setPrecoVenda(precoVenda);
            produto.setGrupo(grupo);// Assumindo que você tem este campo na classe ProdutoVO

            // Inicia a transação e salva
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
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