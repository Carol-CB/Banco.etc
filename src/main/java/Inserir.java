import java.sql.*;
import java.util.Scanner;

public class Inserir {
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
            //Inserindo dados
            String sql = "insert into tb_contato (nome, e_mail, telefone) values "+"(?,?,?)";
            //criando uma requisição
            PreparedStatement requisicao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //obter infos pra contato
            Scanner ler = new Scanner(System.in);
            System.out.println("Nome: ");
            String nome = ler.nextLine();
            System.out.println("E-mail: ");
            String email = ler.next();
            System.out.println("Fone: ");
            String fone = ler.next();
            requisicao.setString(1, nome);
            requisicao.setString(2, email);
            requisicao.setString(3, fone);
            //executar requisição
            requisicao.execute();
            //recuperando id gerado
            ResultSet resultado = requisicao.getGeneratedKeys();
            if(resultado.next()){
                int idContato = resultado.getInt(1);
                System.out.println("Contato "+nome+" gravado com o id "+idContato);
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
