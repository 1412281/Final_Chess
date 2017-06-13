package chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chess.controller.PlayController;
import chess.model.Chess.Team;

public class AI {
	public static enum Level {
		Easy, Normal, Hard
	}

	private Player player;
	private Level level;
	private PlayController playController;
	private Point lastMoveFrom = new Point();
	private Point lastMoveTo = new Point();

	public AI() {

	}

	public AI(Level level, Team team, PlayController playController) {
		player = new Player(level.toString() + "AI", team);
		this.level = level;
		this.setPlayController(playController);
	}

	public void takeAMove() {
		switch (this.level) {
		case Normal:
			takeNormal();
			break;
		case Hard:
			takeHard();
			break;
		default:
			takeEasy();
			break;
		}
	}

	private void takeEasy() {
		Square[][] sq = playController.getBoard();
		
		List<Point> listTeam = playController.getListTeam(player.getTeam());
		List<Point> listMove = new ArrayList<Point>();
		System.out.print("Team: ");
		for (int i = 0; i < listTeam.size(); i++)
			System.out.print(sq[listTeam.get(i).getX()][listTeam.get(i).getY()].getChess().toString() + " ");
		Random ran = new Random();
		int iteam;
		int iMove;
		
		while (true) {
			
			iteam = ran.nextInt(listTeam.size());
			listMove = playController.getListPosibleMoveFrom(listTeam.get(iteam));
			if (listMove.isEmpty())
				continue;
			iMove = ran.nextInt(listMove.size());
			break;
		}

		System.out.println("\n" + sq[listTeam.get(iteam).getX()][listTeam.get(iteam).getY()].getChess().toString());
		System.out.println(listTeam.get(iteam).toString() + " => " + listMove.get(iMove).toString());
		System.out.println("");

		playController.sendMove(new Move(listTeam.get(iteam), listMove.get(iMove)));
	}

	private void takeNormal() {
		int bestValue = -9999;
		Point bestPointFrom = null, bestPointTo = null;
		List<Point> listMove = new ArrayList<Point>();
		List<Point> listTeam = playController.getListTeam(player.getTeam());
		
		Square[][] sq = playController.getBoard();
		System.out.print("Team: ");
		for (int i = 0; i < listTeam.size(); i++)
			System.out.print(sq[listTeam.get(i).getX()][listTeam.get(i).getY()].getChess().toString() + " ");

		for (int iTeam = 0; iTeam < listTeam.size(); iTeam++) {

			listMove = playController.getListPosibleMoveFrom(listTeam.get(iTeam));
			
			if (listMove.isEmpty())
				continue;
			
			// delete last point move from. prevent loop
			for (int iMove = 0; iMove < listMove.size(); iMove++) {
				if (listMove.get(iMove).equal(this.lastMoveFrom) && (listTeam.get(iTeam).equal(this.lastMoveTo))) {
					listMove.remove(iMove);
				}

			}

			for (int iMove = 0; iMove < listMove.size(); iMove++) {

				// move và tính thử
				playController.sendMove(new Move(listTeam.get(iTeam), listMove.get(iMove)));

				int boardValue = getValueBoard(playController.getBoard());
				if (boardValue >= bestValue) {
					bestValue = boardValue;
					bestPointFrom = listTeam.get(iTeam);
					bestPointTo = listMove.get(iMove);
				}

				playController.unMove();
			}
		}
		System.out.println("\n" + sq[bestPointFrom.getX()][bestPointFrom.getY()].getChess().toString());
		System.out.println(bestPointFrom.toString() + " => " + bestPointTo.toString());
		System.out.println("");
		this.lastMoveFrom.setPos(bestPointFrom);
		this.lastMoveTo.setPos(bestPointTo);
		playController.sendMove(new Move(bestPointFrom, bestPointTo));

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

	private void takeHard() {
		
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

	public void setPlayController(PlayController playController) {
		this.playController = playController;
	}

}