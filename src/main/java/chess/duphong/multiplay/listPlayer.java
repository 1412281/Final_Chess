package chess.duphong.multiplay;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import chess.controller.NetworkController;
import chess.duphong.singleplay.Surface;
import chess.duphong.singleplay.playgame;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chess.model.*;
import chess.model.Chess.Team;
import chess.network.Entities.Mail;
import chess.network.Entities.PlayerInfo;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class listPlayer extends JFrame {
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listPlayer frame = new listPlayer();
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
	public listPlayer() {
		setTitle("List Playsers");
		try {
			net = new NetworkController();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		@SuppressWarnings("rawtypes")
		DefaultListModel model = new DefaultListModel();
		
		JList list = new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(model.isEmpty()) return;
				if(null == list.getSelectedValue()) return;
				String selected = list.getSelectedValue().toString();
				if(selected == null ) return;
				
				//gửi offer đến người chơi đã chọn
				try {
					net.sendOffer(new PlayerInfo(selected));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					int reply = JOptionPane.showConfirmDialog(null,
							"Khong gui duoc loi moi", "Loi", JOptionPane.CANCEL_OPTION);
				}
				
				
			}
		});
		contentPane.add(list, BorderLayout.CENTER);
		
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				try {
					model.clear();
					listnguoichoi = net.getListPlayers();
					for(PlayerInfo item:listnguoichoi){
						model.addElement(item.getIP());
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnReload);
		
		
		Timer timercheckOffer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				if(isBusy){ // xét trong lúc vô trận
					
	   /// QUÁ TRÌNH NHẬN THÔNG TIN KHI TRONG TRẬN
					// check hòm thư để xem có MOVE nào không
					//Mail MOVEmail =null;
					Mail MOVEmail = net.getMail_byTitle_IfExist("MOVE");
					if(MOVEmail != null ){// nếu có thư nào là nước đi mới thì đưa vào hòm thư cục bộ 
						Move newmove = new Move(MOVEmail.getContent()); // lấy nội dung và ép kiểu
						listPlayer.setEnemymove(newmove);
							
					}
					
					Mail QUITmail = net.getMail_byTitle_IfExist("QUIT");
					if(QUITmail != null ){// nếu có thư nào là nước đi mới thì đưa vào hòm thư cục bộ 
						setReceiveQUIT(true);
						
					}
					
					
					
		/// QUÁ TRÌNH KIỂM TRA VÀ GỬI THÔNG TIN KHI TRONG TRẬN
					// check hàng đợi gửi thư xem có MOVE nào cần gửi không
					//Mail sentMOVEmail = null;
					Mail sentmail = getSentMOVEmail();
					if(sentmail != null ){// nếu có thư nào là nước đi mới thì gửi đi
							try {
								net.sendMail(sentmail);
								
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					
					
					
					if(sentQUIT == true ){//nếu mình tự thoát thì gửi thông báo cho đối phương
						try {
							net.sendMail(new Mail(enemy, "QUIT",""));
							System.out.println("da gui QUIT"+enemy.getIP());
							Thread.sleep(2000);
							
							
							//System.exit(DISPOSE_ON_CLOSE);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
					}
					
				}
				
				
				
				
				
				
				if(isBusy == true) return; // nếu đã vô trận thì ko làm gì tiếp theo nữa
				Mail offermail = net.getMail_byTitle_IfExist("offer");
				if(offermail != null ){// nếu có thư thì thông báo lên người dùng
					int reply = JOptionPane.showConfirmDialog(null,
							offermail.getSender().getIP(), "Offer", JOptionPane.YES_NO_OPTION);
					 if (reply == JOptionPane.YES_OPTION) {// nếu accept thì gửi accept cho người kia 
						 // và bắt đầu ván đấu, bên nhận là BLACK
						 isBusy = true;
							 try {
								net.sendAccept(offermail.getSender());
								enemy = offermail.getSender();
								playgame game = new playgame(Team.BLACK, "Your Name",offermail.getSender().getIP());
								game.setVisible(true);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        }
				        else {// từ chối thì không làm gì cả
				          
				        }
				}
				
				// check hòm thư xem có Accept nào không
				Mail acceptmail = net.getMail_byTitle_IfExist("accept");
				if(acceptmail != null ){// nếu có accept thì vô trận ngay
					// đổi trạng thái 
					isBusy = true;
					enemy = acceptmail.getSender();
					playgame game = new playgame(Team.WHITE, "Your Name",acceptmail.getSender().getIP());
					game.setVisible(true);
				}

			}
		});
		timercheckOffer.start();
	}
	
	
	
	public static Mail getSentMOVEmail() {
		// lấy ra để gửi và xóa ngay thư này, để thư khác đến
		Mail result = null;
		result = sentMOVEmail;
		setSentMOVEmail(null);
		return result;
		
	}

	public static void setSentMOVEmail(Mail sentMOVEmail) {
		listPlayer.sentMOVEmail = sentMOVEmail;
	}
	public static Move getEnemymove() {
		// lấy ra và xóa thư đó đi
		Move result = null;
		result = enemymove;
		setEnemymove(null);
		return result;
	}

	public static void setEnemymove(Move enemymove) {
		listPlayer.enemymove = enemymove;
	}


	public static boolean isSentQUIT() {
		return sentQUIT;
	}

	public static void setSentQUIT(boolean sentQUIT) {
		listPlayer.sentQUIT = sentQUIT;
	}


	public static boolean isReceiveQUIT() {
		return receiveQUIT;
	}

	public static void setReceiveQUIT(boolean receiveQUIT) {
		listPlayer.receiveQUIT = receiveQUIT;
	}


	private boolean isBusy = false;
	private List<PlayerInfo> listnguoichoi = null;
	private NetworkController net;
	private static Mail sentMOVEmail = null;
	private static boolean sentQUIT = false;
	private static boolean receiveQUIT = false;
	private static Move enemymove = null;
	private PlayerInfo enemy = null;
	

}
