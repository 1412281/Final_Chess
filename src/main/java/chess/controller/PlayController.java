package chess.controller;
import java.util.ArrayList;
import java.util.List;

import chess.model.*;
import chess.model.Chess.Team;


public class PlayController {
	
	private Board board;
	
	PlayController() {
		board = new Board();
	}
	
	
	public List<Position> getListPosibleMove(Position pos) {
		
		List<Position> list = board.getListPosibleMove(pos);
		
		return list;
	}
	

	public void sendPositionChoice(Position pos) {
		
	}
}
