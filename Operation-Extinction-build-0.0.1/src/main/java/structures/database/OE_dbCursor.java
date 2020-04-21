/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package structures.database;

import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.OperationNotSupportedException;

import org.sqlite.SQLiteException;

import structures.OE_Data;
import structures.OE_ERROR_EXCEPTION;

/**
 * A cursor object implementation that updates the main database.
 * At this point, no OE_dbReaders are allowed to execute, thus
 * the code is synchronized.
 * Only every returns boolean for whether it was successful or not.
 * <p>Implements <code>Runnable</code> for multi-threading (future).</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 * @return boolean
 * @see OE_dbReader
 *
 */
public class OE_dbCursor {
	// Different modes the cursor can take
	public enum Mode { READUSER, READCARD, WRITEUSER, WRITECARD };
	// Internal objects
	private Mode _currentMode = null;
	private String TARGET;
	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	// Return objects
	private OEabstractCard card;
	private OEuserData user;
	// ================================ CONSTRUCTORS
	/**
	 * The default constructor.
	 * Acceptable inputs include "read", "write", "user", and "card".
	 * If you use this constructor, you MUST have 2 parameters.
	 * @param String type
	 * @param String target
	 */
	public OE_dbCursor(Mode m) {
		this._currentMode = m;
	}
	/**
	 * The generic constructor.
	 * The cursor's mode will be unknown, but can be changed 
	 * with setMode().
	 * Not recommended, only for unusual circumstances.
	 */
	public OE_dbCursor() {
		
	}

	// ========================================= ESSENTIALS
	
	public void setMode(Mode m) {
		this._currentMode = m;
	}
	
	public boolean setInput(String input) {
		if(this._currentMode != null) {
			this.TARGET = input;
			return true;
		}
		return false;
	}
	
    private void connectToDb() {
    	// TODO Add connection to different databases
        try{
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:oe.db");
            this.conn.setAutoCommit(false);
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

    // ========================================= CARD TOOLS
    
    public OEabstractCard cardRead() {
    	assert(this._currentMode == Mode.READCARD);
        return card;
    }
    
    // ========================================= USER TOOLS
    
    /**
    *  Returns true or false, depending on if the
    *  TARGET User is in the database. <b>Must be in READUSER mode.</b>
    */
    public boolean userExists() {
    	assert(this._currentMode == Mode.READUSER);
    	// 1. Connect to Database via method
    	connectToDb();
    	// 2. Attempt to execute the query
    	// If we get an exception, we return false.
    	// Else, true
    	try {
    		String sqlString = "SELECT userid FROM User WHERE userid='" + TARGET + "'";
    		System.out.println(sqlString);
    		conn.createStatement().executeQuery(sqlString);
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    		try{
    			conn.close();
    		} catch(SQLException e1) {
    			System.out.println("FATAL ERROR: DATABASE CONNECTIONS FAILED TO CLOSE");
    			System.exit(-1);
    		}
    		return false;
    	}
    	try {
    		conn.close();
    	} catch(SQLException e) {
    		System.out.println("FATAL ERROR: DATABASE CONNECTIONS FAILED TO CLOSE");
    		System.exit(-1);
    	}
    	return true;
    }
    
    /**
    *  Given the TARGET, and in WRITEUSER mode, returns the database entry associated with that
    *  User ID (the TARGET).
    *  @return OEuserData
    *  @throws SQLException
    */
    public OEuserData userRead() throws SQLException {
    	assert(this._currentMode == Mode.WRITEUSER);
        // 1. Connect to Database via method
        connectToDb();
        // 2. Get a result (ALWAYS A SINGLE USER)
        System.out.println("Opened Database Successfully");
        this.rs = conn.createStatement().executeQuery("SELECT * FROM User WHERE (userid = '" + TARGET + "')");
        // 3. Fetch user information
        String id = rs.getString("userid");
        System.out.println("userid = " + id);
        String ps = rs.getString("userpass");
        System.out.println("userpass = " + ps);
        int deckid = rs.getInt("userdeckid");
        System.out.println("userdeckid = " + deckid);
        //Blob pfp = rs.getBlob("userpfp");
        //System.out.println("Got userpfp");
        int pts = rs.getInt("userpts");
        System.out.println("userpts = " + pts);
        //Date datejoin = rs.getDate("userdatejoin");
        //System.out.println("Got userdatejoin");
        int urank = rs.getInt("userrank");
        System.out.println("userrank = " + urank);
        
        this.user = new OEuserData(TARGET, ps, deckid, pts, urank);
        // 4. Close all connections and return
        this.rs.close();
        this.conn.close();
        return user;
    }
    
    
    /**
    *  Given the password, creates a new entry in the User table.
    *  Username and Password are set by the user; the other parameters are
    *  set to default values.
    *  Returns true if successful, false otherwise. Shouldn't ever return false.
    *  @param setPass
    *  @return boolean
    */
    public boolean userCreate(String setPass) {
    	// We must be in the write user mode
    	// And user doesn't exist
    	assert(this._currentMode == Mode.WRITEUSER);
    	// The only parts the user controls are userid and userpass. The rest are default.
    	String sql = "INSERT INTO User( userid,userpass,userdeckid,userpfp,userpts,userdatejoin,userrank)"
    			+ " VALUES ('" + TARGET + "','" + setPass + "',300,null,0,'" 
    				+ java.sql.Date.valueOf(java.time.LocalDate.now()) + "',0)";
    	System.out.println("userCreate: " + sql);
    	// 1. Connect to db
        connectToDb();
    	System.out.println("userCreate: Connected to db successfully");
    	// 2. Execute the SQL
    	/**
    	 * userid = the TARGET primary key
    	 * userpass = defined previously
    	 * userdeckid = TODO deprecated; instead, make it a foreign key to the userid, as the primary key to the decks
    	 * userpfp = set to null
    	 */
    	boolean result = true;
    	try {
    		stmt = conn.createStatement();
    		stmt.executeUpdate(sql);
    	}catch (SQLException se) {
    		se.printStackTrace();
			result = false;
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		// proper cleanup
		}finally {
			try {
				if(stmt != null) conn.close();
			} catch(SQLException se) {}
			try {
				if(conn != null) conn.close();
			} catch(SQLException se) {}
		}
    	return result;
    }

    // TODO implement Runnable in the future for multi-threading
    
    
}
















