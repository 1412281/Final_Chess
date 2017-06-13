package chess.view.menubar;


import javax.swing.JMenu;

import chess.view.menubar.Menu_Items.Edit_MenuItems.Undo_move;
import chess.view.menubar.Menu_Items.Edit_MenuItems.Redo_move;

public class Edit_Menu extends JMenu
{
    private final Undo_move Uitem=new Undo_move();
    private final Redo_move Ritem=new Redo_move();
    
    public Edit_Menu()
    {
        setText("Edit");
        add(Uitem);
        add(Ritem);
    }
}