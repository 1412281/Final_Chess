package chess.model;

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
		playController.getBoard();
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
