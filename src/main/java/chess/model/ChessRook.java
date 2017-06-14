package chess.model;

import java.util.ArrayList;
import java.util.List;

//xe
public class ChessRook extends Chess {

	public ChessRook(Team team) {
		super(team);
		setValue();
		super.setValue(100);
	}

	private void setValue() {
		int[][] value = new int[][] {
			{  0,  0,  0,  0,  0,  0,  0,  0},
		    {  1,  2,  2,  2,  2,  2,  2,  1},
		    { -1,  0,  0,  0,  0,  0,  0, -1},
		    { -1,  0,  0,  0,  0,  0,  0, -1},
		    { -1,  0,  0,  0,  0,  0,  0, -1},
		    { -1,  0,  0,  0,  0,  0,  0, -1},
		    { -1,  0,  0,  0,  0,  0,  0, -1},
		    {  0,   0, 0,  1,  1,  0,  0,  0}
		};
		this.setValue(value);
	}

	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/* Kiểm tra và lấy từ 4 hướng có thể đi */
		list.addAll(couldMoveToward_ManyChoices(point, 0, -1));// lên
		list.addAll(couldMoveToward_ManyChoices(point, 0, 1));// xuống
		list.addAll(couldMoveToward_ManyChoices(point, -1, 0));// trái
		list.addAll(couldMoveToward_ManyChoices(point, 1, 0));// phải

		return list;
	}

	public static void main(String[] args) {
		Chess chess = new ChessRook(Team.BLACK);
		// test ở vị trí bất kì
		List<Point> list = chess.getPosibleMove(new Point(7, 7));
		for (Point item : list)
			System.out.println(item.toString());
	}

	@Override
	public String toString() {
		return "Rook";
	}
}
