package chess.duphong.singleplay;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chess.duphong.multiplay.listPlayer;
import chess.duphong.start.startgame;
import chess.model.Chess.Team;

import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Color;

public class playgame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playgame frame = new playgame(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	// hàm constructor cho người chơi qua LAN
	public playgame(int level) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 501);
		setLocationRelativeTo(null);
		JPanel panel = new Surface(level);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){ // khi nhấn phím ESC  
					// hỏi người chơi có muốn thoát hay không
					int reply = JOptionPane.showConfirmDialog(null,
							"Do you want to QUIT game? ", "Quit", JOptionPane.YES_NO_OPTION);
					 if (reply == JOptionPane.YES_OPTION) {// nếu đồng ý thoát
						 if(Surface.isChoionline()){// nếu đang chơi online thì gửi cho đối phương biết
							 // gửi Mail Quit 
							 listPlayer.setSentQUIT(true);
						 }
						 dispose();
					 }
					//startgame start = new startgame();
					//start.setVisible(true);
			    }
			}
		});
		
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				int x = e.getX()/(panel.getWidth()/8);// lấy tọa độ x
				int y =7-e.getY() /(panel.getHeight()/8); // lấy tọa độ Y từ dưới lên 
				
				// gọi hàm Highlight vị trí đang chọn
				try {
					((Surface) panel).highlightSelected(x,y);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
			
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	    
		 this.setResizable(false);
		 this.setVisible(true);
	}

	
	
	// hàm constructor cho người chơi đơn
	public playgame(Team playerteam,String playername,String enemyname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 501);
		setLocationRelativeTo(null);
		JPanel panel = new Surface(playerteam,playername,enemyname);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){ // khi nhấn phím ESC  
					dispose();
					startgame start = new startgame();
					start.setVisible(true);
			    }
			}
		});
		
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				int x = e.getX()/(panel.getWidth()/8);// lấy tọa độ x
				int y =7-e.getY() /(panel.getHeight()/8); // lấy tọa độ Y từ dưới lên 
				
				// gọi hàm Highlight vị trí đang chọn
				try {
					((Surface) panel).highlightSelected(x,y);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
			
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	    
		 this.setResizable(false);
		 this.setVisible(true);
	}

	
	

}
