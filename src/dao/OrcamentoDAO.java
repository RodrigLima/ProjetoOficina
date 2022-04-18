/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import cliente.Cliente;
import conexao.Conexao;
import funcionario.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import orcamento.Orcamento;

/**
 *
 * @author pc
 */
public class OrcamentoDAO {

    private Conexao conexao;
    private Connection conn;

    public OrcamentoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();

    }

    public void inserir(Orcamento orcamento) {
        String sql = "INSERT INTO valor(cpfcliente,servico) VALUES "
                + "(?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, orcamento.getCpfCliente());
            stmt.setString(2, orcamento.getServico());

            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }

    }

   public java.util.List<Orcamento> read() {
        conn = new Conexao().getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List<Orcamento> orcamentos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM valor");
            rs = stmt.executeQuery();{

            while (rs.next()) {
                Orcamento orcamento =new Orcamento();
                Cliente clientes = new Cliente();
            


                clientes.setCpf(rs.getString("cpf"));
                orcamento.setCpfCliente(rs.getString("cpfcliente"));
                orcamento.setServico(rs.getString("servico"));
                orcamento.setValor(rs.getString("Valor"));

           

                orcamentos.add(orcamento);
     
            }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na tabela" + e.getMessage());

        }
        return orcamentos;
    }
    

}
