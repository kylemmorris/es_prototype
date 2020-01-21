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
import java.sql.SQLException;
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
    private JLabel _userLabel, _passwordLabel, _welcomeLabel, _newLabel;
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
        public void actionPerformed(ActionEvent e) {
        	if(e.getSource() == _loginButton) {logIn();}
        	else if(e.getSource() == _resetButton) {resetFields();}
        	else if (e.getSource() == _createButton) {accountCreation();}
        	else {// TODO error handeling
        	}
        }
    };
    // ================================ CONSTRUCTOR
    public OE_StartMenu() {
    	// Create buttons & labels
    	_userLabel = new JLabel("USERNAME");
    	_passwordLabel = new JLabel("PASSWORD");
    	_welcomeLabel = new JLabel("Existing User? Log in!");
    	_newLabel = new JLabel("New User? Sign up!");
    	_userField = new JTextField();
    	_passworField = new JPasswordField();
    	_loginButton = new JButton("LOG IN");
    	_resetButton = new JButton("RESET");
    	_createButton = new JButton("CREATE NEW ACCOUNT");
    	_showPassword = new JCheckBox("SHOW PASSWORD");
    	// Add action listeners
    	_loginButton.addActionListener(buttonAction);
    	_resetButton.addActionListener(buttonAction);
    	_createButton.addActionListener(buttonAction);
    	// Create dbReader
    	_reader = new OE_dbReader();
    	_reader.initUserMode();
    	
    	// ================================ GENERATE PANELS
    	// main panel setup
    	String pathToImage = (_root.concat("/graphics/mainBackground.jpg"));
    	_mainPanel = new OE_GraphicPane(pathToImage);
    	_mainPanel.setLayout(null);
    	// Alignments
    	
    	//TODO Finish StartMenu
    	_welcomeLabel.setBounds(100, 20, 200, 30);
    	_newLabel.setBounds(425, 20, 200, 30);
    	
    	_userLabel.setBounds(50, 70, 100, 30);
    	_userField.setBounds(150, 70, 150, 30);
    	
    	_passwordLabel.setBounds(50, 130, 100, 30);
    	_passworField.setBounds(150, 130, 150, 30);
    	
    	_showPassword.setBounds(150, 170, 250, 50);
    	
    	_loginButton.setBounds(50, 240, 100, 30);
    	_resetButton.setBounds(200, 240, 100, 30);
    	_createButton.setBounds(400, 100, 200, 30);
    	// Add it all to the panel
    	_mainPanel.add(_userLabel);
    	_mainPanel.add(_passwordLabel);
    	_mainPanel.add(_newLabel);
    	_mainPanel.add(_welcomeLabel);
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
    // ================================ BUTTON ACTIONS
    protected void logIn() {
    	_reader.setKeyInput(_userField.getText());
    	try {
    		_tempData = _reader.userRead();
    	} catch(SQLException e) {
    		JOptionPane.showMessageDialog(this, "Username not found.");
    		return;
    	}
    	if(_tempData.getString("password") != _passworField.getText());
    		JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
    		return;
    }
    protected void resetFields() {
    	
    }
    protected void accountCreation() {
    	
    }
}