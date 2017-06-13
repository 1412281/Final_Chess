package chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chess.controller.PlayController;
import chess.model.AI.Level;
import chess.model.Chess.Team;

public class AI {
	public static enum Level {
		Easy, Normal, Hard
	}

	private Player player;
	private Level level;
	private PlayController playController;
	private Move lastMove = null;

	public AI() {

	}

	public AI(Level level, Team team, PlayController playController) {
		player = new Player(level.toString() + "AI", team);
		this.level = level;
		this.playController = playController;
	}

	public void takeAMove() {
		switch (this.level) {
		case Normal:
			takeNormal();
			break;
		case Hard:
			takeHard(3);
			break;
		default:
			takeEasy();
			break;
		}
	}

	private void takeEasy() {

		List<Move> listMove = playController.getListMoveAllTeam(player.getTeam());
		
		Random ran = new Random();

		Move bestMove = listMove.get(ran.nextInt(listMove.size()));
		
		Square[][] sq = playController.getBoardSquare();
		List<Point> listTeam = playController.getListTeam(player.getTeam());
		System.out.print("Team: ");
		for (int i = 0; i < listTeam.size(); i++)
			System.out.print(sq[listTeam.get(i).getX()][listTeam.get(i).getY()].getChess().toString() + " ");
		System.out.print("\n" + sq[bestMove.getFrom().getX()][bestMove.getFrom().getY()].getChess().toString());
		System.out.println(" " + bestMove.toString());
		System.out.println("");

		playController.sendMove(bestMove);
	}

	private void takeNormal() {
		int bestValue = -9999;
		Move bestMove = null;

		List<Move> listMove = playController.getListMoveAllTeam(player.getTeam());
		// delete last point move from. prevent loop
		if (lastMove != null) {
			for (int i = 0; i < listMove.size(); i++) {
				if (listMove.get(i).equal(lastMove.reverse())) {
					listMove.remove(i);
				}
			}
		}
		for (Move move : listMove) {
			// move và tính thử
			playController.sendMove(move);

			int boardValue = getValueBoard(playController.getBoardSquare());
			if (boardValue >= bestValue) {
				bestValue = boardValue;
				bestMove = move;
			}

			playController.unMove();
		}
		Square[][] sq = playController.getBoardSquare();
		List<Point> listTeam = playController.getListTeam(player.getTeam());
		System.out.print("Team: ");
		for (int i = 0; i < listTeam.size(); i++)
			System.out.print(sq[listTeam.get(i).getX()][listTeam.get(i).getY()].getChess().toString() + " ");
		System.out.print("\n" + sq[bestMove.getFrom().getX()][bestMove.getFrom().getY()].getChess().toString());
		System.out.println(" " + bestMove.toString());
		System.out.println("");
		this.lastMove = bestMove;
		playController.sendMove(bestMove);

	}

	private int getValueBoard(Square[][] sq) {
		int result = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Chess chess = sq[i][j].getChess();
				if (chess != null) {
					if (chess.getTeam() != player.getTeam()) {
						result -= chess.getValue();
					} else {
						result += chess.getValue();
					}
				}
			}
		return result;
	}

	private void takeHard(int depth) {
		PlayController tempPlayController = new PlayController(playController);
		List<Move> listMove = tempPlayController.getListMoveAllTeam(player.getTeam());
		int bestValue = -9999;
		Move bestMove = null;
		for (Move move : listMove) {
			// move và tính thử
			tempPlayController.sendMove(move);

			int boardValue = this.minimax(depth - 1, tempPlayController, -10000, 10000, true);
			if (boardValue >= bestValue) {
				bestValue = boardValue;
				bestMove = move;
			}

			tempPlayController.unMove();
		}

		Square[][] sq = playController.getBoardSquare();
		List<Point> listTeam = playController.getListTeam(player.getTeam());
		System.out.print("Team: ");
		for (int i = 0; i < listTeam.size(); i++)
			System.out.print(sq[listTeam.get(i).getX()][listTeam.get(i).getY()].getChess().toString() + " ");
		System.out.print("\n" + sq[bestMove.getFrom().getX()][bestMove.getFrom().getY()].getChess().toString());
		System.out.println(" " + bestMove.toString());
		System.out.println("");
		
		playController.sendMove(bestMove);

	}

	private int minimax(int depth, PlayController playController, int alpha, int beta, boolean isEmy) {
		
		if (depth == 0) {
			return getValueBoard(playController.getBoardSquare());
		}

		List<Move> listMove = playController.getListMoveAllTeam(player.getTeam());

		if (!isEmy) {
			int bestValue = -9999;

			for (int i = 0; i < listMove.size(); i++) {
				
				playController.sendMove(listMove.get(i));
				bestValue = Math.max(bestValue, minimax(depth - 1, playController, alpha, beta, !isEmy));
				playController.unMove();
				
				alpha = Math.max(alpha, bestValue);
				if (beta <= alpha) {
					return bestValue;
				}
			}
			return bestValue;
		} else {
			int bestValue = 9999;

			for (int i = 0; i < listMove.size(); i++) {
				
				playController.sendMove(listMove.get(i));
				bestValue = Math.min(bestValue, minimax(depth - 1, playController, alpha, beta, !isEmy));
				playController.unMove();
				
				beta = Math.min(beta, bestValue);
				if (beta <= alpha) {
					return bestValue;
				}
			}
			return bestValue;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayController getPlayController() {
		return playController;
	}



	public static void main(String[] args) {
		PlayController playController = new PlayController();
		AI ai_1 = new AI(Level.Hard, Team.BLACK, playController);

		ai_1.takeAMove();
		ai_1.takeAMove();
		ai_1.takeAMove();
	}

}