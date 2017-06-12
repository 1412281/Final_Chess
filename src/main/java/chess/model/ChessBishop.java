package chess.model;

import java.util.ArrayList;
import java.util.List;

//bồ
public class ChessBishop extends Chess {

	public ChessBishop(Team team) {
		super(team);
		super.setValue(30);
	}

	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();

		/*
		 * Kiểm tra và lấy từ 4 hướng chéo có thể đi Quân tượng thì mỗi hướng có
		 * nhiều lựa chọn
		 */
		list.addAll(couldMoveToward_ManyChoices(point, -1, -1)); // trái đi lên
		list.addAll(couldMoveToward_ManyChoices(point, -1, 1)); // trái đi xuống
		list.addAll(couldMoveToward_ManyChoices(point, 1, -1));// phải đi lên
		list.addAll(couldMoveToward_ManyChoices(point, 1, 1)); // phải đi xuống

		return list;
	}

	public static void main(String[] args) {
		System.out.println("Bishop");
		ChessBishop bishop = new ChessBishop(Team.BLACK);
		// test ở vị trí bất kì
		List<Point> list = bishop.getPosibleMove(new Point(4, 4));
		for (Point item : list)
			System.out.println(item.toString());
	}

	@Override
	public String toString() {
		return "Bishop";
	}

}
