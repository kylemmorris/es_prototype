/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @since v0.1.0-a
* @version 1
* 
* Exception for any illegal card states.
*
*/

package structures.exceptions;

import java.sql.Connection;

import structures.database.*;
import structures.objects.OE_Card;
import engine.gamelogic.OE_State;

public class OE_CARD_ILLEGAL_STATE_EXCEPTION extends Exception {
	
	public OE_CARD_ILLEGAL_STATE_EXCEPTION(OE_Card problemCard, OE_State currentState) {
		super("Card '" + problemCard.name + (problemCard.isCustom() ? "(Custom): " : ": ") + "tried something with current game state: " + currentState);
	}
	
}
