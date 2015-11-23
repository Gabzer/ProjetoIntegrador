package br.com.cadastro.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastro.util.ConnectionFactory;

public class ClienteDAO {
	
	//pesquiza pelo cpf
	public Cliente selectByCpf(String cpf){
		Cliente cliente = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cliente WHERE cpf = ?";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if (rs.next()){
				cliente = new Cliente();
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCategoria(rs.getString("categoria"));
				cliente.setSal_liq(rs.getDouble("salário líquido"));
			}
		}catch(Exception ex){
			System.err.println("Erro no selectByCpf");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps, rs);
		}
		return cliente;
	}
	
	//atualização de dados
	public void updateByCpf(Cliente cliente){
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "UPDATE cliente SET nome = ?, categoria = ?, sal_liq = ? WHERE cpf = ?";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCategoria());
			ps.setDouble(3, cliente.getSal_liq());
			ps.setString(4, cliente.getCpf());
			ps.executeUpdate();
		}catch(Exception ex){
			System.err.println("Erro no updateByCpf");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps);
		}
	}
	
	//exclusão pelo cpf
	public void deleteByCpf(String cpf){
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM cliente WHERE codigo = ?";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.executeUpdate();
		}catch(Exception ex){
			System.err.println("Erro no deleteByCpf");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps);
		}
	}
	
	//pesquiza por nome
	public List<Cliente> selectByNome(String nome){
		List<Cliente> lsClientes = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT cpf, nome, categoria, sal_liq FROM cliente "
				+ "WHERE nome ILIKE ?";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, nome);
			rs = ps.executeQuery();
			lsClientes = new ArrayList<Cliente>();
			while (rs.next()){
				Cliente a = new Cliente();
				a.setCodigo(rs.getLong("codigo"));
				a.setNome(rs.getString("nome"));
				a.setIdade(rs.getInt("idade"));
				lsAlunos.add(a);
			}
		}catch(Exception ex){
			System.err.println("Erro no selectAll");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps, rs);
		}
		return lsAlunos;
	}
	
	public List<Aluno> selectAll(){
		List<Aluno> lsAlunos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT codigo, nome, idade FROM aluno";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsAlunos = new ArrayList<Aluno>();
			while (rs.next()){
				Aluno a = new Aluno();
				a.setCodigo(rs.getLong("codigo"));
				a.setNome(rs.getString("nome"));
				a.setIdade(rs.getInt("idade"));
				lsAlunos.add(a);
			}
		}catch(Exception ex){
			System.err.println("Erro no selectAll");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps, rs);
		}
		return lsAlunos;
	}
	
	public void insert(Aluno aluno){
		Connection connection = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO aluno (nome, idade) "
                  + "VALUES (?, ?)";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, aluno.getNome());
			ps.setInt(2, aluno.getIdade());
			ps.executeUpdate();
		}catch(Exception ex){
			System.err.println("Erro no insert");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps);
		}
	}
}
