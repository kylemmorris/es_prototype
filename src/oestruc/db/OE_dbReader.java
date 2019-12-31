/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package oestruc.db;

import java.io.*;
import java.sql.*;

/**
 * A cursor object implementation that sends queries
 * to the main database and returns requested data in the form
 * of an <code>OE_dbData</code> object. If the query fails, it will
 * throw <code>QueryException</code>.
 * The linker has multiple modes that must be set
 * before querying.
 * <p>Implements <code>Runnable</code> for multi-threading (future).</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 * @throws QueryException
 * @return OEabstractCard, OEuserData
 * @see oestruc.db.QueryException
 * 
 */
public class OE_dbReader {
    // Current mode
    private int _currentMode = -1;
    // Different modes
    public final int USERMODE = 0;
    public final int CARDMODE = 1;
    // Database Stuff
    private String TARGET;
    private Connection conn = null;
    private Statement stmt = null;
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

    public OEabstractCard cardRead(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqite:test.db");
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        return card;
    }

    public OEuserData userRead() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqite:test.db");
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        return user;
    }

    // implement Runnable in the future for multi-threading
    public void kill(){}
    public void run(){}
}