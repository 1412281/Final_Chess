package chess.model;

import java.util.ArrayList;
import java.util.List;

import chess.model.Chess.Team;

public class Board {

	private static Square[][] square = new Square[8][8];

	public Square[][] getBoard() {
		return square.clone();
	}

	public Board() {
		initChess();

	}

	private void initChess() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				square[i][j] = new Square();
			}
		// khá»Ÿi táº¡o quĂ¢n TRáº®NG
		// khá»Ÿi táº¡o 8 tá»‘t
		for (int i = 0; i < 8; i++) {
			square[i][1].setChess(new ChessPawn(Team.WHITE));
		}
		// khá»Ÿi táº¡o 2 xe
		square[0][0].setChess(new ChessRook(Team.WHITE));
		square[7][0].setChess(new ChessRook(Team.WHITE));
		// khá»Ÿi táº¡o 2 ngá»±a
		square[1][0].setChess(new ChessKnight(Team.WHITE));
		square[6][0].setChess(new ChessKnight(Team.WHITE));
		// khá»Ÿi táº¡o 2 bá»“
		square[2][0].setChess(new ChessBishop(Team.WHITE));
		square[5][0].setChess(new ChessBishop(Team.WHITE));
		// khá»Ÿi táº¡o háº­u
		square[4][0].setChess(new ChessQueen(Team.WHITE));
		// khá»Ÿi táº¡o vua
		 square[3][0].setChess(new ChessKing(Team.WHITE));

		// khá»Ÿi táº¡o quĂ¢n Ä�EN
		// khá»Ÿi táº¡o 8 tá»‘t
		for (int i = 0; i < 8; i++) {
			square[i][6].setChess(new ChessPawn(Team.BLACK));
		}
		// khá»Ÿi táº¡o 2 xe
		square[0][7].setChess(new ChessRook(Team.BLACK));
		square[7][7].setChess(new ChessRook(Team.BLACK));
		// khá»Ÿi táº¡o 2 ngá»±a
		square[1][7].setChess(new ChessKnight(Team.BLACK));
		square[6][7].setChess(new ChessKnight(Team.BLACK));
		// khá»Ÿi táº¡o 2 bá»“
		square[2][7].setChess(new ChessBishop(Team.BLACK));
		square[5][7].setChess(new ChessBishop(Team.BLACK));
		// khá»Ÿi táº¡o háº­u
		square[4][7].setChess(new ChessQueen(Team.BLACK));
		// khá»Ÿi táº¡o vua
		square[3][7].setChess(new ChessKing(Team.BLACK));

	}

	// láº¥y danh dĂ¡ch táº¥t cáº£ quĂ¢n cá»� cĂ²n láº¡i cá»§a 1 team
	public List<Point> getListTeam(Team team) {
		List<Point> list = new ArrayList<Point>();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (square[i][j].getChess() != null)
					if (square[i][j].getChess().getTeam().equals(team))
						list.add(new Point(i, j));
			}
		return list;
	}

	
	public boolean haveNoMove(Team team) {
		List<Point> listOfTeamMembers = getListTeam(team);
		for (Point item : listOfTeamMembers) {
			if (0 == this.getListPosibleMoveFrom(item).size())
				return true;
		}
		return false;

	}

	public boolean isKing_Checkmate(Team team) {
		
		List<Point> listOfTeamMembers = getListTeam(team);
		Point King = null; // lÆ°u láº¡i vá»‹ trĂ­ quĂ¢n King
		for (Point item : listOfTeamMembers) {
			if (ChessKing.class == getChessFrom(item).getClass()) {
				King = new Point(item.getX(), item.getY());
				break;
			}
		}
		return willKing_checkmate(team, King);
	}

	
	public boolean willKing_checkmate(Team team, Point King) {
		if (King == null)
			return true;
		Team enemy = Team.WHITE;
		if (team == Team.WHITE)
			enemy = Team.BLACK;
		List<Point> listOfEnemies = getListTeam(enemy);
		for (Point item : listOfEnemies) {
			if (getChessFrom(item).getClass() == ChessKing.class)
				continue;
			List<Point> templist = getListPosibleMoveFrom(item);

			if (getChessFrom(item).getClass() == ChessPawn.class) {
				
				int Yvector = 1;
									// WHITE
				if (Team.BLACK == enemy)
					Yvector = -1;

				
				if (item.getX() + 1 == King.getX() && item.getY() + Yvector == King.getY())
					return true;
				if (item.getX() - 1 == King.getX() && item.getY() + Yvector == King.getY())
					return true;
			} else
				for (Point item2 : templist) {
					if (item2.getX() == King.getX() && item2.getY() == King.getY())
						return true;
				}
		}
		return false;
	}

	

	public void move(Point fromPoint, Point toPoint) {
		Board.square[toPoint.getX()][toPoint.getY()]
				.setChess(Board.square[fromPoint.getX()][fromPoint.getY()].getChess());
		Board.square[fromPoint.getX()][fromPoint.getY()].setChess(null);
	}
	
	public List<Point> getListPosibleMoveFrom(Point point) {
		List<Point> result = new ArrayList<Point>();
		Square square = Board.square[point.getX()][point.getY()];
		
		Chess curChess = square.getChess();
		if (null == curChess)
			return result;
		List<Point> listPoint = curChess.getPosibleMove(point);
		if (null == listPoint)
			return result;
		
		if (curChess.getClass() != ChessKnight.class) {
			listPoint = getPosiblePoints_except_Prevented(point, listPoint);
		}

		
		listPoint = getPosiblePoints_except_Allies(listPoint, square.getChess().getTeam());
		
		return listPoint;
	}

	
	private List<Point> getPosiblePoints_except_Prevented(Point fromPoint, List<Point> list) {
		Square square = Board.square[fromPoint.getX()][fromPoint.getY()];
		Chess curChess = square.getChess();

	
		List<Point> result = new ArrayList<Point>();

		
		if (curChess.getClass() == ChessKing.class) {
			for (Point point : list) {
				if (false == willKing_checkmate(curChess.getTeam(), point)) {
					result.add(point);
				}
			}
			return result;
		}
		if (curChess.getClass() == ChessPawn.class) {

			for (Point point : list) {
				if (point.getX() == fromPoint.getX()) {
					if (null == this.getChessFrom(point)) {
						result.add(point);
						continue;
					}

					if (2 == Math.abs(point.getY() - fromPoint.getY())) { 
						
						int x = point.getX();
						int y = 5;
						if (curChess.getTeam() == Team.WHITE) { 
							y = 2;
						}
						if (null == this.getChessFrom(new Point(x, y))) {
							result.add(point);
						}
					}
				} else {
					Chess Enemy = this.getChessFrom(point);
					if (null == Enemy) {
						
					} else if (Enemy.getTeam() != curChess.getTeam()) { 
						result.add(point);
					}
				}
			}
		}

		
		else {
			for (Point point : list) {

				
				if (1 == Math.abs(point.getX() - fromPoint.getX()) || 1 == Math.abs(point.getY() - fromPoint.getY())) {
					result.add(point);
					continue;
				}
				
				int Xvector = 0;
				int Yvector = 0;
				if (fromPoint.getX() != point.getX()) {
					Xvector = (point.getX() - fromPoint.getX()) / Math.abs(point.getX() - fromPoint.getX());
				}
				if (fromPoint.getY() != point.getY()) {
					Yvector = (point.getY() - fromPoint.getY()) / Math.abs(point.getY() - fromPoint.getY());
				}

				int x = fromPoint.getX() + Xvector;
				int y = fromPoint.getY() + Yvector;

				
				while (!(x == point.getX() && y == point.getY())) {
					Point tempPoint = new Point(x, y);
					if (null == this.getChessFrom(tempPoint)) {
						x = x + Xvector;
						y = y + Yvector;
					} else {
						break;
					}
				}
				if (x == point.getX() && y == point.getY()) { 
					result.add(point);
				}
			}
		}
		return result;
	}

	
	private List<Point> getPosiblePoints_except_Allies(List<Point> listPoint, Team team) {
		List<Point> result = new ArrayList<Point>();
		for (Point point : listPoint) {
			Chess chess = this.getChessFrom(point); 
			if (null != chess) { 
				if (!chess.getTeam().equals(team)) { 
					result.add(point);
				}
			} else {
				result.add(point);
			}

		}
		return result;
	}

	private Chess getChessFrom(Point point) {
		return square[point.getX()][point.getY()].getChess();
	}

	public Team checkWin() {
		Team team = Team.WHITE;

		if (isKing_Checkmate(team) && haveNoMove(team))
			return team;
		
		team = Team.BLACK;
		if (isKing_Checkmate(team) && haveNoMove(team))
			return team;

		return null;
	}

	public static void main(String[] args) {
		Board newboard = new Board();
		Point point = new Point(3, 3);
		System.out.println(point.toString());
		square[point.getX()][point.getY()].setChess(new ChessKing(Team.BLACK));

		List<Point> list = newboard.getListPosibleMoveFrom(point);
		System.out.println(list.size());
		for (Point point1 : list) {
			System.out.println(point1.toString());
		}

		System.out.println(newboard.isKing_Checkmate(Team.BLACK));
		System.out.println(newboard.checkWin());
	}

}
