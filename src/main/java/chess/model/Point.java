package chess.model;

public class Point {
	private int x;
	private int y;

	public Point() {
		this.x = -1;
		this.y = -1;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
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

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + (char)(97 + this.x) + "," + String.valueOf(this.y + 1) + ")";
	}

}