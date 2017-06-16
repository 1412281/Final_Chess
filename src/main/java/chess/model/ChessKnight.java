package chess.model;

import java.util.ArrayList;
import java.util.List;


//ngựa
public class ChessKnight extends Chess {

	public ChessKnight(Team team) {
		super(team);
		setValue();
		setValue(60);
	}

	private void setValue() {
		int[][] value = new int[][] {
			{-10, -8, -6, -6, -6, -6, -8, -10},
	        {-8, -4,  0,  0,  0,  0, -4, -8},
	        {-6,  0,  2,  3,  3,  2,  0, -6},
	        {-6,  1,  3,  4,  4,  3,  1, -6},
	        {-6,  0,  3,  4,  4,  3,  0, -6},
	        {-6,  1,  2,  3,  3,  2,  1, -6},
	        {-8, -4,  0,  1,  1,  0, -4, -8},
	        {-10, -8, -6, -6, -6, -6, -8, -10}
		};
		this.setValue(value);
	}

	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/* Kiểm tra và lấy từ 8 hướng có thể đi */
		list.addAll(couldMoveToward(point, -1, -2));// trái đi lên
		list.addAll(couldMoveToward(point, -1, 2));// trái đi xuống
		list.addAll(couldMoveToward(point, 1, -2));// phải đi lên
		list.addAll(couldMoveToward(point, 1, 2));// phải đi xuống
		list.addAll(couldMoveToward(point, -2, -1));// trái đi lên 2
		list.addAll(couldMoveToward(point, -2, 1));// trái đi xuống 2
		list.addAll(couldMoveToward(point, 2, -1));// phải đi lên 2
		list.addAll(couldMoveToward(point, 2, 1));// phải đi xuống 2

		return list;
	}

	public static void main(String[] args) {
		System.out.println("Bishop");
		Chess chess = new ChessKnight(Team.BLACK);
		// test ở vị trí bất kì
		List<Point> list = chess.getPosibleMove(new Point(6, 6));
		for (Point item : list)
			System.out.println(item.toString());
	}

	@Override
	public String toString() {
		return getTeam() == Team.WHITE ? "N" : "n";
	}

	
}
