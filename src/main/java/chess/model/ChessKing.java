package chess.model;

import java.util.ArrayList;
import java.util.List;

//vua
public class ChessKing extends Chess {

	public ChessKing(Team team) {
		super(team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/* Kiểm tra và lấy từ 8 hướng có thể đi */
		list.addAll(couldMoveToward(point, -1, -1)); // trái đi lên
		list.addAll(couldMoveToward(point, -1, 1)); // trái đi xuống
		list.addAll(couldMoveToward(point, 1, -1));// phải đi lên
		list.addAll(couldMoveToward(point, 1, 1));// phải đi xuống
		list.addAll(couldMoveToward(point, 0, -1));// lên
		list.addAll(couldMoveToward(point, 0, 1));// xuống
		list.addAll(couldMoveToward(point, -1, 0));// trái
		list.addAll(couldMoveToward(point, 1, 0));// phải

		return list;
	}

	public static void main(String[] args) {
		System.out.println("Bishop");
		Chess chess = new ChessKing(Team.BLACK);
		// test ở vị trí bất kì
		List<Point> list = chess.getPosibleMove(new Point(7, 3));
		for (Point item : list)
			System.out.println(item.toString());
	}

}