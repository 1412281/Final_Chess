package chess.network.Entities;
/*Class lưu thông tin thông điệp mà Server lắng nghe được
 * */
public class Mail {
	private PlayerInfo sender; // người gửi
	private String Title; // Chủ đề, loại thông tin
	private String content; // nội dung thông tin
	public PlayerInfo getSender() {
		return sender;
	}
	public void setSender(PlayerInfo sender) {
		this.sender = sender;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Mail() {
		super();
	}
	public Mail(PlayerInfo sender, String title, String content) {
		super();
		this.sender = sender;
		Title = title;
		this.content = content;
	}
	
	
}
