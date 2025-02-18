package ifmt.cba.Apps;

import ifmt.cba.VO.ClienteVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class CadastroCliente {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("Testando");
            em = emf.createEntityManager();

            // Coleta dados do cliente
            String nome = obterDadosUsuario("Digite o nome do cliente");
            if (nome == null) return;

            String rg = obterDadosUsuario("Digite o RG do cliente");
            if (rg == null) return;

            String cpf = obterDadosUsuario("Digite o CPF do cliente");
            if (cpf == null) return;

            // Coleta limite de crédito e valida
            String limiteCreditoStr = JOptionPane.showInputDialog("Digite o limite de crédito do cliente");
            if (limiteCreditoStr == null || limiteCreditoStr.trim().isEmpty()) {
                System.out.println("Operação cancelada: limite de crédito não pode ser vazio");
                return;
            }

            float limiteCredito = 0;
            try {
                limiteCredito = Float.parseFloat(limiteCreditoStr);
                if (limiteCredito <= 0) {
                    JOptionPane.showMessageDialog(null, "Erro: O limite de crédito deve ser maior que zero.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Digite um valor numérico válido para o limite de crédito.");
                System.out.println("Erro de formato numérico: " + e.getMessage());
                return;
            }

            // Cria e popula o ClienteVO
            ClienteVO cliente = new ClienteVO();
            cliente.setNome(nome);
            cliente.setRg(rg);
            cliente.setCpf(cpf);
            cliente.setLimiteCredito(limiteCredito);

            // Persiste o cliente
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\n" +
                    "Código: " + cliente.getCodigo() + "\n" +
                    "Nome: " + cliente.getNome() + "\n" +
                    "RG: " + cliente.getRg() + "\n" +
                    "CPF: " + cliente.getCpf() + "\n" +
                    "Limite de Crédito: R$ " + String.format("%.2f", cliente.getLimiteCredito()));

        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage());
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
