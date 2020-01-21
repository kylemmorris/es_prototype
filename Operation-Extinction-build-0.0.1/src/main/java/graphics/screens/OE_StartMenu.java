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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import graphics.OE_ScreenConstants;
import structures.database.OEuserData;
import structures.database.OE_dbReader;
/**
 * The starting menu, where the user must log in to access.
 * <p> Implements the <code>Menu</code> interface.</p>
 * <p> Uses a <code>OE_dbReader</code> object to fetch and verify information.</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 * @see oegrafx.screens.Menu
 */
public class OE_StartMenu extends JFrame implements Menu {
    // Frame
    private JFrame _frame;
    // Panels
    private OE_GraphicPane _mainPanel;
    // Buttons
    private JButton _loginButton, _resetButton, _createButton;
    // Checkbox
    private JCheckBox _showPassword;
    // Areas
    private JTextField _userField;
    private JPasswordField _passworField;
    // Labels
    private JLabel _userLabel;
    private JLabel _passwordLabel;
    // Database Information
    private OEuserData _tempData;
    private OE_dbReader _reader;
    // Constants
    private Dimension _size = new Dimension(OE_ScreenConstants._X / 3, (OE_ScreenConstants._Y / 3));
    private String _root = new File("").getAbsolutePath();
    private String _tempUsername, _tempPassword;
    private int _wChisle = (int) _size.getWidth();
    private int _hChisle = (int) _size.getHeight();
    
    // ================================ ACTION LISETENER
    private ActionListener buttonAction = new ActionListener(){
        public void actionPerformed(ActionEvent e){
        }
    };
    // ================================ CONSTRUCTOR
    public OE_StartMenu() {
    	// Create buttons & labels
    	_userLabel = new JLabel("USERNAME");
    	_passwordLabel = new JLabel("PASSWORD");
    	_userField = new JTextField();
    	_passworField = new JPasswordField();
    	_loginButton = new JButton("LOG IN");
    	_resetButton = new JButton("RESET");
    	_createButton = new JButton("CREATE NEW ACCOUNT");
    	_showPassword = new JCheckBox("SHOW PASSWORD");
    	// Create dbReader
    	_reader = new OE_dbReader();
    	
    	// ================================ GENERATE PANELS
    	// main panel setup
    	String pathToImage = (_root.concat("/graphics/mainBackground.jpg"));
    	_mainPanel = new OE_GraphicPane(pathToImage);
    	GridBagConstraints mConstraints = new GridBagConstraints();
    	_mainPanel.setLayout(null);
    	//	_mainPanel.setLayout(new GridBagLayout());
    	// Allignments
    	mConstraints.fill = GridBagConstraints.NONE;
    	
    	//TODO Finish StartMenu
    	_userLabel.setBounds(50, 50, 100, 30);
    	_userField.setBounds(150, 50, 150, 30);
    	
    	_passwordLabel.setBounds(50, 110, 100, 30);
    	_passworField.setBounds(150, 110, 150, 30);
    	
    	_showPassword.setBounds(150, 150, 250, 50);
    	
    	_loginButton.setBounds(50, 220, 100, 30);
    	_resetButton.setBounds(200, 220, 100, 30);
    	_createButton.setBounds(400, 110, 200, 30);
    	// Add it all to the panel
    	_mainPanel.add(_userLabel);
    	_mainPanel.add(_passwordLabel);
    	_mainPanel.add(_userField);
    	_mainPanel.add(_passworField);
    	_mainPanel.add(_showPassword);
    	_mainPanel.add(_loginButton);
    	_mainPanel.add(_resetButton);
    	_mainPanel.add(_createButton);
    	
    	// ================================ GENERATE FRAME & ADD CONTENT
        _frame = new JFrame("Operation Extinction - " + OE_ScreenConstants._Gversion);
        _frame.setMinimumSize(_size);
        _frame.setMaximumSize(OE_ScreenConstants._screenSize);
        _frame.setContentPane(_mainPanel);
        _frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        _frame.setResizable(false);
        _frame.pack();
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }
    
    
    
}