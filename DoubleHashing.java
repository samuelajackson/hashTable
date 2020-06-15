/**
 * DoubleHashing holds the equation for double hashing.
 *
 * @author samjackson
 * 
 */
public class DoubleHashing<E> {

	/**
	 * Constructor
	 */
	public DoubleHashing() {
	}
	
	/**
	 * @param insertedHash hashobject
	 * @param i probe number
	 * @param capacity of the hash table
	 * @return calculated position for insertion
	 */
	public int calculate(HashObject<E> insertedHash, int i, int capacity) {
		int m = capacity;
		int k = insertedHash.getKey();
		int primary = k % m;
		
		if(primary < 0) {
			primary += capacity;
		}
		
		int secondary = (1 + (k % (m-2)));
		if(secondary < 0) {
			secondary += capacity -2;
		}
		
		int returnVal = (primary + (i * (secondary))) % m; 
		if(returnVal < 0) {
			returnVal += capacity;
		}
		return returnVal;
	}
}
