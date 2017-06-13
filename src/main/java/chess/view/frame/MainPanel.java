package chess.view.frame;

import chess.controller.*;
import chess.model.*;
import chess.network.*;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.*;
import java.lang.String;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.net.*;


public class MainPanel extends JPanel {
	
	//private Player P1 = new Player();
	//private Player P2 = new Player();
    private final int Divide=600/8;
    private  int  move =0;
    private Rectangle2D rec;
    private  short players_turn=1;
    public  final ToolPanel myTool;
    private  final StatusPanel myStatus;
    private  boolean GameOver=false;
    private boolean Iam_Server=false;
    private boolean Iam_Client=false;
    private ServerSocket ServerSock;
    private Socket Sock;
    private BufferedReader in;
    private PrintWriter out;
    private String Box;
    private boolean local=true;
    private JButton startServer;
    private JButton startClient;
    private String MyIp_Address;
    private String MyPort_number;
    private boolean Game_started=true;
    private ChatPanel Refe_Chat;
    
    public void start_Again() {
    	/*
        P1=new player1();
        P2=new player2();*/
        move =0;
        players_turn=1;
        GameOver=false;
        local=true;
        //myTool.start_Again();
        myStatus.start_Again();
        Iam_Server=false;
        Iam_Client=false;
        repaint();
        
    }
    
    
    public MainPanel(ToolPanel myToolPanel,StatusPanel myStatusPanel) {
        setBackground(Color.WHITE);
        
        setSize(600,600);
        setLocation(3,10);
        
        MousewhenMove mouseDragAndDrop=new MousewhenMove();
        Mousehere    mouseHereEvent=new Mousehere();
        addMouseMotionListener(mouseDragAndDrop);
        addMouseListener(mouseHereEvent);
        
        myTool=myToolPanel;
        myStatus=myStatusPanel;
        setLayout(null); 
    }
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
           
        Graphics2D g2 = (Graphics2D)g;
        
        int iWidth = 600;
        int iHeight = 600;
        
        
        // Drawing the board
        for (int i=0; i<8; i=i+2) {
            for (int j=0; j<8; j=j+2) {
                
                g2.setColor(Color.BLUE);
                rec=new Rectangle2D.Double(j*iWidth/8,(1+i)*iWidth/8,Divide,Divide);
                g2.fill(rec);
                rec=new Rectangle2D.Double((1+j)*iWidth/8,i*iWidth/8,Divide,Divide);
                g2.fill(rec);
                
            }
        }
        
        /// Puting the pieces
        Point postionPoint;
        int postX;
        int postY;
        Image img;
        /*
        for (int i = 1; i <= 32; i++) {
            if(i<17) {
                if(i==P2.GetInhand()) {
                    postionPoint=P2.getPixelPoint(i);
                    
                } else {
                    postionPoint=P2.returnPostion(i);   }
                img=P2.returnIconImage(i);
                
            }
            
            else {
                
                
                if(i==P1.GetInhand()) {
                    
                    postionPoint=P1.getPixelPoint(i);
                    
                    
                } else {
                    postionPoint=P1.returnPostion(i);   }
                img=P1.returnIconImage(i);
            }
            
            
            if(i==P1.GetInhand())
                g2.drawImage(img,postionPoint.x-25,postionPoint.y-25,Divide-40,Divide-12 ,this);
            else if(i==P2.GetInhand())
                g2.drawImage(img,postionPoint.x-25,postionPoint.y-25,Divide-40,Divide-12 ,this);
            else {
                postX=rowToX(postionPoint.x);
                postY=colToY(postionPoint.y);
                g2.drawImage(img,postX+20,postY+4,Divide-40,Divide-12 ,this);
            }
           
        }     */    
    }
    
    /// You can inherit from Adapter and avoid meaningless
    private class  Mousehere implements MouseListener {
        
        public void mouseClicked(MouseEvent e) {
            
            
        }
        
        public void mousePressed(MouseEvent e) {
            
            
        }
              
        
        public void mouseEntered(MouseEvent e) {
            
        }
        
        public void mouseExited(MouseEvent e) {
            
        }

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }

    private class MousewhenMove implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {

        }
        public void mouseMoved(MouseEvent e) {
            
        }
    }
      
    
    private void CheckStatus() {
        if(players_turn==1) {
            
            players_turn=2;
            //myTool.add_to_History("White : "+P1.Tell_me_About_last_move());
        } else if(players_turn==2) {
            
            players_turn=1;
            //myTool.add_to_History("Black : "+P2.Tell_me_About_last_move());
        }
        
        
        myStatus.changeStatus(" Check! ");
    }
    
    
    private void GameOver() {
        
        myStatus.changeStatus(" Check Mate! ");
        
        
        
        GameOver=true;
    }
    
    
    public void Send_move() {
        out.print(Box);
        out.print("\r\n");
        out.flush();
        
    } 
}