package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import DAO.CarroDAO;

public class ModeloTabelaCarroBusca extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private CarroDAO dao;
	private List<Carro> lista;
	private int numColunas = 0;
	private int numLinhas = 0;

	public ModeloTabelaCarroBusca() {
		dao = new CarroDAO();
		List<Carro> lista = new ArrayList<Carro>();
		numLinhas = lista.size();
		numColunas = Carro.class.getDeclaredFields().length;
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
		Carro c = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return (Object) c.getIdCar();
		case 1:
			return (Object) c.getModelo();
		case 2:
			return (Object) c.getAno();
		case 3:
			return (Object) c.getCor();
		case 4:
			return (Object) c.getPreco();
		case 5:
			return (Object) c.getMarca().getNome();
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
			return "Modelo";
		case 2:
			return "Ano";
		case 3:
			return "Cor";
		case 4:
			return "Preco";
		case 5:
			return "Marca";
		default:
			return "";
		}
	}

	public void busca(Carro obj, int min, int max) {
		lista = dao.buscaByObj(obj, min, max);
		numLinhas = lista.size();
		this.fireTableDataChanged();
	}

}
