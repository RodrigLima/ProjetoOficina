package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    public Connection getConexao(){
        try {
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/projetooficina?serverTimezone=UTC",
             "rodrigolima",
             "81810351"     
            );   
            return conn;
           
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar" + e.getMessage());
            return null;
        }
    }
}
