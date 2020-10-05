package pontos.model;

public class Pontos {

	// Declarando variaveis de Pontos
	private int id_jogo, jg_placar, jg_pts_max, jg_pts_min, jg_pts_recMax, jg_pts_recMin;

	// ===============================GettersAndSetters===============================
	
	// Atribuem e Informão dados da classe Pontos por meio de objetos da mesma
	
	public int getId_jogo() {
		return id_jogo;
	}

	public void setId_jogo(int id_jogo) {
		this.id_jogo = id_jogo;
	}

	public int getJg_pts_max() {
		return jg_pts_max;
	}

	public void setJg_pts_max(int jg_pts_max) {
		this.jg_pts_max = jg_pts_max;
	}

	public int getJg_pts_min() {
		return jg_pts_min;
	}

	public void setJg_pts_min(int jg_pts_min) {
		this.jg_pts_min = jg_pts_min;
	}

	public int getJg_pts_recMax() {
		return jg_pts_recMax;
	}

	public void setJg_pts_recMax(int jg_pts_recMax) {
		this.jg_pts_recMax = jg_pts_recMax;
	}

	public int getJg_pts_recMin() {
		return jg_pts_recMin;
	}

	public void setJg_pts_recMin(int jg_pts_recMin) {
		this.jg_pts_recMin = jg_pts_recMin;
	}

	public int getJg_placar() {
		return jg_placar;
	}

	public void setJg_placar(int jg_placar) {
		this.jg_placar = jg_placar;
	}

}
