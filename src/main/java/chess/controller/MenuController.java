package chess.controller;

import chess.model.Chess.Team;
import chess.model.Player;

public class MenuController {
	
	public static enum Mode {
		Signle, Multi
	}
	
	Player user, emy;
	NetworkController networkController;
	
	public MenuController(Mode mode, String userName) { 
		
		this.user = new Player(userName, Team.WHITE);
		
		switch (mode) {
		case Signle:
			
			break;
		case Multi:
			networkController = new NetworkController();
			break;
		}
	}
	
	
}
