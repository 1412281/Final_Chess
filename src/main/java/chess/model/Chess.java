package chess.model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public abstract class Chess {
	static final int BOARD_MIN = 0;
	static final int BOARD_MAX = 7;
	
	public enum Team {
		BLACK, WHITE	
	}
	
	protected Team team;
	
	public Chess() {
		
	}
	
	public Chess(Team team) {
		this.team = team;
	}
	
	public abstract List<Point> getPosibleMove(Point point);
	
	/*Hàm này áp dụng cho các quân cờ mỗi hướng có 1 lựa chọn.*/ 
	protected Collection<? extends Point> couldMoveToward(Point Pfrom, int Xdirection, int Ydirection) {
		Point temp= new Point(Pfrom);
		ArrayList<Point> list = new ArrayList<Point>();
		/*Kiểm tra direction*/	
			int x= temp.getX()+ Xdirection;
			int y= temp.getY()+ Ydirection;
			/*nếu Point mới nằm trong bàn cờ thì lấy Point đó*/
			if((BOARD_MIN<=x && x<= BOARD_MAX) && ((BOARD_MIN<=y && y<= BOARD_MAX)))
			{
				list.add(new Point(x, y));
				// tiến thêm 1 bước
				temp.setX(x);
				temp.setY(y);
			}
			else {
				return list;
			}
			
			return list;
	}
	
	/* Đối với các quân cờ mỗi hướng có nhiều lựa chọn ta dùng hàm này */
	protected List<Point> couldMoveToward_ManyChoices(Point Pfrom,int Xdirection, int Ydirection){
		Point temp= new Point(Pfrom);
		ArrayList<Point> list = new ArrayList<Point>();
		/*Kiểm tra direction*/
		while(true){
			int x= temp.getX()+ Xdirection;
			int y= temp.getY()+ Ydirection;
			/*nếu Point mới nằm trong bàn cờ thì lấy Point đó*/
			if((BOARD_MIN<=x && x<= BOARD_MAX) && ((BOARD_MIN<=y && y<= BOARD_MAX)))
			{
				list.add(new Point(x, y));
				// tiến thêm 1 bước thoe hướng này
				temp.setX(x);
				temp.setY(y);
			}
			else {
				return list;
			}
		}
	}
		
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public abstract String toString();
}

