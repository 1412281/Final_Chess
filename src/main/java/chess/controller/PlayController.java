package chess.controller;

import java.util.List;

import chess.model.*;
import chess.model.Chess.Team;

public class PlayController {

	private Board board;

	// last move

	public PlayController() {
		board = new Board();

	}

	public List<Point> getListTeam(Team team) {
		return board.getListTeam(team);
	}

	public List<Point> getListPosibleMoveFrom(Point point) {
		return board.getListPosibleMoveFrom(point);
	}

	public void unMove() {
		board.unMove();
	}

	public void sendMove(Move move) {
		board.move(move);
	}

	public Team getTeamTurn() {
		return board.getTeamTurn();
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

	public List<Move> getLastMove() {

		return board.getLastMove();
	}

}