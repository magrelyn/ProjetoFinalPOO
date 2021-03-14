package modelo;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import DAO.MarcaDAO;

public class ModeloTabelaMarca extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private MarcaDAO dao;
	private List<Marca> lista;
	private int numColunas = 0;
	private int numLinhas = 0;

	public ModeloTabelaMarca() {
		dao = new MarcaDAO();
		lista = dao.getAll();
		numLinhas = lista.size();
		numColunas = Marca.class.getDeclaredFields().length;
	}

	@Override
	public int getRowCount() {
		return this.numLinhas;
	}

	@Override
	public int getColumnCount() {
		return this.numColunas;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Marca p = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return (Object) p.getIdMar();
		case 1:
			return (Object) p.getNome();
		case 2:
			return (Object) p.getCnpj();
		case 3:
			return (Object) p.getSlogan();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Nome";
		case 2:
			return "CNPJ";
		case 3:
			return "Slogan";
		default:
			return "";
		}
	}

	public void inserir(String nome, String cnpj, String slogan) {
		dao.inserir(new Marca(0, nome, cnpj, slogan));
		atualizar();
	}
	
	public void remover(int id) {
		dao.remover(id);
		atualizar();
	}
	
	public void update(int id, String nome, String cnpj, String slogan) {
		Marca m = new Marca(id, nome, cnpj, slogan);
		dao.atualizar(m);
		atualizar();
	}

	private void atualizar() {
		lista = dao.getAll();
		numLinhas = lista.size();
		this.fireTableDataChanged();
	}

}
