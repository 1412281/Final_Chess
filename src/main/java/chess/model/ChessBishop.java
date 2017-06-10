package chess.model;

import java.util.ArrayList;
import java.util.List;

//bồ
public class ChessBishop extends Chess {

	final int BOARD_MIN = 1;
	final int BOARD_MAX = 8;
	
	@Override
	public List<Point> getPosibleMove(Point point) {
		List<Point> list = new ArrayList<Point>();
		
		list.addAll(moveTopLeft(point));
		list.addAll(moveTopRight(point));
		
		return list;
	}
	
	
	public List<Point> moveTopLeft(Point point) {
		Point curPos = new Point(point);
		List<Point> list = new ArrayList<Point>();
		
		while (curPos.getX() < BOARD_MAX && curPos.getY() < BOARD_MAX) {
			curPos.setPos(curPos.getX() + 1, curPos.getY() + 1);
			System.out.println(curPos.toString());
			list.add(curPos);
		}
		return list;
	}
	
	public List<Point> moveTopRight(Point point) {
		Point curPos = new Point(point);
		List<Point> list = new ArrayList<Point>();
		
		while (curPos.getX() > BOARD_MIN && curPos.getY() < BOARD_MAX) {
			curPos.setPos(curPos.getX() - 1, curPos.getY() + 1);
			System.out.println(curPos.toString());
			list.add(curPos);
		}
		return list;
	}
	
	
}
