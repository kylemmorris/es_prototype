/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
* Board layout:
* 
* 		-----------------------------
* 	   |                             |
*	-5 |  X     X     X     X     X  |
*      |-----------------------------|
* 	-4 |     |     |     |     |     |
*      |-----------------------------|
* 	-3 |  X  |     |     |     |     |		(P2)
*      |-----------------------------|
* 	-2 |  X  |     |     |     |     |
*      |-----------------------------|
* 	-1 |  X  |     |     |     |     |
*      |-----------------------------|
*	 >>   A  |  B  |  C  |  D  |  E   <<
*	   |-----------------------------|
* 	 1 |     |     |     |     |  X  |
* 	   |-----------------------------|
* 	 2 |     |     |     |     |  X  |
*      |-----------------------------|
* 	 3 |     |     |     |     |  X  |		(P1)
*      |-----------------------------|
* 	 4 |     |     |     |     |     |
*      |-----------------------------|
* 	 5 |  X     X     X     X     X  |
*	   |                             |
*		-----------------------------
*
*	p1Deck = E3
*	p1Grave = E2
*	p1NA = E1
*	p1R1/2/3/4/5 = A5/B5/C5/D5/E5
*
*	p2Deck = -A3
*	p2Grave = -A2
*	p2NA = -A1
*	p2R1/2/3/4/5 = -E5/-D5/-C5/-B5/-A5
*
*/

package engine.gamelogic;

import java.util.HashMap;

import structures.objects.OE_Card;

/**
 * OE_Board - 
 * 	<p>This object manages the game board.</p>
 *  <p>It contains a mapping from an OE_Board.BoardCoord to the individual card containers.
 *  Each container has the OE_Card object itself.
 *  OE_Board also has methods that modify it: </p>
 *   <p> 1. isEmpty(BoardCoord) - returns a boolean to tell whether the location is empty. </p>
 *   <p> 2. getCard(BoardCoord) - returns the OE_Card object inside that location. If it's empty, it returns null. </p>
 *   <p> 3. insertToEmpty(BoardCoord, OE_Card) - inserts the OE_Card into the specific coordinate, only if it's empty.
 *   										 Returns true if successful, false if the coordinate is not empty. </p>
 *   <p> 4. moveToEmpty(BoardCoord, BoardCoord) - moves one card in the specific coordinate to another, empty coordinate.
 *   										  Returns true if successful, false otherwise. </p>
 *   <p> 5. swapCoordData(BoardCoord, BoardCoord) - swaps 2 given coordinate cards. Only works if both coords are nonempty. </pz>
 *   <p> 6. replaceCoordData(BoardCoord, OE_Card) - inserts the given card into the (nonempty) location.
 *   										    Returns the OE_Card object, or null if that location was empty. </p>
 * @author Kyle M. Morris
 * @since 0.0.1
 */
public class OE_Board {
	private HashMap<BoardCoord, cardContainer> board;
	public enum BoardCoord {
			nA4, nB4, nC4, nD4, nE4,
			     nB3, nC3, nD3, nE3,
			     nB2, nC2, nD2, nE2,
			     nB1, nC1, nD1, nE1,
			A1,  B1,  C1,  D1,
			A2,  B2,  C2,  D2,
			A3,  B3,  C3,  D3,
			A4,  B4,  C4,  D4,  E4
	}; // 34 board coordinates
	public enum SpecialCoord {
			p2R1, p2R2, p2R3, p2R4, p2R5,
			p2Deck, p2Grave, p2NA,
			p1NA, p1Deck, p1Grace,
			p1R1, p1R2, p1R3, p1R4, p1R5
	}
	// Initialize a cardContainer for each board coordinate
	public OE_Board() {
		board = new HashMap<BoardCoord, cardContainer>(34);
		for(BoardCoord b : BoardCoord.values()) {
			board.put(b, new cardContainer());
		}
	}
	// Small container class
	private class cardContainer {
		public boolean isEmpty;
		public OE_Card _card;
		public cardContainer() {
			isEmpty = true;
			_card = null;
		}
	}
	// The following methods are needed:
	// 1. Check if coord has data.
	// 2. Get the OE_Card from coord
	// 3. Insert new card to empty coord
	// 4. Move one card from one coord to another, empty coord
	// 5. Swap two nonempty coords
	// 6. Replace a nonempy coord with a new card
	
	public boolean isEmpty(BoardCoord loc) {
		return board.get(loc).isEmpty;
	}
	public OE_Card getCard(BoardCoord loc) {
		return board.get(loc)._card;
	}
	public boolean insertToEmpty(BoardCoord loc, OE_Card card) {
		if(!board.get(loc).isEmpty)
			return false;
		else {
			board.get(loc)._card = card;
			board.get(loc).isEmpty = false;
			return true;
		}
	}
	public boolean moveToEmpty(BoardCoord origcoord, BoardCoord newcoord) {
		if(!board.get(newcoord).isEmpty)
			return false;
		else {
			insertToEmpty(newcoord, board.get(origcoord)._card);
			board.get(origcoord)._card = null;
			board.get(origcoord).isEmpty = true;
			return true;
		}
	}
	public boolean swapCoordData(BoardCoord loc1, BoardCoord loc2) {
		if(board.get(loc1).isEmpty || board.get(loc2).isEmpty)
			return false;
		cardContainer temp = board.get(loc1);
		board.put(loc1, board.get(loc2));
		board.put(loc2, temp);
		return true;
	}
	public OE_Card replaceCoordData(BoardCoord loc, OE_Card newcard) {
		if(board.get(loc).isEmpty)
			return null;
		else {
			OE_Card temp = board.get(loc)._card;
			board.get(loc)._card = newcard;
			return temp;
		}
	}
	
}



