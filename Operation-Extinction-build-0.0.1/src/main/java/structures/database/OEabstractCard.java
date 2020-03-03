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
 * The OE card. Contains attributes that all cards share. Dynamically
 * created at runtime to store card data from the database.
 * <b>Note</b> that this is not a grapical object, though it does interact
 * with graphics.
 * @author Kyle M. Morris
 * @since 0.0.1
 * @see OEinterpreter
 */
public class OEabstractCard implements OE_Data {
    public String getSig() {
    	return "okay";
    }
    
    public String getHashedSig() {
    	return "hgb";
    }
    
    public void transmit() {
    }
}