package chess.controller;
import java.util.List;

import chess.model.*;
import chess.model.Chess.Team;


public class PlayController {
	
	
	private Board board;
	private Team teamTurn;
	
	public PlayController() {
		board = new Board();
		setTeamTurn(Team.WHITE);
	}
	
	
	public List<Point> getListPosibleMoveFrom(Point point) {
		return board.getListPosibleMoveFrom(point);
	}
	

	public void sendMove(Point fromPoint, Point toPoint) {
		
		nextTurn();
	}


	private void nextTurn() {
		if (teamTurn.equals(Team.WHITE))
			setTeamTurn(Team.BLACK);
		else {
			setTeamTurn(Team.WHITE);
		}
	}


	public Team getTeamTurn() {
		return teamTurn;
	}


	public void setTeamTurn(Team teamTurn) {
		this.teamTurn = teamTurn;
	}

	public Square[][] getBoard() {
		return board.getBoard();
	}
	
	public boolean notWin() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
