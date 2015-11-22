package br.com.cadastro.visao;

import java.sql.Connection;

import br.com.cadastro.util.ConnectionFactory;

public class TesteConnectionFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conexao = ConnectionFactory.getConnection();
		
		if (conexao != null){
			System.out.println ("Sucesso!");
		} else {
			System.out.println ("Erro!");
		}

	}

}
