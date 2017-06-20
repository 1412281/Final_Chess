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
	
	/*Main này cho máy 1*/
	/*public static void main(String[] args) throws IOException, InterruptedException {
		PlayerInfo partner = null;
		
		NetworkController netController = new NetworkController();
		int numofplay = 0;
		List<PlayerInfo> listplayers = null;
		do{
			listplayers = netController.getListPlayers();
			numofplay = listplayers.size();
		}
		while(numofplay == 0);
		for(PlayerInfo item:listplayers){
			System.out.println(item.getName());
		}
		// gui loi moi den nguoi nay
		//netController.sendAccept(listplayers.get(0));
		partner = listplayers.get(0);
		//netController.sendOffer(listplayers.get(0));
		//Mail mail = netController.waitToReceiveMailFrom(partner);
		
		PlayController playController = new PlayController();
//		AI ai_1 = new AI(1, Team.BLACK, playController);
//		AI ai_2 = new AI(1, Team.WHITE, playController);
		System.out.println("START");
		Team x = Team.WHITE ;
		int time = 1;
		while ((playController.checkWin() == null) && (!playController.isNotWin())) {
			System.out.println(time++);
			//System.out.println((new Fen(playController.getBoard()).getFenString()));
			DataInputStream din = new DataInputStream(System.in);
			
			
			switch (x) {
			case WHITE:
				System.out.println("WHITE: ");
				do{
					Mail rmail = netController.waitToReceiveMailFrom(partner);
					System.out.println(rmail.getContent());
					if (rmail.getTitle().equals("MOVE")) {
						Move move = new Move(rmail.getContent());
						//playController.sendMove(move);
						System.out.println(move.toString());
						x = Team.BLACK;
						break;
					}
					
				}
				while(true);
				break;
			case BLACK:
				System.out.println("BLACK: ");
				String st = din.readLine();
				Mail smail = new Mail(partner, "MOVE", st);
				netController.sendMail(smail);
				Move move2 = new Move(smail.getContent());
				//playController.sendMove(move);
				System.out.println(move2.toString());
				
				
				x = Team.WHITE;
				break;
			}
		}
		if (playController.isNotWin()) {
			System.out.println("Have not team is Win");
		} else {
			String teamString = playController.checkWin() == Team.WHITE ? "WHITE" : "BLACK";
			System.out.println("WIN => " + teamString);
		}
	}*/
	
	
	
	/*Main này cho máy 2, máy 2 đi trước*/
	
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
		for(PlayerInfo item:listplayers){
			System.out.println(item.getName());
		}
		// gui loi moi den nguoi nay
		//netController.sendOffer(listplayers.get(0));
//		
		
//		
//		netController.sendDeny(listplayers.get(0));
		
		partner = listplayers.get(0);
		//Mail mail = netController.waitToReceiveMailFrom(partner);
		//netController.sendAccept(listplayers.get(0));
		
		PlayController playController = new PlayController();
//		AI ai_1 = new AI(1, Team.BLACK, playController);
//		AI ai_2 = new AI(1, Team.WHITE, playController);
		System.out.println("START");
		Team x = Team.WHITE ;
		int time = 1;
		while ((playController.checkWin() == null) && (!playController.isNotWin())) {
			System.out.println(time++);
			//System.out.println((new Fen(playController.getBoard()).getFenString()));
			DataInputStream din = new DataInputStream(System.in);
			
			
			switch (playController.getTeamTurn()) {
			case WHITE:
				System.out.println("WHITE: ");
				String st = din.readLine();
				Mail smail = new Mail(partner, "MOVE", st);
				netController.sendMail(smail);
				Move move2 = new Move(smail.getContent());
				playController.sendMove(move2);
				System.out.println(move2.toString());
				break;
			case BLACK:
				System.out.println("BLACK: ");
				do{
					Mail rmail = netController.waitToReceiveMailFrom(partner);
					System.out.println(rmail.getContent());
					if (rmail.getTitle().equals("MOVE")) {
						Move move = new Move(rmail.getContent());
						playController.sendMove(move);
						System.out.println(move.toString());
				
						break;
					}
					
				}
				while(true);
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