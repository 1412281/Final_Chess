package chess.model;

public class Move {
	private Point from;
	private Point to;
	private Chess chessDead;

	public Move(String st) {
		
	}
	
	public Move(Move move) {
		this.from = move.getFrom();
		this.to = move.getTo();
		this.chessDead = move.getChessDead();
	}

	public Move(Point from, Point to, Chess dead) {
		this.from = from;
		this.to = to;
		this.chessDead = dead;
	}

	public Move(Point from, Point to) {
		this.from = from;
		this.to = to;
	}

	public Move reverse() {
		
		return new Move(this.getTo(), this.getFrom());
	}

	public Point getFrom() {
		return from;
	}

	public void setFrom(Point from) {
		this.from = from;
	}

	public Point getTo() {
		return to;
	}

	public void setTo(Point to) {
		this.to = to;
	}

	public Chess getChessDead() {
		return chessDead;
	}

	public void setChessDead(Chess chessDead) {
		this.chessDead = chessDead;
	}
	
	public boolean equal(Move move) {
		return this.getFrom().equal(move.getFrom()) && this.getTo().equal(move.getTo());
	}
	
	public String toString() {
		return this.from.toString() + " " + this.to.toString();
	}
	
}
