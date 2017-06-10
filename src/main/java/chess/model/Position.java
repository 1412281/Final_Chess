package chess.model;

public class Position {
	private int x;
	private int y;
	
	public Position() {
		this.x = 1;
		this.y = 1;
	}
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position pos){
		this.x = pos.getX();
		this.y = pos.getY();
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equal(Position pos) {
		return (this.x == pos.getX() && this.y == pos.getY());
	}
	public Position setPos(int x, int y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	public String toString() {
		return '(' + String.valueOf(x) + ',' +  String.valueOf(y) + ')';
	}
	
	public Position clone() {
		Position newPos = new Position(this);
		return newPos;
	}
}
