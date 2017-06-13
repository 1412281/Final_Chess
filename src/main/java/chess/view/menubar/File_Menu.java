package chess.view.menubar;

import javax.swing.JMenu;
import chess.view.menubar.Menu_Items.File_MenuItems.New_Item;
import chess.view.menubar.Menu_Items.File_MenuItems.Save_Item;
import chess.view.menubar.Menu_Items.File_MenuItems.Load_Item;
import chess.view.menubar.Menu_Items.File_MenuItems.Exit_Item;
import chess.view.frame.MainFrame;


public class File_Menu extends JMenu
{
    
    /** Creates a new instance of File_Menu */
    public File_Menu(MainFrame ff)
    {
        NItem=new New_Item(ff);
        setText("File");
        add(NItem);
        addSeparator();
        add(SItem);
        add(LItem);
        add(EItem);
        
    }
    public String getIPaddress()
    {
        return NItem.getIpAddress();
    }
    public String getportNumber()
    {
        return NItem.getportNumber();
    }
    
    private final New_Item NItem;
    private final Save_Item SItem=new Save_Item();
    private final Load_Item LItem=new Load_Item();
    private final Exit_Item EItem=new Exit_Item();
}