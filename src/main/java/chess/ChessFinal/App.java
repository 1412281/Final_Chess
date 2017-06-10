package chess.ChessFinal;
import java.util.List;

import chess.model.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ChessBishop bishop = new ChessBishop();
    	List<Position> list = bishop.getPosibleMove();
    	System.out.println(list.size());
    	
        
    }
}