package chess.ChessFinal;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import chess.controller.NetworkController;
import chess.controller.PlayController;
import chess.model.AI;
import chess.model.Fen;
import chess.model.Move;
import chess.model.Chess.Team;
import chess.network.Entities.Mail;
import chess.network.Entities.PlayerInfo;

public class App {
	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		PlayerInfo partner = null;
		
		NetworkController netController = new NetworkController();
		int numofplay = 0;
		List<PlayerInfo> listplayers = null;
		do{
			listplayers = netController.getListPlayers();
			numofplay = listplayers.size();
		}
		while(numofplay == 0);
		
		System.out.println(listplayers.size());
		for(PlayerInfo item:listplayers){
			System.out.println(item.getName());
			
			
		}
		// gui loi moi den nguoi nay
		netController.sendOffer(listplayers.get(0));
//		
//		netController.sendAccept(listplayers.get(0));
//		
//		netController.sendDeny(listplayers.get(0));
		netController.waitToReceiveMailFrom(listplayers.get(0));
		
		partner = listplayers.get(0);
		
		PlayController playController = new PlayController();
//		AI ai_1 = new AI(1, Team.BLACK, playController);
//		AI ai_2 = new AI(1, Team.WHITE, playController);
		
		int time = 1;
		while ((playController.checkWin() == null) && (!playController.isNotWin())) {
			System.out.println(time++);
			System.out.println((new Fen(playController.getBoard()).getFenString()));
			
			switch (playController.getTeamTurn()) {
			case WHITE:
				System.out.println("WHITE: ");
				DataInputStream din = new DataInputStream(System.in);
				String st = din.readLine();
				Mail smail = new Mail(partner, "MOVE", st);
				netController.sendMail(smail);
				break;
			case BLACK:
				System.out.println("BLACK: ");
				Mail rmail = netController.waitToReceiveMailFrom(listplayers.get(0));
				if (rmail.getTitle().equals("MOVE")) {
					Move move = new Move(rmail.getContent());
					playController.sendMove(move);
				}
				break;
			}
		}
		if (playController.isNotWin()) {
			System.out.println("Have not team is Win");
		} else {
			String teamString = playController.checkWin() == Team.WHITE ? "WHITE" : "BLACK";
			System.out.println("WIN => " + teamString);
		}
	}

}