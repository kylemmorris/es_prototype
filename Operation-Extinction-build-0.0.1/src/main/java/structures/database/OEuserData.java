/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package structures.database;

import java.io.*;
import java.sql.*;

import structures.OE_Data;

/**
 * A small container object for the current user's specific data.
 * <p>Contains: getString(), getInt(), getProfilePic(), and getDateJoined().</p>
 * <p>Inputs for these include: "id", "password", "deck_ID", "points", and "rank".</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 * 
 */
public class OEuserData implements OE_Data {
    // TODO Uses hashing for private material. Will be thread-safe.
	private String signature;
	private OE_dbConnector _dbCon;
	private boolean altered;
	
    private String id;
    private String password;
    private int deck_ID;
    private int points;
    private Timestamp joinDate;
    private int rank;
    private String pfpPath;

    // Constructors
    public OEuserData(String id, String password, int deck_ID, int points, Timestamp joinDate, int rank) {
    	// Change variable
    	this.altered = false;
    	// Cannot change
        this.id = id;
        this.deck_ID = deck_ID;
        this.joinDate = joinDate;
        // Could change
        this.password = password;
        this.points = points;
        this.rank = rank;
        //this.pfpPath = pfpPath;
    }
    /**
     * If the data has been altered, calling this method
     * will push the changes to the database.
     * <p>Possible inputs: "password", "points", "rank", or "pfp".
     * Will update the inputed attribute.
     */
    public void updateData(String s) {
    	if(this.altered) {
    		// push to db
    		this.altered = false;
    	}
    }
    public boolean needsUpdate() {
    	if(this.altered)
    		return true;
    	return false;
    }
    /**
     * Gets the Username or Password of the user.
     * <p>Possible inputs: "id", "password", or "pfppath".</p>
     * <p>Returns "ERROR:OEuserData.getString() FAILED" on failure.</p>
     * @param s
     * @return String
     */
    public String getString(String s) {
        if(s == "id") return this.id;
        else if(s == "password") return this.password;
        else if (s == "pfppath") return this.pfpPath;
        else return "ERROR:OEUserData.getString() FAILED";
    }
    /**
     * Gets the DeckID, Points and Rank of the user.
     * <p>Possible inputs: "deck_ID", "points, "rank".</p>
     * <p>Returns -1 on failure.</p>
     * @param s
     * @return int
     */
    public int getInt(String s) {
        if(s == "deck_ID") return this.deck_ID;
        else if(s == "points") return this.points;
        else if(s == "rank") return this.rank;
        else return -1;
    }
    /**
     * Gets the join date of the user, as a Date.
     * @return Date
     */
    public Timestamp getDateJoined(){
        return this.joinDate;
    }
    
    public String getSig() {
    	return "okay";
    }
    
    public String getHashedSig() {
    	return "hgb";
    }
    
    public void transmit() {
    }
}