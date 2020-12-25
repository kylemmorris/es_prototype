/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package engine;
import graphics.screens.Menu;
import structures.database.OEuserData;


/**
 * Contains the abstract datatypes needed for the engine to work.
 * @author Kyle M. Morris
 * @since 0.0.1
 * @see engine.OE_GameStructs
 * 
 */
public final class OE_DataTypes {
    // Enumerations
    public enum Fellowship {ALLIANCE, SPAWN, GENESIS, REFLECTION, OCCULT, BEHELD};
    public enum Type {OVERLORD, UNIT, WEAPON, EVENT, INVENTION, MONUMENT, BASE, SURPLUS};
    public enum Subtype {OFFENSE, DEFENSE, SUPPORT, PRIMARY, SECONDARY, HEAVY, SPECIAL};
    // Datatypes
    public class Tuple<X, Y> {
        public final int x;
        public final int y;
        public Tuple(int X, int Y){
            this.x = X;
            this.y = Y;
        }
    }
    
    public class OE_Effect{
    }
    
    // Data structures
    
}









