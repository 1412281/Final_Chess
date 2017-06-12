package chess.model;

public class AI {
	public static enum Level {
		Easy, Normal, Hard
	}

	private Player player;
	private Level level;

	public AI() {

	}

	public AI(Level level) {
		player.setName(level.toString() + "AI");
		this.level = level;
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
		// TODO Auto-generated method stub
		
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

}
