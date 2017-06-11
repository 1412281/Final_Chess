package chess.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//ngựa
public class ChessKnight extends Chess {

	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/*Kiểm tra và lấy từ 8 hướng có thể đi*/
		list.addAll(couldMoveToward(point,-1 ,-2));// trái đi lên
		list.addAll(couldMoveToward(point,-1 , 2));// trái đi xuống
		list.addAll(couldMoveToward(point, 1 ,-2));// phải đi lên
		list.addAll(couldMoveToward(point, 1 , 2));// phải đi xuống
		list.addAll(couldMoveToward(point,-2 ,-1));// trái đi lên 2
		list.addAll(couldMoveToward(point,-2 , 1));// trái đi xuống 2
		list.addAll(couldMoveToward(point, 2 ,-1));// phải đi lên 2
		list.addAll(couldMoveToward(point, 2 , 1));// phải đi xuống 2
		
		return list;
	}
	
	public static void main(String[] args){
		System.out.println("Bishop");
		Chess chess = new ChessKnight();
		// test ở vị trí bất kì
		List<Point> list  =  chess.getPosibleMove(new Point(6, 6));
		for(Point item:list) item.printposition();
	}
}
