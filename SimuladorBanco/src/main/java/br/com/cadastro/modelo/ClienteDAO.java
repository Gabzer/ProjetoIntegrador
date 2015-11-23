package br.com.cadastro.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastro.util.ConnectionFactory;

public class ClienteDAO {
	
	//incluir cliente
	public void insert(Cliente cliente){
		Connection connection = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO cliente (cpf, nome, categoria, sal_liq) "
                  + "VALUES (?, ?, ?, ?)";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getCategoria());
			ps.setDouble(4, cliente.getSal_liq());
			ps.executeUpdate();
		}catch(Exception ex){
			System.err.println("Erro no insert");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps);
		}
	}
	
	//listar todos clientes
	public List<Cliente> selectAll(){
		List<Cliente> lsClis = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT cpf, nome, categoria, sal_liq FROM cliente";
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			lsClis = new ArrayList<Cliente>();
			while (rs.next()){
				Cliente cli = new Cliente();
				cli.setCpf(rs.getString("cpf"));
				cli.setNome(rs.getString("nome"));
				cli.setCategoria(rs.getString("categoria"));
				cli.setSal_liq(rs.getDouble("sal_liq"));
				lsClis.add(cli);
			}
		}catch(Exception ex){
			System.err.println("Erro no selectAll");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps, rs);
		}
		return lsClis;
	}
		
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
				Cliente cli = new Cliente();
				cli.setCpf(rs.getString("cpf"));
				cli.setNome(rs.getString("nome"));
				cli.setCategoria(rs.getString("categoria"));
				cli.setSal_liq(rs.getDouble("sal_liq"));
				lsClientes.add(cli);
			}
		}catch(Exception ex){
			System.err.println("Erro no selectByNome");
			ex.printStackTrace();
		}finally{
			ConnectionFactory.encerrarConexao(connection, ps, rs);
		}
		return lsClientes;
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
}
