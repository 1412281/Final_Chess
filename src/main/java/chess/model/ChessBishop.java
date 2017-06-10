package chess.model;

import java.util.List;

//bồ
public class ChessBishop extends Chess {

	final int BOARD_MIN = 1;
	final int BOARD_MAX = 8;

	@Override
	public List<Position> getPosibleMove() {
		List<Position> list = null;
		Position pos = this.getPos();
		
		moveTopLeft(pos, list);
		moveTopRight(pos, list);
		
		return list;
	}
	
	private void moveTopLeft(Position cur_pos, List<Position> list) {
		cur_pos.setPos(cur_pos.getX() + 1, cur_pos.getY() + 1);
		list.add(cur_pos);
		if (cur_pos.getX() < BOARD_MAX && cur_pos.getY() < BOARD_MAX)
			moveTopLeft(cur_pos, list);
	}
	
	private void moveTopRight(Position cur_pos, List<Position> list) {
		cur_pos.setPos(cur_pos.getX() - 1, cur_pos.getY() + 1);
		list.add(cur_pos);
		if (cur_pos.getX() < BOARD_MAX && cur_pos.getY() < BOARD_MAX)
			moveTopLeft(cur_pos, list);
	}
}
