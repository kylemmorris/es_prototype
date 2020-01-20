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

/**
 * A cursor object implementation that sends queries
 * to the main database and returns requested data in the form
 * of an <code>OE_dbData</code> object. If the query fails, it will
 * throw <code>SQLException</code>.
 * The linker has multiple modes that must be set
 * before querying.
 * <p>Implements <code>Runnable</code> for multi-threading (future).</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 * @throws SQLException
 * @return OEabstractCard, OEuserData
 */
public class OE_dbReader {
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
    private OEabstractCard card = null;
    private OEuserData user = null;
    // ================================ CONSTRUCTORS
    public OE_dbReader() {
    }
    
    public void initUserMode() {
    	this._currentMode = USERMODE;
    }
    
    public void initCardMode() {
    	this._currentMode = CARDMODE;
    }

    public boolean setKeyInput(String s){
        // To find a user, the input is a string by default (string ID is primary key).
        // For card, however, the input string must be parsed.
        
        // EXAMPLE:
        //      USERMODE:   "KyletheGameGuy"
        //      CARDMODE:   "B1-0-0001" -> [oedb.B1Cards.0001].getSignature()

        if(_currentMode == USERMODE || _currentMode == CARDMODE) {
            TARGET = s;
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

    public OEabstractCard cardRead(){
        return card;
    }

    public OEuserData userRead() throws SQLException {
        // 1. Connect to Database via method
        connectToDb();
        // 2. Get a result (ALWAYS A SINGLE USER)
        System.out.println("Opened Database Successfully");
        rs = conn.createStatement().executeQuery("SELECT * FROM User WHERE (userid = '" + TARGET + "')");
        // 3. Fetch user information
        String id = rs.getString("userid");
        System.out.println("Got userid");
        String ps = rs.getString("userpass");
        System.out.println("Got userpass");
        int deckid = rs.getInt("userdeckid");
        System.out.println("Got userdeckid");
        //Blob pfp = rs.getBlob("userpfp");
        //System.out.println("Got userpfp");
        int pts = rs.getInt("userpts");
        System.out.println("Got userpts");
        Date datejoin = rs.getDate("userdatejoin");
        System.out.println("Got userdatejoin");
        int urank = rs.getInt("userrank");
        System.out.println("Got userrank");
        
        user = new OEuserData(TARGET, ps, deckid, pts, datejoin, urank);
        // 4. Close all connections and return
        rs.close();
        conn.close();
        return user;
    }

    // TODO implement Runnable in the future for multi-threading
    public void kill(){}
    public void run(){}
}