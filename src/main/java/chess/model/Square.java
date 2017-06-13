package chess.model;

public class Square {
	private Chess chess = null;

	public Chess getChess() {
		return chess;
	}

	public Square() {
	}
	
	public Square(Chess chess) {
		this.chess = chess;
	}
	
	public void setChess(Chess chess) {
		this.chess = chess;
	}
	
	
}
