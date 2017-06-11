package chess.network.Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*Server được khởi tạo và lắng nghe các yêu cầu kết nối tới
 * */

import chess.network.Entities.Mail;
import chess.network.Entities.PlayerInfo;
import chess.network.main.network;

public class createServerThread extends Thread{
	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@SuppressWarnings("resource")
	@Override
	public void run() {
		
		
		try {
			ServerSocket listener = null;
			Socket socketServer = null;
			System.out.println("Open a ServerSocket");
			listener = new ServerSocket(9999);
			do{	
				BufferedReader is;
				BufferedWriter os;
				socketServer = listener.accept();// chờ và chấp nhận 1 kết nối
				// open IOStream
				is = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
				os = new BufferedWriter(new OutputStreamWriter(socketServer.getOutputStream()));
				String IPsender = socketServer.getInetAddress().getHostAddress();
				//sau khi kết nối thì nhận ngay thông tin yêu cầu của client
				String line = is.readLine();
				switch (line) {
				case "connect":
					{ // gửi username của server đến cho client biết là server tồn tại
						os.write(username);
						os.newLine();
						os.flush();
					}
					break;
				case "message":
					{// gửi ACK sẵn sàng nhận message
						os.write("ACK");
						os.newLine();
						os.flush();
						// nhận lại message
						sleep(100);
						line = is.readLine();
						System.out.print(line);
						// đóng gói thư và đưa vào hàng đợi
						network.addMail(new Mail(new PlayerInfo(IPsender), "message",line));
					}
					break;
				default:
					break;
				}	
				is.close();
				os.close();
			}
			while (true);
			//System.out.print("Server stopped!");
		}
		catch (Exception e) {
	
		}
	}
	
}
