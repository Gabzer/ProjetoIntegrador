package br.com.cadastro.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.com.cadastro.controle.EmprestControl;
import br.com.cadastro.modelo.Cliente;

public class Sac extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sac frame = new Sac();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sac() {
	setTitle("Tabela SAC");
			
		    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setResizable(false);
	
	        jTableSAC1 = new JTable();
	        jTableSAC1.setBorder(new LineBorder(Color.LIGHT_GRAY));
	        jTableSAC1.setBounds(44, 85, 533, 569);
	
	
	        setSize(new Dimension(621, 694));
	        setLocationRelativeTo(null);
	        
	        jTableSAC1.setModel(new DefaultTableModel(
	        	new Object[][] {
	        			
	        	},
	        	new String[] {
	        		
	        	}
	        ));
	        JScrollPane scrollPane = new JScrollPane(jTableSAC1);
		    add(scrollPane, BorderLayout.CENTER);
	}
////OBJETOS INSTANCIADOS PARA USO NESTA CLASSE
	EmprestControl emprestcontrol	= new EmprestControl();
	NumberFormat x 		= NumberFormat.getCurrencyInstance();
		
	private JTable jTableSAC1;
		
	////DECLARAÇÃO DAS VARIAVEIS
	double salDevAtt;
	private float pmt;
	private float rendaCliente;
	
	public void setValTableSAC() {
		 
		emprestcontrol.setSalDev(emprestcontrol.getSalDev());
		 
		String[] nomeColunas;
	    nomeColunas = new String[]{"Mês", "Prestações", "Amortização", "Juros", "Saldo Devedor"};
	    Object[][] data = new Object[emprestcontrol.getNumParcela()][5];

	    ////Inserir meses
	    for (int i = 0; i < emprestcontrol.getNumParcela(); i++) {
	    	data[i][0] = i + 1;
	    }
	        
	    /////////////////////////////////////////////////////////////////////

	    ////Inserir a amortização
	    for (int i = 0; i < emprestcontrol.getNumParcela(); i++) {
	        data[i][2] = x.format(EmprestControl.calcAmortSac(emprestcontrol.getNumParcela(), emprestcontrol.getSalDev()));
	    }
	    
	    /////////////////////////////////////////////////////////////////////    

	    ////Inserir o juros
	    for (int i = 0; i < emprestcontrol.getNumParcela(); i++) {
	        if (i <= 0) {
	            	
	        	salDevAtt =  EmprestControl.calcSalDevSac(emprestcontrol.getSalDev(), EmprestControl.calcAmortSac(emprestcontrol.getNumParcela(), emprestcontrol.getSalDev()));
	            data[i][4] = x.format(salDevAtt);
	            data[i][3] = x.format(EmprestControl.calcJurosSac(emprestcontrol.getSalDev(), emprestcontrol.getJuros()));
	            data[i][1] = x.format(EmprestControl.calcSacPMT(EmprestControl.calcAmortSac(emprestcontrol.getNumParcela(), emprestcontrol.getSalDev()), EmprestControl.calcJurosSac(emprestcontrol.getSalDev(), emprestcontrol.getJuros())));
	            pmt = (float) EmprestControl.calcSacPMT(EmprestControl.calcAmortSac(emprestcontrol.getNumParcela(), emprestcontrol.getSalDev()), EmprestControl.calcJurosSac(emprestcontrol.getSalDev(), emprestcontrol.getJuros()));
	            
	        } else {
	            data[i][3] = x.format(EmprestControl.calcJurosSac(salDevAtt, emprestcontrol.getJuros()));
	            data[i][1] = x.format(EmprestControl.calcSacPMT(EmprestControl.calcAmortSac(emprestcontrol.getNumParcela(), emprestcontrol.getSalDev()), EmprestControl.calcJurosSac(salDevAtt, emprestcontrol.getJuros())));
	            salDevAtt =  EmprestControl.calcSalDevSac(salDevAtt, EmprestControl.calcAmortSac(emprestcontrol.getNumParcela(), emprestcontrol.getSalDev()));
	            if (salDevAtt >= 0) {
	            	data[i][4] = x.format(salDevAtt);
	            } else {
	                data[i][4] = x.format(0.00);
	            }

	        }

	    }
	    //----------------//
	       
	    jTableSAC1.setModel(new DefaultTableModel((Object[][]) data, nomeColunas));
	        
	    rendaCliente = EmprestControl.comparaSalario(Cliente.getSal_liq());
	        
	    if(pmt <= rendaCliente){
	     	JOptionPane.showMessageDialog(null, "Emprestimo Autorizado!");
	    }else{
	      	JOptionPane.showMessageDialog(null, "O emprestimo não será autorizado, pois a parcela é maior ou equivalente a 30% da reda liquida do cliente!");
	    }
	        
	}

}
