package br.com.cadastro.visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.cadastro.modelo.Cliente;
import br.com.cadastro.modelo.ClienteDAO;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ManterCliente extends JFrame {

	private JPanel contentPane;
	private JTextField boxNome;
	private JTextField boxCpf;
	private JTextField boxSal_liq;
	private JTextField textField_3;
	private JTextField boxValorEmprest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		Cliente cliente = new Cliente();
		
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
		Scanner ler = new Scanner(System.in);
		Cliente cliente = new Cliente();
		ClienteDAO cliDao = new ClienteDAO();
		
		String nome;
		String cpf;
		double sal_liq;
		double margem;
		
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
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 46, 46, 14);
		contentPane.add(lblNome);
		
		//caixa de texto NOME
		boxNome = new JTextField();
		nome = getText(boxNome);
		boxNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setNome(nome);
			}
		});
		boxNome.setBounds(48, 43, 463, 20);
		contentPane.add(boxNome);
		boxNome.setColumns(10);
		
		
		
		JButton btnPesqNome = new JButton("Pesquisar");
		btnPesqNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<ManterCliente> mostrarcli = new ArrayList<ManterCliente>();
				cliDao.selectByNome(nome);
				
				for(int i = 0; i < 4; i++){
					if(i == 0){
						mostrarcli[i].setText(boxCpf);
					}
				}
				mostrarcli[0].setText(boxCpf);
				mostrarcli[1].setText(boxNome);
				if(mostrarcli[2] == "Aposentado"){
					rdbtnAposentado.isSelected();
				}else{
					if(mostrarcli[2] == "Pensionista"){
						rdbtnPensionista.isSelected();
					}else{
						rdbtnFuncPublico.isSelected();
					}
				}
				mostrarcli[3].setDouble(boxSal_liq);
			}
		});
		btnPesqNome.setBounds(521, 42, 89, 23);
		contentPane.add(btnPesqNome);
		
				
		//caixa de texto CPF
		boxCpf = new JTextField();
		cpf = getText(boxCpf);
		boxCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setCpf(cpf);
			}
		});
		boxCpf.setBounds(48, 74, 197, 20);
		contentPane.add(boxCpf);
		boxCpf.setColumns(10);		
		
		JButton btnPesqCpf = new JButton("Pesquisar");
		btnPesqCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPesqCpf.setBounds(255, 74, 89, 23);
		contentPane.add(btnPesqCpf);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 77, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(185, 117, 78, 14);
		contentPane.add(lblCategoria);
		
		//escolha de categorias		
		JRadioButton rdbtnAposentado = new JRadioButton("Aposentado");
		rdbtnAposentado.setBounds(288, 113, 109, 23);
		contentPane.add(rdbtnAposentado);
		
		JRadioButton rdbtnPensionista = new JRadioButton("Pensionista");
		rdbtnPensionista.setBounds(288, 139, 109, 23);
		contentPane.add(rdbtnPensionista);
		
		JRadioButton rdbtnFuncPublico = new JRadioButton("Funcionário Público");
		rdbtnFuncPublico.setBounds(288, 167, 171, 23);
		contentPane.add(rdbtnFuncPublico);
		
		if(rdbtnAposentado.isSelected()){
			cliente.setCategoria("Aposentado");
		}else{
			if(rdbtnPensionista.isSelected()){
				cliente.setCategoria("Pensionista");
			}else{
				cliente.setCategoria("Funcionário Público");
			}
		}
		
		
		JLabel lblSalrioLquido = new JLabel("Salário Líquido:");		
		lblSalrioLquido.setBounds(10, 199, 97, 14);
		contentPane.add(lblSalrioLquido);
		
		//caixa de texto Sal_liq
		boxSal_liq = new JTextField();
		sal_liq = getDouble(boxSal_liq);
		boxSal_liq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setSal_liq(sal_liq);
			}
		});
		boxSal_liq.setBounds(96, 197, 137, 20);
		contentPane.add(boxSal_liq);
		boxSal_liq.setColumns(10);
		
		JLabel lblMargem = new JLabel("Margem*:");
		lblMargem.setBounds(285, 225, 61, 14);
		contentPane.add(lblMargem);
		
		//caixa de texto não editável da Margem
		textField_3 = new JTextField();
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double margem;
				double margemfinal;
				double emp_feito;
				
				margem = cliente.getSal30();
				emp_feito = cliente.getEmp_feito();
								
				if(cliente.getEmprestFeito()){
					margemfinal = margem - emp_feito;
				}
				System.out.println(margem);
			}
		});
		textField_3.setEditable(false);
		textField_3.setBounds(371, 222, 239, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);		
		
		JLabel lblSeOMesmo = new JLabel("*Se o mesmo já possui empréstimos, \n"
				+ "aparecerá a margem RESTANTE no campo.");
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
		double emp_feito = getDouble(boxValorEmprest);
		
		if(cliente.getEmprestFeito() == false) {
			boxValorEmprest.setEditable(false);
		}else{
			boxValorEmprest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cliente.setEmp_feito(emp_feito);
				}
			});		
		}
		boxValorEmprest.setBounds(96, 297, 137, 20);
		contentPane.add(boxValorEmprest);
		boxValorEmprest.setColumns(10);
		
		//botão SALVAR
		JButton btnCadastrarCli = new JButton("Cadastrar");
		btnCadastrarCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliDao.insert(cliente);
			}
		});
		btnCadastrarCli.setBounds(162, 328, 101, 23);
		contentPane.add(btnCadastrarCli);
		
		JButton btnExcluirCli = new JButton("Excluir");
		btnExcluirCli.setBounds(381, 328, 89, 23);
		contentPane.add(btnExcluirCli);
		
		JButton btnAlterarCli = new JButton("Alterar");
		btnAlterarCli.setBounds(275, 328, 89, 23);
		contentPane.add(btnAlterarCli);
	}

	private double getDouble(JTextField boxSal_liq2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String getText(JTextField boxNome2) {
		// TODO Auto-generated method stub
		return null;
	}	
}
