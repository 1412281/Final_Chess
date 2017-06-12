package chess.model;

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
	
	private Square[][] square;

	public AI() {

	}

	public AI(Level level, Team team, PlayController playController) {
		player = new Player(level.toString() + "AI", team);
		this.level = level;
		this.setPlayController(playController);
	}

	public void takeAMove() {
		square = playController.getBoard();
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
			int iteam = ran.nextInt(listTeam.size() - 1);
			List<Point> listMove = playController.getListPosibleMoveFrom(listTeam.get(iteam));
			if (listMove.isEmpty()) continue;
			int iMove = ran.nextInt(listMove.size() - 1);
			String teamString = player.getTeam() == Team.BLACK ? "BLACK: " : "WHITE: ";
			System.out.println(teamString + square[listTeam.get(iteam).getX()][listTeam.get(iteam).getY()].getChess().toString());
			System.out.println(listTeam.get(iteam) + " => " + listMove.get(iMove));
			System.out.println("");
			playController.sendMove(listTeam.get(iteam), listMove.get(iMove));
			break;
		}
	}

	private void takeHard() {
		// TODO Auto-generated method stub

	}

	private void takeNormal() {
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
