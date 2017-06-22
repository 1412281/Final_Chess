package chess.duphong.singleplay;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chess.duphong.start.startgame;

import javax.swing.JRadioButton;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

public class selectlevel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			selectlevel dialog = new selectlevel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public selectlevel() {
		setLocationRelativeTo(null);
		setBounds(100, 100, 376, 250);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_2, BorderLayout.WEST);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		JButton btnNewButton_1 = new JButton("Easy");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playgame s= new playgame(1);
				dispose();
				s.setVisible(true);
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Normal");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playgame s= new playgame(3);
				dispose();
				s.setVisible(true);
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		contentPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Hard");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playgame s= new playgame(5);
				dispose();
				s.setVisible(true);
			}
		});
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		contentPanel.add(btnNewButton_3);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startgame s = new startgame();
				dispose();
				s.setVisible(true);
				
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnNewButton);
		{
		}
		
		
	}

}
