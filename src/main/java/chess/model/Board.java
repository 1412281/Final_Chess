package chess.model;

import java.util.List;

public class Board {
	private List<Chess> listBlack;
	private List<Chess> listWhile;
	
	public Board() {
		
	}
	
	public void choose(Chess chess) {
		
	}
	
	public boolean checkWin() {
		return false;
	}
	
	public boolean checkPosibleMoveBlack(){
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
	
	public List<Chess> getListWhile() {
		return listWhile;
	}
	
	public void setListWhile(List<Chess> listWhile) {
		this.listWhile = listWhile;
	}
}
