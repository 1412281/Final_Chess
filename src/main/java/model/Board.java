package model;

import java.util.List;

public class Board {
	private List<Chess> listBlackChess;
	private List<Chess> listWhileChess;
	
	public boolean checkWin() {
		return false;
	}
	
	public boolean checkPosibleMove() {
		return false; 
	}
	
	
	public List<Chess> getListBlackChess() {
		return listBlackChess;
	}
	
	
	public void setListBlackChess(List<Chess> listBlackChess) {
		this.listBlackChess = listBlackChess;
	}
	
	public List<Chess> getListWhileChess() {
		return listWhileChess;
	}
	
	public void setListWhileChess(List<Chess> listWhileChess) {
		this.listWhileChess = listWhileChess;
	}
}
