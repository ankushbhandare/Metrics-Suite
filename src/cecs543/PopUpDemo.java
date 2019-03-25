/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs543;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ankush
 */
public class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo(){
        anItem = new JMenuItem("Click Me!");
        add(anItem);
    }
}
