package chess.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private List<Chess> listChess = new ArrayList<Chess>();
	private Position[][] pos = new Position[8][8];
	
	public Board() {
//		khởi tạo 64 ô
		for(int i = 1; i < 9; i++)
			for(int j = 1; j < 9; j++) {
				listPos.add(new Position(i, j));
			}
		
	}
	
	
	public List<Position> getListPosibleMove(Position pos) {
		
		List<Position> list = new Array
		
	}
	
	public boolean checkWin() {
		return false;
	}
	
	public boolean checkPosibleMoveBlack(Chess chess){
		return false; 
	}
	
	public boolean checkPosibleMoveWhite() {
		return false;
	}
	
	
	
	public List<Chess> getListBlack() {
		return listBlack;
	}
	
	public void setListBlack(List<Chess> listBlack) {
		this.listBlack = listBlack;
	}
	
	public List<Chess> getListWhite() {
		return listWhite;
	}
	
	public void setListWhite(List<Chess> listWhile) {
		this.listWhite = listWhile;
	}

	public Chess getChessInPos(Position pos) {
		for(int i = 0; i < listWhite.size(); i++)
		{
			if (listWhite.get(i).isLive() && listWhite.get(i).getPos().equal(pos))
				return listWhite.get(i);
		}
		return null;
	}
}
