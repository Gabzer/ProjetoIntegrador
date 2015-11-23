package br.com.cadastro.modelo;

import br.com.cadastro.controle.ClienteControl;

public class Cliente {
	
	private String cpf;
	private String nome;
	private String categoria;
	private double sal_liq;	
	
	ClienteControl cc = new ClienteControl();	
	private double sal30 = cc.calcul_trinta(sal_liq);
	
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
	public void setSal30(double sal30) {
		this.sal30 = sal30;
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
	public double getSal30() {
		return sal30;
	}
	
	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", categoria="
				+ categoria + ", sal_liq=" + sal_liq + "]";
	}	
}
