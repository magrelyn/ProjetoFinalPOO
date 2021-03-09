package modelo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import DAO.CarroDAO;

public class ModeloTabelaCarro extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	CarroDAO dao;
	List<Carro> lista;
	int numColunas = 0;
	int numLinhas = 0;

	public ModeloTabelaCarro() {
		dao = new CarroDAO();
		lista = dao.getAll();
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

	public void inserir(String modelo, int ano, String cor, double preco, Marca marca) {
		dao.inserir(new Carro(0, modelo, ano, cor, preco, marca));
		atualizar();
	}
	
	public void remover(int id) {
		dao.remover(id);
		atualizar();
	}
	
	public void update(int id, String modelo, int ano, String cor, double preco, Marca marca) {
		Carro c = new Carro(id, modelo, ano, cor, preco, marca);
		dao.atualizar(c);
		atualizar();
	}

	private void atualizar() {
		lista = dao.getAll();
		numLinhas = lista.size();
		this.fireTableDataChanged();
	}

}
