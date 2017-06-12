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
    	AI ai_1 = new AI(Level.Easy, Team.WHITE, playController);
    	AI ai_2 = new AI(Level.Easy, Team.BLACK, playController);
    	while (playController.notWin()) {
    		switch (playController.getTeamTurn()) {
    		case BLACK:
    			ai_1.takeAMove();
    			break;
    		case WHITE:
    			ai_2.takeAMove();
    			break;
    		}
    	}
    }
    
}