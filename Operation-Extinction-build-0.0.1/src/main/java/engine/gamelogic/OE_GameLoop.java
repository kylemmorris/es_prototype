/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
* Abstract loop structure:
* while(currState != OE_State.End || currState != OE_State.ERROR) {
* 	switch(currState) {
* 		case OE_State.Initial:
* 			initialize(); break;
* 		case OE_State.Load:
* 			load(); break;
* 		...
* 	};
* 	currState = currState.next();
* }
*/

package engine.gamelogic;
import engine.OE_DataTypes;
import engine.gamelogic.OE_GameStruct;
import engine.gamelogic.OE_State;
import structures.database.OE_dbConnector;

public class OE_GameLoop {
	private OE_State currState;			// The current game state
	private OE_GameStruct game;			// The game struct containing player data
	//private OE_Board board;
	//private OE_Deck deck;
	//private OE_Hand hand;
	//private OE_Interp interp;
	//private OE_IZ iz;
	//private OE_DataTypes.List passiveList;
	//private OE_DataTypes.List triggerList;
	//private OE_DataTypes.List activateList;
	//private OE_FileAccess pointer;
	private OE_dbConnector cursor;		// The connection to the local database
	//private OE_Logger logger;
	
	
	public OE_GameLoop() {
		currState = OE_State.Initial;
	}
	
	// --------------------
	// The following functions are in the starting stages
	// --------------------
	
	/**
	 * init() is the first method called on the OE_GameLoop.
	 * It will do the following:
	 * 1. Create the OE_GameStruct object.
	 * 2. Create the OE_Board object.
	 * 3. Create the OE_Hand object.
	 * 4. Create the OE_Interp object.
	 * 5. Create the OE_IZ object.
	 * 6. Initialize all OE_DataTypes.List objects.
	 * 7. Create the OE_FileAccess object.
	 * 8. Create and initialize the OE_dbConnector object.
	 * 9. Create the OE_Logger object, and log an initial message.
	 * 10.After all the previous have been complete, call OE_State.next();
	 * 
	 * If any object does not initialize properly, it will return 0.
	 * The state will be OE_State.Initial.
	 * @return 1 on success, 0 on failure
	 */
	public int init() {
		game = new OE_GameStruct();
		return 0;
	}
	public int load() {
		return 0;
	}
	public int config() {
		return 0;
	}
	public int start() {
		return 0;
	}

}















