/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @since v0.1.0-a
* @version 1
* 
* Exception for any database errors (retrieval, editng, etc).
* It will print what card had the issue, and what command produced the error.
* 
* Example:
* 
* OE_CARD_DATABASE_EXCEPTION: Card name: 'Nurse Bot' - from command = SELECT * FROM ....
*
*/

package structures.exceptions;

import java.sql.Connection;

import structures.database.*;
import structures.objects.OE_Card;

public class OE_CARD_DATABASE_EXCEPTION extends Exception {
	
	public OE_CARD_DATABASE_EXCEPTION(OE_Card problemCard, String SQLCommand) {
		super("Card name: '" + problemCard.name + "' - from command = " + SQLCommand);
	}
	
}
