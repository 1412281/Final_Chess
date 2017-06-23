package chess.duphong.singleplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import chess.controller.PlayController;
import chess.duphong.multiplay.listPlayer;
import chess.model.AI;
import chess.model.Chess;
import chess.model.ChessBishop;
import chess.model.ChessKing;
import chess.model.ChessKnight;
import chess.model.ChessPawn;
import chess.model.ChessQueen;
import chess.model.ChessRook;
import chess.model.Move;
import chess.model.Point;
import chess.model.Square;
import chess.network.Entities.Mail;
import chess.network.Entities.PlayerInfo;
import chess.model.Chess.Team;


/*Sureface đảm nhiệm vai trò vẽ toàn bộ bàn cờ trong suốt trò chơi
 * 
 * */
public class Surface extends JPanel {
	 

	
	//Các Image xuất hiện trong bàn cờ
	private BufferedImage banco;
	private BufferedImage Squarechon;
	private BufferedImage Squaregoiy;
	// 2 Hash Map dùng để lấy hình ảnh quân cờ , dựa theo kí hiệu của FEN
	private HashMap<String,BufferedImage> blackImages = new HashMap<>();
	private HashMap<String,BufferedImage> whiteImages = new HashMap<>();
	chess.controller.PlayController player =new PlayController();
	
	// các bien phuc vu viec di chuyen co
	List<Point> listposible= new ArrayList<Point>();
	int Xchon,Ychon,Xdi,Ydi ;
	boolean dangchon = false;
	boolean dangdi = false;
	boolean choionline = false;
	private String playerName = "";
	private String enemyName = "";
	private Team yourTeam = Team.WHITE; // mặc định là team white, nếu khởi tạo đi sau thì team BLACK
	private chess.model.AI computer = null;
	
	// hàm khởi tạo cho chế độ chơi qua LAN
	public Surface(Team yourteam, String playername,String enemyname){
		super();
		choionline = true;
		
		yourTeam = yourteam;
		playerName= playername;
		enemyName = enemyname;
	}
	
	// hàm khởi tạo máy khi chơi với máy
	public Surface(int level) {
		super();
		
		computer = new AI(level, Team.BLACK, player);
	}
	
	
	private void doDrawing(Graphics g) {
		Square[][] currentMatrix = player.getBoardSquare();
		LoadAllBoard();
		Graphics2D g2d = (Graphics2D) g;
		// Vẽ theo thứ tự : Bàn cờ -> Ô highlight -> quân cờ ->Nước đi gợi ý
		// Vẽ bàn cờ
		g2d.drawImage(banco,0,0,this.getWidth(),this.getHeight(),null,this);
		
        // vẽ ô highlight
		if(dangchon) {// nếu có ai chọn thì vẽ highlight ô đó lên, kích thước bằng kích thước 1 ô
			Chess temp= currentMatrix[Xchon][Ychon].getChess();
			if(temp != null ){// nếu phần tử này có cờ 
        	g2d.drawImage(Squarechon, this.getWidth()/8*Xchon,  this.getHeight()/8*(7-Ychon), 
        			this.getWidth()/8,this.getHeight()/8 , null,this);
			}
        }
		
		
        //vẽ các quân cờ trong Board
        // duyệt từng phần tử trong ma trận lấy ra
		for(int i = 0;i<8;i++){
			for(int j=0;j<8;j++ ){
				Chess temp= currentMatrix[i][j].getChess();
				if(temp != null ){// nếu phần tử này có cờ 
					// thì lấy hình ảnh tương tự và vẽ lên đúng vị trí [i][j] này
					// sử dụng hàm vẽ định nghĩa sẵn			
					String character = "";
					if(Team.BLACK == temp.getTeam()){// nếu là quân đen
						if(ChessBishop.class == temp.getClass()) {
							character = "b";
						}
						if(ChessKing.class == temp.getClass()) {
							character = "k";
						}
						if(ChessPawn.class == temp.getClass()) {
							character = "p";
						}
						if(ChessQueen.class == temp.getClass()) {
							character = "q";
						}
						if(ChessKnight.class == temp.getClass()) {
							character = "n";
						}
						if(ChessRook.class == temp.getClass()) {
							character = "r";
						}
						drawChessAt(g2d,blackImages.get(character),i,j);
					}
					else{//  nếu là quân trắng
						if(ChessBishop.class== temp.getClass()) {
							character = "B";
						}
						if(ChessKing.class== temp.getClass()) {
							character = "K";
						}
						if(ChessPawn.class== temp.getClass()) {
							character = "P";
						}
						if(ChessQueen.class== temp.getClass()) {
							character = "Q";
						}
						if(ChessKnight.class== temp.getClass()) {
							character = "N";
						}
						if(ChessRook.class== temp.getClass()) {
							character = "R";
						}
						drawChessAt(g2d,whiteImages.get(character),i,j);
					}
					
				}
			}
		}
		// vẽ lên các vị trí gợi ý nếu có 
		if(dangchon) {// nếu có ô nào được chọn thì lấy danh sách các ô gợi ý
			Chess temp= currentMatrix[Xchon][Ychon].getChess();
			if(temp != null ){// nếu phần tử này có cờ
			// kiểm tra lượt đi
				if(temp.getTeam() == player.getTeamTurn() && player.getTeamTurn() == yourTeam ){
					 listposible=player.getListPosibleMoveFrom(new Point(Xchon, Ychon));
					 for(Point item:listposible){
						 drawHintAt(g2d,Squaregoiy,item.getX(),item.getY());
					 }
				}
			}
	    }
		g2d.setColor(Color.RED);
		g2d.drawString("Press ESC to exit",this.getWidth()/3, this.getHeight()/2);
    }
 
