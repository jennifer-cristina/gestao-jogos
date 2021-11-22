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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import java.awt.Font;

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
		setBounds(100, 100, 533, 392);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelFormularioJogos = new JPanel();
		panelFormularioJogos.setBounds(59, 0, 458, 361);
		contentPane.add(panelFormularioJogos);
		panelFormularioJogos.setLayout(null);
		panelFormularioJogos.setVisible(true);

		JPanel panelFormularioFabricante = new JPanel();
		panelFormularioFabricante.setBounds(59, 0, 466, 361);
		contentPane.add(panelFormularioFabricante);
		panelFormularioFabricante.setVisible(false);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitulo.setBounds(26, 62, 52, 17);
		panelFormularioJogos.add(lblTitulo);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(26, 90, 86, 20);
		panelFormularioJogos.add(txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setFont(new Font("Arial", Font.BOLD, 14));
		lblFabricante.setBounds(26, 124, 86, 14);
		panelFormularioJogos.add(lblFabricante);

		JComboBox cmbFabricante = new JComboBox();
		cmbFabricante.setBounds(26, 149, 86, 22);
		panelFormularioJogos.add(cmbFabricante);

		JogoRepository jogos = new JogoRepository(32);
		FabricanteRepository fabricantes = new FabricanteRepository(32);

		DefaultComboBoxModel<String> modelFabricante = carregarFabricantes(fabricantes);

		cmbFabricante.setModel(modelFabricante);

		JCheckBox chbZerado = new JCheckBox("Zerado");
		chbZerado.setFont(new Font("Arial", Font.BOLD, 14));
		chbZerado.setHorizontalAlignment(SwingConstants.CENTER);
		chbZerado.setBounds(156, 181, 86, 23);
		panelFormularioJogos.add(chbZerado);

		JLabel lblConsole = new JLabel("Console:");
		lblConsole.setFont(new Font("Arial", Font.BOLD, 14));
		lblConsole.setBounds(156, 63, 73, 14);
		panelFormularioJogos.add(lblConsole);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Arial", Font.BOLD, 14));
		lblValor.setBounds(156, 124, 52, 14);
		panelFormularioJogos.add(lblValor);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(156, 150, 86, 20);
		panelFormularioJogos.add(txtValor);

		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservacoes.setFont(new Font("Arial", Font.BOLD, 14));
		lblObservacoes.setBounds(26, 190, 104, 14);
		panelFormularioJogos.add(lblObservacoes);

		JComboBox cmbConsole = new JComboBox();
		cmbConsole.setBounds(156, 89, 86, 22);
		panelFormularioJogos.add(cmbConsole);
		DefaultComboBoxModel<String> modelConsole = new DefaultComboBoxModel<String>();

		for (Console c : Console.values()) {
			modelConsole.addElement(c.getDescricao());
		}

		cmbConsole.setModel(modelConsole);

		JLabel lblMeusJogos = new JLabel("Meus Jogos:");
		lblMeusJogos.setFont(new Font("Arial", Font.BOLD, 14));
		lblMeusJogos.setBounds(301, 57, 104, 27);
		panelFormularioJogos.add(lblMeusJogos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(301, 91, 132, 203);
		panelFormularioJogos.add(scrollPane);
		
				JList listJogos = new JList();
				scrollPane.setViewportView(listJogos);
				
						listJogos.addKeyListener(new KeyListener() {
				
							@Override
							public void keyReleased(KeyEvent key) {
				
								int code = key.getKeyCode();
				
								if (code == KeyEvent.VK_RIGHT) {
									listJogos.setSelectedIndex(listJogos.getSelectedIndex() + 1);
								} else if (code == KeyEvent.VK_LEFT) {
									listJogos.setSelectedIndex(listJogos.getSelectedIndex() - 1);
								}
				
							}
				
							@Override
							public void keyTyped(KeyEvent key) {
							}
				
							@Override
							public void keyPressed(KeyEvent e) {
				
							}
				
						});
						
						DefaultListModel<String> listaModel = new DefaultListModel<String>();

								listJogos.setModel(listaModel);
								
										listJogos.addListSelectionListener(new ListSelectionListener() {
								
											@Override
											public void valueChanged(ListSelectionEvent e) {
								
												Jogo jogo = jogos.listarJogo(listJogos.getSelectedIndex());
												txtTitulo.setText(jogo.getTitulo());
								
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
														txtValor.setText(String.valueOf(jogo.getValor()));
														txtObservacoes.setText(jogo.getObservacoes());
										
													}
												});


		JButton btnEsquerda = new JButton("<");
		btnEsquerda.setToolTipText("<");
		btnEsquerda.setBounds(327, 312, 41, 23);
		panelFormularioJogos.add(btnEsquerda);

		JButton btnDireita = new JButton(">");
		btnDireita.setBounds(378, 312, 41, 23);
		panelFormularioJogos.add(btnDireita);

		btnEsquerda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				listJogos.setSelectedIndex(listJogos.getSelectedIndex() - 1);

			}
		});

		btnDireita.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				listJogos.setSelectedIndex(listJogos.getSelectedIndex() + 1);

			}
		});

		getRootPane().getActionMap().put("myAction", new AbstractAction() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println("Funfou");
			}
		});

		JButton btnSalvar = new JButton("Salvar Jogo");
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
		btnSalvar.setBounds(156, 311, 140, 23);
		panelFormularioJogos.add(btnSalvar);

		JLabel lblTituloFormularioJogo = new JLabel("Formul\u00E1rio de Jogos");
		lblTituloFormularioJogo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTituloFormularioJogo.setBounds(137, 11, 186, 27);
		panelFormularioJogos.add(lblTituloFormularioJogo);

		txtObservacoes = new JTextField();
		txtObservacoes.setBounds(26, 220, 105, 94);
		panelFormularioJogos.add(txtObservacoes);
		txtObservacoes.setColumns(10);

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

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(169, 33, 46, 14);
		panelFormularioFabricante.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(211, 30, 86, 20);
		panelFormularioFabricante.add(txtNome);
		txtNome.setColumns(10);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(10, 57, 50, 304);
		contentPane.add(panelMenu);

		JButton btnFormularioJogo = new JButton();
		panelMenu.add(btnFormularioJogo);

		ImageIcon imagemJogo = new ImageIcon(getClass().getResource("../images/controle-de-jogo.png"));

		btnFormularioJogo.setIcon(imagemJogo);

		JButton btnFormularioFabricante = new JButton();
		panelMenu.add(btnFormularioFabricante);

		ImageIcon imagemFabricante = new ImageIcon(getClass().getResource("../images/fabricante.png"));

		btnFormularioFabricante.setIcon(imagemFabricante);

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
