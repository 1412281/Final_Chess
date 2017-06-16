package chess.model;

public class Castle {
	private boolean K;
	private boolean Q;
	private boolean k;
	private boolean q;
	
	Castle() {
		K = Q = k = q = true;
		
	}
	
	public Castle(String string) {
		this.setK(string.contains("K"));
		this.setQ(string.contains("Q"));
		this.setk(string.contains("k"));
		this.setq(string.contains("q"));
		
	}

	public boolean getK() {
		return this.K;
	}

	public boolean getQ() {
		return this.Q;
	}
	
	public boolean getk() {
		return this.k;
	}
	
	public boolean getq() {
		return this.q;
	}
	
	public void setK(boolean K) {
		this.K = K;
	}
	
	public void setQ(boolean Q) {
		this.Q = Q;
	}
	
	public void setk(boolean k) {
		this.k = k;
	}
	
	public void setq(boolean q) {
		this.q = q;
	}
	
	public String toString() {
		String result = "";
		if (K) result += 'K';
		if (Q) result += 'Q';
		if (k) result += 'k';
		if (q) result += 'q';
		return result;
	}
}
