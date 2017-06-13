package chess.view.menubar;

import javax.swing.JMenu;
import chess.view.menubar.Menu_Items.Help_MenuItems.*;

public class Help_Menu extends JMenu
{
    private final About_Chess_Game AboutGame=new About_Chess_Game();
    private final About_Game_Programmers AboutUs=new About_Game_Programmers(); 
    
    public Help_Menu()
    {
        setText("Help");
        add(AboutGame);
        add(AboutUs);
    }  
}
