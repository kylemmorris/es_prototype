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
	private enum Mode { READUSER, READCARD, WRITEUSER, WRITECARD };
	// Internal objects
	private Mode _currentMode = null;
	private String TARGET;
	private Connection conn = null;
	private ResultSet rs = null;
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
	public OE_dbCursor(String type, String target) {
		switch(type + target) {
		case "readuser":
			this._currentMode = Mode.READUSER;
		case "readcard":
			this._currentMode = Mode.READCARD;
		case "writeuser":
			this._currentMode = Mode.WRITEUSER;
		case "writecard":
			this._currentMode = Mode.WRITECARD;
		default:
			new OE_ERROR_EXCEPTION("OE_dbCursor: " + type + " " + target + " is unknown cursor type.");
		}
	}
	/**
	 * The generic constructor.
	 * The cursor's mode will be unknown, but can be changed 
	 * with setMode().
	 * Not recommended, only for unusual circumstances.
	 */
	public OE_dbCursor() {
		
	}
	
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
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:oe.db");
            conn.setAutoCommit(false);
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

    public OEabstractCard cardRead() {
    	assert(this._currentMode == Mode.READCARD);
        return card;
    }

    public OEuserData userRead() throws SQLException {
    	assert(this._currentMode == Mode.READUSER);
        // 1. Connect to Database via method
        connectToDb();
        // 2. Get a result (ALWAYS A SINGLE USER)
        System.out.println("Opened Database Successfully");
        rs = conn.createStatement().executeQuery("SELECT * FROM User WHERE (userid = '" + TARGET + "')");
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
        
        user = new OEuserData(TARGET, ps, deckid, pts, urank);
        // 4. Close all connections and return
        rs.close();
        conn.close();
        return user;
    }

    // TODO implement Runnable in the future for multi-threading
    public void kill(){}
    public void run(){}
    
    
}
















