package executaveis;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.MarcaDAO;
import modelo.Carro;
import modelo.Marca;
import modelo.ModeloTabelaCarroBusca;
import modelo.ModeloTabelaMarcaBusca;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

public class JanelaBusca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMarca;
	private JTable tableMarca;
	private ModeloTabelaMarcaBusca modelTableBuscaMarca;
	private JLabel lblNewLabel_1;
	private JTextField tfModelo;
	private JLabel lblNewLabel_2;
	private JTextField tfAno;
	private JLabel lblNewLabel_3;
	private JTextField tfCor;
	private JLabel lblNewLabel_4;
	private JTextField tfMin;
	private JTextField tfMax;
	private Color azul = new Color(88, 78, 204);
	private Color branco = new Color(209, 207, 232);
	private MarcaDAO mDao;
	private JTable tabelaCarro;
	private ModeloTabelaCarroBusca modelTableBuscaCarro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaBusca frame = new JanelaBusca();
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
	public JanelaBusca() {

		modelTableBuscaMarca = new ModeloTabelaMarcaBusca();
		modelTableBuscaCarro = new ModeloTabelaCarroBusca();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome da Marca");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 44, 591, 14);
		contentPane.add(lblNewLabel);

		tfMarca = new JTextField();
		tfMarca.setHorizontalAlignment(SwingConstants.CENTER);
		tfMarca.setBounds(10, 58, 591, 20);
		contentPane.add(tfMarca);
		tfMarca.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 690, 84);
		contentPane.add(scrollPane);

		tableMarca = new JTable();
		scrollPane.setViewportView(tableMarca);
		tableMarca.setModel(modelTableBuscaMarca);

		JButton btnBuscaMarca = new JButton("Buscar");
		btnBuscaMarca.setBackground(azul);
		btnBuscaMarca.setForeground(branco);
		btnBuscaMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (!tfMarca.getText().equals("")) {
						modelTableBuscaMarca.buscaPorChave(tfMarca.getText());
					} else {
						JOptionPane.showMessageDialog(contentPane, "Insira uma chave de busca!");
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao buscar marca!");
				}

			}
		});
		btnBuscaMarca.setBounds(611, 57, 89, 23);
		contentPane.add(btnBuscaMarca);

		lblNewLabel_1 = new JLabel("Modelo");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 211, 129, 14);
		contentPane.add(lblNewLabel_1);

		tfModelo = new JTextField();
		tfModelo.setHorizontalAlignment(SwingConstants.CENTER);
		tfModelo.setBounds(10, 225, 129, 20);
		contentPane.add(tfModelo);
		tfModelo.setColumns(10);

		lblNewLabel_2 = new JLabel("Ano");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(149, 211, 62, 14);
		contentPane.add(lblNewLabel_2);

		tfAno = new JTextField();
		tfAno.setHorizontalAlignment(SwingConstants.CENTER);
		tfAno.setBounds(149, 225, 62, 20);
		contentPane.add(tfAno);
		tfAno.setColumns(10);

		lblNewLabel_3 = new JLabel("Cor");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(221, 211, 86, 14);
		contentPane.add(lblNewLabel_3);

		tfCor = new JTextField();
		tfCor.setHorizontalAlignment(SwingConstants.CENTER);
		tfCor.setBounds(221, 225, 86, 20);
		contentPane.add(tfCor);
		tfCor.setColumns(10);

		lblNewLabel_4 = new JLabel("Preco");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(317, 211, 159, 14);
		contentPane.add(lblNewLabel_4);

		tfMin = new JTextField();
		tfMin.setHorizontalAlignment(SwingConstants.CENTER);
		tfMin.setBounds(317, 225, 62, 20);
		contentPane.add(tfMin);
		tfMin.setColumns(10);

		JComboBox<String> cbMar = new JComboBox<String>();
		cbMar.setBounds(486, 224, 129, 22);
		contentPane.add(cbMar);

		cbMar.addItem("-- SELECIONAR --");

		mDao = new MarcaDAO();

		List<String> lista = mDao.allNomes();
		int tam = lista.size();
		for (int i = 0; i < tam; i++) {
			cbMar.addItem(lista.get(i));
		}

		contentPane.add(cbMar);

		//

		JLabel lblNewLabel_5 = new JLabel("Marca");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(486, 211, 144, 14);
		contentPane.add(lblNewLabel_5);

		tfMax = new JTextField();
		tfMax.setHorizontalAlignment(SwingConstants.CENTER);
		tfMax.setColumns(10);
		tfMax.setBounds(399, 225, 76, 20);
		contentPane.add(tfMax);

		JLabel lblNewLabel_6 = new JLabel("ate");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(373, 228, 35, 14);
		contentPane.add(lblNewLabel_6);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 257, 690, 144);
		contentPane.add(scrollPane_1);

		tabelaCarro = new JTable();
		scrollPane_1.setViewportView(tabelaCarro);
		tabelaCarro.setModel(modelTableBuscaCarro);

		JLabel lblNewLabel_7 = new JLabel("Buscar carro");
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 186, 690, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_7_1 = new JLabel("Buscar Marca");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		lblNewLabel_7_1.setBounds(10, 11, 690, 14);
		contentPane.add(lblNewLabel_7_1);

		JButton btnBuscaCar = new JButton("Buscar");
		btnBuscaCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!(tfModelo.getText().equals("") && tfAno.getText().equals("") && tfCor.getText().equals("")
							&& tfMin.getText().equals("") && tfMax.getText().equals("")
							&& cbMar.getSelectedItem().equals("-- SELECIONAR --"))) {
						// se mim for selecionado max tbm precisa e vice versa
						if (tfMin.getText().equals("") && !tfMax.getText().equals("")
								|| !tfMin.getText().equals("") && tfMax.getText().equals("")) {
							JOptionPane.showMessageDialog(contentPane, "Preco min e max sao necessarios!");
						} else {
							
							int ano, min, max;
							try {
								ano = Integer.parseInt(tfAno.getText());
							} catch (Exception e) {
								ano = 0;
							}
							try {
								min = Integer.parseInt(tfMin.getText());
							} catch (Exception e) {
								min = 0;
							}
							try {
								max = Integer.parseInt(tfMax.getText());
							} catch (Exception e) {
								max = 0;
							}
							
							if(cbMar.getSelectedItem().equals("-- SELECIONAR --")) {
								modelTableBuscaCarro.busca(
										new Carro(0, tfModelo.getText(), ano, tfCor.getText(), 0, null), min, max);
							} else {
								Marca marca = mDao.getByNome(cbMar.getSelectedItem().toString());

								modelTableBuscaCarro.busca(
										new Carro(0, tfModelo.getText(), ano, tfCor.getText(), 0, marca), min, max);

							}
							
							
						}

					} else {
						JOptionPane.showMessageDialog(contentPane, "Insira ao menos uma chave de busca!");
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao buscar carro! " + e.getMessage());
				}
			}
		});
		btnBuscaCar.setBackground(azul);
		btnBuscaCar.setForeground(branco);
		btnBuscaCar.setBounds(624, 224, 76, 23);
		contentPane.add(btnBuscaCar);
	}
}
