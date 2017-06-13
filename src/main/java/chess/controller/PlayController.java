package chess.controller;

import java.util.List;

import chess.model.*;
import chess.model.AI.Level;
import chess.model.Chess.Team;

public class PlayController {

	private Board board;

	// last move

	
	public PlayController(PlayController play) {
		this.board = new Board(play.board);
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
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

	public Square[][] getBoardSquare() {
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

	public List<Move> getListMoveAllTeam(Team team) {
		
		return board.getListMoveAllTeam(team);
	}
	
	public static void main(String[] args) {
		PlayController playController = new PlayController();
		PlayController newController = new PlayController(playController);
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,2);
		Move move = new Move(p1, p2);
		Square[][] sq = playController.getBoardSquare();
		newController.sendMove(move);
		Square[][] sq1 = newController.getBoardSquare();
		playController.sendMove(move);
		
	}

}