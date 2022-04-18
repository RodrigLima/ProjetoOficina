package dao;

import cliente.Cliente;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {

    private Conexao conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, cpf, email, endereco, placa, telefone, servico) VALUES "
                + "(?,?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail()); 
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getPlaca());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getServico());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }

    }

    public java.util.List<Cliente> read() {
        conn = new Conexao().getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List<Cliente> cliente = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();{

            while (rs.next()) {
                Cliente clientes = new Cliente();
            
                clientes.setId(rs.getInt("idcliente"));
                clientes.setNome(rs.getString("nome"));
                clientes.setCpf(rs.getString("cpf"));
                clientes.setEmail(rs.getString("email"));
                clientes.setEndereco(rs.getString("endereco"));
                clientes.setPlaca(rs.getString("placa"));
                clientes.setValor(rs.getString("valor"));
                clientes.setTelefone(rs.getString("telefone"));
                clientes.setServico(rs.getString("servico"));

                cliente.add(clientes);
            }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na tabela" + e.getMessage());

        }
        return cliente;
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM cliente WHERE idcliente = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro em excluir cliente" + e.getMessage());
        }
        
    }
    
    
    public Cliente getCliente(int id){
        String sql = "SELECT * FROM cliente WHERE idcliente = ?";
          try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
   
           ResultSet rs =  stmt.executeQuery();
           Cliente cliente = new Cliente();
           
           rs.first();
           cliente.setId(id);
           cliente.setNome(rs.getString("nome"));
           cliente.setPlaca(rs.getString("placa"));
           cliente.setServico(rs.getString("servico"));
           
           return cliente;
            
        } catch (SQLException e) {
            return null;

        }
    }
  public void inserirValor(Cliente cliente) {
        String sql = "INSERT INTO funcionario(valor) VALUES"
                + "(?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getValor());
    

            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir valor " + e.getMessage());
        }
    }



}


