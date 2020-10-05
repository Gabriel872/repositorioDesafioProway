package connection;

import java.sql.*;

public class Conexao {

	// Declara variaveis para a conexão
	private Connection con;
	private final String URL = "jdbc:mysql://localhost:3306/tabela?useTimezone=true&serverTimezone=UTC";
	private final String USER = "root";
	private final String PASSWORD = "root";
	private final String TPCONEXAO = "com.mysql.cj.jdbc.Driver";

	// Método para iniciar conexão com o banco
	public Connection abrirConexao() {

		// Tenta iniciar conexao
		try {

			// Procura a classe de TPCONEXAO
			Class.forName(TPCONEXAO);

			// Recebe conexão pela classe DriverManager
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException ex) {

			// Envia um aviso de erro
			ex.printStackTrace();
		}

		// Retorna conexão
		return con;
	}
}
