package chess.controller;

import java.io.IOException;

import chess.model.Chess.Team;
import chess.model.Player;

public class MenuController {
	
	public static enum Mode {
		Signle, Multi
	}
	
	Player user, emy;
	NetworkController networkController;
	
	public MenuController(Mode mode, String userName) throws IOException { 
		
		this.user = new Player(userName, Team.WHITE);
		
		switch (mode) {
		case Signle:
			
			break;
		case Multi:
			try {
				networkController = new NetworkController();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	
}
