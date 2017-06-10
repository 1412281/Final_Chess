package chess.model;
import java.util.List;



public abstract class Chess {
	
	public enum Team {
		BLACK, WHITE
	}
	
	private Team team;
	private boolean live = true;
	
	public abstract List<Position> getPosibleMove();
	
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}


}

