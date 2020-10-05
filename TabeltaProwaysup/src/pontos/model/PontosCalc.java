package pontos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import DAO.TabelaPontosDAO;
import connection.Conexao;

public class PontosCalc {

	TabelaPontosDAO dao = new TabelaPontosDAO();
	int placar, minimoTemporada = 1000, maximoTemporada, recordeMaximo, recordeMinimo;

	// Inicia o método verifica
	public void verifica(int n) {

		// Se "n" maior ou igual a 0 e menor ou igual a 1000 executa:
		if (n >= 0 && n <= 1000) {

			// Percorre uma lista de Pontos
//			for (Pontos p : dao.readLast()) {
//
//				// Atribui nas variaveis da classe os atributos da ultima linha do banco de
//				// dados
//				placar = p.getJg_placar();
//				maximoTemporada = p.getJg_pts_max();
//				minimoTemporada = p.getJg_pts_min();
//				recordeMaximo = p.getJg_pts_recMax();
//				recordeMinimo = p.getJg_pts_recMin();
//			}

			// Cria um objeto da Classe Ponto
			Pontos p = new Pontos();

			// Se "n" maior que maximoTemporada executa:
			if (n > maximoTemporada) {
				// Atribui o valor de "n" na variavel jg_pts_max e aumenta o recordeMaximo em 1
				p.setJg_pts_max(n);
				p.setJg_pts_recMax(recordeMaximo + 1);

				// Se "n" menor que maximoTemporada executa:
			} else {

				// Atribui o valor de "maximoTemporada" na variavel jg_pts_max e o recordeMaximo
				// continua o mesmo do ultimo dado do banco
				p.setJg_pts_max(maximoTemporada);
				p.setJg_pts_recMax(recordeMaximo);
			}

			// Se "n" menor que minimoTemporada E minimoTemporada diferente de 0 executa:
			if (n < minimoTemporada && minimoTemporada != 0) {

				// Se minimoTemporada igual a 0 executa:
				if (minimoTemporada == 0) {

					// Atribui o valor de "n" na variavel jg_pts_min do objeto Pontos e o
					// recordeMinimo se mantem o mesmo do dado recebido do banco
					p.setJg_pts_min(n);
					p.setJg_pts_recMin(recordeMinimo);
				}

				// Se "n" igual a 0 executa:
				if (n == 0) {

					// Atribui o valor de "n" na variavel jg_pts_min do objeto Pontos e o
					// recordeMinimo se mantem o mesmo do dado recebido do banco
					p.setJg_pts_min(n);
					p.setJg_pts_recMin(recordeMinimo);
				}

				// Atribui o valor de "n" na variavel jg_pts_min do objeto Pontos e o
				// recordeMinimo aumenta em 1 em relação ao recebido do banco
				p.setJg_pts_min(n);
				p.setJg_pts_recMin(recordeMinimo + 1);

				// Se "n" maior que minimoTemporada OU minimoTemporada igual a 0 executa:
			} else {

				// Atribui o valor de minimoTemporada na variavel jg_pts_min do objeto Pontos e
				// o recordeMinimo se mantem o mesmo do dado recebido do banco
				p.setJg_pts_min(minimoTemporada);
				p.setJg_pts_recMin(recordeMinimo);
			}

			// Atribui o valor de "n" no jg_placar do objeto Pontos
			p.setJg_placar(n);

			// Chama o método create da classe TabelaPontosDAO para adicionar os dados do
			// onjeto Pontos criado no Banco de Dados, e envia a SQL necessária para a
			// adição de um novo registro ao banco
			dao.create(p,
					"INSERT INTO jogo (jg_placar, jg_pts_max, jg_pts_min, jg_pts_recMax, jg_pts_recMin) VALUES (?, ?, ?, ?, ?)");

			// Se "n" menor que 0 e maior que 1000 executa:
		} else {
			JOptionPane.showMessageDialog(null, "Numero invalido");

		}
	}
}
