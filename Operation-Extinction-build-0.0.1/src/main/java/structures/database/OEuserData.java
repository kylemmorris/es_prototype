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

/**
 * A small container object for the current user's specific data.
 * <p>Contains: getString(), getInt(), getProfilePic(), and getDateJoined().</p>
 * <p>Inputs for these include: "id", "password", "deck_ID", "points", and "rank".</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 * 
 */
public class OEuserData {
    // TODO Uses hashing for private material. Will be thread-safe.
    private String id;
    private String password;
    private int deck_ID;
    private Blob profilePic;
    private int points;
    private Date joinDate;
    private int rank;

    // Constructors
    public OEuserData(String id, String password, int deck_ID, Blob profilePic, int points, Date joinDate, int rank) {
        this.id = id;
        this.password = password;
        this.deck_ID = deck_ID;
        this.profilePic = profilePic;
        this.points = points;
        this.joinDate = joinDate;
        this.rank = rank;
    }
    public OEuserData(String id, String password, int deck_ID, int points, Date joinDate, int rank) {
        this.id = id;
        this.password = password;
        this.deck_ID = deck_ID;
        this.points = points;
        this.joinDate = joinDate;
        this.rank = rank;
    }
    public OEuserData(String id, String password, int deck_ID, int points, int rank) {
        this.id = id;
        this.password = password;
        this.deck_ID = deck_ID;
        this.points = points;
        this.rank = rank;
    }
    /**
     * Gets the Username or Password of the user.
     * <p>Possible inputs: "id", "password".</p>
     * <p>Returns "ERROR:OEuserData.getString() FAILED" on failure.</p>
     * @param s
     * @return String
     */
    public String getString(String s) {
        if(s == "id") return this.id;
        else if(s == "password") return this.password;
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
     * Gets the profile picture of the user, as a BLOB.
     * @return Blob
     */
    public Blob getProfilePic(){
        return this.profilePic;
    }
    /**
     * Gets the join date of the user, as a Date.
     * @return Date
     */
    public Date getDateJoined(){
        return this.joinDate;
    }
}