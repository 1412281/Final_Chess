package chess.network.Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import chess.network.Entities.PlayerInfo;
import chess.network.main.network;

public class detectAnotherServerThread extends Thread {
	private String IP ;
	final static int PORT = 9999;
	@SuppressWarnings("resource")
	@Override
	public void run() {
		Socket socketClient = null;
		BufferedReader is = null;
		BufferedWriter os = null;
		//System.out.println(username+" đang tìm "+IP);	
		try{
				// send connect	
				socketClient = new Socket(IP, PORT);
				os = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
				is = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				
				// send connect request
				os.write("connect");
				os.newLine();
				os.flush();
				
				sleep(100);
				//Nhận về tin nhắn username của server thì lưu lại thông tin server này
				String line = is.readLine(); // line lưu username
				// cập nhật IP này vào danh sách, cùng với username
				network.addIP(IP);
				network.addNewPlayer(new PlayerInfo(IP,line));
				// xong nhiệm vụ của Thread này
				is.close();
				os.close();
				socketClient.close();
				
				return;
			} catch(UnknownHostException e){
				return;
				
			} catch (IOException e) {
				return;
				
			} catch (InterruptedException e) {
				return;
			}
	}

	public String getIP() {
		return IP;
	}



	public void setIP(String iP) {
		IP = iP;
	}


	
	
}
