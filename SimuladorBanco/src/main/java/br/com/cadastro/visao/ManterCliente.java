package br.com.cadastro.visao;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.cadastro.modelo.Cliente;
import br.com.cadastro.modelo.ClienteDAO;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ManterCliente extends JFrame {

	private JPanel contentPane;
	private JTextField boxNome;
	private JTextField boxCpf;
	private JTextField boxSal_liq;
	private JTextField textField_3;
	private JTextField boxValorEmprest;
	private JTextField boxCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManterCliente frame = new ManterCliente();
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
	public ManterCliente() {
		ClienteDAO cliDao = new ClienteDAO();
		Cliente cliente = new Cliente();
		
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setBounds(275, 376, 89, 23);
		contentPane.add(btnVoltar);
		
		//CPF
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 77, 46, 14);
		contentPane.add(lblCpf);
		
		//caixa de texto CPF
		boxCpf = new JTextField();		
		boxCpf.setBounds(48, 74, 197, 20);
		contentPane.add(boxCpf);
		boxCpf.setColumns(10);		
		
		//PESQUISA pelo CPF		
		JButton btnPesqCpf = new JButton("Pesquisar");
		btnPesqCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lsCli = cliDao.selectByCpf(boxCpf.getText());
				String[] dados;				
				
				dados = lsCli.split(",");				
				
				boxCpf.setText(dados[0]);
				boxNome.setText(dados[1]);
				boxCategoria.setText(dados[2]);
				boxSal_liq.setText(dados[3]);				
			}
		});
		btnPesqCpf.setBounds(255, 74, 89, 23);
		contentPane.add(btnPesqCpf);
		
		//NOME
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 46, 46, 14);
		contentPane.add(lblNome);
		
		//caixa de texto NOME
		boxNome = new JTextField();	
		boxNome.setBounds(48, 43, 463, 20);	
		contentPane.add(boxNome);			
		boxNome.setColumns(10);
				
		//PESQUISA pelo NOME
		JButton btnPesqNome = new JButton("Pesquisar");
		btnPesqNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String lsCli = cliDao.selectByNome(boxNome.getText());
				String[] dados;
				
				dados = lsCli.split(",");				
				
				boxCpf.setText(dados[0]);
				boxNome.setText(dados[1]);
				boxCategoria.setText(dados[2]);
				boxSal_liq.setText(dados[3]);
			}
		});
		btnPesqNome.setBounds(521, 42, 89, 23);
		contentPane.add(btnPesqNome);
					
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 131, 78, 14);
		contentPane.add(lblCategoria);
		
		//caixa de texto CATEGORIA
		boxCategoria = new JTextField();
		boxCategoria.setBounds(108, 128, 236, 20);
		contentPane.add(boxCategoria);
		boxCategoria.setColumns(10);
				
		
		JLabel lblSalrioLquido = new JLabel("Salário Líquido:");		
		lblSalrioLquido.setBounds(10, 199, 97, 14);
		contentPane.add(lblSalrioLquido);
		
		//caixa de texto Sal_liq
		boxSal_liq = new JTextField();		
		boxSal_liq.setBounds(96, 197, 137, 20);
		contentPane.add(boxSal_liq);		
		boxSal_liq.setColumns(10);
		
		JLabel lblMargem = new JLabel("Margem*:");
		lblMargem.setBounds(285, 225, 61, 14);
		contentPane.add(lblMargem);
		
		//caixa de texto não editável da Margem
		textField_3 = new JTextField();
		textField_3.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				System.out.println(cliente.getSal30());
			}
		});
		textField_3.setEditable(false);
		textField_3.setBounds(371, 222, 239, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);		
		
		JLabel lblSeOMesmo = new JLabel("*Se o mesmo já possui empréstimos, \n"
				+ "\n aparecerá a margem RESTANTE no campo.");
		lblSeOMesmo.setBounds(288, 250, 325, 55);
		contentPane.add(lblSeOMesmo);	
		
		JLabel lblOMesmoJ = new JLabel("Já possui empréstimo ?");
		lblOMesmoJ.setBounds(10, 238, 118, 14);
		contentPane.add(lblOMesmoJ);
		
		//opção se ele TEM empréstimo
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.setBounds(162, 234, 71, 23);
		contentPane.add(rdbtnSim);
		
		JRadioButton rdbtnNo = new JRadioButton("Não");
		rdbtnNo.setBounds(162, 262, 71, 23);
		contentPane.add(rdbtnNo);
		
		if(rdbtnSim.isSelected()){
			cliente.setEmprestFeito(true);
		}else{
			cliente.setEmprestFeito(false);
		}
		
		JLabel lblQualOValor = new JLabel("Qual o valor ?");
		lblQualOValor.setBounds(10, 300, 71, 14);
		contentPane.add(lblQualOValor);
		
		//valor do EMPREST JÁ realizado
		boxValorEmprest = new JTextField();
		boxValorEmprest.setText("000.00");
		if(cliente.getEmprestFeito() == false) {
			boxValorEmprest.setEditable(false);
		}else{
			boxValorEmprest.setEditable(true);		
		}		
		boxValorEmprest.setBounds(96, 297, 137, 20);
		contentPane.add(boxValorEmprest);
		boxValorEmprest.setColumns(10);
		cliente.setEmp_feito(Double.parseDouble(boxValorEmprest.getText()));
		
		//botão SALVAR
		JButton btnCadastrarCli = new JButton("Cadastrar");
		btnCadastrarCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setCpf(boxCpf.getText());
				cliente.setNome(boxNome.getText());				
				cliente.setCategoria(boxCategoria.getText());					
				cliente.setSal_liq(Double.parseDouble(boxSal_liq.getText()));				
				
				cliDao.insert(cliente);
			}
		});
		btnCadastrarCli.setBounds(162, 328, 101, 23);
		contentPane.add(btnCadastrarCli);
		
		//botão EXCLUIR
		JButton btnExcluirCli = new JButton("Excluir");
		btnExcluirCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliDao.deleteByCpf(cliente.getCpf());
			}
		});
		btnExcluirCli.setBounds(381, 328, 89, 23);
		contentPane.add(btnExcluirCli);
		
		//botão ALTERAR
		JButton btnAlterarCli = new JButton("Alterar");
		btnAlterarCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setNome(boxNome.getText());				
				cliente.setCategoria(boxCategoria.getText());				
				cliente.setSal_liq(Double.parseDouble(boxSal_liq.getText()));
				
				cliDao.updateByCpf(cliente);
			}
		});
		btnAlterarCli.setBounds(275, 328, 89, 23);
		contentPane.add(btnAlterarCli);
		
		
	}
}
