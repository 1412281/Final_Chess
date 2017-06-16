package chess.ChessFinal;

import chess.controller.PlayController;
import chess.model.AI;
import chess.model.Chess.Team;

public class App {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlayController playController = new PlayController();
		AI ai_1 = new AI(1, Team.BLACK, playController);
		AI ai_2 = new AI(1, Team.WHITE, playController);
		playController.getListMoveAllTeam(Team.BLACK);
		int time = 1;
		while ((playController.checkWin() == null) && (!playController.checkNotWin())) {
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
		if (playController.checkNotWin()) {
			System.out.println("Have not team is Win");
		} else {
			String teamString = playController.checkWin() == Team.WHITE ? "WHITE" : "BLACK";
			System.out.println("WIN => " + teamString);
		}
	}

}