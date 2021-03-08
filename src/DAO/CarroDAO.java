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
	
	private final String INSERT = "INSERT INTO CARRO (modelo, ano, cor, preco, marca) VALUES (?,?,?,?)";
	private final String UPDATE = "UPDATE CARRO SET MODELO=?, ANO=?, COR=?, PRECO=? WHERE ID_CAR=?";
	private final String DELETE = "DELETE FROM CARRO WHERE ID_CAR=?";
	private final String LIST = "SELECT * FROM CARRO";
	private final String LISTBYID = "SELECT * FROM CARRO WHERE ID_CAR=?";

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
				ano =  rs.getInt(3);
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

}
