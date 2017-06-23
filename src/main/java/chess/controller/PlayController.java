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
		this.board = new Fen(play.board).getBoard();
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public PlayController() {
		board = (new Fen()).getBoard();

	}

	public List<Point> getListTeam(Team team) {
		return board.getListTeam(team);
	}

	public List<Point> getListPosibleMoveFrom(Point point) {
		List<Point> list = new ArrayList<Point>();
		List<Point> result = new ArrayList<Point>();
		
		list.addAll(board.getListPosibleMoveFrom(point));
		
		for(Point toPoint: list) {
			if (this.checkMate(new Move(point, toPoint))) {
				result.add(toPoint);
			}
		}
		
		return result;
	}

	private boolean checkMate(Move move) {
		boolean result = false;
		
		Team lastTurn = this.getTeamTurn();
		
		this.sendMove(move);
		
		result = board.isKing_Checkmate(lastTurn);
			
		this.unMove();
		
		return result;
	
	}
	
	public void unMove() {
		int size = listHistory.size();

		board = listHistory.get(size - 1).getBoard();

		listHistory.remove(size - 1);
		
		if (board.getTeamTurn() == Team.WHITE) {
			board.setFullMove(board.getFullMove() - 1);
		}
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

	
	
	public boolean isNotWin() {
		return board.checkNotWin() || checkPermanent();
	}

	private boolean checkPermanent() {
		if (listHistory.isEmpty())
			return false;
		int count = 0;
		Fen fenCurrent = listHistory.get(listHistory.size() - 1);
		for (Fen fen : listHistory) {
			if (fen.getBoardSquareString().equals(fenCurrent.getBoardSquareString())) {
				count++;
			}
			if (count == 3)
				return true;
				
		}
		return false;
	}

	public List<Move> getListMoveAllTeam(Team team) {
		List<Move> listMove = board.getListMoveAllTeam(team);
		List<Move> result= new ArrayList<Move>();
		
		for(int i = 0; i < listMove.size(); i++) {
			if (!checkMate(listMove.get(i)))
			{	
				result.add(listMove.get(i));
			}
		}
		return result;
	}

	public static void main(String[] args) {
		PlayController playController = new PlayController();
		PlayController newController = new PlayController(playController);
		Point p1 = new Point(6, 1);
		Point p2 = new Point(4, 1);
		Move move = new Move(p1, p2);
		System.out.println((new Fen(newController.getBoard()).getFenString()));
		newController.sendMove(move);
		System.out.println((new Fen(newController.getBoard()).getFenString()));
		Point p11 = new Point(5, 1);
		Point p21 = new Point(4, 1);
		Move move1 = new Move(p11, p21);
		newController.sendMove(move1);
		System.out.println((new Fen(newController.getBoard()).getFenString()));
		newController.unMove();
		System.out.println((new Fen(newController.getBoard()).getFenString()));

	}

}