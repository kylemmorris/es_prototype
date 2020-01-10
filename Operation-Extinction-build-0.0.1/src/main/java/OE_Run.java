/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
import javax.swing.JFrame;
import graphics.screens.OE_MainMenu;
/**
 * File containing the <code>main</code> method. 
 * 
 * @author Kyle M. Morris
 * @since 0.0.1
 */
public class OE_Run extends JFrame {
    public static OE_MainMenu _main;
    
    // main startup method
    public static void main(String[] args) {
    	_main = new graphics.screens.OE_MainMenu();
    }
}