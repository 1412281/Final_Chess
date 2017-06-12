package chess.model;

import chess.model.Chess.Team;

public class Player {
	private String name;
	private String ipAdress;
	private Team team;
	
	public Player(String name, Team team) {
		this.name = name;
		this.team = team;
	}
	
	public Player(String name, String ipAdress) {
		this.name = name;
		this.ipAdress = ipAdress;
		this.setTeam(team);
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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
