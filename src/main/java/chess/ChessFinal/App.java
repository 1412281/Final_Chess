package chess.ChessFinal;

import java.util.List;

import chess.controller.*;
import chess.model.Point;

public class App 
{
    public static void main( String[] args )
    {
    	PlayController playController = new PlayController();
    	Point point = new Point(4,4);
        List<Point> list = playController.getListPosibleMove(point);
        for(int i = 0; i < list.size(); i++) {
        	System.out.println(list.get(i).toString());
        }
    }
}