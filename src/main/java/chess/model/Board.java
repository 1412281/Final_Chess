package chess.model;

import java.util.ArrayList;
import java.util.List;

import chess.model.Chess.Team;

public class Board {

	private Square[][] square = new Square[8][8];
	private Team teamTurn = Team.WHITE;
	private Castle castle = new Castle();
	private String enPassant = "-";
	private int countFifty = 0;
	private int fullMove = 1;

	public Square[][] getBoardSquare() {
		Square[][] result = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.square[i][j] != null)
					result[i][j] = new Square(this.square[i][j].getChess());
				else {
					result[i][j] = new Square();
				}
			}
		}
		return result;
	}

	public Board() {
		
	}

	public Board(Board board) {
		this.square = board.getBoardSquare();
		this.castle = board.castle;
		this.countFifty = board.countFifty;
		this.enPassant = board.enPassant;
		this.fullMove = board.fullMove;
		this.teamTurn = board.teamTurn;
	}
	

	
	
	public List<Point> getListTeam(Team team) {
		List<Point> list = new ArrayList<Point>();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Square sq = square[i][j]; 
				if (sq.getChess() != null)
				{
					if (sq.getChess().getTeam() == team)
						list.add(new Point(i, j));
				}
			}
		return list;
	}

	public boolean haveMove(Team team) {
		List<Point> listOfTeamMembers = getListTeam(team);
		for (Point item : listOfTeamMembers) {
			if (0 != this.getListPosibleMoveFrom(item).size())
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

	public boolean hasCastle(String st) {
		switch (st) {
		case "K":
			if (this.castle.getK() && (this.square[5][0].getChess() == null) && (this.square[6][0].getChess() == null)) {
				return true;
			}
			break;
		case "Q":
			if (this.castle.getQ() && (this.square[3][0].getChess() == null) && (this.square[2][0].getChess() == null)
					&& (this.square[1][0].getChess() == null)) {
				return true;
			}
			break;
		case "k":
			if (this.castle.getk() && (this.square[5][7].getChess() == null) && (this.square[6][7].getChess() == null)) {
				return true;
			}
			break;
		case "q":
			if (this.castle.getq() && (this.square[3][7].getChess() == null) && (this.square[2][7].getChess() == null)
					&& (this.square[1][7].getChess() == null)) {
				return true;
			}
			break;
		}
		return false;
	}

	public void move(Move move) {
		nextTurn();
		
		Chess chessFrom = this.getChessFrom(move.getFrom());
		Chess chessTo = this.getChessFrom(move.getTo());
		
		
//		check castle
		if (chessFrom.getClass() == ChessKing.class) {
			
				
			if (move.getTo().equal(new Point(2, 0)) && this.hasCastle("Q")) {
				this.actionMove(new Move(new Point(0,0), new Point(3,0)));
				nextTurn();
			}
			if (move.getTo().equal(new Point(6, 0)) && this.hasCastle("K")) {
				this.actionMove(new Move(new Point(7,0), new Point(5,0)));
				nextTurn();
			}
			if (move.getTo().equal(new Point(2, 7)) && this.hasCastle("q")) {
				this.actionMove(new Move(new Point(0,7), new Point(3,7)));	
				nextTurn();
			}
			if (move.getTo().equal(new Point(6, 7)) && this.hasCastle("k")) {
				this.actionMove(new Move(new Point(7,7), new Point(5,7)));	
				nextTurn();
			}
			
			if (chessFrom.getTeam() == Team.BLACK) {
				this.castle.setk(false);
				this.castle.setq(false);
			}
			else {
				this.castle.setK(false);
				this.castle.setQ(false);
			}
				
		}
		if (chessFrom.getClass() == ChessRook.class) {

			if (move.getFrom().equal(new Point(0,0))) {
				this.castle.setQ(false);
			}
			if (move.getFrom().equal(new Point(7,0))) {
				this.castle.setK(false);
			}
			if (move.getFrom().equal(new Point(7,7))) {
				this.castle.setk(false);
			}
			if (move.getFrom().equal(new Point(0,7))) {
				this.castle.setq(false);
			}
		}
		
//		check enPassant
		if ((chessFrom.getClass() == ChessPawn.class) && (move.getTo().getY() == move.getFrom().getY() - 2)) {
			this.enPassant = move.getFrom().toString();
		}
		else {
			this.enPassant = "-";
		}
		
//		check 50 rule
		if (chessFrom.getClass() == ChessPawn.class || chessTo != null) {
			this.countFifty = 0;
		}
		else {
			this.countFifty++;
		}
		
		
		if (this.teamTurn == Team.BLACK) {
			this.fullMove++;
		}
		
//		action move
		actionMove(move);

	}
	
	private void actionMove(Move move) {
		
		Chess chessFrom = this.getChessFrom(move.getFrom());
		this.square[move.getTo().getX()][move.getTo().getY()] = new Square(chessFrom);

		this.square[move.getFrom().getX()][move.getFrom().getY()].setChess(null);
	}

	private void nextTurn() {
		if (teamTurn.equals(Team.WHITE))
			setTeamTurn(Team.BLACK);
		else {
			setTeamTurn(Team.WHITE);
		}
	}

	public List<Point> getListPosibleMoveFrom(Point point) {
		List<Point> result = new ArrayList<Point>();
		Square square = this.square[point.getX()][point.getY()];

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

		if (curChess.getClass() == ChessKing.class) {
			if (curChess.getTeam() == Team.WHITE) {
				if (this.hasCastle("K")) {
					listPoint.add(new Point(6,0));
				}
				if (this.hasCastle("Q")) {
					listPoint.add(new Point(2, 0));
				}
			}
			else {
				if (this.hasCastle("k")) {
					listPoint.add(new Point(6,7));
				}
				if (this.hasCastle("q")) {
					listPoint.add(new Point(2, 7));
				}
			}
		}
		return listPoint;
	}

	private List<Point> getPosiblePoints_except_Prevented(Point fromPoint, List<Point> list) {
		Square square = this.square[fromPoint.getX()][fromPoint.getY()];
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
				if (point.getX() == fromPoint.getX() ) {
					if (null == this.getChessFrom(point) && (2 != Math.abs(point.getY() - fromPoint.getY()))) {
						result.add(point);
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

	public List<Move> getListMoveAllTeam(Team team) {
		List<Point> listTeam = this.getListTeam(team);
		List<Move> result = new ArrayList<Move>();
		for (int i = 0; i < listTeam.size(); i++) {
			List<Point> listPoint = this.getListPosibleMoveFrom(listTeam.get(i));
			for (int j = 0; j < listPoint.size(); j++) {
				result.add(new Move(listTeam.get(i), listPoint.get(j)));
			}
		}
		return result;
	}

	private Chess getChessFrom(Point point) {
		return square[point.getX()][point.getY()].getChess();
	}

	public Team checkWin() {
		Team team = Team.WHITE;

		if (isKing_Checkmate(team) && !haveMove(team))
			return team;

		team = Team.BLACK;
		if (isKing_Checkmate(team) && !haveMove(team))
			return team;

		return null;
	}
	
	

	public Team getTeamTurn() {
		return teamTurn;
	}

	public void setTeamTurn(Team teamTurn) {
		this.teamTurn = teamTurn;
	}

	public boolean checkNotWin() {
		if (checkPAT() || checkQuantity() || check50Step())
			return true;
		return false;
	}

	private boolean check50Step() {
		if (this.countFifty == 50)
			return true;
		return false;
	}

	private boolean checkQuantity() {
		int count = 0;

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if ((square[i][j].getChess() != null) && (square[i][j].getChess().getClass() == ChessRook.class)
						&& (square[i][j].getChess().getClass() == ChessQueen.class)
						&& (square[i][j].getChess().getClass() == ChessPawn.class)

				) {
					return false;
				} else {
					count++;
					if (count > 3)
						return false;
				}

			}
		return true;
	}

	

	// đến lượt nhưng ko có nước đi hợp lệ, vua ko bị chiếu
	private boolean checkPAT() {

		if (!isKing_Checkmate(teamTurn) && !haveMove(teamTurn))
			return true;

		return false;
	}


	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

	public String getEnPassant() {
		return enPassant;
	}

	public void setEnPassant(String enPassant) {
		this.enPassant = enPassant;
	}

	public int getCountFifty() {
		return countFifty;
	}

	public void setCountFifty(int countFifty) {
		this.countFifty = countFifty;
	}

	public int getFullMove() {
		return fullMove;
	}

	public void setFullMove(int fullMove) {
		this.fullMove = fullMove;
	}

	public void setSquare(Square[][] square2) {

		this.square = square2;
	}


	public static void main(String[] args) {
		Board board = (new Fen()).getBoard();
		List<Move> list = board.getListMoveAllTeam(Team.WHITE);
		for(Move move: list) {
			System.out.println(move.toString());
		}

	}

}
