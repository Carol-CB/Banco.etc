import java.sql.*;

public class Conectar {
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
