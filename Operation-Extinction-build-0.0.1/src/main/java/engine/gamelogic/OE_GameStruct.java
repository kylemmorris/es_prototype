/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/

package engine.gamelogic;
import java.util.HashMap;

import engine.OE_DataTypes;

/**
 * The main structure, containing all the current game data.
 * The OE_GameLoop class will be acting on this structure, along with
 * the restrictions based on OE_State.
 * Internally, it contains primitives, complexes, and specific data types
 * specified in OE_DataTypes.
 * @author Kyle Morris
 * @since 0.0.1
 * @see OE_GameLoop
 * @see OE_State
 * @see OE_DataTypes
 *
 */

/**
 * OE_GameStruct - 
 * 	<p>This object manages the current user's</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 */
public class OE_GameStruct {
	public static String Version = "v1";
	public OE_State currentState;
	public int p1Life,p2Life,turnNum,p1HandSize,p2HandSize,p1HandCount,p2HandCount,
	p1DeckCount,p2DeckCount,p1GraveCount,p2GraveCount;
	//public HashMap<OE_DataTypes.BoardID, OE_DataTypes.CardID> CardsOnBoard;
	public HashMap<Integer,Integer> cardsOnBoard;
	public boolean replaced, switched, resourced;
	
	public OE_GameStruct() {}
	
	public void configure(int startingLife, int startingHandSize) {
		p1Life = startingLife;
		p2Life = startingLife;
		p1HandSize = startingHandSize;
		p2HandSize = startingHandSize;
		turnNum = 1;
		replaced = false;
		switched = false;
		resourced = false;
	}
}











