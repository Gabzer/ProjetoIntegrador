package br.com.cadastro.modelo;

public class Cli_EmprestDAO {
	
	public double formPmtPost () {
		double pmt;
		int n = 0;
		float i = 0;
		double g = Math.pow(1 + i, n); 
		
		Cliente cli = new Cliente();
		double pv = cli.getSal_liq();
		
		pmt = pv * ((i * g) / (g - 1));
		
		return pmt;
	}
	
	public double formPmtAnt (){
		double pmt;
		int n = 0;
		float i = 0;
		double g = Math.pow(1 + i, n); 
		
		Cliente cli = new Cliente();
		double pv = cli.getSal_liq();
		
		pmt = pv * ((i * g) / (g - 1)) * (1 / (1 + i));
		
		return pmt;
	}
}
