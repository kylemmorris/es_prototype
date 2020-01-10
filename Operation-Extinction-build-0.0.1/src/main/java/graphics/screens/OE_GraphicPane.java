/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package graphics.screens;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Image;
/**
 * An implementation of <code>JLayeredPane</code> that includes
 * a background image, instead of the standard backdrop.
 * @author Kyle M. Morris
 * @since 0.0.1
 * @see javax.swing.JLayeredPane
 */
public class OE_GraphicPane extends JLayeredPane {
    private BufferedImage _backdrop;
    private Dimension _size;
    public enum Scale {FULLSCALE, NOSCALE};
    /**
     * Creates a new pane with background that can be found in 
     * <code>path</code> directory.
     * @param path The path (as a string) to the desired image.
     */
    public OE_GraphicPane(String path){
        try{
            this._backdrop = ImageIO.read(new File(path));
        } catch(IOException e){
            System.out.println("ERROR: Image path \"" + path + "\" does not exist");
            e.printStackTrace();
        }
    }
    public OE_GraphicPane(String path, Scale scale){
        new OE_GraphicPane(path);
        if(scale == Scale.FULLSCALE){
            int scaledWidth = _backdrop.getWidth() / this.getX();
            int scaledHeight = _backdrop.getHeight() / this.getY();
            Image tmp = _backdrop.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            _backdrop = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = _backdrop.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(_backdrop, 0, 0, this);
    }
}