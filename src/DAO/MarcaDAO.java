package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.FabricaConexao;
import modelo.Marca;

public class MarcaDAO implements PadraoDAO<Marca> {

	private final String INSERT = "INSERT INTO MARCA (nome, cnpj, slogan) VALUES (?,?,?)";
	private final String UPDATE = "UPDATE MARCA SET NOME=?, CNPJ=?, SLOGAN=? WHERE ID_MAR=?";
	private final String DELETE = "DELETE FROM MARCA WHERE ID_MAR=?";
	private final String LIST = "SELECT * FROM MARCA";
	private final String LISTBYID = "SELECT * FROM MARCA WHERE ID_MAR=?";

	@Override
	public int inserir(Marca obj) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(INSERT);
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getCnpj());
			ps.setString(3, obj.getSlogan());

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
	public int atualizar(Marca obj) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(UPDATE);
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getCnpj());
			ps.setString(3, obj.getSlogan());
			ps.setInt(4, obj.getIdMar());

			res = ps.executeUpdate();
			FabricaConexao.fechaConexao(con, ps);
			return res;
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
			return 1;
		}
	}

	@Override
	public List<Marca> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Marca> lista = new ArrayList<>();

		int idMar;
		String nome;
		String cnpj;
		String slogan;

		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(LIST);
			rs = ps.executeQuery();

			while (rs.next()) {

				idMar = rs.getInt(1);
				nome = rs.getString(2);
				cnpj = rs.getString(3);
				slogan = rs.getString(4);

				lista.add(new Marca(idMar, nome, cnpj, slogan));
			}
			FabricaConexao.fechaConexao(con, ps, rs);
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
		return lista;
	}

	@Override
	public Marca getById(int codigo) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idMar;
		String nome;
		String cnpj;
		String slogan;
		Marca m = null;

		try {
			con = FabricaConexao.getConexao();
			ps = con.prepareStatement(LISTBYID);

			ps.setInt(1, codigo);

			rs = ps.executeQuery();

			while (rs.next()) {
				idMar = rs.getInt(1);
				nome = rs.getString(2);
				cnpj = rs.getString(3);
				slogan = rs.getString(4);
				m = new Marca(idMar, nome, cnpj, slogan);
			}

			FabricaConexao.fechaConexao(con, ps, rs);
		} catch (SQLException e) {
			System.out.println("Erro: " + e);
		}
		return m;
	}

}
