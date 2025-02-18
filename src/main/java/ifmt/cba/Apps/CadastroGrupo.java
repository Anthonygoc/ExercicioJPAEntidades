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
            emf = Persistence.createEntityManagerFactory("Testando");
            em = emf.createEntityManager();

            // Coleta o nome do grupo
            String nome = obterDadosUsuario("Digite o nome do grupo");
            if (nome == null) return;

            // Cria o objeto GrupoProdutoVO
            GrupoProdutoVO grupo = new GrupoProdutoVO();
            grupo.setNome(nome);

            // Persiste o grupo no banco de dados
            em.getTransaction().begin();
            em.persist(grupo);
            em.getTransaction().commit();

            // Exibe mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!");

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar grupo: " + ex.getMessage());
            System.out.println("Inclusão não realizada: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

    // Método reutilizável para coletar dados do usuário
    private static String obterDadosUsuario(String mensagem) {
        String dado = JOptionPane.showInputDialog(mensagem);
        if (dado == null || dado.trim().isEmpty()) {
            System.out.println("Operação cancelada: " + mensagem.toLowerCase() + " não pode ser vazio");
            return null;
        }
        return dado;
    }
}
