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
 * A small container object for the current user's specific data.
 * Uses hashing for private / sensitive material.
 * @author Kyle M. Morris
 * @since 0.0.1
 * 
 */
public class OEuserData {
    private String id;
    private String password;
    private int deck_ID;
    private Blob profilePic;
    private int points;
    private Date joinDate;
    private int rank;
}