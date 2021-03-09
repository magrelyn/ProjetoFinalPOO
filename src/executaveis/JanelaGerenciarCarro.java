package executaveis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.MarcaDAO;
import modelo.Marca;
import modelo.ModeloTabelaCarro;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class JanelaGerenciarCarro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfModelo;
	private JTextField tfAno;
	private JTextField tfCor;
	private JTextField tfPreco;
	private JTable table;
	private ModeloTabelaCarro modeloTabela;
	private MarcaDAO mDao;
	private JComboBox<String> cbMarca;
	private int idCarro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaGerenciarCarro frame = new JanelaGerenciarCarro();
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
	public JanelaGerenciarCarro() {

		modeloTabela = new ModeloTabelaCarro();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 598, 171);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.getSelectedRow();
				idCarro = (int) table.getValueAt(linha, 0);
				tfModelo.setText("" + table.getValueAt(linha, 1));
				tfAno.setText("" + table.getValueAt(linha, 2));
				tfCor.setText("" + table.getValueAt(linha, 3));
				tfPreco.setText("" + table.getValueAt(linha, 4));	
				cbMarca.setSelectedItem(table.getValueAt(linha, 5));
			}
		});

		tfModelo = new JTextField();
		tfModelo.setHorizontalAlignment(SwingConstants.CENTER);
		tfModelo.setBounds(10, 225, 479, 20);
		contentPane.add(tfModelo);
		tfModelo.setColumns(10);

		tfAno = new JTextField();
		tfAno.setHorizontalAlignment(SwingConstants.CENTER);
		tfAno.setBounds(499, 225, 109, 20);
		contentPane.add(tfAno);
		tfAno.setColumns(10);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 212, 479, 14);
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblModelo);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(499, 212, 109, 14);
		lblAno.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAno);

		tfCor = new JTextField();
		tfCor.setHorizontalAlignment(SwingConstants.CENTER);
		tfCor.setBounds(10, 270, 201, 20);
		contentPane.add(tfCor);
		tfCor.setColumns(10);

		tfPreco = new JTextField();
		tfPreco.setHorizontalAlignment(SwingConstants.CENTER);
		tfPreco.setBounds(221, 270, 176, 20);
		tfPreco.setColumns(10);
		contentPane.add(tfPreco);

		cbMarca = new JComboBox<String>();
		cbMarca.setBounds(407, 269, 201, 22);

		// Codigo de preenchimento do JComboBox

		cbMarca.addItem("-- SELECIONAR --");

		mDao = new MarcaDAO();

		List<String> lista = mDao.allNomes();
		int tam = lista.size();
		for (int i = 0; i < tam; i++) {
			cbMarca.addItem(lista.get(i));
		}

		contentPane.add(cbMarca);

		// das Ende

		JLabel lblCor = new JLabel("Cor");
		lblCor.setBounds(10, 256, 201, 14);
		lblCor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCor);

		JLabel lblPreco = new JLabel("Preco");
		lblPreco.setBounds(221, 256, 176, 14);
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPreco);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(407, 256, 201, 14);
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMarca);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if(tfModelo.getText().equals("") || tfAno.getText().equals("") || tfCor.getText().equals("") || tfPreco.getText().equals("")
							|| cbMarca.getSelectedItem().equals("-- SELECIONAR --")) {
						JOptionPane.showMessageDialog(contentPane, "Preencha os campos!");
					} else {
						
						Marca marca = mDao.getByNome(cbMarca.getSelectedItem().toString());
						
						modeloTabela.inserir(tfModelo.getText(), Integer.parseInt(tfAno.getText()), tfCor.getText(),
								Double.parseDouble(tfPreco.getText()), marca);
						limpar();
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao cadastrar!");
				}
			}
		});
		btnInserir.setBounds(10, 311, 304, 23);
		contentPane.add(btnInserir);

		JButton btnAtt = new JButton("Atualizar");
		btnAtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(tfModelo.getText().equals("") || tfAno.getText().equals("") || tfCor.getText().equals("") || tfPreco.getText().equals("")
							|| cbMarca.getSelectedItem().equals("-- SELECIONAR --")) {
						JOptionPane.showMessageDialog(contentPane, "Preencha os campos!");
					} else  {
						
						Marca marca = mDao.getByNome(cbMarca.getSelectedItem().toString());
						
						modeloTabela.update(idCarro, tfModelo.getText(), Integer.parseInt(tfAno.getText()), tfCor.getText(), Double.parseDouble(tfPreco.getText()), marca);
						limpar();
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao atualizar!");
				}
			}
		});
		btnAtt.setBounds(324, 311, 284, 23);
		contentPane.add(btnAtt);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					modeloTabela.remover(idCarro);
					limpar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao remover!");
				}
			}
		});
		btnRemover.setBounds(10, 345, 304, 23);
		contentPane.add(btnRemover);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		btnLimpar.setBounds(324, 345, 284, 23);
		contentPane.add(btnLimpar);
	}

	private void limpar() {
		this.tfModelo.setText("");
		this.tfAno.setText("");
		this.tfCor.setText("");
		this.tfPreco.setText("");
		this.cbMarca.setSelectedIndex(0);
		this.tfModelo.requestFocus();
	}
}
