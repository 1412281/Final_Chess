package chess.model;

import java.util.ArrayList;
import java.util.List;

//hậu
public class ChessQueen extends Chess {

 /*Bản chất Queen là một Super King*/
	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/*Kiểm tra và lấy từ 8 hướng có thể đi*/
		list.addAll(couldMoveToward_ManyChoices(point,-1 ,-1)); // trái đi lên
		list.addAll(couldMoveToward_ManyChoices(point,-1 , 1)); // trái đi xuống
		list.addAll(couldMoveToward_ManyChoices(point, 1 ,-1));// phải đi lên
		list.addAll(couldMoveToward_ManyChoices(point, 1 , 1));// phải đi xuống
		list.addAll(couldMoveToward_ManyChoices(point, 0 ,-1));// lên
		list.addAll(couldMoveToward_ManyChoices(point, 0 , 1));// xuống
		list.addAll(couldMoveToward_ManyChoices(point,-1 , 0));// trái
		list.addAll(couldMoveToward_ManyChoices(point, 1 , 0));// phải
		
		return list;
	}
	
	
	public static void main(String[] args){
		Chess chess = new ChessQueen();
		// test ở vị trí bất kì
		List<Point> list  =  chess.getPosibleMove(new Point(3, 3));
		for(Point item:list) item.printposition();
	}
}
