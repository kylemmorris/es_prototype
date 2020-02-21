/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package structures.database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OE_dbWriter {
	// Current mode
	private int _currentMode = -1;
	// Different modes
	private final int USERMODE = 0;
	private final int CARDMODE = 1;
	// Database objects
	private String TARGET;
	private Connection conn = null;
	private ResultSet rs = null;
	// Returns
	
}