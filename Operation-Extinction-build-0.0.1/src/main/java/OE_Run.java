/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
import javax.swing.JFrame;
import graphics.screens.OE_StartMenu;
import structures.OE_GameConstants;

/**
 * File containing the <code>main</code> method. 
 * 
 * @author Kyle M. Morris
 * @since 0.0.1
 */
public class OE_Run extends JFrame {
    // main startup method
    public static void main(String[] args) {
    	structures.OE_GameConstants._CURRENTMENU_ = new OE_StartMenu();
    }
}