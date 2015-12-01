package br.com.cadastro.modelo;

import br.com.cadastro.controle.ClienteControl;

public class Cliente {
	
	private String cpf;
	private String nome;
	private String categoria;
	private static double sal_liq;
	private double sal30;
	private String emprestFeito;
	private double vl_emp_feito;	
		
	
	
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
		sal30 = sal_liq * 0.3;
		this.sal30 = sal30;
	}
	public void setEmprestFeito(String emprestFeito) {
		this.emprestFeito = emprestFeito;
	}
	public void setEmp_feito(double emp_feito) {
		this.vl_emp_feito = emp_feito;
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
	public static double getSal_liq() {
		return sal_liq;
	}
	public double getSal30() {
		return sal30;
	}
	public String getEmprestFeito() {
		return emprestFeito;
	}
	public double getEmp_feito() {
		return vl_emp_feito;
	}
	
	@Override
	public String toString() {
		return cpf + "," + nome + ","
				+ categoria + "," + sal_liq + "," + sal30;
	}	
	
	
}
