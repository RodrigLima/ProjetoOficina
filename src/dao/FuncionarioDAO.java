package dao;

import conexao.Conexao;
import funcionario.Funcionario;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class FuncionarioDAO {

    private Conexao conexao;
    private Connection conn;

    public FuncionarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario(nome,cpf,cargo,senha) VALUES"
                + "(?,?,?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getSenha());

            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionário " + e.getMessage());
        }
    }

    public ResultSet autenticarFuncionario(Funcionario funcionario) {
        conn = new Conexao().getConexao();
        try {
            String sql = "Select * from funcionario where nome = ? and senha = ? and cargo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getCargo());

            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Funcionário: " + e.getMessage());
            return null;
        }
    }

    public java.util.List<Funcionario> read() {
        conn = new Conexao().getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();
            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("idfuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCargo(rs.getString("Cargo"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na tabela" + e.getMessage());
        }
        return funcionarios;

    }
  


}