	private void drawHintAt(Graphics2D g2d, BufferedImage squaregoiy, int x, int y) {
		g2d.drawImage(squaregoiy,
    			x*this.getWidth()/8, (7-y)*this.getHeight()/8, // ánh xạ đến vị trí hợp lệ
    			this.getWidth()/8/4 ,this.getHeight()/8/4, // kích thước bằng 1/4 ô
    			null, this);
	}

	// hàm vẽ 1 quân cờ tại một tọa độ 
    private void drawChessAt(Graphics2D g2d, BufferedImage bufferedImage, int x, int y) {
			
    	g2d.drawImage(bufferedImage,
    			x*this.getWidth()/8, (7-y)*this.getHeight()/8, // ánh xạ đến vị trí hợp lệ
    			this.getWidth()/8 ,this.getHeight()/8, // kích thước bằng 1 ô
    			null, this);
		
	}

	private void LoadAllBoard() {
    	try {
    		// Tải 3 hình sử dụng chung
			banco = ImageIO.read(new FileInputStream("res/board/1.png"));
			Squarechon = ImageIO.read(new FileInputStream("res/square/sel_square.png"));
			Squaregoiy = ImageIO.read(new FileInputStream("res/square/able_square.png"));
			
			//tải danh sách các hình sử dụng riêng cho từng phe,mỗi phe 6 loại quân cờ
			BufferedImage bi;
			// quân đen
			bi = ImageIO.read(new FileInputStream("res/chess/blackBishop.png"));
			blackImages.put("b", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/blackKing.png"));
			blackImages.put("k", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/blackKnight.png"));
			blackImages.put("n", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/blackPawn.png"));
			blackImages.put("p", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/blackQueen.png"));
			blackImages.put("q", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/blackRook.png"));
			blackImages.put("r", bi);
			
			
			// quân trắng 
			bi = ImageIO.read(new FileInputStream("res/chess/whiteBishop.png"));
			whiteImages.put("B", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/whiteKing.png"));
			whiteImages.put("K", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/whiteKnight.png"));
			whiteImages.put("N", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/whitePawn.png"));
			whiteImages.put("P", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/whiteQueen.png"));
			whiteImages.put("Q", bi);
			
			bi = ImageIO.read(new FileInputStream("res/chess/whiteRook.png"));
			whiteImages.put("R", bi);
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
    public void paintComponent(Graphics g) {
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(choionline){// nếu đang chơi online
					// kiểm tra xem có MOVE từ đối phương hay không
					Move newmove = listPlayer.getEnemymove();
					if(newmove != null){
						player.sendMove(newmove);
					}
				}
				
				
				
				repaint();
			}
		});		
		timer.start();
        super.paintComponent(g);
        doDrawing(g);
    }
    
    public void highlightSelected(int x,int y) throws InterruptedException{ // hiển thị ô đang chọn
    	boolean contains = false;
    	for(Point item:listposible){
    		if(x == item.getX() && y == item.getY()) {
    			contains = true;
    			break;
    		}
    	}
    	// nếu điểm đó đang chọn là một ô khác, không nằm trong danh sách gợi ý 
    	// 
    	if(!contains){
    	
	    	dangchon = true;
	    	dangdi = false;
	    	Xchon = x;
	    	Ychon = y;
	    
    	}
    	else{ //  nguoc lai thi thuc hien nuoc di 
    		dangchon = false;
    		dangdi = true;
    		Xdi = x;
    		Ydi = y;
    		
    		System.out.println("WHITE:");
    		
    		player.sendMove(new Move(new Point(Xchon, Ychon), new Point(Xdi, Ydi)));
    		
    		
    		
    		
    		
    		if(choionline == false){// nếu đang chơi một mình thì cho máy đi
    			AITakeMoveThread thread = new AITakeMoveThread(computer);
    			thread.start();
    			System.out.println(player.getBoard().getFullMove());
    			
    		}
    		else{// nếu đang chơi online thì gửi nước đi và chờ nhận lại nước đi của người chơi
    			
    			// gửi nước đi đến hàng đợi 1 phần tử của listPlayer, nơi đang giữ network
    			Mail myMOVEmail = new Mail(new PlayerInfo(enemyName), "MOVE", 
    					new Move(new Point(Xchon, Ychon), new Point(Xdi, Ydi)).toString()); 
    			
    			listPlayer.setSentMOVEmail(myMOVEmail);
    			// chờ nhận lại nước đi thông qua timer đã định nghĩa phía trên
    			// nếu nước đi mới == null thì không làm gì, nếu có thì cho playcontroller điểu khiển
    			
    		}
    		
    		
    	}
    	listposible.clear();
    	
    	repaint();
    }

	


    
    
    
}
 

