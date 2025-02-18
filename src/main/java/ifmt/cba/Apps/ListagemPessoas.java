package ifmt.cba.Apps;

import ifmt.cba.VO.PessoaVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import javax.swing.*;
import java.util.List;

public class ListagemPessoas {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("Testando");
            em = emf.createEntityManager();

            // Consulta e exibição
            List<PessoaVO> pessoas = obterPessoasOrdenadas(em);

            if (pessoas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não existem pessoas cadastradas!");
                return;
            }

            // Exibe as informações das pessoas
            exibirRelatorioPessoas(pessoas);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar pessoas: " + ex.getMessage());
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

    // Método para obter as pessoas ordenadas por nome
    private static List<PessoaVO> obterPessoasOrdenadas(EntityManager em) {
        String jpql = "SELECT p FROM PessoaVO p ORDER BY p.nome";
        TypedQuery<PessoaVO> query = em.createQuery(jpql, PessoaVO.class);
        return query.getResultList();
    }

    // Método para exibir o relatório com as informações das pessoas
    private static void exibirRelatorioPessoas(List<PessoaVO> pessoas) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO DE PESSOAS ===\n\n");

        for (PessoaVO pessoa : pessoas) {
            relatorio.append(String.format("Código: %d\n", pessoa.getCodigo()));
            relatorio.append(String.format("Nome: %s\n", pessoa.getNome()));
            relatorio.append(String.format("Tipo: %s\n",
                    pessoa.getClass().getSimpleName().replace("VO", "")));
            relatorio.append("\n" + "=".repeat(30) + "\n\n");
        }

        // Criar a interface gráfica com a listagem
        JTextArea textArea = new JTextArea(relatorio.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Configurar e exibir o diálogo com o relatório
        JDialog dialog = new JDialog();
        dialog.setTitle("Listagem de Pessoas");
        dialog.add(scrollPane);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
