package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.FabricaConexao;
import modelo.Carro;
import modelo.Marca;

public class CarroDAO implements PadraoDAO<Carro> {

	private final String INSERT = "INSERT INTO CARRO (modelo, ano, cor, preco, marca) VALUES (?,?,?,?,?)";
	private final String UPDATE = "UPDATE CARRO SET MODELO=?, ANO=?, COR=?, PRECO=?, MARCA=? WHERE ID_CAR=?";
	private final String DELETE = "DELETE FROM CARRO WHERE ID_CAR=?";
	private final String LIST = "SELECT * FROM CARRO";
	private final String LISTBYID = "SELECT * FROM CARRO WHERE ID_CAR=?";

	private final String DELLBYMARCA = "DELETE FROM CARRO WHERE MARCA=?";

	@Override
	public int inserir(Carro obj) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(INSERT);
			ps.setString(1, obj.getModelo());
			ps.setInt(2, obj.getAno());
			ps.setString(3, obj.getCor());
			ps.setDouble(4, obj.getPreco());
			ps.setInt(5, obj.getMarca().getIdMar());

			res = ps.executeUpdate();
			FabricaConexao.fechaConexao(con, ps);
			return res;
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
			return 1;
		}
	}

	@Override
	public int remover(int codigo) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(DELETE);
			ps.setInt(1, codigo);
			res = ps.executeUpdate();
			FabricaConexao.fechaConexao(con, ps);
			return res;
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
			return 1;
		}
	}

	public int dellByMarca(int marca) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(DELLBYMARCA);
			ps.setInt(1, marca);
			res = ps.executeUpdate();
			FabricaConexao.fechaConexao(con, ps);
			return res;
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
			return 1;
		}
	}

	@Override
	public int atualizar(Carro obj) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(UPDATE);
			ps.setString(1, obj.getModelo());
			ps.setInt(2, obj.getAno());
			ps.setString(3, obj.getCor());
			ps.setDouble(4, obj.getPreco());
			ps.setInt(5, obj.getMarca().getIdMar());
			ps.setInt(6, obj.getIdCar());

			res = ps.executeUpdate();
			FabricaConexao.fechaConexao(con, ps);
			return res;
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
			return 1;
		}
	}

	@Override
	public List<Carro> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Carro> lista = new ArrayList<>();

		int idCar;
		String modelo;
		int ano;
		String cor;
		double preco;
		Marca marca;
		int codMar;

		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(LIST);
			rs = ps.executeQuery();
			while (rs.next()) {

				idCar = rs.getInt(1);
				modelo = rs.getString(2);
				ano = rs.getInt(3);
				cor = rs.getString(4);
				preco = rs.getDouble(5);
				codMar = rs.getInt(6);
				MarcaDAO mDao = new MarcaDAO();
				marca = mDao.getById(codMar);

				lista.add(new Carro(idCar, modelo, ano, cor, preco, marca));
			}
			FabricaConexao.fechaConexao(con, ps, rs);
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
		return lista;
	}

	@Override
	public Carro getById(int codigo) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idCar;
		String modelo;
		int ano;
		String cor;
		double preco;
		Marca marca;
		int codMar;
		Carro c = null;

		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(LISTBYID);

			ps.setInt(1, codigo);

			rs = ps.executeQuery();

			while (rs.next()) {
				idCar = rs.getInt(1);
				modelo = rs.getString(2);
				ano = rs.getInt(3);
				cor = rs.getString(4);
				preco = rs.getDouble(5);
				codMar = rs.getInt(6);

				MarcaDAO mDao = new MarcaDAO();
				marca = mDao.getById(codMar);

				c = new Carro(idCar, modelo, ano, cor, preco, marca);
			}

			FabricaConexao.fechaConexao(con, ps, rs);
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
		return c;
	}

	public List<Carro> buscaByObj(Carro obj, int min, int max) {

		String BYOBJ = "SELECT * FROM CARRO WHERE ";

		String cons = "";
		
		if(obj.getModelo() != null && !(obj.getModelo().equals(""))) {
			cons += "MODELO LIKE ?";
		}
		if(obj.getAno() != 0) {
			if (cons.equals("")) {
				cons += "ANO = ?";
			} else {
				cons += " AND ANO = ?";
			}
		}
		if(obj.getCor() != null && !(obj.getCor().equals(""))) {
			if (cons.equals("")) {
				cons += "COR = ?";
			} else {
				cons += " AND COR = ?";
			}
		}
		if(max != 0) {
			if (cons.equals("")) {
				cons += "PRECO BETWEEN ? AND ?";
			} else {
				cons += " AND PRECO BETWEEN ? AND ?";
			}
		}
		if(obj.getMarca() != null && obj.getMarca().getIdMar() != 0) {
			if (cons.equals("")) {
				cons += "MARCA = ?";
			} else {
				cons += " AND MARCA = ?";
			}
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idCar;
		String modelo;
		int ano;
		String cor;
		double preco;
		Marca marca;
		int marcaId;
		Carro m = null;
		List<Carro> lista = new ArrayList<>();

		try {
			BYOBJ += cons;
						
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(BYOBJ);
			
			int i = 0;
			if (obj.getModelo() != null && !(obj.getModelo().equals(""))) {
				i++;
				ps.setString(i, '%' + obj.getModelo() + '%');
			}
			if (obj.getAno() != 0) {
				i++;
				ps.setInt(i, obj.getAno());
			}
			if(obj.getCor() != null && !(obj.getCor().equals(""))) {
				i++;
				ps.setString(i, obj.getCor());
			}
			if(max != 0) {
				i++;
				ps.setInt(i, min);
				i++;
				ps.setInt(i, max);
			}
			if(obj.getMarca() != null && obj.getMarca().getIdMar() != 0) {
				i++;
				ps.setInt(i, obj.getMarca().getIdMar());
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				idCar = rs.getInt(1);
				modelo = rs.getString(2);
				ano = rs.getInt(3);
				cor = rs.getString(4);
				preco = rs.getDouble(5);
				marcaId = rs.getInt(6);
				
				MarcaDAO mDao = new MarcaDAO();
				marca = mDao.getById(marcaId);
				
				m = new Carro(idCar, modelo, ano, cor, preco, marca);
				lista.add(m);
			}
			
			FabricaConexao.fechaConexao(con, ps, rs);
			
			System.out.println(BYOBJ);

		} catch (SQLException e) {
			System.out.println("Erro na busca por obj: " + e);
		}
		return lista;

	}

}
