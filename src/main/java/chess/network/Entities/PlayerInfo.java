package chess.network.Entities;

public class PlayerInfo {
	private String IP;
	private String name;
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PlayerInfo(String iP){
		IP = iP;
	}
	public PlayerInfo(String iP, String name) {
		super();
		IP = iP;
		this.name = name;
	}
	
}
