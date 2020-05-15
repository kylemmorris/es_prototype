/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
* The user's profile.
* Menu path: StartMenu -> MainMenu -> ProfileMenu
*
*/
package graphics.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.PrimitiveIterator.OfDouble;
import java.util.regex.Pattern;
import graphics.OE_ScreenConstants;
import structures.database.OEuserData;
import structures.OE_GameConstants;
import structures.database.OE_dbConnector;

public class OE_ProfileMenu extends JFrame implements Menu {
	// Frame
	private JFrame _frame;
	// Panels
	private OE_GraphicPane _mainPanel, _pfpGraphicPanel, _rankGraphicPanel;
	// Buttons
	private JButton _backButton;
	// Labels
	private JLabel _nameLabel, _joinedLabel, _rankLabel, _pointsLabel;
	// Constants
    private Dimension _size = new Dimension(OE_ScreenConstants._X / 3, (OE_ScreenConstants._Y / 3));
    private String _root = new File("").getAbsolutePath();
    private int _wChisle = (int) _size.getWidth();
    private int _hChisle = (int) _size.getHeight();
    private String pathToImage;
    // Data
    private OEuserData _userData;
    
    private ActionListener buttonAction = new ActionListener(){
        public void actionPerformed(ActionEvent e) {
        }
    };
    
	// ============================== CONSTRUCTOR
    public OE_ProfileMenu() {
    	// Current data
    	
    	// Create buttons and labels
    	_nameLabel = new JLabel("Username:");
    	_joinedLabel = new JLabel("Joined on:");
    	_rankLabel = new JLabel("Rank:");
    	_pointsLabel = new JLabel("Points:");
    	_backButton = new JButton("BACK");
    	_backButton.addActionListener(buttonAction);
    	
    }
	
	
}