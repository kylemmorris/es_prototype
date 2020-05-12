/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/

package engine;
import java.awt.Container;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;

import graphics.screens.OE_GraphicPane;
import graphics.screens.OE_StartMenu;
import engine.OE_DataTypes.*;
import structures.database.OEuserData;

/**
 * Wrapper object for the main data structures.
 * Uses the data types defined in OE_GameConstants.
 * Normal play will instantiate this composite object.
 * Use facade, composite and factory pattern.
 * 
 * @author Kyle M. Morris
 * @since 0.0.1
 * @see structures.OE_GameConstants
 */
public class OE_GameStruct {
	// Datapoints to maintain
	private OEuserData _player, _opponent;
	private String[] _cardCache;							//TODO Change this to private OE_abstractCard cardCache;
	private Container _uField, _opField; 					//TODO Change this to private OE_field field1, field2;
															//Note that OE_Fields will contain all the zones, etc (other data types)
	private int _uLife, _opLife;							// Life points
	private Tuple<Fellowship, Integer>[] _uRpool, _opRpool;	// Resource pools for each player
	private Phases _phase;									// Current phase of play
	private int _turnNum;									// Current turn
	private Stack<OE_Effect> _EFStack;						//TODO Implement OE_Effects
	
	/**
	 * How the initial game is started.
	 * Both user and opponent's data are taken. Next, the mode (which determines initialization),
	 * and finally the names of both decks being used. 
	 * Once the deck objects are found, they will be integrated and set here.
	 */
	public OE_GameStruct(OEuserData userData, OEuserData opData, String mode, String userDeckName, String opDeckName) {
		// Initialization depends on mode.
		// Current modes: Solo mode, Campaign (Single Player), LAN (Multiplayer).
		
		switch(mode) {
		case "solo":
			this._player = userData;
			this._opponent = null;
			this._uLife = 20;
			this._opLife = 20;
			//this._uField = new _uField();
			//this._opField = new _opField("empty");
			break;
		case "campaign":
			this._player = userData;
			//this._opponent = userData.getLevel();
			this._uLife = 20;
			this._opLife = 20;
			//this._uField = new _uField();
			//this._opField = new _opField("empty");
			break;
		default:
			System.exit(-1);
		}
	}
}















