package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FabricaConexao {
	private final static String url = "jdbc:mysql://localhost/concessonaria";
	private final static String user = "root";
	private final static String pass = "";

	private static Connection con;

	public static Connection getConexao() throws SQLException {
		con = DriverManager.getConnection(url, user, pass);
		return con;
	}

	public static void fechaConexao(Connection con) throws SQLException {
		if (con != null)
			con.close();
	}

	public static void fechaConexao(Connection con, Statement st) throws SQLException {
		if (st != null)
			st.close();
		fechaConexao(con);
	}

	public static void fechaConexao(Connection con, Statement st, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		fechaConexao(con, st);
	}
}
