package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.senai.sp.jandira.model.Console;
import br.senai.sp.jandira.model.Jogo;
import br.senai.sp.jandira.repository.JogoRepository;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class FrameGestaoJogos extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtFabricante;
	private JTextField txtValor;
	private JTextField txtObservacoes;
	
	private int posicao = 0;

	public FrameGestaoJogos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(10, 22, 47, 14);
		contentPane.add(lblTitulo);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(48, 19, 86, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setBounds(10, 51, 86, 14);
		contentPane.add(lblFabricante);

		txtFabricante = new JTextField();
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(74, 48, 86, 20);
		contentPane.add(txtFabricante);

		JCheckBox chbZerado = new JCheckBox("Zerado");
		chbZerado.setHorizontalAlignment(SwingConstants.CENTER);
		chbZerado.setBounds(63, 72, 97, 23);
		contentPane.add(chbZerado);

		JLabel lblConsole = new JLabel("Console:");
		lblConsole.setBounds(10, 105, 86, 14);
		contentPane.add(lblConsole);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 142, 86, 14);
		contentPane.add(lblValor);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(74, 139, 86, 20);
		contentPane.add(txtValor);

		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservacoes.setBounds(10, 179, 86, 14);
		contentPane.add(lblObservacoes);

		txtObservacoes = new JTextField();
		txtObservacoes.setColumns(10);
		txtObservacoes.setBounds(84, 176, 117, 74);
		contentPane.add(txtObservacoes);

		JComboBox cmbConsole = new JComboBox();
		cmbConsole.setBounds(74, 102, 86, 22);
		contentPane.add(cmbConsole);
		DefaultComboBoxModel<String> modelConsole = new DefaultComboBoxModel<String>();

		for (Console c : Console.values()) {
			modelConsole.addElement(c.getDescricao());
		}
		
		cmbConsole.setModel(modelConsole);
		
		JLabel lblMeusJogos = new JLabel("Meus Jogos:");
		lblMeusJogos.setBounds(251, 22, 86, 14);
		contentPane.add(lblMeusJogos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 51, 130, 195);
		contentPane.add(scrollPane);

		DefaultListModel<String> listaModel = new DefaultListModel<String>();

		JList listJogos = new JList();
		scrollPane.setViewportView(listJogos);

		listJogos.setModel(listaModel);

		JogoRepository jogos = new JogoRepository(2);

		JButton btnSalvar = new JButton("Salvar Jogo");
		btnSalvar.setBounds(27, 277, 89, 23);
		contentPane.add(btnSalvar);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Jogo jogo = new Jogo();
				jogo.setTitulo(txtTitulo.getText());
				jogo.setFabricante(txtFabricante.getText());
				jogo.setZerado(chbZerado.isSelected());
				jogo.setConsole(determinarConsole(cmbConsole.getSelectedIndex()));
				
				jogos.gravar(jogo, posicao);
				
				posicao++;
				
				listaModel.addElement(jogo.getTitulo());

			}
		});
		
		listJogos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				Jogo jogo = jogos.listarJogo(listJogos.getSelectedIndex());
				txtTitulo.setText(jogo.getTitulo());
				txtFabricante.setText(jogo.getFabricante());
				chbZerado.setSelected(jogo.isZerado());
				
				
			}
		});
		
		
	}	

	private Console determinarConsole(int consoleSelecionado) { 
		
		switch (consoleSelecionado ) {
			case 0 : 
				return Console.PLAYSTATION;
			case 1 :
				return Console.XBOX;
			default :
				return Console.NINTENDO;
		}	
	}
}
