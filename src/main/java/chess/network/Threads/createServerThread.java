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
				String line;
				line = is.readLine();
				//System.out.println(line);
				switch (line) {
				case "connect":
					{ // gửi username của server đến cho client biết là server tồn tại
						os.write(username);
						os.newLine();
						os.flush();
					}
					break;
				case "MOVE": case "QUIT": case "offer": case "accept":
					{// gửi ACK sẵn sàng nhận thông điệp có chủ đề chứa trong "line"
						String line2 ;
						os.write("ACK");
						os.newLine();
						os.flush();
						// nhận lại message
						line2 = is.readLine();
						//System.out.print(line2);
						// đóng gói thư và đưa vào hàng đợi
						network.addMail(new Mail(new PlayerInfo(IPsender), line, line2));
					}
					break;
				}
				line = null;
				is.close();
				os.close();
				//socketServer.close();
			}
			while (true);
			//System.out.print("Server stopped!");
		}
		catch (Exception e) {
	
		}
	}
	
}
