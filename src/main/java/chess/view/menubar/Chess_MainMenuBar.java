package chess.view.menubar;

import javax.swing.JMenuBar;
import chess.view.frame.*;

public class Chess_MainMenuBar extends JMenuBar
{
    public Chess_MainMenuBar(MainFrame ff)
    {
        Fmenu=new File_Menu(ff);
        add(Fmenu);
        add(Emenu);
        add(Tmenu);
        add(Hmenu);
        
    }
    public String getIpAddress()
    {
        return Fmenu.getIPaddress();
    }
    public String getPortnumber()
    {
        return Fmenu.getportNumber();
    }
    
    private final File_Menu Fmenu;
    private final Edit_Menu Emenu=new Edit_Menu();
    private final Tools_Menu Tmenu=new Tools_Menu();
    private final Help_Menu Hmenu=new Help_Menu();
}
