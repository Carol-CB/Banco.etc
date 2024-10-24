import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("localhost", "3306", "root", "");
        if (banco.conectado()) {
            Contato contato = new Contato(banco.obterConexao());

            try {
                contato.setNome("Claudioberto Mineiro");
                contato.setEMail("claudiobertomineirao@educar.rs.gob.br");
                contato.setTelefone("09090808");
                contato.gravarContato();
                System.out.println("gravado com sucesso: " + contato);
                for (Contato c : contato.obterListaContato()) {
                    System.out.println(c);
                }

                contato.setNome("ClaudiAbertA MineirA");
                contato.atualizarContato();
                System.out.println("atualizado para : " + contato);

                contato.deletarContato(contato.idContato);
                System.out.println("Contato deletado com sucesso!!!.");

            } catch (SQLException e) {
                System.out.println("Erro no bd: " + e.getMessage());
            } finally {
                banco.encerraConexao();
            }
        } else {
            System.out.println("não foi possível conectar o bd: " + banco.obterMensagemErro());
        }
    }
}