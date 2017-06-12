package chess.controller;
import java.util.List;

import chess.model.*;


public class PlayController {
	
	private Board board;
	
	public PlayController() {
		board = new Board();
	}
	
	
	public List<Point> getListPosibleMove(Point point) {
		return board.getListPosibleMoveFrom(point);
	}
	

	public void sendPositionChoice(Point point) {
		board.sendPositionChoice(point);
	}
}
