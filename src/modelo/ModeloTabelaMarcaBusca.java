package modelo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import DAO.MarcaDAO;

public class ModeloTabelaMarcaBusca extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	MarcaDAO dao;
	List<Marca> lista;
	int numColunas = 0;
	int numLinhas = 0;

	public ModeloTabelaMarcaBusca() {
		dao = new MarcaDAO();
		List<Marca> lista = new ArrayList<Marca>();
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

	public void buscaPorChave(String chave) {
		lista = dao.getByNomes(chave);
		numLinhas = lista.size();
		this.fireTableDataChanged();
	}

}
