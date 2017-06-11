package chess.model;

import java.util.ArrayList;
import java.util.List;

//xe
public class ChessRook extends Chess {

	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/*Kiểm tra và lấy từ 4 hướng có thể đi*/
		list.addAll(couldMoveToward_ManyChoices(point, 0 ,-1));// lên
		list.addAll(couldMoveToward_ManyChoices(point, 0 , 1));// xuống
		list.addAll(couldMoveToward_ManyChoices(point,-1 , 0));// trái
		list.addAll(couldMoveToward_ManyChoices(point, 1 , 0));// phải
		
		return list;
	}
	
	
	public static void main(String[] args){
		Chess chess = new ChessRook();
		// test ở vị trí bất kì
		List<Point> list  =  chess.getPosibleMove(new Point(7, 7));
		for(Point item:list) item.printposition();
	}
}
