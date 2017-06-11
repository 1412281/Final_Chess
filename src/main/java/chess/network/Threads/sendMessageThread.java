package chess.network.Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import chess.network.Entities.PlayerInfo;

/*Thread được tạo ra để gửi thông điệp đến người nhận là server khác đang tham gia trò chơi
 * */
public class sendMessageThread extends Thread {
	final static int PORT = 9999;
	private PlayerInfo receiver;
	private String  message;
	public PlayerInfo getReceiver() {
		return receiver;
	}
	public void setReceiver(PlayerInfo receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@SuppressWarnings("resource")
	public void run(){
		Socket socketClient = null;
		BufferedReader is = null;
		BufferedWriter os = null;
		try{
			// send connect	
			socketClient = new Socket(receiver.getIP(), PORT);
			os = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			is = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			
			// send message request
			os.write("message");
			os.newLine();
			os.flush();
			
			sleep(100);
			//Nhận về tin nhắn phản hồi của server thì gửi message đi
			String line = is.readLine(); // line lưu ACK
			switch (line) {
			case "ACK":
					{
						// gửi tin nhắn đi
						os.write(message);
						os.newLine();
						os.flush();
					}
				break;

			default:
				break;
			}
			
			
			// xong nhiệm vụ của Thread này
			is.close();
			os.close();
			socketClient.close();
			return;
		} catch(UnknownHostException e){
			//System.err.println("Wrong Host ");
			return;
			
		} catch (IOException e) {
			//System.err.println("cannot get Socket ");
			return;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
