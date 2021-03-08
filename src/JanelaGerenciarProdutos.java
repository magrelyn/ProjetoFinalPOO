import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class JanelaGerenciarProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private ModeloTabelaMarca modeloTabela;
	private JTextField tfDesc;
	private JTextField tfUni;
	private JTextField tfPrec;
	private JTextField tfQuant;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaGerenciarProdutos frame = new JanelaGerenciarProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaGerenciarProdutos() {

		modeloTabela = new ModeloTabelaMarca();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDesc = new JLabel("Descri\u00E7\u00E3o");
		lblDesc.setBounds(10, 166, 88, 14);
		contentPane.add(lblDesc);

		tfDesc = new JTextField();
		tfDesc.setBounds(108, 163, 174, 20);
		tfDesc.requestFocus();
		contentPane.add(tfDesc);
		tfDesc.setColumns(10);

		JLabel lblUni = new JLabel("Unidade");
		lblUni.setBounds(330, 169, 46, 14);
		contentPane.add(lblUni);

		tfUni = new JTextField();
		tfUni.setBounds(386, 166, 174, 20);
		contentPane.add(tfUni);
		tfUni.setColumns(10);

		JLabel lblPrec = new JLabel("Preço Unitário");
		lblPrec.setBounds(10, 203, 88, 14);
		contentPane.add(lblPrec);

		tfPrec = new JTextField();
		tfPrec.setBounds(108, 200, 174, 20);
		contentPane.add(tfPrec);
		tfPrec.setColumns(10);

		JLabel lblQuant = new JLabel("Quantidade");
		lblQuant.setBounds(311, 203, 69, 14);
		contentPane.add(lblQuant);

		tfQuant = new JTextField();
		tfQuant.setBounds(386, 197, 174, 20);
		contentPane.add(tfQuant);
		tfQuant.setColumns(10);

		JButton btnIns = new JButton("Inserir");
		btnIns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				StringBuilder errorMessage = new StringBuilder();

				try {

					String desc = tfDesc.getText();
					String unidade = tfUni.getText();
					double preco = Double.parseDouble(tfPrec.getText());
					int quant = Integer.parseInt(tfQuant.getText());

					modeloTabela.inserir(desc, unidade, preco, quant);
					limpar();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Preencha os campos corretamente!");
				}

			}
		});
		btnIns.setBounds(9, 250, 273, 23);
		contentPane.add(btnIns);

		JButton btnLim = new JButton("Limpar");
		btnLim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		btnLim.setBounds(311, 250, 249, 23);
		contentPane.add(btnLim);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 550, 131);
		contentPane.add(scrollPane);
		
				table = new JTable();
				scrollPane.setViewportView(table);
				table.setModel(modeloTabela);
	}
	
	private void limpar() {
		this.tfDesc.setText("");
		this.tfUni.setText("");
		this.tfPrec.setText("");
		this.tfQuant.setText("");
		this.tfDesc.requestFocus();
	}
}
