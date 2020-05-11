/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
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
import structures.database.*;
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
    private JPanel _leftPane, _rightPane;
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
    //private OE_dbCursor _Cursor;
    private OE_dbConnector _dbcon;
    // Constants
    private String pathToImage;
    private Dimension _size = new Dimension((int)(OE_ScreenConstants._X / 3),(int)(OE_ScreenConstants._Y / 3));
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
        	else if (e.getSource() == _showPassword) {showPass();}
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
    	_showPassword.addActionListener(buttonAction);
    	// Create dbCursor
    	_dbcon = new OE_dbConnector();
    	// ================================ GENERATE PANELS
    	// main panel setup
    	pathToImage = (_root.concat("/graphics/mainBackground.jpg"));
    	_mainPanel = new OE_GraphicPane(pathToImage);
    	// =============================================================================={{{{{
    	// 					MAKE IT LOOK PRETTY SOME OTHER TIME
    	// leftmost panel -------
//    	GridBagConstraints lConst = new GridBagConstraints();
//       	_leftPane = new JPanel(new GridBagLayout());
//    	_leftPane.setBackground(Color.RED);
//    	_leftPane.setOpaque(true);
//    	lConst.fill = GridBagConstraints.NONE;
//    	lConst.anchor = GridBagConstraints.LINE_START;
//    	lConst.gridx = 0;
//    	lConst.gridy = 0;
//    	lConst.insets = new Insets(5, 0, 0, 0);
//    	_leftPane.add(_welcomeLabel, lConst);
//    	lConst.gridy = 1;
//    	_leftPane.add(_userLabel, lConst);
//
//    	// rightmost panel -------
//    	_rightPane = new JPanel(new GridBagLayout());
//    	_rightPane.setBackground(Color.BLUE);
//    	_rightPane.setOpaque(true);
//
//    	GridBagConstraints mConst = new GridBagConstraints();
//    	_mainPanel.setLayout(new GridBagLayout());
//    	mConst.fill = GridBagConstraints.NONE;
//    	mConst.ipadx = _wChisle/2;
//    	mConst.ipady = _hChisle;
//    	mConst.gridx = 0;
//    	mConst.gridy = 1;
//    	_mainPanel.add(_leftPane, mConst, JLayeredPane.FRAME_CONTENT_LAYER);
//    	mConst.gridx = 1;
//    	_mainPanel.add(_rightPane, mConst, JLayeredPane.FRAME_CONTENT_LAYER);
    	// ============================================================================= }}}}}
    	// Alignments
    	// _wChisle = 640, _hChisle = 360
    	//_loginButton.setBounds(50, 240, 100, 30);
    	_loginButton.setBounds((int)(_wChisle/12.8), (int)(_hChisle/1.5), (int)(_wChisle/6.4), (int)(_hChisle/12));
//    	//_resetButton.setBounds(200, 240, 100, 30);
    	_resetButton.setBounds((int)(_wChisle/3.2), (int)(_hChisle/1.5), (int)(_wChisle/6.4), (int)(_hChisle/12));
//    	//_createButton.setBounds(400, 100, 200, 30);
    	_createButton.setBounds((int)(_wChisle/1.6), (int)(_hChisle/3.6), (int)(_wChisle/3.2), (int)(_hChisle/12));
//    	//_welcomeLabel.setBounds(100, 20, 200, 30);
    	_welcomeLabel.setBounds((int)(_wChisle/6.4), (int)(_hChisle/18), (int)(_wChisle/3.2), (int)(_hChisle/12));
//    	//_newLabel.setBounds(425, 20, 200, 30);
    	_newLabel.setBounds((int)(_wChisle/1.5), (int)(_hChisle/18), (int)(_wChisle/3.2), (int)(_hChisle/12));
//    	//_userLabel.setBounds(50, 70, 100, 30);
    	_userLabel.setBounds((int)(_wChisle/12.8), (int)(_hChisle/5.14), (int)(_wChisle/6.4), (int)(_hChisle/12));
//    	//_userField.setBounds(150, 70, 150, 30);
    	_userField.setBounds((int)(_wChisle/4.3), (int)(_hChisle/5.14), (int)(_wChisle/4.26), (int)(_hChisle/12));
//    	//_passwordLabel.setBounds(50, 130, 100, 30);
    	_passwordLabel.setBounds((int)(_wChisle/12.8), (int)(_hChisle/2.76), (int)(_wChisle/6.4), (int)(_hChisle/12));
//    	//_passworField.setBounds(150, 130, 150, 30);
    	_passworField.setBounds((int)(_wChisle/4.3), (int)(_hChisle/2.76), (int)(_wChisle/4.26), (int)(_hChisle/12));
//    	//_showPassword.setBounds(150, 170, 250, 50);
    	_showPassword.setBounds((int)(_wChisle/4.3), (int)(_hChisle/2.117), (int)(_wChisle/2.56), (int)(_hChisle/7.2));

    	 //Add it all to the panel
    	_mainPanel.setLayout(null);
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
        _frame = new JFrame(OE_ScreenConstants._Gsignature);
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
    	try {
    		//attempt to get the user's info
    		_tempData = _dbcon.userRead(_userField.getText());
    	} catch(SQLException e) {
    		System.out.println(e.getMessage());
    		JOptionPane.showMessageDialog(this, "Username not found.");
    		return;
    	}
    	// User exists, so passwords must match too.
    	// Note .getPassword() is a char[] array. So use String instance.
    	// TODO Scramble Password for security.
		String _tempPassString = String.valueOf(_passworField.getPassword());
    	System.out.println("PASS :::: " + _tempPassString);
    	if(_tempData.getString("password").equals(_tempPassString)) {
    		// Update all constants
    		OE_GameConstants._CURRENTUSER_ = _tempData;
    		OE_ScreenConstants._GuserID = _tempData.getString("id");
    		OE_GameConstants._CURRENTMENU_ = new OE_MainMenu();
    		_frame.dispose();
    		this.dispose();

    	}
    	// If not, this can't be a legitimate password
    	else {
    		_passworField.setText("");
    		JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
    		return;
    	}
    }
    protected void resetFields() {
    	_userField.setText("");
    	_passworField.setText("");
    	
    }
    protected void showPass() {
    	if(_showPassword.isSelected()) _passworField.setEchoChar((char) 0);
    	else _passworField.setEchoChar('*');
    }
    protected void accountCreation() {
    	OE_GameConstants._CURRENTMENU_ = new OE_AccountCreationMenu();
    }
}
    
    
    
    
    
    
    
    