package chess.duphong.singleplay;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chess.duphong.start.startgame;

import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Color;

public class singleplay extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					singleplay frame = new singleplay();
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
	public singleplay() {
		
	    
	    
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 501);
		
		JPanel panel = new Surface();
		
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
			public void keyPressed(KeyEvent e) {
				dispose();
				startgame start = new startgame();
				start.setVisible(true);
			}
		});
		
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				int x = e.getX()/(panel.getWidth()/8);// lấy tọa độ x
				int y =7-e.getY() /(panel.getHeight()/8); // lấy tọa độ Y từ dưới lên 
				
				// gọi hàm Highlight vị trí đang chọn
				((Surface) panel).highlightSelected(x,y);
				
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
