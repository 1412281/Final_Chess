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

	// kiá»ƒm tra xem má»™t Team cĂ³ cĂ²n nÆ°á»›c Ä‘i nĂ o ko. TRUE : khĂ´ng
	// cĂ²n nÆ°á»›c Ä‘i nĂ o Ä‘Æ°á»£c, FALSE: ngÆ°á»£c láº¡i
	public boolean haveNoMove(Team team) {
		List<Point> listOfTeamMembers = getListTeam(team);
		for (Point item : listOfTeamMembers) {
			if (0 == this.getListPosibleMoveFrom(item).size())
				return true;
		}
		return false;

	}

	/*
	 * Kiá»ƒm tra tĂ¬nh tráº¡ng xem King cá»§a 1 Team cĂ³ Ä‘ang bá»‹ chiáº¿u hay
	 * khĂ´ng. Náº¿u bá»‹ chiáº¿u tráº£ vá»� TRUE
	 */
	public boolean isKing_Checkmate(Team team) {
		Team enemy = Team.WHITE;
		if (team == Team.WHITE)
			enemy = Team.BLACK;
		List<Point> listOfTeamMembers = getListTeam(team);
		@SuppressWarnings("unused")
		List<Point> listOfEnemies = getListTeam(enemy);
		Point King = null; // lÆ°u láº¡i vá»‹ trĂ­ quĂ¢n King
		for (Point item : listOfTeamMembers) {
			if (ChessKing.class == getChessFrom(item).getClass()) {
				King = new Point(item.getX(), item.getY());
				break;
			}
		}
		return willKing_checkmate(team, King);
	}

	/*
	 * HĂ m kiá»ƒm tra xem náº¿u King cá»§a "team" nĂ y á»Ÿ vá»‹ trĂ­ "point"
	 * thĂ¬ cĂ³ bá»‹ tá»± xĂ¡c hay khĂ´ng
	 */
	public boolean willKing_checkmate(Team team, Point King) {
		if (King == null)
			return false;

		Team enemy = Team.WHITE;
		if (team == Team.WHITE)
			enemy = Team.BLACK;
		List<Point> listOfEnemies = getListTeam(enemy);
		for (Point item : listOfEnemies) {
			/*
			 * Bá»� qua quĂ¢n King cá»§a Ä‘á»‹ch. Náº¿u khĂ´ng sáº½ máº¯c lá»—i
			 * Ä‘á»‡ quy vĂ²ng
			 */
			if (getChessFrom(item).getClass() == ChessKing.class)
				continue;
			List<Point> templist = getListPosibleMoveFrom(item);

			/*
			 * Ä�á»‘i vá»›i quĂ¢n Pawn cá»§a Ä‘á»‹ch thĂ¬ Ä‘áº·c biá»‡t hÆ¡n,
			 * Pawn chá»‰ Äƒn chĂ©o. CĂ¡c nÆ°á»›c Ä‘i tháº³ng khĂ´ng áº£nh
			 * hÆ°á»Ÿng Ä‘áº¿n King
			 */
			if (getChessFrom(item).getClass() == ChessPawn.class) {
				// náº¿u Pawn Ä‘á»‹ch vĂ  King Ä‘á»©ng chĂ©o nhau thĂ¬ bá»‹
				// chiáº¿u tÆ°á»›ng
				int Yvector = 1; // vector biá»ƒu thá»‹ chiá»�u Ä‘i cá»§a Pawn
									// WHITE
				if (Team.BLACK == enemy)
					Yvector = -1;// náº¿u Ä‘á»‹ch lĂ  Ä‘en thĂ¬ Pawn Ä‘á»‹ch Ä‘i
									// xuá»‘ng, nĂªn Vector Ă¢m

				/*
				 * Kiá»ƒm tra 2 kháº£ nÄƒng Äƒn cá»§a Pawn Ä‘á»‹ch cĂ³ trĂºng
				 * King khĂ´ng
				 */
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

	/*
	 * HĂ m tráº£ vá»� cĂ¡c vá»‹ trĂ­ há»£p lá»‡ cĂ³ thá»ƒ Ä‘i tá»« vá»‹ trĂ­
	 * Ä‘Ă£ chá»�n trĂªn bĂ n cá»� CĂ¡c bÆ°á»›c thá»±c hiá»‡n: +Láº¥y danh sĂ¡ch
	 * cĂ¡c Ä‘iá»ƒm cá»§a quĂ¢n cá»� Ä‘ang chá»�n cĂ³ thá»ƒ Ä‘i, bao gá»“m cáº£
	 * trĂ¹ng vá»›i Ä‘á»“ng minh hay Ä‘á»‹ch +Loáº¡i bá»� cĂ¡c Ä‘iá»ƒm bá»‹
	 * "trĂ¹ng" vá»›i Ä‘á»“ng minh, hoáº·c bá»‹ "cáº£n" tá»« xa (Trá»« quĂ¢n
	 * cá»� Knight)
	 */
	public List<Point> getListPosibleMoveFrom(Point point) {
		List<Point> result = new ArrayList<Point>();
		Square square = Board.square[point.getX()][point.getY()];
		/* bÆ°á»›c 1: láº¥y ra táº¥t cáº£ cĂ¡c Ä‘iá»ƒm cĂ³ thá»ƒ Ä‘i */
		Chess curChess = square.getChess();
		if (null == curChess)
			return result;
		List<Point> listPoint = curChess.getPosibleMove(point);
		if (null == listPoint)
			return result;
		/* bÆ°á»›c 2: loáº¡i ra cĂ¡c Ä‘iá»ƒm bá»‹ "cáº£n" */
		/*
		 * cĂ³ 1 loáº¡i quĂ¢n cá»� khĂ´ng thá»ƒ bá»‹ "cáº£n" vĂ¬ bÆ°á»›c Ä‘i
		 * quĂ¡ ngáº¯n: Knight
		 */
		if (curChess.getClass() != ChessKnight.class) {
			listPoint = getPosiblePoints_except_Prevented(point, listPoint);
		}

		/*
		 * bÆ°á»›c 3: loáº¡i ra cĂ¡c Ä‘iá»ƒm Ä‘Ă£ cĂ³ Ä‘á»“ng minh Ä‘á»©ng Ä‘Ă³
		 */
		listPoint = getPosiblePoints_except_Allies(listPoint, square.getChess().getTeam());
		/**/
		return listPoint;
	}

	// HĂ m lá»�c ra cĂ¡c vá»‹ trĂ­ khĂ´ng bá»‹ cáº£n (hoáº·c khĂ´ng bá»‹
	// chiáº¿u Ä‘á»‘i vá»›i King)
	private List<Point> getPosiblePoints_except_Prevented(Point fromPoint, List<Point> list) {
		Square square = Board.square[fromPoint.getX()][fromPoint.getY()];
		Chess curChess = square.getChess();

		/* Ta tĂ¬m cĂ¡c Ä‘iá»ƒm há»£p lá»‡ lÆ°u vĂ o danh sĂ¡ch dÆ°á»›i Ä‘Ă¢y */
		List<Point> result = new ArrayList<Point>();

		/*
		 * Loáº¡i quĂ¢n cá»� lĂ  King thĂ¬ loáº¡i bá»� cĂ¡c vá»‹ trĂ­ bá»‹
		 * chiáº¿u tÆ°á»›ng
		 */
		if (curChess.getClass() == ChessKing.class) {
			for (Point point : list) {
				if (false == willKing_checkmate(curChess.getTeam(), point)) {// náº¿u
																				// Ä‘iá»ƒm
																				// nĂ y
																				// khĂ´ng
																				// bá»‹
																				// chiáº¿u
					result.add(point);
				}
			}
			return result;
		}

		/* Loáº¡i quĂ¢n cá»� Pawn xá»­ lĂ­ Ä‘áº·c biá»‡t hÆ¡n */
		if (curChess.getClass() == ChessPawn.class) {

			for (Point point : list) {// cĂ³ 3 trÆ°á»�ng há»£p dĂ nh cho Pawn
				if (point.getX() == fromPoint.getX()) {// náº¿u Ä‘iá»ƒm Ä‘áº¿n
														// náº±m trÆ°á»›c máº·t
														// con Pawn
					if (null == this.getChessFrom(point)) {// náº¿u Ä‘iá»ƒm nĂ y
															// khĂ´ng
															// cĂ³ quĂ¢n cá»�
															// nĂ o
															// thĂ¬ há»£p lá»‡
						result.add(point);
						continue;
					}

					if (2 == Math.abs(point.getY() - fromPoint.getY())) { // náº¿u
																			// bÆ°á»›c
																			// nháº£y
																			// lĂ 
																			// 2
						/*
						 * ta cháº¯c cháº¯n biáº¿t Ä‘Æ°á»£c tá»�a Ä‘á»™ váº­t
						 * cáº£n (Y) chá»‰ cĂ³ thá»ƒ lĂ  2 hoáº·c 5
						 */
						int x = point.getX();
						int y = 5; // máº·c Ä‘á»‹nh bĂªn quĂ¢n Ä‘en
						if (curChess.getTeam() == Team.WHITE) { // náº¿u bĂªn
																// phe
																// WHITE,thĂ¬
																// xĂ©t
																// vá»‹ trĂ­ Y =
																// 2
							y = 2;
						}
						if (null == this.getChessFrom(new Point(x, y))) {// náº¿u
																			// Ä‘iá»ƒm
																			// cá»‘
																			// Ä‘á»‹nh
																			// nĂ y
																			// khĂ´ng
																			// cĂ³
																			// quĂ¢n
																			// cá»�
																			// nĂ o
																			// thĂ¬
																			// há»£p
																			// lá»‡
							result.add(point);
						}
					}
				} else {// 2 vá»‹ trĂ­ chĂ©o Pawn cĂ³ thá»ƒ Äƒn Ä‘á»‹ch thĂ¬
						// pháº£i cĂ³ Ä‘á»‹ch
						// táº¡i Ä‘Ă³
					Chess Enemy = this.getChessFrom(point);
					if (null == Enemy) {// náº¿u khĂ´ng cĂ³ ai
						// Ä‘iá»ƒm nĂ y ko Ä‘Æ°á»£c
					} else if (Enemy.getTeam() != curChess.getTeam()) { // náº¿u
																		// cĂ³
																		// mĂ 
																		// khĂ´ng
																		// cĂ¹ng
																		// phe
																		// thĂ¬
																		// ok
						result.add(point);
					}
				}
			}
		}

		/* CĂ¡c loáº¡i cĂ²n láº¡i xá»­ lĂ­ nhÆ° nhau */
		else {
			for (Point point : list) {// duyá»‡t tá»«ng vá»‹ trĂ­ trong danh
										// sĂ¡ch

				// náº¿u 2 Ă´ Ä‘ang xĂ©t liá»�n ká»� nhau thĂ¬ cháº¯c cháº¯n
				// khĂ´ng cĂ³ ai cáº£n
				if (1 == Math.abs(point.getX() - fromPoint.getX()) || 1 == Math.abs(point.getY() - fromPoint.getY())) {
					result.add(point);
					continue;
				}
				/*
				 * bÆ°á»›c 1: láº¥y vector tá»« Ä‘iá»ƒm ban Ä‘áº§u Ä‘áº¿n vá»‹
				 * trĂ­ Ä‘Ă³, vector luĂ´n cĂ³ Ä‘á»™ dĂ i 1
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
				 * bÆ°á»›c 2: cá»™ng dá»“n vector nĂ y, duyá»‡t cĂ¡c Ä‘iá»ƒm
				 * náº±m "giá»¯a" Ä‘áº¿n khi "cháº¡m" Ä‘iá»ƒm Ä‘Ă­ch
				 */
				while (!(x == point.getX() && y == point.getY())) {
					Point tempPoint = new Point(x, y);
					if (null == this.getChessFrom(tempPoint)) {// náº¿u Ä‘iá»ƒm
																// nĂ y
																// khĂ´ng cĂ³
																// quĂ¢n
																// cá»� nĂ o
						x = x + Xvector;
						y = y + Yvector;
					} else {// náº¿u cĂ³ quĂ¢n cá»� nĂ o thĂ¬ Ä‘iá»ƒm "point"
							// khĂ´ng há»£p lá»‡
						break;
					}
				}
				if (x == point.getX() && y == point.getY()) { // khi khĂ´ng cĂ³
																// báº¥t
																// kĂ¬ quĂ¢n
																// nĂ o náº±m
																// giá»¯a thĂ¬
																// duyá»‡t
																// Ä‘iá»ƒm nĂ y
					result.add(point);
				}
			}
		}
		return result;
	}

	// hĂ m lá»�c ra cĂ¡c vá»‹ trĂ­ khĂ´ng cĂ³ Ä‘á»“ng minh vĂ  cĂ³ Ä‘á»‹ch
	// Ä‘ang Ä‘á»©ng
	// loáº¡i bá»� cĂ¡c vá»‹ trĂ­ cĂ³ Ä‘á»“ng minh
	private List<Point> getPosiblePoints_except_Allies(List<Point> listPoint, Team team) {
		List<Point> result = new ArrayList<Point>();
		for (Point point : listPoint) {// xĂ©t tá»«ng Ä‘iá»ƒm trong danh sĂ¡ch
			Chess chess = this.getChessFrom(point); // xĂ©t quĂ¢n cá»� trĂªn
													// Ä‘iá»ƒm nĂ y
			if (null != chess) { // náº¿u cĂ³ 1 quĂ¢n cá»�
				if (!chess.getTeam().equals(team)) { // náº¿u quĂ¢n Ä‘Ă³ lĂ 
														// Ä‘á»‹ch
					result.add(point);
				}
			} else {// cĂ¡c trÆ°á»�ng há»£p cĂ²n láº¡i thá»�a yĂªu cáº§u
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

	public boolean checkPosibleMoveAllTeam(Team team) {
		return false;
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
	}

}
