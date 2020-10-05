package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TabelaPontosDAO;
import pontos.model.Pontos;
import pontos.model.PontosCalc;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewEntrada extends JFrame {

	public boolean bol = true;
	Pontos p = new Pontos();
	PontosCalc pcB = new PontosCalc();
	private JPanel contentPane;
	private JTextField txtPontos;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEntrada frame = new ViewEntrada();
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
	@SuppressWarnings("serial")
	public ViewEntrada() {
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Adicionar Pontos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ======================================Label======================================

		// Criação de titulos
		JLabel lblPontuacaoMaria = new JLabel("Pontua\u00E7\u00E3o da partida:");
		lblPontuacaoMaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblPontuacaoMaria.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPontuacaoMaria.setBounds(149, 45, 140, 29);
		contentPane.add(lblPontuacaoMaria);

		JLabel lblTitulo = new JLabel("Tabela de Pontos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
		lblTitulo.setBounds(109, 11, 224, 23);
		contentPane.add(lblTitulo);

		// ======================================Tabela======================================

		// Criação da tabela
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, }, new String[] {
				"Jogo", "Placar", "Pts. M\u00E1ximo", "Pts. M\u00EDnimo", "Recorde M\u00E1x.", "Recorde M\u00EDn." }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setMinWidth(80);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setMinWidth(80);
		table.setBounds(10, 107, 440, 193);

		// ======================================ScrollTable======================================

		// Criação do scroll da tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 440, 192);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		txtPontos = new JTextField();
		txtPontos.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPontos.setBounds(171, 77, 86, 20);
		contentPane.add(txtPontos);
		txtPontos.setColumns(10);

		// ======================================Botôes======================================

		// ---------------------------------AdicionaPontos---------------------------------
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String confirm = txtPontos.getText();
				boolean numero = true;
				PontosCalc pc = new PontosCalc();

				// Texte para verificar se é um número
				try {
					Integer.parseInt(txtPontos.getText());
				} catch (Exception ex) {
					numero = false;
				}

				// Se o valor armazanado no txtPontos for diferente de null e for um número:
				if (confirm != null && numero) {

					// Chama o metodo da classe PontosCals verificar para validar o numero digitado
					// dentro das especificações
					pc.verifica(Integer.parseInt(txtPontos.getText()));

					// Lê e constroi a tabela após a alteração e limpa o campo de texto do txtPontos
					readJTable();
					txtPontos.setText("");
				} else {

					// Se o valor armazenado no txtPontos for null ou diferente de um número executa
					// o aviso:
					JOptionPane.showMessageDialog(null, "Digite um valor numérico e inteiro.");
				}
			}
		});
		btnAdicionar.setBounds(313, 311, 89, 23);
		contentPane.add(btnAdicionar);

		// ----------------------------LimpaCaixaDeTexto------------------------
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Modifica o valor dentro das caixas de texto para vazio
				txtPontos.setText("");
			}
		});
		btnLimpar.setBounds(44, 311, 89, 23);
		contentPane.add(btnLimpar);

		readJTable();
	}

	// ======================================ConstruçãoJTableDoBanco======================================

	// Metodo de Criação da tabela do banco de dados no JTable
	public void readJTable() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		TabelaPontosDAO tdao = new TabelaPontosDAO();

		// Tabela começara com 0 linhas
		modelo.setNumRows(0);

		// Percorre uma lista de Pontos
		for (Pontos p : tdao.read("SELECT * FROM jogo")) {

			// Adiciona em cada linha um objeto novo com os atributos do objeto Pontos lido
			// no momento
			modelo.addRow(new Object[] { p.getId_jogo(), p.getJg_placar(), p.getJg_pts_max(), p.getJg_pts_min(),
					p.getJg_pts_recMax(), p.getJg_pts_recMin() });
		}

	}
}
