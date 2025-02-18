package ifmt.cba.Apps;

import ifmt.cba.VO.ProdutoVO;
import ifmt.cba.VO.GrupoProdutoVO;
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

            // Coleta informações do produto
            String nome = obterDadosUsuario("Digite o nome do produto");
            if (nome == null) return;

            // Coleta o preço de venda
            Float precoVenda = obterPrecoVenda();
            if (precoVenda == null) return;

            // Coleta o código do grupo
            Long grupoCodigo = obterCodigoGrupo();
            if (grupoCodigo == null) return;

            // Encontra o grupo no banco de dados
            GrupoProdutoVO grupo = em.find(GrupoProdutoVO.class, grupoCodigo);
            if (grupo == null) {
                JOptionPane.showMessageDialog(null, "Grupo não encontrado com o código: " + grupoCodigo);
                return;
            }

            // Cria e popula o ProdutoVO
            ProdutoVO produto = new ProdutoVO();
            produto.setNome(nome);
            produto.setPrecoVenda(precoVenda);
            produto.setGrupo(grupo);

            // Persiste o produto
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!\n" +
                    "Código: " + produto.getCodigo() + "\n" +
                    "Nome: " + produto.getNome() + "\n" +
                    "Preço: R$ " + String.format("%.2f", produto.getPrecoVenda()) + "\n" +
                    "Grupo: " + produto.getGrupo().getNome());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Digite um valor numérico válido");
            System.out.println("Erro de formato numérico: " + e.getMessage());
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
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

    // Método reutilizável para obter dados do usuário
    private static String obterDadosUsuario(String mensagem) {
        String dado = JOptionPane.showInputDialog(mensagem);
        if (dado == null || dado.trim().isEmpty()) {
            System.out.println("Operação cancelada: " + mensagem.toLowerCase() + " não pode ser vazio");
            return null;
        }
        return dado;
    }

    // Método para obter o preço de venda, validando se é um número positivo
    private static Float obterPrecoVenda() {
        String precoStr = JOptionPane.showInputDialog("Digite o preço de venda do produto");
        if (precoStr == null || precoStr.trim().isEmpty()) {
            System.out.println("Operação cancelada: preço não pode ser vazio");
            return null;
        }
        try {
            Float precoVenda = Float.parseFloat(precoStr);
            if (precoVenda <= 0) {
                JOptionPane.showMessageDialog(null, "Erro: O preço deve ser um valor positivo.");
                return null;
            }
            return precoVenda;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Digite um valor numérico válido para o preço.");
            System.out.println("Erro de formato numérico: " + e.getMessage());
            return null;
        }
    }

    // Método para obter o código do grupo
    private static Long obterCodigoGrupo() {
        String grupoCodigoStr = JOptionPane.showInputDialog("Digite o código do grupo do produto");
        if (grupoCodigoStr == null || grupoCodigoStr.trim().isEmpty()) {
            System.out.println("Operação cancelada: código do grupo não pode ser vazio");
            return null;
        }
        try {
            return Long.parseLong(grupoCodigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Digite um código válido para o grupo.");
            System.out.println("Erro de formato numérico para código de grupo: " + e.getMessage());
            return null;
        }
    }
}
