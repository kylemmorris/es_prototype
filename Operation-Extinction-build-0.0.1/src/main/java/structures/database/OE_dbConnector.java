package structures.database;
import java.awt.Component;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;

import javax.naming.OperationNotSupportedException;
import javax.swing.JOptionPane;

import org.sqlite.SQLiteException;

import structures.OE_Data;
import structures.OE_ERROR_EXCEPTION;
import structures.OE_GameConstants;

/**
 * 
 * OE_dbConnector connects to the SQLite3 database of cards/users.
 * Used to be OE_dbCursor, which contained different modes (READUSER,
 * WRITEUSER, etc) which have been deprecated for a more simple model.
 * 
 * The new Connector simply has methods that do what the piece of code
 * that's using it wants it to do. 
 * 
 * The 3 fundamental operations are:
 * 
 * Creating a new User / new Card
 * Updating a User / Card
 * Querying a User / Card.
 * 
 * @since 0.0.1
 */
public class OE_dbConnector {
	// Constants and returns
	private String JDBC_URL = "jdbc:sqlite:oe.db";
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement stmt = null;
	private OEuserData _user;
	private OEabstractCard _card;
	
	// Only need a default constructor.
	public OE_dbConnector() {}
	
	// Method for connecting to the database
	private void connect() {
        try{
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection(JDBC_URL);
            //this.conn.setAutoCommit(false);
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
			try {
				conn.close();
			} catch(SQLException e2) {
				System.err.println(e2.getMessage());
				System.exit(-1);
			}
        }
	}
	
	// ======================================== FUNCTIONS
	/**
	 * Given a username, queries the User table and returns
	 * an OEuserData wrapper object associated with that 
	 * account. Throws SQLException if that user is not found.
	 * Exceptions must be handled by caller.
	 * @throws SQLException
	 * @param TARGET
	 * @return OEuserData
	 * @see OEuserData
	 */
	public OEuserData userRead(String TARGET) throws SQLException{
		try {
			// 1. Connect to DB
			this.connect();
			// 2. Prepare statement SELECT * FROM User WHERE userid = 'TARGET'
			this.stmt = this.conn.prepareStatement("SELECT * FROM User WHERE userid = '" + TARGET + "'");
			// 3. Execute
			this.rs = stmt.executeQuery();
			// 4. Get results
			String tempid = this.rs.getString("userid");
			String tempass = this.rs.getString("userpass");
			int tempdeckid = this.rs.getInt("userdeckid");
			int temppts = this.rs.getInt("userpts");
			int temprank = this.rs.getInt("userrank");
			Timestamp tempdatejoin = this.rs.getTimestamp("userdatejoin");
			// 5. Create and return wrapper
			this._user = new OEuserData(tempid, tempass, tempdeckid, temppts, tempdatejoin, temprank);
		}catch(SQLException e) {
			// If user doesn't exist
			// Calling program must handle exception
			throw e;
		}finally {
			// Either way, ALWAYS CLOSE CONNECTION
			try {
				this.conn.close();
			} catch(SQLException e) {
				System.err.println(e.getMessage());
				System.exit(-1);
			}
		}
		return this._user;
	}
	/**
	 * Given a desired ID and password, first checks if that username
	 * already exists. If it doesn't, it will create that user & return true
	 * If it does, it will return false
	 * @param newID
	 * @param newPass
	 * @return boolean
	 */
	public boolean createUser(String newID, String newPass) {
		// 1. Connect to DB
		connect();
		// 2. Now we check for username uniqueness.
		// The following query MUST return nothing. 
		// Else, an exception is thrown.
		try {
			this.stmt = this.conn.prepareStatement("SELECT * FROM User WHERE userid = '" + newID + "'");
			this.rs = stmt.executeQuery();
			// RETURN FALSE IF IT EXISTS
			if(this.rs.next())
				return false;
		} catch(SQLException e) {
			// Username already exists, close conn
			JOptionPane.showMessageDialog((Component) OE_GameConstants._CURRENTMENU_, "There was an issue (ERROR dbConnector:129)");
			try {
				this.conn.close();
			} catch(SQLException f) {}
		}
		// 3. From this point on, username is new. So create
		try {
			// 4. Prepare statement
			this.stmt = this.conn.prepareStatement("INSERT INTO User (userid, userpass, userdeckid, userpts, userdatejoin, userrank, userpfppath)"
					+ " VALUES(?, ?, ?, ?, ?, ?, NULL)");
			this.stmt.setString(1, newID);
			this.stmt.setString(2, newPass);
			this.stmt.setInt(3, new Random().nextInt(100));
			this.stmt.setInt(4, 0);
			this.stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			this.stmt.setInt(6, 0);
			
			// 5. Execute
			this.stmt.executeUpdate();
			
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		} finally {
			// ALWAYS CLOSE CONNECTION
			try {
				this.conn.close();
			} catch(SQLException e) {
				System.err.println(e.getMessage());
				System.exit(-1);
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}