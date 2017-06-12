package chess.model;

public class Player {
	private String name;
	private String ipAdress;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, String ipAdress) {
		this.name = name;
		this.ipAdress = ipAdress;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
}
