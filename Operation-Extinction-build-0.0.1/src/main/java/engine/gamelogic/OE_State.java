/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*/

package engine.gamelogic;

/**
 * <p> An enumeration containing the state of the game. </p>
 * <p> It contains the initial states and the game-looping states. </p>
 * @author keylime_py
 *
 */
public enum OE_State {
	//Initial states
	Initial, Loading, Config, Start,
	//Looping states
	Player_Select, Draw, Dawn, Main_1,
	//This is where the states branch
	Passive, 
	//OR
	Battle, ATKDeclare, Damage_1,
	//Maybe
	Counter, Damage_2,
	//The rest
	Main_2, Fortify, Dusk;

	/**
	 * This refers to the last known state.
	 * Use this for the game logic as such:
	 * <p>if prev == Main_1</p>
	 * <p>	if (want to attack)</p>
	 * <p>		state = Battle </p>
	 * <p>	else </p>
	 * <p>		state = Main_2 </p>
	 * <p>if prev == Damage_1 </p>
	 * <p>	if (opp wants counter) </p>
	 * <p>		state = Counter </p>
	 * <p>	else </p>
	 * <p>		state = Main_2 </p>
	 * <p>end</p>
	 */
	public static OE_State prev;
	
	public OE_State nextState() {
		// Set the 'previous' reference
		OE_State.prev = OE_State.values()[ordinal()];
		// If it's the last state, rollover to the PlayerSelect
		if(ordinal() == values().length - 1)
			return OE_State.Player_Select;
		// Else, go to the next one
		return OE_State.values()[ordinal() + 1];
	}
}









