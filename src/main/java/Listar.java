import java.sql.*;

public class Listar {
    public static void main(String[] args) {
        try{
            //carregando o drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            //url de conexao
            //protocolo:sgbd://servidor:porta(opcional)/banco_dados
            String url = "jdbc:mysql://localhost:3306/prog2sexta";
            String usuario = "root";
            String senha = "";
            //tentando conectar
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Estou conectando");
            String sql = "select contato_id, nome, e_mail, telefone from tb_contato";
            PreparedStatement requisicao = conexao.prepareStatement(sql);
            ResultSet resultado = requisicao.executeQuery();
            while(resultado.next()){
                int idContato = resultado.getInt("contato_id");
                String nome = resultado.getString("nome");
                String email = resultado.getString("e_mail");
                String fone = resultado.getString("telefone");
                System.out.println("Id: "+idContato);
                System.out.println("Nome: "+nome);
                System.out.println("E-mail: "+email);
                System.out.println("Telefone: "+fone);
            }
            conexao.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("Não foi possível carregar o drive.");
        }
        catch(SQLException e){
            System.out.println("Erro de sql "+e.getMessage());
        }
    }
}
