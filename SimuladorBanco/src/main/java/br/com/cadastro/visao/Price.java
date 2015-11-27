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

public class Price extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Price frame = new Price();
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
	public Price() {
		setTitle("Tabela Price");
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        jTablePrice = new javax.swing.JTable();
            
        jTablePrice = new JTable();
        jTablePrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
        jTablePrice.setBounds(44, 85, 533, 569);

        setSize(new Dimension(621, 646));
        setLocationRelativeTo(null);
        
        jTablePrice.setModel(new DefaultTableModel(
        	new Object[][] {},
        	new String[] {}
        )); 
        
        JScrollPane scrollPane = new JScrollPane(jTablePrice);
	    add(scrollPane, BorderLayout.CENTER);
	}
	//Instanciando os objetos
		EmprestControl emprestControl 	= new EmprestControl();
		Operacao operacao 	= new Operacao();
		NumberFormat x 		= NumberFormat.getCurrencyInstance();
		Cliente cliente 	= new Cliente();

		
		//Variáveis
		private float salDevAttPrice;
		//Variável que recebe 30% da reda do cliente
		private double rendaCliente;
		private double pmt;
		private JTable jTablePrice;
		
		public void setValTablePrice() {
	        String[] nomeCol;
	        nomeCol = new String[]{"Mês", "Prestação", "Amortização", "Juros", "Saldo Devedor"};
	        Object[][] valor = new Object[operacao.getNumParcela()][5];

	        //SetarMesesTabela
	        for (int i = 0; i < operacao.getNumParcela(); i++) {
	            valor[i][0] = i + 1;
	        }
	        //--------------------//

	        for (int i = 0; i < operacao.getNumParcela(); i++) {
	            valor[i][1] = x.format(EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela()));
	            pmt = EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela());
	        }
	        
	        for (int i = 0; i < operacao.getNumParcela(); i++) {
	            if (i <= 0) {
	                valor[i][3] = x.format(EmprestControl.calcJurosPrice(operacao.getValor(), emprestControl.getJuros()));
	                valor[i][2] = x.format(EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela()), EmprestControl.calcJurosPrice(operacao.getValor(), emprestControl.getJuros())));
	                valor[i][4] = x.format(EmprestControl.calcSalDevPrice(operacao.getValor(), EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela()), EmprestControl.calcJurosPrice(operacao.getValor(), emprestControl.getJuros()))));
	                salDevAttPrice = (float) EmprestControl.calcSalDevPrice(operacao.getValor(), EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela()), EmprestControl.calcJurosPrice(operacao.getValor(), emprestControl.getJuros())));       
	            } else {
	                valor[i][3] 	= x.format(EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros()));
	                valor[i][2] 	= x.format(EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela()), EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros())));
	                valor[i][4] 	= x.format(EmprestControl.calcSalDevPrice(salDevAttPrice, EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela()), EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros()))));
	                salDevAttPrice 	= (float) EmprestControl.calcSalDevPrice(salDevAttPrice, EmprestControl.calcAmortPrice(EmprestControl.calcPricePMT(operacao.getValor(), emprestControl.getJuros(), operacao.getNumParcela()), EmprestControl.calcJurosPrice(salDevAttPrice, emprestControl.getJuros())));       
	                if(salDevAttPrice <= 0){
	                    valor[i][4] = x.format(0.00);
	                }	

	            }
	        }

	        jTablePrice.setModel(new DefaultTableModel((Object[][]) valor, nomeCol));
	        
	        rendaCliente =  EmprestControl.comparaSalario(Cliente.getSal_liq());

	        
	        if(pmt <= rendaCliente){
	        	JOptionPane.showMessageDialog(null, "Emprestimo Autorizado");
	        }
	        else{
	        	JOptionPane.showMessageDialog(null, "O emprestimo não será autorizado, pois a parcela é maior ou equivalente a 30% da reda liquida do cliente!");        	
	        }

	    }

}
