package chess.controller;

import java.util.List;

import chess.model.*;
import chess.model.Chess.Team;

public class PlayController {

	private Board board;
	private Team teamTurn;
	//last move
	private Point lastFromPointMove, lastToPointMove;
	public PlayController() {
		board = new Board();
		setTeamTurn(Team.WHITE);
	}

	public List<Point> getListTeam(Team team) {
		return board.getListTeam(team);
	}

	public List<Point> getListPosibleMoveFrom(Point point) {
		return board.getListPosibleMoveFrom(point);
	}

	public void unMove() {
		
		sendMove(this.lastToPointMove, this.lastFromPointMove);
		
	}
	public void sendMove(Point fromPoint, Point toPoint) {
		nextTurn();
		board.move(fromPoint, toPoint);
		this.lastFromPointMove = fromPoint;
		this.lastToPointMove = toPoint;
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

	public Team checkWin() {
		Team teamWin = board.checkWin();
		if (teamWin == null) {
			return null;
		} else {
			return teamWin;
		}

	}

}
