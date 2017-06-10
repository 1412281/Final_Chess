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
	// khởi tạo quân TRẮNG
		// khởi tạo 8 tốt
		for (int i = 0; i < 8; i++) { square[i][1].setChess(new ChessPawn()); }
		// khởi tạo 2 xe
		square[1][0].setChess(new ChessRook());
		square[7][0].setChess(new ChessRook());
		// khởi tạo 2 ngựa
		square[1][0].setChess(new ChessKnight());
		square[6][0].setChess(new ChessKnight());
		// khởi tạo 2 bồ
		square[2][0].setChess(new ChessBishop());
		square[5][0].setChess(new ChessBishop());
		// khởi tạo hậu
		square[4][0].setChess(new ChessQueen());
		// khởi tạo vua
		square[3][0].setChess(new ChessKing());

	// khởi tạo quân ĐEN
		// khởi tạo 8 tốt
		for (int i = 0; i < 8; i++) { square[i][6].setChess(new ChessPawn()); }
		// khởi tạo 2 xe
		square[1][7].setChess(new ChessRook());
		square[7][7].setChess(new ChessRook());
		// khởi tạo 2 ngựa
		square[1][7].setChess(new ChessKnight());
		square[6][7].setChess(new ChessKnight());
		// khởi tạo 2 bồ
		square[2][7].setChess(new ChessBishop());
		square[5][7].setChess(new ChessBishop());
		// khởi tạo hậu
		square[4][7].setChess(new ChessQueen());
		// khởi tạo vua
		square[3][7].setChess(new ChessKing());

	}

	public List<Point> getListPosibleMoveFrom(Point point) {
		List<Point> result = new ArrayList<Point>();
		Square square = this.square[point.getX()][point.getY()];
		
		List<Point> listPoint = square.getChess().getPosibleMove(point);
		
		for(int i = 0; i < listPoint.size(); i++) {
			if (checkMove(point, listPoint.get(i))) {
				result.add(listPoint.get(i));
			}
		}
		return result;
	}

	private boolean checkMove(Point fromPoint, Point toPoint) {
		Chess chess = this.square[fromPoint.getX()][fromPoint.getY()].getChess();
		Point cur_point = new Point(fromPoint);
//		how to arrive goal
//			scan all ways
		while (!cur_point.equals(toPoint)) {
			chess.step();
			if (VaCham) return false;
		}
		return true;
	}

	public boolean checkWin(Team team) {
		return false;
	}

	public boolean checkPosibleMoveAllTeam(Team team) {
		return false;
	}


}
