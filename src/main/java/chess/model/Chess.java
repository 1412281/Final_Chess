package chess.model;
import java.util.List;

public abstract class Chess {
	
	private boolean team;
	private int id;
	private boolean live = true;
	private Position pos;
	
	public abstract List<Position> getPosibleMove();
	
	
	public boolean getTeam() {
		return team;
	}
	public void setTeam(boolean team) {
		this.team = team;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
}

