package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.senai.sp.jandira.model.Console;
import br.senai.sp.jandira.model.Fabricante;
import br.senai.sp.jandira.model.Jogo;
import br.senai.sp.jandira.repository.FabricanteRepository;
import br.senai.sp.jandira.repository.JogoRepository;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JSlider;

public class FrameGestaoJogos extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtFabricante;
	private JTextField txtValor;
	private JTextField txtObservacoes;
 
	private int posicaoJogo = 0;
	private int posicaoFabricante = 0;

	private JTextField txtNome;

	public FrameGestaoJogos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelFormularioJogos = new JPanel();
		panelFormularioJogos.setBounds(47, 0, 437, 361);
		contentPane.add(panelFormularioJogos);
		panelFormularioJogos.setLayout(null);
		panelFormularioJogos.setVisible(true);

		JPanel panelFormularioFabricante = new JPanel();
		panelFormularioFabricante.setBounds(47, 0, 437, 361);
		contentPane.add(panelFormularioFabricante);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(23, 33, 30, 14);
		panelFormularioJogos.add(lblTitulo);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(73, 30, 86, 20);
		panelFormularioJogos.add(txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setBounds(23, 61, 55, 14);
		panelFormularioJogos.add(lblFabricante);

		JComboBox cmbFabricante = new JComboBox();
		cmbFabricante.setBounds(97, 57, 62, 22);
		panelFormularioJogos.add(cmbFabricante);

		JogoRepository jogos = new JogoRepository(32);
		FabricanteRepository fabricantes = new FabricanteRepository(32);

		DefaultComboBoxModel<String> modelFabricante = carregarFabricantes(fabricantes);

		cmbFabricante.setModel(modelFabricante);

		JCheckBox chbZerado = new JCheckBox("Zerado");
		chbZerado.setHorizontalAlignment(SwingConstants.CENTER);
		chbZerado.setBounds(100, 88, 59, 23);
		panelFormularioJogos.add(chbZerado);

		JLabel lblConsole = new JLabel("Console:");
		lblConsole.setBounds(26, 126, 42, 14);
		panelFormularioJogos.add(lblConsole);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(23, 163, 28, 14);
		panelFormularioJogos.add(lblValor);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(73, 160, 86, 20);
		panelFormularioJogos.add(txtValor);

		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservacoes.setBounds(11, 205, 67, 14);
		panelFormularioJogos.add(lblObservacoes);

		txtObservacoes = new JTextField();
		txtObservacoes.setColumns(10);
		txtObservacoes.setBounds(97, 205, 86, 20);
		panelFormularioJogos.add(txtObservacoes);

		JComboBox cmbConsole = new JComboBox();
		cmbConsole.setBounds(86, 122, 73, 22);
		panelFormularioJogos.add(cmbConsole);
		DefaultComboBoxModel<String> modelConsole = new DefaultComboBoxModel<String>();

		for (Console c : Console.values()) {
			modelConsole.addElement(c.getDescricao());
		}

		cmbConsole.setModel(modelConsole);

		JLabel lblMeusJogos = new JLabel("Meus Jogos:");
		lblMeusJogos.setBounds(290, 33, 60, 14);
		panelFormularioJogos.add(lblMeusJogos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 59, 132, 172);
		panelFormularioJogos.add(scrollPane);

		DefaultListModel<String> listaModel = new DefaultListModel<String>();

		JList listJogos = new JList();
		scrollPane.setViewportView(listJogos);

		listJogos.setModel(listaModel);

		listJogos.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				Jogo jogo = jogos.listarJogo(listJogos.getSelectedIndex());
				txtTitulo.setText(jogo.getTitulo());
				chbZerado.setSelected(jogo.isZerado());

			}
		});

		JButton btnEsquerda = new JButton("<");
		btnEsquerda.setToolTipText("<");
		btnEsquerda.setBounds(268, 256, 41, 23);
		panelFormularioJogos.add(btnEsquerda);

		JButton btnSalvar = new JButton("Salvar Jogo");
		btnSalvar.setBounds(38, 256, 89, 23);
		panelFormularioJogos.add(btnSalvar);

		JButton btnSalvarFabricante = new JButton("Salvar");
		btnSalvarFabricante.setBounds(169, 72, 89, 23);
		panelFormularioFabricante.add(btnSalvarFabricante);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				 Jogo jogo = new Jogo();
				jogo.setTitulo(txtTitulo.getText());
				jogo.setFabricante(fabricantes.listarFabricante(cmbFabricante.getSelectedIndex()));
				jogo.setZerado(chbZerado.isSelected());
				jogo.setConsole(determinarConsole(cmbConsole.getSelectedIndex()));
				
				// Transformando o texto (String) em double, porque ele esta pedindo double.
				jogo.setValor(Double.parseDouble(txtValor.getText()));
				jogo.setObservacoes(txtObservacoes.getText());

				jogos.gravar(jogo, posicaoJogo);

				posicaoJogo++;

				listaModel.addElement(jogo.getTitulo());

			}
		});


		btnSalvarFabricante.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Fabricante fabricante = new Fabricante();
				fabricante.setNome(txtNome.getText());

				fabricantes.gravar(fabricante, posicaoFabricante);

				posicaoFabricante++;

			}
		});

		listJogos.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				Jogo jogo = jogos.listarJogo(listJogos.getSelectedIndex());

				txtTitulo.setText(jogo.getTitulo());
				cmbFabricante.setSelectedIndex(fabricantes.getIndex(jogo.getFabricante()));
				chbZerado.isSelected();
				cmbConsole.setSelectedIndex(jogo.getConsole().ordinal());
				txtValor.setText(Double.parseDouble(jogo.getValor(txtValor.setText())));
				txtObservacoes.setText(jogo.getObservacoes());



			}
		});

		
		JButton btnDireita = new JButton(">");
		btnDireita.setBounds(336, 256, 41, 23);
		panelFormularioJogos.add(btnDireita);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(169, 33, 46, 14);
		panelFormularioFabricante.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(211, 30, 86, 20);
		panelFormularioFabricante.add(txtNome);
		txtNome.setColumns(10);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 48, 361);
		contentPane.add(panelMenu);

		JButton btnFormularioJogo = new JButton("J");
		panelMenu.add(btnFormularioJogo);

		JButton btnFormularioFabricante = new JButton("F");
		panelMenu.add(btnFormularioFabricante);

		btnFormularioJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultComboBoxModel<String> modelFabricante = carregarFabricantes(fabricantes);
				cmbFabricante.setModel(modelFabricante);

				panelFormularioJogos.setVisible(true);
				panelFormularioFabricante.setVisible(false);

			}
		});

		btnFormularioFabricante.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				panelFormularioFabricante.setVisible(true);
				panelFormularioJogos.setVisible(false);

			}
		});

	}

	private Console determinarConsole(int consoleSelecionado) {

		switch (consoleSelecionado) {
		case 0:
			return Console.PLAYSTATION;
		case 1:
			return Console.XBOX;
		default:
			return Console.NINTENDO;
		}
	}

	private DefaultComboBoxModel<String> carregarFabricantes(FabricanteRepository fabricantes) {

		DefaultComboBoxModel<String> modelFabricante = new DefaultComboBoxModel<String>();

		Fabricante[] fabricantesGravados = fabricantes.listarTodos();

		for (Fabricante f : fabricantesGravados) {
			if (f != null) {
				modelFabricante.addElement(f.getNome());
			}
		}

		return modelFabricante;

	}
}
