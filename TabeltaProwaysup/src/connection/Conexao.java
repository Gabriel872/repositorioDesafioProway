package connection;

import java.sql.*;

public class Conexao {

	// Declara variaveis para a conex�o
	private Connection con;
	private final String URL = "jdbc:mysql://localhost:3306/tabela?useTimezone=true&serverTimezone=UTC";
	private final String USER = "root";
	private final String PASSWORD = "root";
	private final String TPCONEXAO = "com.mysql.cj.jdbc.Driver";

	// M�todo para iniciar conex�o com o banco
	public Connection abrirConexao() {

		// Tenta iniciar conexao
		try {

			// Procura a classe de TPCONEXAO
			Class.forName(TPCONEXAO);

			// Recebe conex�o pela classe DriverManager
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException ex) {

			// Envia um aviso de erro
			ex.printStackTrace();
		}

		// Retorna conex�o
		return con;
	}
}
