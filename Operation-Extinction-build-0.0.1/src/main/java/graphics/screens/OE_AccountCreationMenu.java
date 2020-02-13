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
import structures.database.OE_dbReader;

public class OE_AccountCreationMenu implements Menu{
	// Frame
	private JFrame _frame;
	// Panels
	private OE_GraphicPane _mainPanel;
	// Buttons
	private JButton _backButton;
	// Areas
	private JTextField _newUserName;
	private JPasswordField _newPassword, _confirmPassword;
	// Label
	// Database Information
	private OEuserData _tempData;
	private OE_dbReader _reader;
	
}