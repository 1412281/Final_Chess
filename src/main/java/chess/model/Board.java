package chess.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import chess.model.Chess.Team;

public class Board {

	private static final int MAX_LAST_MOVE = 20;
	private Square[][] square = new Square[8][8];
	private Team teamTurn = Team.WHITE;;
	Deque<Move> lastMove = new ArrayDeque<Move>();

	public Square[][] getBoard() {
		Square[][] result = new Square[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				result[i][j] = new Square(this.square[i][j].getChess());;
			}
		}
		return result;
	}

	public Board() {
		initChess();
		lastMove.clear();
	}

	public Board(Board board) {
		this.square = board.getBoard();
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
		// khá»Ÿi táº¡o vua
		square[3][0].setChess(new ChessKing(Team.WHITE));
		// khá»Ÿi táº¡o háº­u
		square[4][0].setChess(new ChessQueen(Team.WHITE));

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
		// khá»Ÿi táº¡o vua
		square[3][7].setChess(new ChessKing(Team.BLACK));
		// khá»Ÿi táº¡o háº­u
		square[4][7].setChess(new ChessQueen(Team.BLACK));

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

	public void unMove() {
		nextTurn();

		Move move = this.lastMove.pop();

		actionMove(move.reverse());

	}

	public void move(Move move) {
		nextTurn();

		if (this.lastMove.size() == MAX_LAST_MOVE) {
			this.lastMove.removeLast();
		}

		Chess dead = this.square[move.getTo().getX()][move.getTo().getY()].getChess();
		
		actionMove(move);
		
		move.setChessDead(dead);
		
		this.lastMove.push(move);

	}

	private void actionMove(Move move) {

		this.square[move.getTo().getX()][move.getTo().getY()]
				.setChess(this.square[move.getFrom().getX()][move.getFrom().getY()].getChess());

		this.square[move.getFrom().getX()][move.getFrom().getY()].setChess(move.getChessDead());

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

	public List<Move> getListMoveAllTeam(Team team) {
		List<Point> listTeam = this.getListTeam(team);
		List<Move> result = new ArrayList<Move>();
		for(int i = 0; i < listTeam.size(); i++) {
			List<Point> listPoint = this.getListPosibleMoveFrom(listTeam.get(i));
			for(int j = 0; j < listPoint.size(); j++) {
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

	public List<Move> getLastMove() {
		List<Move> result = new ArrayList<Move>();
		for (Move move : this.lastMove) {
			result.add(move);
		}
		return result;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Board newboard = new Board();
		Point p1 = new Point(3, 3);
		Point p2 = new Point(4, 4);
		newboard.square[p1.getX()][p1.getY()].setChess(new ChessKing(Team.BLACK));
		newboard.square[p2.getX()][p2.getY()].setChess(new ChessKing(Team.BLACK));

		Move move = new Move(p1, p2);
		System.out.println("Kill");
		newboard.move(move);
//		System.out.println(newboard.square[p1.getX()][p1.getY()].getChess().toString());
		System.out.println(newboard.square[p2.getX()][p2.getY()].getChess().toString());
		System.out.println("Unmove");
		newboard.unMove();
		System.out.println(newboard.square[p1.getX()][p1.getY()].getChess().toString());
//		System.out.println(newboard.square[p2.getX()][p2.getY()].getChess().toString());

	}

}
