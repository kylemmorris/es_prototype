/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.io.File;
/**
 * A collection of information pertaining to the screen, as well as any 
 * visual representations of user information such as textboxes, shapes, etc.
 * 
 * @author Kyle M. Morris
 * @since 0.0.1
 */

public class OE_ScreenConstants {
    public static String _GuserID;
    public static final String _Gversion = "build 0.0.1";
    public static final Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int _X = (int)_screenSize.getWidth();
    public static int _Y = (int)_screenSize.getHeight();
}