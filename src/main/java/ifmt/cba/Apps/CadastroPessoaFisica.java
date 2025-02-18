package ifmt.cba.Apps;

import ifmt.cba.VO.PessoaFisicaVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class CadastroPessoaFisica {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("Testando");
            em = emf.createEntityManager();

            // Coleta os dados da pessoa física
            String nome = obterDadosUsuario("Digite o nome da pessoa física");
            if (nome == null) return;

            String rg = obterDadosUsuario("Digite o RG");
            if (rg == null || rg.length() < 5) {
                JOptionPane.showMessageDialog(null, "Erro: RG inválido.");
                return;
            }

            String cpf = obterDadosUsuario("Digite o CPF");
            if (cpf == null || cpf.length() != 11) {
                JOptionPane.showMessageDialog(null, "Erro: CPF inválido. Deve conter 11 dígitos.");
                return;
            }

            // Cria e popula o PessoaFisicaVO
            PessoaFisicaVO pessoaFisica = new PessoaFisicaVO();
            pessoaFisica.setNome(nome);
            pessoaFisica.setRg(rg);
            pessoaFisica.setCpf(cpf);

            // Persiste a pessoa física
            em.getTransaction().begin();
            em.persist(pessoaFisica);
            em.getTransaction().commit();

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Pessoa Física cadastrada com sucesso!\n" +
                    "Código: " + pessoaFisica.getCodigo() + "\n" +
                    "Nome: " + pessoaFisica.getNome() + "\n" +
                    "RG: " + pessoaFisica.getRg() + "\n" +
                    "CPF: " + pessoaFisica.getCpf());

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar pessoa física: " + ex.getMessage());
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
