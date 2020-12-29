/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @since v0.1.0-a
* @version 1
* 
* This exception will be used for any card interpretation errors.
* 
* TODO This exception will be more robust and provide more information, similar to an actual interpreter.
* 
* For now it prints like this:
* 
* OE_CARD_ERROR_EXCEPTION: Card Interpreter Error: 'Nurse Bot'
* 
* or
* 
* OE_CARD_ERROR_EXCEPTION: Card Interpreter Error(Custom Card): 'Nurse Bot 2'
*
*/

package structures.exceptions;

import structures.database.*;
import structures.objects.OE_Card;

public class OE_CARD_ERROR_EXCEPTION extends Exception {
	
	public OE_CARD_ERROR_EXCEPTION(OE_Card problemCard) {
		super("Card Interpreter Error" + (problemCard.isCustom() ? "(Custom Card): '" : ": '") + problemCard.name + "'");
	}
	
}
