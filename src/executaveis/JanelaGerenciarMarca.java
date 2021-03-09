package executaveis;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ModeloTabelaMarca;

import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JanelaGerenciarMarca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private ModeloTabelaMarca modeloTabela;
	private JTextField tfNome;
	private JTextField tfCnpj;
	private JTextField tfSlogan;
	private int idMarca;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaGerenciarMarca frame = new JanelaGerenciarMarca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaGerenciarMarca() {

		modeloTabela = new ModeloTabelaMarca();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnIns = new JButton("Inserir");
		btnIns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (tfNome.getText().equals("") || tfCnpj.getText().equals("") || tfSlogan.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Preencha os campos!");
					} else {
						modeloTabela.inserir(tfNome.getText(), tfCnpj.getText(), tfSlogan.getText());
						limpar();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Preencha os campos corretamente!");
				}

			}
		});
		btnIns.setBounds(10, 286, 273, 23);
		contentPane.add(btnIns);

		JButton btnAtt = new JButton("Atualizar");
		btnAtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if (tfNome.getText().equals("") || tfCnpj.getText().equals("") || tfSlogan.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Selecione um carro!");
					} else {
						modeloTabela.update(idMarca, tfNome.getText(), tfCnpj.getText(), tfSlogan.getText());
						limpar();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao atualizar!");
				}
			}
		});
		btnAtt.setBounds(312, 286, 249, 23);
		contentPane.add(btnAtt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 550, 131);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.getSelectedRow();
				idMarca = (int) table.getValueAt(linha, 0);
				tfNome.setText("" + table.getValueAt(linha, 1));
				tfCnpj.setText("" + table.getValueAt(linha, 2));
				tfSlogan.setText("" + table.getValueAt(linha, 3));
			}
		});

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(9, 164, 273, 14);
		contentPane.add(lblNome);

		tfNome = new JTextField();
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);
		tfNome.setBounds(9, 178, 273, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfCnpj = new JTextField();
		tfCnpj.setHorizontalAlignment(SwingConstants.CENTER);
		tfCnpj.setColumns(10);
		tfCnpj.setBounds(311, 178, 249, 20);
		contentPane.add(tfCnpj);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setHorizontalAlignment(SwingConstants.CENTER);
		lblCnpj.setBounds(311, 164, 249, 14);
		contentPane.add(lblCnpj);

		JLabel lblSlogan = new JLabel("Slogan");
		lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlogan.setBounds(10, 215, 551, 14);
		contentPane.add(lblSlogan);

		tfSlogan = new JTextField();
		tfSlogan.setHorizontalAlignment(SwingConstants.CENTER);
		tfSlogan.setColumns(10);
		tfSlogan.setBounds(10, 229, 550, 20);
		contentPane.add(tfSlogan);

		JButton btnRemove = new JButton("Remover");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					modeloTabela.remover(idMarca);
					limpar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Não foi possivel remover!");
				}
			}
		});
		btnRemove.setBounds(10, 320, 273, 23);
		contentPane.add(btnRemove);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		btnLimpar.setBounds(312, 320, 249, 23);
		contentPane.add(btnLimpar);

	}

	private void limpar() {
		this.tfCnpj.setText("");
		this.tfNome.setText("");
		this.tfSlogan.setText("");
		this.tfNome.requestFocus();
	}
}
