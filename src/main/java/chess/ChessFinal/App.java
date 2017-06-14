package chess.ChessFinal;

import chess.controller.PlayController;
import chess.model.AI;
import chess.model.AI.Level;
import chess.model.Chess.Team;

public class App 
{
    public static void main( String[] args )
    {
    	PlayController playController = new PlayController();
    	AI ai_1 = new AI(4, Team.BLACK, playController);
    	AI ai_2 = new AI(1, Team.WHITE, playController);
    	
    	int time = 1;
    	while (playController.checkWin() == null) {
    		System.out.println(time++);
    		
    		switch (playController.getTeamTurn()) {
    		case BLACK:
    			System.out.println("BLACK: ");
    			ai_1.takeAMove();
    			break;
    		case WHITE:
    			System.out.println("WHITE: ");
    			ai_2.takeAMove();
    			break;
    		}
    	}
    	String teamString = playController.checkWin() == Team.WHITE ? "WHITE" : "BLACK";
    	System.out.println("WIN => " + teamString);
    }
    
}