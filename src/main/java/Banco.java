import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
    private Connection conexao;
    private boolean estaConectado;
    private String mensagemErro;

    public Banco(String servidor, String porta, String usuario, String senha) {
        String url = "jdbc:mysql://" + servidor + ":" + porta + "/prog2sexta";
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            estaConectado = true;
            System.out.println("Conexão FEITA.");
        } catch (SQLException e) {
            mensagemErro = e.getMessage();
            estaConectado = false;
        }
    }

    public Banco(String usuario, String senha) {
        this("localhost", "3306", usuario, senha);
    }

    public Connection obterConexao() {
        return conexao;
    }

    public boolean conectado() {
        return estaConectado;
    }

    public String obterMensagemErro() {
        return mensagemErro;
    }

    public void encerraConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("COnexão fechada.");
            }
        } catch (SQLException e) {
            mensagemErro = e.getMessage();
        }
    }
}