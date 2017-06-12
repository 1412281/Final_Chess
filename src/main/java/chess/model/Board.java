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
		// khởi tạo quân TRẮNG
		// khởi tạo 8 tốt
		for (int i = 0; i < 8; i++) {
			square[i][1].setChess(new ChessPawn(Team.WHITE));
		}
		// khởi tạo 2 xe
		square[0][0].setChess(new ChessRook(Team.WHITE));
		square[7][0].setChess(new ChessRook(Team.WHITE));
		// khởi tạo 2 ngựa
		square[1][0].setChess(new ChessKnight(Team.WHITE));
		square[6][0].setChess(new ChessKnight(Team.WHITE));
		// khởi tạo 2 bồ
		 square[2][0].setChess(new ChessBishop(Team.WHITE));
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
		square[0][7].setChess(new ChessRook(Team.BLACK));
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
	
	// lấy danh dách tất cả quân cờ còn lại của 1 team -> AI
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
	
	public void move(Point fromPoint, Point toPoint) {
		Board.square[toPoint.getX()][toPoint.getY()]
				.setChess(Board.square[fromPoint.getX()][fromPoint.getY()].getChess());
		Board.square[fromPoint.getX()][fromPoint.getY()].setChess(null);
	}
	
	
	/*
	 * Hàm trả về các vị trí hợp lệ có thể đi từ vị trí đã chọn trên bàn cờ Các
	 * bước thực hiện: +Lấy danh sách các điểm của quân cờ đang chọn có thể đi,
	 * bao gồm cả trùng với đồng minh hay địch +Loại bỏ các điểm bị "trùng" với
	 * đồng minh, hoặc bị "cản" từ xa (Trừ quân cờ Knight)
	 */
	public List<Point> getListPosibleMoveFrom(Point point) {
		List<Point> result = new ArrayList<Point>();
		Square square = Board.square[point.getX()][point.getY()];
		/* bước 1: lấy ra tất cả các điểm có thể đi */
		Chess curChess = square.getChess();
		if (null == curChess)
			return result;
		List<Point> listPoint = curChess.getPosibleMove(point);
		if (null == listPoint)
			return result;
		/* bước 2: loại ra các điểm bị "cản" */
		/*
		 * có 2 loại quân cờ không thể bị "cản" vì bước đi quá ngắn: King,Knight
		 */
		if (curChess.getClass() != ChessKing.class && curChess.getClass() != ChessKnight.class) {
			listPoint = getPosiblePoints_except_Prevented(point, listPoint);
		}

		/* bước 3: loại ra các điểm đã có đồng minh đứng đó */
		listPoint = getPosiblePoints_except_Allies(listPoint, square.getChess().getTeam());
		/**/
		return listPoint;
	}

	// Hàm lọc ra các vị trí không bị cản
	private List<Point> getPosiblePoints_except_Prevented(Point fromPoint, List<Point> list) {
		Square square = Board.square[fromPoint.getX()][fromPoint.getY()];
		Chess curChess = square.getChess();

		/* Ta tìm các điểm hợp lệ lưu vào danh sách dưới đây */
		List<Point> result = new ArrayList<Point>();

		/* Loại quân cờ Pawn xử lí đặc biệt hơn */
		if (curChess.getClass() == ChessPawn.class) {

			for (Point point : list) {// có 3 trường hợp dành cho Pawn
				if (point.getX() == fromPoint.getX()) {// nếu điểm đến nằm trước mặt
													// con Pawn
					if (null == this.getChessFrom(point)) {// nếu điểm này không
															// có quân cờ nào
															// thì hợp lệ
						result.add(point);
						continue;
					}

					if (2 == Math.abs(point.getY() - fromPoint.getY())) { // nếu bước
																		// nhảy
																		// là 2
						/*
						 * ta chắc chắn biết được tọa độ vật cản (Y) chỉ có thể
						 * là 2 hoặc 5
						 */
						int x = point.getX();
						int y = 5; // mặc định bên quân đen
						if (curChess.getTeam() == Team.WHITE) { // nếu bên phe
																// WHITE,thì xét
																// vị trí Y = 2
							y = 2;
						}
						if (null == this.getChessFrom(new Point(x, y))) {// nếu
																			// điểm
																			// cố
																			// định
																			// này
																			// không
																			// có
																			// quân
																			// cờ
																			// nào
																			// thì
																			// hợp
																			// lệ
							result.add(point);
						}
					}
				} else {// 2 vị trí chéo Pawn có thể ăn địch thì phải có địch
						// tại đó
					Chess Enemy = this.getChessFrom(point);
					if (null == Enemy) {// nếu không có ai
						// điểm này ko được
					} else if (Enemy.getTeam() != curChess.getTeam()) { // nếu
																		// có mà
																		// không
																		// cùng
																		// phe
																		// thì
																		// ok
						result.add(point);
					}
				}
			}
		}

		/* Các loại còn lại xử lí như nhau */
		else {
			for (Point point : list) {// duyệt từng vị trí trong danh sách

				// nếu 2 ô đang xét liền kề nhau thì chắc chắn không có ai cản
				if (1 == Math.abs(point.getX() - fromPoint.getX()) || 1 == Math.abs(point.getY() - fromPoint.getY())) {
					result.add(point);
					continue;
				}
				/*
				 * bước 1: lấy vector từ điểm ban đầu đến vị trí đó, vector luôn
				 * có độ dài 1
				 */
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

				/*
				 * bước 2: cộng dồn vector này, duyệt các điểm nằm "giữa" đến
				 * khi "chạm" điểm đích
				 */
				while (!(x == point.getX() && y == point.getY())) {
					Point tempPoint = new Point(x, y);
					if (null == this.getChessFrom(tempPoint)) {// nếu điểm này
																// không có quân
																// cờ nào
						x = x + Xvector;
						y = y + Yvector;
					} else {// nếu có quân cờ nào thì điểm "point" không hợp lệ
						break;
					}
				}
				if (x == point.getX() && y == point.getY()) { // khi không có bất
															// kì quân nào nằm
															// giữa thì duyệt
															// điểm này
					result.add(point);
				}
			}
		}
		return result;
	}

	// hàm lọc ra các vị trí không có đồng minh và có địch đang đứng
	// loại bỏ các vị trí có đồng minh
	private List<Point> getPosiblePoints_except_Allies(List<Point> listPoint, Team team) {
		List<Point> result = new ArrayList<Point>();
		for (Point point : listPoint) {// xét từng điểm trong danh sách
			Chess chess = this.getChessFrom(point); // xét quân cờ trên điểm này
			if (null != chess) { // nếu có 1 quân cờ
				if (!chess.getTeam().equals(team)) { // nếu quân đó là địch
					result.add(point);
				}
			} else {// các trường hợp còn lại thỏa yêu cầu
				result.add(point);
			}

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

	public static void main(String[] args) {
		Board newboard = new Board();
		Point point = new Point(3, 3);
		System.out.println(point.toString());
		square[point.getX()][point.getY()].setChess(new ChessQueen(Team.WHITE));
		
		List<Point> list = newboard.getListPosibleMoveFrom(point);
		System.out.println(list.size());

		for (Point point1 : list) {
			System.out.println(point1.toString());
		}
	}

}
