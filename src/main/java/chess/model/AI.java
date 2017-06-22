package chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.SliderUI;

import chess.controller.PlayController;
import chess.model.AI.Level;
import chess.model.Chess.Team;

public class AI {
	public static enum Level {
		Easy, Normal, Hard
	}

	private Team team;
	private int level;
	private PlayController playController;
	private Move lastMove = null;

	public AI() {

	}

	public AI(int level, Team team, PlayController playController) {
		this.setTeam(team);
		this.level = level;
		this.playController = playController;
	}

	public void takeAMove() throws InterruptedException {
		Thread.sleep(100);
		takeHard(level);
	}

	private int getValueBoard(Square[][] sq) {
		int result = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Chess chess = sq[i][j].getChess();
				if (chess != null) {
					if (chess.getTeam() == Team.WHITE) {
						result += chess.getValue() + chess.getValueWHITE()[i][j];
					} else {
						result += chess.getValue() + chess.getValueBLACK()[i][j];
					}
				}
			}
		return this.getTeam() == Team.WHITE ? result : -result;
	}

	private void takeHard(int depth) {
		PlayController tempPlayController = new PlayController(playController);

		List<Move> listMove = tempPlayController.getListMoveAllTeam(this.getTeam());

//		// delete last point move from. prevent loop
//		if (lastMove != null) {
//			for (int i = 0; i < listMove.size(); i++) {
//				if (listMove.get(i).equal(lastMove.reverse())) {
//					listMove.remove(i);
//				}
//			}
//		}

		int bestValue = -9999 * 2;
		Move bestMove = null;
		for (Move move : listMove) {
			// move và tính thử
			tempPlayController.sendMove(move);
			
			Team emy = this.getTeam().equals(Team.WHITE) ? Team.BLACK : Team.WHITE;
			int boardValue = this.minimax(depth - 1, tempPlayController, -10000 * 2, 10000 * 2, true, emy);
			if (boardValue >= bestValue) {
				bestValue = boardValue;
				bestMove = move;
			}
			
			tempPlayController.unMove();
		}

		Square[][] sq = playController.getBoardSquare();
		List<Point> listTeam = playController.getListTeam(this.getTeam());
		System.out.print("Team: ");
		for (int i = 0; i < listTeam.size(); i++)
			System.out.print(sq[listTeam.get(i).getX()][listTeam.get(i).getY()].getChess().toString() + " ");
		System.out.print("\n" + sq[bestMove.getFrom().getX()][bestMove.getFrom().getY()].getChess().toString());
		System.out.println(" " + bestMove.toString());
		System.out.println("");
		this.lastMove = bestMove;
		playController.sendMove(bestMove);

	}

	private int minimax(int depth, PlayController playController, int alpha, int beta, boolean isEmy, Team team) {
		Team emy = team.equals(Team.WHITE) ? Team.BLACK : Team.WHITE;

		if (depth == 0) {
			return -getValueBoard(playController.getBoardSquare());
		}

		List<Move> listMove = playController.getListMoveAllTeam(team);

		if (!isEmy) {
			int bestValue = -9999 * 2;

			for (int i = 0; i < listMove.size(); i++) {

				playController.sendMove(listMove.get(i));
				
				bestValue = Math.max(bestValue, minimax(depth - 1, playController, alpha, beta, !isEmy, emy));
				
				playController.unMove();

				alpha = Math.max(alpha, bestValue);
				if (beta <= alpha) {
					return bestValue;
				}
			}
			return bestValue;
		} else {
			int bestValue = 9999 * 2;

			for (int i = 0; i < listMove.size(); i++) {

				playController.sendMove(listMove.get(i));
				
				bestValue = Math.min(bestValue, minimax(depth - 1, playController, alpha, beta, !isEmy, emy));
				
				playController.unMove();

				beta = Math.min(beta, bestValue);
				if (beta <= alpha) {
					return bestValue;
				}
			}
			return bestValue;
		}
	}

	public PlayController getPlayController() {
		return playController;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public static void main(String[] args) throws InterruptedException {
		PlayController playController = new PlayController();

		PlayController newController = new PlayController(playController);

		AI ai_1 = new AI(3, Team.BLACK, playController);
		ai_1.takeAMove();
	}

}