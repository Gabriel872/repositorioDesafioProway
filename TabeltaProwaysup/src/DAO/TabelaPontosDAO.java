package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.Conexao;
import pontos.model.Pontos;

public class TabelaPontosDAO {

	// private Statement stmt;

	Conexao con = new Conexao();

	// ==============================CREATE==============================

	// Método para adicionar dados ao banco
	@SuppressWarnings("resource")
	public void create(Pontos p, String sql) {

		// Declarando variaveis para Conexao
		Connection connection;
		con = new Conexao();
		PreparedStatement pstmt = null;

		// Criando variavel para SQL
		String sqlSafeOff = "SET SQL_SAFE_UPDATES=0;";
		String sqlSafeOn = "SET SQL_SAFE_UPDATES=1;";
		String sql1 = "SET @count = 0;";
		String sql2 = "UPDATE jogo SET jogo.id_jogo = @count:= @count + 1;";

		// Iniciando Conexao
		connection = con.abrirConexao();

		// Tenta iniciar a conexão com PreparedStatemend pstmt
		try {

			// Inicia PreparedStatement para executar SQL para o banco
			pstmt = connection.prepareStatement(sql);

			// Recebe atributos do objeto Pontos recebido e posiciona eles em ordem na
			// variavel sql
			pstmt.setInt(1, p.getJg_placar());
			pstmt.setInt(2, p.getJg_pts_max());
			pstmt.setInt(3, p.getJg_pts_min());
			pstmt.setInt(4, p.getJg_pts_recMax());
			pstmt.setInt(5, p.getJg_pts_recMin());

			// Executa sql
			pstmt.execute();

			// Desligando safemode para organizar a tabela
			pstmt = connection.prepareStatement(sqlSafeOff);
			pstmt.execute();

			// Organizando a tabela
			pstmt = connection.prepareStatement(sql1);
			pstmt.execute();
			pstmt = connection.prepareStatement(sql2);
			pstmt.execute();

			// Ligando safemode
			pstmt = connection.prepareStatement(sqlSafeOn);
			pstmt.execute();

			// Informa o sucesso em salvar
			JOptionPane.showMessageDialog(null, "Pontos salvos");

		} catch (SQLException e) {

			// Se falhar na tentativa envia um aviso:
			JOptionPane.showMessageDialog(null, "Erro ao salvar");
			e.printStackTrace();
		} finally {

			// Tenta finalizar a conexao
			try {

				// Fechando conexão
				connection.close();
				pstmt.close();
			} catch (SQLException e) {

				// Se falhar lança exeção
				e.printStackTrace();
			}
		}

	}

	// ==============================READ==============================

	// Método para fazer leitura do banco de dados e retornar um lista de objetos
	// Pontos, recebe a sql necessaria
	public List<Pontos> read(String sql) {

		// Declarando variaveis para Conexao
		Connection connection;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pontos> pontos = new ArrayList<>();

		// Iniciando Conexao
		connection = con.abrirConexao();

		// Tenta iniciar a conexão com PreparedStatemend pstmt
		try {

			// Inicia PreparedStatement para executar SQL para o banco
			pstmt = connection.prepareStatement(sql);

			// Recebe SQL para executar no banco e pegar resultado
			rs = pstmt.executeQuery();

			// Enquanto existir próximo resultado da pesquisa no banco
			while (rs.next()) {

				// Cria um novo objeto Pontos e adiciona atributos do banco nos respectivos
				// campos correspondidos
				Pontos p = new Pontos();

				p.setId_jogo(rs.getInt("id_jogo"));
				p.setJg_placar(rs.getInt("jg_placar"));
				p.setJg_pts_max(rs.getInt("jg_pts_max"));
				p.setJg_pts_min(rs.getInt("jg_pts_min"));
				p.setJg_pts_recMax(rs.getInt("jg_pts_recMax"));
				p.setJg_pts_recMin(rs.getInt("jg_pts_recMin"));

				// Adiciona o objeto Pontos na lista pontos
				pontos.add(p);
			}

		} catch (SQLException e) {

			// Em caso de falha na conexão envia uma aviso
			e.printStackTrace();
		} finally {

			// Tenta finalizar a conexao
			try {

				// Fechando conexão
				rs.close();
				pstmt.close();
				connection.close();
			} catch (SQLException e) {

				// Em caso de falha na conexão envia uma aviso
				e.printStackTrace();
			}

		}

		// Retorna lista criada do tipo Pontos
		return pontos;
	}

	// ==============================READLAST==============================

	// Método para ler a úliima linha do banco
	public List<Pontos> readLast() {

		// Declarando variaveis para Conexao
		Connection connection;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pontos> pontos = new ArrayList<>();

		// Criando variavel para SQL
		// OBS: pode ser alterado para pegar mais que uma linha
		String sql = "SELECT * FROM jogo ORDER BY id_jogo DESC LIMIT 1;";

		// Iniciando Conexao
		connection = con.abrirConexao();

		// Tenta iniciar a conexão com PreparedStatemend pstmt
		try {

			// Inicia PreparedStatement para executar SQL para o banco
			pstmt = connection.prepareStatement(sql);

			// Recebe SQL para executar no banco e pegar resultado
			rs = pstmt.executeQuery();

			// Enquanto existir próximo resultado da pesquisa no banco
			while (rs.next()) {

				// Cria um novo objeto Pontos e adiciona atributos do banco nos respectivos
				// campos correspondidos
				Pontos p = new Pontos();

				p.setId_jogo(rs.getInt("id_jogo"));
				p.setJg_placar(rs.getInt("jg_placar"));
				p.setJg_pts_max(rs.getInt("jg_pts_max"));
				p.setJg_pts_min(rs.getInt("jg_pts_min"));
				p.setJg_pts_recMax(rs.getInt("jg_pts_recMax"));
				p.setJg_pts_recMin(rs.getInt("jg_pts_recMin"));

				// Adiciona o objeto Pontos na lista pontos
				pontos.add(p);
			}
		} catch (SQLException e) {

			// Em caso de falha na conexão envia uma aviso
			e.printStackTrace();
		} finally {

			// Tenta finalizar a conexao
			try {

				// Fechando conexão
				rs.close();
				pstmt.close();
				connection.close();
			} catch (SQLException e) {

				// Em caso de falha na conexão envia uma aviso
				e.printStackTrace();
			}

		}

		// Retorna ultima linha
		return pontos;
	}
}
