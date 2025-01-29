package ifmt.cba.Apps;

import ifmt.cba.VO.GrupoProdutoVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.swing.*;

public class CadastroGrupo {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // Criar apenas uma instância do EntityManagerFactory
            emf = Persistence.createEntityManagerFactory("Testando");
            em = emf.createEntityManager();

            // Criar e preencher o objeto grupo
            GrupoProdutoVO grupo = new GrupoProdutoVO();
            String nome = JOptionPane.showInputDialog("Digite o nome do grupo");
            grupo.setNome(nome);

            // Iniciar a transação e salvar
            em.getTransaction().begin();
            em.persist(grupo); // Usando persist para novos registros
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!");

        } catch (Exception ex) {
            // Fazer rollback se houver erro durante a transação
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            // Fechar as conexões
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}