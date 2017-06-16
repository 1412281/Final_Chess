package chess.controller;

import java.util.ArrayList;
import java.util.List;

import chess.model.*;
import chess.model.AI.Level;
import chess.model.Chess.Team;

public class PlayController {

	private Board board;
	private List<Fen> listHistory = new ArrayList<Fen>();

	// last move

	public PlayController(PlayController play) {
		this.board = new Board(play.board);
	}

	public Board getBoard() {
		return this.board;
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
		int size = listHistory.size();
		
		board = listHistory.get(size - 1).getBoard();
		
		listHistory.remove(size - 1);
	}

	public void sendMove(Move move) {

		Fen fen = new Fen(this.board);

		listHistory.add(fen);
		
		board.move(move);

	}

	public Team getTeamTurn() {
		return board.getTeamTurn();
	}

	public Square[][] getBoardSquare() {
		return board.getBoardSquare();
	}

	public Team checkWin() {
		Team teamWin = board.checkWin();
		if (teamWin == null) {
			return null;
		} else {
			return teamWin;
		}
	}

	public boolean checkNotWin() {
		return board.checkNotWin() && checkPermanent();
	}

	private boolean checkPermanent() {
		if (listHistory.isEmpty())
			return false;
		int count = 0;
		Fen fenCurrent = listHistory.get(listHistory.size() - 1);
		for (Fen fen : listHistory) {
			if (fen.equal(fenCurrent))
				count++;
		}
		return count == 3;
	}

	public List<Move> getListMoveAllTeam(Team team) {

		return board.getListMoveAllTeam(team);
	}

	public static void main(String[] args) {
		PlayController playController = new PlayController();
		PlayController newController = new PlayController(playController);
		Point p1 = new Point(0, 6);
		Point p2 = new Point(0, 4);
		Move move = new Move(p1, p2);
		System.out.println((new Fen(newController.getBoard()).getFenString()));
		newController.sendMove(move);
		System.out.println((new Fen(newController.getBoard()).getFenString()));
		newController.unMove();
	System.out.println((new Fen(newController.getBoard()).getFenString()));

	}

}