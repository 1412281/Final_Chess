package chess.view.menubar;

import javax.swing.JMenu;
import chess.view.menubar.Menu_Items.Tool_MenuItems.Options;

public class Tools_Menu extends JMenu
{
    public Tools_Menu()
    {
        setText("Tools");
        add(GameOptions);
    }
    
    private final Options GameOptions=new Options();
}