/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package structures;
/**
 * Interface for generic data.
 * Has 2 standard methods, getSig() and getHashedSig().
 * Every datatype will have this functionality.
 * transmit() will be used by most other objects that manipulate
 * the data.
 * 
 * Current datatypes: OE_userData, OE_Card
 * 
* @author Kyle M. Morris
* @since v0.1.0-a
* @version 1
* 
 *
 */
public interface OE_Data {
	public String getSig();
	public String getHashedSig();
	public void transmit();
}