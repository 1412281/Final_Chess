package chess.model;

import java.util.ArrayList;
import java.util.List;

import chess.model.Chess.Team;

public class Board {

	private Square[][] square = new Square[8][8];

	public Board() {
		initChess();

	}

	private void initChess() {
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				square[i][j] = new Square();
			}
		// khởi tạo quân TRẮNG
		// khởi tạo 8 tốt
		for (int i = 0; i < 8; i++) {
			square[i][1].setChess(new ChessPawn(Team.WHITE));
		}
		// khởi tạo 2 xe
		square[1][0].setChess(new ChessRook(Team.WHITE));
		square[7][0].setChess(new ChessRook(Team.WHITE));
		// khởi tạo 2 ngựa
		square[1][0].setChess(new ChessKnight(Team.WHITE));
		square[6][0].setChess(new ChessKnight(Team.WHITE));
		// khởi tạo 2 bồ
//		square[2][0].setChess(new ChessBishop(Team.WHITE));
		square[4][4].setChess(new ChessBishop(Team.WHITE)); //TEST
		square[5][0].setChess(new ChessBishop(Team.WHITE));
		// khởi tạo hậu
		square[4][0].setChess(new ChessQueen(Team.WHITE));
		// khởi tạo vua
		square[3][0].setChess(new ChessKing(Team.WHITE));

		// khởi tạo quân ĐEN
		// khởi tạo 8 tốt
		for (int i = 0; i < 8; i++) {
			square[i][6].setChess(new ChessPawn(Team.BLACK));
		}
		// khởi tạo 2 xe
		square[1][7].setChess(new ChessRook(Team.BLACK));
		square[7][7].setChess(new ChessRook(Team.BLACK));
		// khởi tạo 2 ngựa
		square[1][7].setChess(new ChessKnight(Team.BLACK));
		square[6][7].setChess(new ChessKnight(Team.BLACK));
		// khởi tạo 2 bồ
		square[2][7].setChess(new ChessBishop(Team.BLACK));
		square[5][7].setChess(new ChessBishop(Team.BLACK));
		// khởi tạo hậu
		square[4][7].setChess(new ChessQueen(Team.BLACK));
		// khởi tạo vua
		square[3][7].setChess(new ChessKing(Team.BLACK));

	}

	public List<Point> getListPosibleMoveFrom(Point point) {
		List<Point> result = new ArrayList<Point>();
		Square square = this.square[point.getX()][point.getY()];

		List<Point> listPoint = square.getChess().getPosibleMove(point);

		for (int i = 0; i < listPoint.size(); i++) {
			result.addAll(getListMove(point, listPoint.get(i)));
		}

		return result;
	}

	//xuất list ko đụng độ
	private List<Point> getListMove(Point fromPoint, Point toPoint) {
		List<Point> result = new ArrayList<Point>();
		Chess chess = getChessFrom(fromPoint);
		
		List<Point> listMove = chess.getPosibleMove(fromPoint);
		
		int next = 0;
		Point cur_point = new Point(listMove.get(0));
		while (!cur_point.equals(toPoint)) {
			
			if (getChessFrom(cur_point) != null) { // đụng độ
				if (!chess.getTeam().equals(getChessFrom(cur_point).getTeam())) {
					result.add(cur_point);
					break;
				}
			}
			
			result.add(cur_point);
			if (next >= listMove.size()) return result;
			cur_point = listMove.get(++next);
		}
		return result;
	}

	private Chess getChessFrom(Point point) {
		return square[point.getX()][point.getY()].getChess();
	}

	public boolean checkWin(Team team) {
		return false;
	}

	public boolean checkPosibleMoveAllTeam(Team team) {
		return false;
	}

}
