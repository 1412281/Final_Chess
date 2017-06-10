package chess.controller;
import java.util.ArrayList;
import java.util.List;

import chess.model.*;
import chess.model.Chess.Team;


public class PlayController {
	
	private Board board;
	
	public PlayController() {
		board = new Board();
	}
	
	
	public List<Square> getListPosibleMove(Square square) {
		
		return board.getListPosibleMoveFrom(square);
	}
	

	public void sendPositionChoice(Square square) {
		
	}
}
