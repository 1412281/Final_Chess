package chess.model;

import java.util.ArrayList;
import java.util.List;

//bồ
public class ChessBishop extends Chess {

	final int BOARD_MIN = 1;
	final int BOARD_MAX = 8;
	
	@Override
	public List<Position> getPosibleMove() {
		List<Position> list = new ArrayList<Position>();
		Position pos = this.getPos();
		pos.setPos(5, 5);
		list.addAll(moveTopLeft(pos));
		list.addAll(moveTopRight(pos));
		
		return list;
	}
	
	
	public List<Position> moveTopLeft(Position pos) {
		Position curPos = new Position(pos);
		List<Position> list = new ArrayList<Position>();
		
		while (curPos.getX() < BOARD_MAX && curPos.getY() < BOARD_MAX) {
			curPos.setPos(curPos.getX() + 1, curPos.getY() + 1);
			System.out.println(curPos.toString());
			list.add(curPos);
		}
		return list;
	}
	
	public List<Position> moveTopRight(Position pos) {
		Position curPos = new Position(pos);
		List<Position> list = new ArrayList<Position>();
		
		while (curPos.getX() > BOARD_MIN && curPos.getY() < BOARD_MAX) {
			curPos.setPos(curPos.getX() - 1, curPos.getY() + 1);
			System.out.println(curPos.toString());
			list.add(curPos);
		}
		return list;
	}
	
	
}
