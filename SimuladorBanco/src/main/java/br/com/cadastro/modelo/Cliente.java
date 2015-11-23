package br.com.cadastro.modelo;

public class Cliente {
	
	private String cpf;
	private String nome;
	private String categoria;
	private double sal_liq;
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setSal_liq(double sal_liq) {
		this.sal_liq = sal_liq;
	}
	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public double getSal_liq() {
		return sal_liq;
	}

}
