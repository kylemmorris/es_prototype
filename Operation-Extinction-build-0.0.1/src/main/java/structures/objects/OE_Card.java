/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @since v0.1.0-a
* @version 1
* 
*/

package structures.objects;

import graphics.screens.OE_Texture;

/**
* OE_Card - 
* 	<p>A class for every card type to inherit from. </p>
* 	<p>It contains basic state information about any card: </p>
* 	<p> ID, name, cost, resource number, fellowship, type, effect object and texture.</p>
* 	<p>It contains the concrete methods each card performs, regardless of type:</p>
* 	<p>-------------------------</p>
* 	<p>	1. public OE_Card() 		 		  - Superclass constructor. All cards have a name, cost, resource number, type, and card text. </p>
* 	<p> 2. public boolean canPlay()  		  - Returns whether this card can be played or not.  </p>
* 	<p> 3. public void render()				  - Renders the card object.  </p>
* 	<p> 4. public OE_IZObject effectActivate()- Runs the interpreter with the 'create' command, to create an Imaginery Zone object. </p>
* 	<p> 5. public boolean isCustom()		  - At initialization, marks the card as 'custom'. Treated differently in the game. </p>
* 	<p> 6. public abstract boolean canActivate() - Attempts to activate the card. Returns false if you can't. </p>
* 	<p> 7. public abstract boolean canMove()  - Returns whether this card can be moved or not. </p>
* 	<p> 8. public abstract void play()		  - Begins the process of playing the card. </p>
* 
* @throws OE_CARD_DATABASE_EXCEPTION
* @throws OE_CARD_ERROR_EXCEPTION
* @throws OE_CARD_RENDER_EXCEPTION
* @throws OE_CARD_CUSTOM_EXCEPTION
* @throws OE_CARD_ILLEGAL_STATE_EXCEPTION
* 
* @author Kyle M. Morris
* @since v0.1.0-a
* @version 1
* 
*/
public abstract class OE_Card {
	// public OE_Card(int ID, string name, int cost, int rNumber, OE_FellowshipEnum, OE_TypeEnum type, OE_Effect, effect, OE_Texture texture) {}
	// 	OR
	// public OE_Card(OE_dbConnector conn) {}
	// public boolean canPlay(OE_ResourcePool myPool) {}
	// public boolean activate() {}
	
	
	// *********************************
	// General data other files should be able to access
	// *********************************
	public String name;
	public int cost;
	public int rNumber;
	//public OE_FellowshipEnum fellowship;
	//public OE_TypeEnum type;
	//public OE_Effect effect;
	public OE_Texture texture;
	
	
	// *********************************
	// Private data, relating to internal state
	// *********************************
	private int _id = -1;			// <- (-1) means the card has not been instantiated
	private boolean _activated = false;
	private boolean _moved = false;
	private boolean _custom;
	
	
	public OE_Card() {
		
	}
	
	
	// *********************************
	// Abstract methods 
	// Some cards can/can't activate or be moved.
	// And, each card is played differently.
	// *********************************
	public abstract boolean canActivate();
	public abstract boolean canMove();
	public abstract void play();
}
