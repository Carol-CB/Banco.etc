import java.sql.*;
import java.util.Scanner;

public class Update {
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
            String sql2 = "Update tb_contato set nome = ?, e_mail = ?, telefone = ? where contato_id = ?";
            requisicao = conexao.prepareStatement(sql2);
            Scanner ler = new Scanner(System.in);
            System.out.println("Digite o Id do registro que deseja alterar: ");
            int termo = ler.nextInt();
            System.out.println("Novo nome: ");
            String nome = ler.next();
            System.out.println("Novo e-mail: ");
            String email = ler.next();
            System.out.println("Novo telefone: ");
            String fone = ler.next();
            requisicao.setString(1, nome);
            requisicao.setString(2, email);
            requisicao.setString(3, fone);
            requisicao.setInt(4, termo);
            System.out.println(requisicao);
            System.out.println("Registro "+termo+" alterado com sucesso!");
            requisicao.execute();
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
