package chess.network.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import chess.network.Entities.*;
import chess.network.Threads.*;


/*Class phụ trách nhiệm vụ:
 * + Khởi tạo server : trong hàm constructor
 * + Lấy thông tin danh sách các server khác đang tham gia trò chơi: Hàm "getListPlaysers"
 * + Gửi thông điệp đi đến 1 Player khác: sendMessage
 * + Nhận thông điệp về từ 1 Player khác: Có một hàng đợi các message được gửi đến server này,
 * 		 message nào đến trước thì xếp trước, không phân biệt player nào: FIFO
 *  
 **/
public class network {
	private static ArrayList<String> ListIP = new ArrayList();
	private static ArrayList<PlayerInfo> ListPlayers = new ArrayList();  
	private static ArrayList<Mail> mails= new ArrayList();

	public network () throws IOException, InterruptedException{
		createServer();
	}
	public static ArrayList<PlayerInfo> getListPlayers() throws UnknownHostException, InterruptedException {
		//lấy thông tin các server khác thông qua việc gửi đồng loạt các connection tới
		// các server khác từ 1 đến 254, server nào phản hồi về UserName của server thì ghi nhận
		// sau đó đóng kết nối ngay
		ListPlayers.clear();
		detectOthers();	
		Thread.sleep(1*1000);
		return ListPlayers;
	}
	/*Gửi tin nhắn đến một IP*/
	public String sendMail(PlayerInfo receiver,String title ,String c) throws InterruptedException{// thư này là bất kì, ko bao gồm lệnh "connect"
		sendMailThread sender = new sendMailThread();
		sender.setReceiver(receiver);
		sender.setTitle(title);
		sender.setContent(c);
		sender.start();
		
		//Thread.sleep(300);
		return "";
	}
	/*Lấy toàn bộ mail trong hàng đợi và xóa toàn bộ mail*/
	public static ArrayList<Mail> getAllMails() {
		// chưa xóa mail
		return mails;
	}
	
	/*Nhận mail đầu tiên trong hàng đợi từ player được chỉ định và xóa mail đó đi*/
	public Mail receiveFirstMailFrom(String IPsender) throws InterruptedException{
		
		for(int i=0; i<mails.size(); i++){
			Mail m = mails.get(i);
			if(m.getSender().getIP().equals(IPsender)){ // kiểm tra IP của người gửi
				mails.remove(i);
				return m;
			}
		}
		Thread.sleep(100);
		return null;
	}
	
	
	private static void detectOthers() throws InterruptedException, UnknownHostException {
		String semiIP="";
		InetAddress localhost = InetAddress.getLocalHost();
	    byte[] ip = localhost.getAddress();
	    
	    for(int i=0;i<3;i++){
	    	int t = (int) ip[i];
	    	if(t<0) t+=256;
	    	semiIP+=String.valueOf(t)+".";
	    }
	    //System.out.println(semiIP);
	    
		for (int i = 1; i <= 254; i++)
	    {
			if(ip[3] == i || ip[3]+256 == i) continue; // bỏ qua giá trị IP trùng với máy
			String fullIP=semiIP + String.valueOf(i);
			detectAnotherServerThread client = new detectAnotherServerThread();
			client.setIP(fullIP);
			client.start();	
		}
	}

	private void createServer() {
		createServerThread newserver = new createServerThread();
		newserver.setUsername("May HP");
		newserver.start();
	}
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		/*network newnet = new network();
		
		//while(true){// Tìm kiếm danh sách các người chơi đang tồn tại
		ArrayList<PlayerInfo> currentplayers = getListPlayers();
		
		System.out.println(currentplayers.size());
		
		for(PlayerInfo item:currentplayers){
			System.out.println(item.getIP()+":" +item.getName());
		}
		Thread.sleep(10*1000);
		//}
		// gửi tin nhắn "hello" đi đến các server khác
		for(PlayerInfo item:currentplayers){
			newnet.sendMail(item, "message","Hello");
		}
		
		
		for(PlayerInfo item:currentplayers){
			Mail m = newnet.receiveFirstMailFrom(item.getIP());
			if(null == m)  continue;
			System.out.println(m.getSender().getIP());
			System.out.println(m.getTitle());
			System.out.println(m.getContent());
		}
		*/
		
	}

	
	
	
	
	
	public static void addIP(String output) throws InterruptedException {
		synchronized (ListIP) {
			if(ListIP.contains(output)) return ;// nếu có rồi thì không thêm nữa
			ListIP.add(output);
		}	
	}

	public static ArrayList<String> getListIP() {
		return ListIP;
	}

	public static void setListIP(ArrayList<String> listIP) {
		ListIP = listIP;
	}

	public static void setListPlayers(ArrayList<PlayerInfo> listPlayers) throws InterruptedException {
		ListPlayers = listPlayers;
	}
	public static void addNewPlayer(PlayerInfo newplayer){
		synchronized (ListPlayers) {
			for(PlayerInfo item:ListPlayers){
				if(item.getIP().equals(newplayer.getIP()))
						return;
			}
			ListPlayers.add(newplayer);
		}
		
	}
	

	public static void setMails(ArrayList<Mail> mails) {
		network.mails = mails;
	}
	public static void addMail(Mail newmail){
		synchronized (mails) {
			//System.out.println("Tang 1");
			mails.add(newmail);
		}
	}
}
