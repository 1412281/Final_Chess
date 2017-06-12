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

		while (true) {
			Random ran = new Random();
			List<Point> listTeam = playController.getListTeam(player.getTeam());
			int iteam = ran.nextInt(listTeam.size());
			List<Point> listMove = playController.getListPosibleMoveFrom(listTeam.get(iteam));
			if (listMove.isEmpty())
				continue;
			int iMove = ran.nextInt(listMove.size());
			System.out.println(playController.getBoard()[listTeam.get(iteam).getX()][listTeam.get(iteam).getY()].getChess().toString());
			System.out.println(listTeam.get(iteam) + " => " + listMove.get(iMove));
			System.out.println("");
			playController.sendMove(listTeam.get(iteam), listMove.get(iMove));
			break;
		}
	}

	private void takeNormal() {
		int bestValue = -9999;
		Point bestPointFrom = null, bestPointTo = null;
		List<Point> listMove = new ArrayList<Point>();
		List<Point> listTeam = playController.getListTeam(player.getTeam());

		for (int iTeam = 0; iTeam < listTeam.size(); iTeam++) {
			
			listMove = playController.getListPosibleMoveFrom(listTeam.get(iTeam));
			
			for (int iMove = 0; iMove < listMove.size(); iMove++) {
			
				// move và tính thử
				playController.sendMove(listTeam.get(iTeam), listMove.get(iMove));
				
				int boardValue = getValueBoard(playController.getBoard());
				if (boardValue > bestValue) {
					bestValue = boardValue;
					bestPointFrom = listTeam.get(iTeam);
					bestPointTo = listMove.get(iMove);
				}
				playController.unMove();
			}
		}
		System.out.println(playController.getBoard()[bestPointFrom.getX()][bestPointFrom.getY()].getChess().toString());
		System.out.println(bestPointFrom + " => " + bestPointTo);
		System.out.println("");
		playController.sendMove(bestPointFrom, bestPointTo);

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
		// TODO Auto-generated method stub

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
