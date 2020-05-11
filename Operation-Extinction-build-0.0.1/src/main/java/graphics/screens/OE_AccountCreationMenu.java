/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
* The account creation menu.
* Menu path: StartMenu -> AccountCreationMenu
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

import graphics.OE_ScreenConstants;
import structures.database.OEuserData;
import structures.OE_GameConstants;
import structures.database.OE_dbConnector;
import structures.database.OE_dbCursor;

public class OE_AccountCreationMenu extends JFrame implements Menu {
	// Frame
	private JFrame _frame;
	// Panels
	private OE_GraphicPane _mainPanel, _randCardPane;
	// Buttons
	private JButton _backButton, _createButton, _resetButton;
	// Areas
	private JTextField _newUserName;
	private JPasswordField _newPassword, _confirmPassword;
	// Labels
	private JLabel _welcomeLabel, _userLabel, _passLabel, _confirmLabel;
	// Constants
    private Dimension _size = new Dimension(OE_ScreenConstants._X / 3, (OE_ScreenConstants._Y / 3));
    private String _root = new File("").getAbsolutePath();
    private int _wChisle = (int) _size.getWidth();
    private int _hChisle = (int) _size.getHeight();
    private String pathToImage;
    // Data
    private OEuserData _tempData;
    private OE_dbConnector _dbCon;
    
    private ActionListener buttonAction = new ActionListener(){
        public void actionPerformed(ActionEvent e) {
        	if(e.getSource() == _backButton) {back();}
        	else if(e.getSource() == _createButton) {accountCreation();}
        	else if(e.getSource() == _resetButton) {resetText();}
        	else { // TODO error handeling
        	}
        }
    };
	// ============================== CONSTRUCTOR
	public OE_AccountCreationMenu() {
		// Create buttons and labels
		_welcomeLabel = new JLabel("Create a new account and start playing!");
		_userLabel = new JLabel("New Username");
		_passLabel = new JLabel("New Password");
		_confirmLabel = new JLabel("Confirm Password");
		_newUserName = new JTextField();
		_newPassword = new JPasswordField();
		_confirmPassword = new JPasswordField();
		_backButton = new JButton("BACK");
		_createButton = new JButton("SIGN UP");
		_resetButton = new JButton("RESET");
		// Add action listeners
		_backButton.addActionListener(buttonAction);
		_createButton.addActionListener(buttonAction);
		_resetButton.addActionListener(buttonAction);
		// Create dbCursor
		_dbCon = new OE_dbConnector();
		
		// Alignment
		// _wChisle = 640, _hChisle = 360
		//_backButton.setBounds(x, y, width, height);
		_backButton.setBounds(30, 20, 100, 30);
		_welcomeLabel.setBounds(200, 20, 500, 30);
		_userLabel.setBounds(50, 100, 200, 30);
		_passLabel.setBounds(50, 150, 200, 30);
		_confirmLabel.setBounds(30, 200, 200, 30);
		_newUserName.setBounds(200, 100, 200, 30);
		_newPassword.setBounds(200, 150, 200, 30);
		_confirmPassword.setBounds(200, 200, 200, 30);
		_createButton.setBounds(180, 250, 100, 30);
		_resetButton.setBounds(320, 250, 100, 30);
		// Add it all to the panel
		pathToImage = (_root.concat("/graphics/mainBackground.jpg"));
    	_mainPanel = new OE_GraphicPane(pathToImage);
		_mainPanel.setLayout(null);
		_mainPanel.add(_welcomeLabel);
		_mainPanel.add(_userLabel);
		_mainPanel.add(_passLabel);
		_mainPanel.add(_confirmLabel);
		_mainPanel.add(_newUserName);
		_mainPanel.add(_newPassword);
		_mainPanel.add(_confirmPassword);
		_mainPanel.add(_backButton);
		_mainPanel.add(_createButton);
		_mainPanel.add(_resetButton);
		// =============================== GENERATE FRAME, ADD CONTENT
        _frame = new JFrame(OE_ScreenConstants._Gsignature + " - Account Creation");
        _frame.setMinimumSize(_size);
        _frame.setMaximumSize(OE_ScreenConstants._screenSize);
        _frame.setContentPane(_mainPanel);
        _frame.setResizable(false);
        _frame.pack();
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
	}
	// ============================== BUTTON ACTIONS
	protected void accountCreation() {
		// If any fields are blank, stop
		if(_newUserName.getText().equals("")
				|| String.valueOf(_newPassword.getPassword()).equals("")
				|| String.valueOf(_confirmPassword.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Please fill out all fields.");
			return;
		}
		// If the passwords do not match, stop
		if(! String.valueOf(_newPassword.getPassword()).equals(String.valueOf(_confirmPassword.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Passwords do not match.");
			return;
		}
		// Create the user.
		// If Connector cannot create user, throw the pane.
		// If it can, it has been created, so let user know.
		if(!_dbCon.createUser(_newUserName.getText(), String.valueOf(_newPassword.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Username already exists.");
		}
		else {
			JOptionPane.showMessageDialog(this, "User " + _newUserName.getText() + " created!");
			resetText();
			back();
		}
	}
	
	protected void resetText() {
		_newUserName.setText("");
		_newPassword.setText("");
		_confirmPassword.setText("");
	}
	
	protected void back() {
		_frame.dispose();
		this.dispose();
	}


}