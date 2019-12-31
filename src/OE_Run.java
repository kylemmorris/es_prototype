/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
import javax.swing.JFrame;
/**
 * File containing the <code>main</code> method. 
 * 
 * @author Kyle M. Morris
 * @since 0.0.1
 */
public class OE_Run extends JFrame {

    private oegrafx.screens.Menu _main;
    public OE_Run() {
      // create the main menu
      _main = new oegrafx.screens.OE_MainMenu();
    }
    public static void main(String[] args){
      new OE_Run();
    }
}
