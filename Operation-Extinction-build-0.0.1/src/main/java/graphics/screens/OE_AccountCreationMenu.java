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

import graphics.OE_ScreenConstants;
import structures.database.OEuserData;
import structures.OE_GameConstants;
import structures.database.OE_dbCursor;

public class OE_AccountCreationMenu implements Menu {
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
    private String _signature = "Operation Extinction - " + OE_ScreenConstants._Gversion + " - Account Creation";
    private int _wChisle = (int) _size.getWidth();
    private int _hChisle = (int) _size.getHeight();
    private String pathToImage;
    // Data
    private OEuserData _tempData;
    private OE_dbCursor _Cursor;
    
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
		_Cursor = new OE_dbCursor();
		
		// =============================== GENERATE PANELS
		// =============================== GENERATE FRAME, ADD CONTENT
		_frame = new JFrame(_signature);
	}
	// ============================== BUTTON ACTIONS
	protected void accountCreation() {
		
	}
	protected void resetText() {
		
	}
	protected void back() {
		
	}


}