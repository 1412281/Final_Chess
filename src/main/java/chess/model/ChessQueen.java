package chess.model;

import java.util.ArrayList;
import java.util.List;

//hậu
public class ChessQueen extends Chess {

	public ChessQueen(Team team) {
		super(team);
		setValue();
		super.setValue(90);
	}

	private void setValue() {
		int[][] value = new int[][] {
			 	{ -4, -2, -2, -1, -1, -2, -2, -4},
			    { -2,  0,  0,  0,  0,  0,  0, -2},
			    { -2,  0,  1,  1,  1,  1,  0, -2},
			    { -1,  0,  1,  1,  1,  1,  0, -1},
			    {  0,  0,  1,  1,  1,  1,  0, -1},
			    { -2,  1,  1,  1,  1,  1,  0, -2},
			    { -2,  0,  1,  0,  0,  0,  0, -2},
			    { -4, -2, -2, -1, -1, -2, -2, -4}
		};
		this.setValue(value);
	}

	/* Bản chất Queen là một Super King */
	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/* Kiểm tra và lấy từ 8 hướng có thể đi */
		list.addAll(couldMoveToward_ManyChoices(point, -1, -1)); // trái đi lên
		list.addAll(couldMoveToward_ManyChoices(point, -1, 1)); // trái đi xuống
		list.addAll(couldMoveToward_ManyChoices(point, 1, -1));// phải đi lên
		list.addAll(couldMoveToward_ManyChoices(point, 1, 1));// phải đi xuống
		list.addAll(couldMoveToward_ManyChoices(point, 0, -1));// lên
		list.addAll(couldMoveToward_ManyChoices(point, 0, 1));// xuống
		list.addAll(couldMoveToward_ManyChoices(point, -1, 0));// trái
		list.addAll(couldMoveToward_ManyChoices(point, 1, 0));// phải

		return list;
	}

	public static void main(String[] args) {
		Chess chess = new ChessQueen(Team.BLACK);
		// test ở vị trí bất kì
		List<Point> list = chess.getPosibleMove(new Point(3, 3));
		for (Point item : list)
			System.out.println(item.toString());
	}

	@Override
	public String toString() {
		return "Queen";
	}
}
