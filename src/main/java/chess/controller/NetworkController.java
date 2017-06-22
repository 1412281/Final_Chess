package chess.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import chess.network.Entities.Mail;
import chess.network.Entities.PlayerInfo;
import chess.network.main.network;

public class NetworkController {
	private network net;
	public NetworkController() throws IOException{
		try {
			net = new network();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ham lay danh sach nguoi choi trong mang LAN
	public List<PlayerInfo> getListPlayers() throws UnknownHostException, InterruptedException {
		List<PlayerInfo> list = network.getListPlayers();
		return list;
	}
	
	//ham gui moi choi den nguoi choi duoc chi dinh
	public void sendOffer(PlayerInfo player) throws InterruptedException{
		net.sendMail(player, "offer","");
	}
	// gui dong y den nguoi choi da moi
	public void sendAccept(PlayerInfo player) throws InterruptedException{
		net.sendMail(player, "accept","");
	}
	public void sendDeny(PlayerInfo player) throws InterruptedException{
		net.sendMail(player, "deny","");
	}
	
	// kiem tra trong hom thu co Offer nao khong
	public Mail getMail_byTitle_IfExist(String title){
		if(title == null ) return null;
		List<Mail> listmail= net.getAllMails();
		Mail result = null;
		for(Mail item:listmail){
			if(item.getTitle().equals(title)){
				result = item;
				listmail.remove(item);
				break;
			}
		}
		return result;
	}
	// ham doi va receive tin nhan dau tien tu nguoi choi chi dinh
	public Mail waitToReceiveMailFrom(PlayerInfo player) throws InterruptedException{
		Mail m = null;
		do{
			m = net.receiveFirstMailFrom(player.getIP());
		}while(null == m);
		return m;
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException{
		/*NetworkController netcontroller = new NetworkController();
		int numofplay = 0;
		List<PlayerInfo> listplayers = null;
		do{
			listplayers = netcontroller.getListPlayers();
			numofplay = listplayers.size();
		}
		while(numofplay == 0);
		
		System.out.println(listplayers.size());
		for(PlayerInfo item:listplayers){
			System.out.println(item.getName());
			
			
		}
		// gui loi moi den nguoi nay
		netcontroller.sendOffer(listplayers.get(0));
		
		netcontroller.sendAccept(listplayers.get(0));
		
		netcontroller.sendDeny(listplayers.get(0));*/
		
		
	}

	public void sendMail(Mail mail) throws InterruptedException {
		net.sendMail(mail.getSender(), mail.getTitle(), mail.getContent());
	}
	
}
