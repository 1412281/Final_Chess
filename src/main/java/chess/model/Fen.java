package chess.model;

import chess.model.Chess.Team;

public class Fen {

	private String fenString = "";

	public static final String START_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

	public Fen() {
		fenString = START_POSITION;
	}
	
	public Fen(Board board) {
		init(board);
	}

	

	private void init(Board board) {
		Square[][] square = board.getBoardSquare();
		String tempString = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square sq = square[j][i];
				if (sq.getChess() != null) {
					tempString += sq.getChess().toString();
				} else {
					tempString += '-';
				}
			}
			tempString += '/';
		}
		tempString = fix(tempString);

		tempString += " " + (board.getTeamTurn() == Team.WHITE ? "w" : "b");
		tempString += " " + board.getCastle().toString();
		tempString += " " + board.getEnPassant();
		tempString += " " + String.valueOf(board.getCountFifty());
		tempString += " " + String.valueOf(board.getFullMove());

		setFenString(tempString);
	}

	private String fix(String string) {
		String result = new String();
		char[] fenArray = string.toCharArray();
		int i = 0;
		while (i < fenArray.length - 1) {
			if (fenArray[i] == '-') {
				int flag = i;
				while ((fenArray[i] == '-') && (i < fenArray.length))
					i++;
				result += String.valueOf(i - flag);
				i--;
			} else {
				result += String.valueOf(fenArray[i]);
			}
			i++;
		}
		return result;
	}


	public Board getBoard() {
		Board result = new Board();
		String[] arrayFen = fenString.split(" ");
//		set square[][]
		String squareString = arrayFen[0];
		Square[][] square = new Square[8][8];
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				square[i][j] = new Square();
			}
		int countString = 0;
		for (int i = 0; i < 8; i++) {
			int j = 0;
			while (j < 8) {
				char ch = squareString.charAt(countString++);
				if ((ch >= '0') && (ch <= '9')) {
					j += Character.getNumericValue(ch);
				}
				else {
					square[j][i] = new Square(getChess(ch));
				}
				j++;
			}
			countString++;
		}
		result.setSquare(square);
		
//		set team turn
		String turnString = arrayFen[1];
		result.setTeamTurn(turnString.equals("w") ? Team.WHITE : Team.BLACK);
		
//		set castle
		String castleString = arrayFen[2];
		result.setCastle(new Castle(castleString));
		
//		set en passant
		String enPassant = arrayFen[3];
		result.setEnPassant(enPassant);
		
//		set fiftymove
		String fiftyString = arrayFen[4];
		result.setCountFifty(Integer.valueOf(fiftyString));
		
//		set fullmove
		String fullMove = arrayFen[5];
		result.setFullMove(Integer.valueOf(fullMove));
		
		return result;
	}

	private Chess getChess(char c) {
		Chess result = null;
		switch (c) {
		case 'b':
		case 'B':
			result = new ChessBishop(Team.BLACK);
			break;
		case 'k':
		case 'K':
			result = new ChessKing(Team.BLACK);
			break;
		case 'n':
		case 'N':
			result = new ChessKnight(Team.BLACK);
			break;
		case 'p':
		case 'P':
			result = new ChessPawn(Team.BLACK);
			break;
		case 'q':
		case 'Q':
			result = new ChessQueen(Team.BLACK);
			break;
		case 'r':
		case 'R':
			result = new ChessRook(Team.BLACK);
			break;
		}
		if ((c >= 'A') && (c <= 'Z')) {
			result.setTeam(Team.WHITE);
		}

		return result;
	}

	public String getFenString() {
		return fenString;
	}

	public void setFenString(String fenString) {
		this.fenString = fenString;
	}

	public boolean equal(Fen fen) {
		return (this.fenString.equals(fen.getFenString()));
	}

	public static void main(String[] args) {
		Board board = new Board();
		Fen fen = new Fen();
		System.out.println(fen.getFenString());
		board = fen.getBoard();
		Fen fen1 = new Fen(board);

		System.out.println(fen1.getFenString());
	}

}
