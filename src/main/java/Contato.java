import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Contato {
    int idContato;
    private String nome;
    private String eMail;
    private String telefone;
    private Connection conexao;

    public Contato(Connection conexao) {
        this.conexao = conexao;
    }

    public Contato() {}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getEMail() {
        return eMail;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public void gravarContato() throws SQLException {
        String sql = "insert into tb_contato (nome, e_mail, telefone) values (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nome);
            stmt.setString(2, eMail);
            stmt.setString(3, telefone);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.idContato = rs.getInt(1);
            }
        }
    }

    public void atualizarContato() throws SQLException {
        String sql = "update tb_contato set nome = ?, e_mail = ?, telefone = ? where contato_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, eMail);
            stmt.setString(3, telefone);
            stmt.setInt(4, idContato);
            stmt.executeUpdate();
        }
    }

    public void deletarContato(int idContato) throws SQLException {
        String sql = "delete from tb_contato where contato_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idContato);
            stmt.executeUpdate();
        }
    }

    public List<Contato> obterListaContato() throws SQLException {
        List<Contato> lista = new ArrayList<>();
        String sql = "select contato_id, nome, e_mail, telefone from tb_contato";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Contato contato = new Contato();
                contato.idContato = rs.getInt("contato_id");
                contato.nome = rs.getString("nome");
                contato.eMail = rs.getString("e_mail");
                contato.telefone = rs.getString("telefone");
                lista.add(contato);
            }
        }
        return lista;
    }

    public String toString() {
        return "\nContato id=" + idContato + ",\nnome=" + nome + ",\neMail=" + eMail + ",\ntelefone=" + telefone + "\n";
    }
}