package chess.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import chess.model.*;
import chess.model.Chess.Team;

public class PlayController {

	private Board board;

	// last move
	Deque<Point> lastMove = new ArrayDeque<Point>();

	public PlayController() {
		board = new Board();
		lastMove.clear();
	}

	public List<Point> getListTeam(Team team) {
		return board.getListTeam(team);
	}

	public List<Point> getListPosibleMoveFrom(Point point) {
		return board.getListPosibleMoveFrom(point);
	}

	public void unMove() {
		sendMove(this.lastMove.pop(),this.lastMove.pop());
	}

	public void sendMove(Point fromPoint, Point toPoint) {

		board.move(fromPoint, toPoint);
		if (this.lastMove.size() == 4) { this.lastMove.removeLast(); this.lastMove.removeLast(); }
		this.lastMove.push(fromPoint);
		this.lastMove.push(toPoint);

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
	
	public List<Point> getLastMove(){
		List<Point> result = new ArrayList<Point>();
		for(Point point: this.lastMove) {
			result.add(point);
		}
		return result;
	}

}
