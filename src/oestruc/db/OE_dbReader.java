/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package oestruc.db;

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
    public static final int USERMODE = 0;
    public static final int CARDMODE = 1;
    // Database objects
    private String TARGET;
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    // Returns
    private OEabstractCard card = null;
    private OEuserData user = null;
    // ================================ CONSTRUCTORS
    public OE_dbReader() {
    }

    public boolean setMode(int m) {
        // Setter method for setting what kind of data we are looking for.
        if(m != USERMODE || m != CARDMODE){
            return false;
        }
        this._currentMode = m;
        return true;
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
        
        final String DB_URL = "jdbc:sqlite:oe.db";
        try{
            Class.forName("org.");
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(false);
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
    }

    public OEabstractCard cardRead(){
        return card;
    }

    public OEuserData userRead() throws SQLException {
        // 1. Connect to Database via method
        connectToDb();
        // 2. Create a statement
        stmt = conn.createStatement();
        // 3. Get a result (ALWAYS A SINGLE USER)
        rs = stmt.executeQuery("SELECT " + TARGET + " FROM USER");
        // 4. Fetch user information
        String id = rs.getString("USER_ID");
        String ps = rs.getString("USER_PASS");
        int deckid = rs.getInt("USER_DECK_ID");
        Blob pfp = rs.getBlob("USER_PFP");
        int pts = rs.getInt("USER_PTS");
        Date datejoin = rs.getDate("USER_DATEJOIN");
        int urank = rs.getInt("USER_RANK");
        user = new OEuserData(id, ps, deckid, pfp, pts, datejoin, urank);
        // 5. Close all connections and return
        rs.close();
        stmt.close();
        conn.close();
        return user;
    }

    // TODO implement Runnable in the future for multi-threading
    public void kill(){}
    public void run(){}
}