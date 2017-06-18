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
public class sendMailThread extends Thread {
	final static int PORT = 9999;
	private PlayerInfo receiver;
	private String title;
	private String  content;
	
	public PlayerInfo getReceiver() {
		return receiver;
	}
	public void setReceiver(PlayerInfo receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return  content;
	}
	public void setContent(String c) {
		this. content = c;
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
			
			//System.out.println(title);
			// send message request
			os.write(title);
			os.newLine();
			os.flush();
			//Nhận về tin nhắn phản hồi của server thì gửi content đi
			String line = is.readLine(); // line lưu ACK
			//System.out.println(line);
			switch (line) {
			case "ACK":
					{
						// gửi tin nhắn đi
						os.write(content);
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
		} 
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
