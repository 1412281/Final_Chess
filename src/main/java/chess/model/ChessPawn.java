package chess.model;

import java.util.ArrayList;
import java.util.List;


//tốt
public class ChessPawn extends Chess {


	public ChessPawn(Team team) {
		super(team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		/*Kiểm tra và lấy từ 3 hướng có thể đi*/
		switch (this.team) {
		case BLACK:
			list.addAll(couldMoveToward(point, 0 ,-1));
			list.addAll(couldMoveToward(point,-1 ,-1));
			list.addAll(couldMoveToward(point, 1 ,-1));
			// trường hợp đặc biệt khi Pawn đang ở điểm bắt đầu ván đấu, có thể nhảy 2 bước
			if(6 ==  point.getY()){
				list.addAll(couldMoveToward(point, 0 ,-2));//lên 2 bước
			}
			break;

		case WHITE:
			list.addAll(couldMoveToward(point, 0 , 1));
			list.addAll(couldMoveToward(point,-1 , 1));
			list.addAll(couldMoveToward(point, 1 , 1));
			// trường hợp đặc biệt khi Pawn đang ở điểm bắt đầu ván đấu, có thể nhảy 2 bước
			if(1  ==  point.getY()){
				list.addAll(couldMoveToward(point, 0 ,2));//tiến 2 bước
			}
			break;
		}
		
		
		return list;
	}
	
	public static void main(String[] args){
		System.out.println("Bishop");
		Chess chess = new ChessPawn(Team.BLACK);
		
		chess.setTeam(Team.BLACK);
		
		// test ở vị trí bất kì
		List<Point> list  =  chess.getPosibleMove(new Point(1, 6));
		for(Point item:list) System.out.println(item.toString());
	}
}