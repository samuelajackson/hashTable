/**
 * LinearProbe holds an equation for linear probing.
 *
 * @author samjackson
 */
public class LinearProbe<E> {

	/**
	 * Constructor
	 */
	public LinearProbe() {
	}
	
	/**
	 * @param insertedHash hashobject
	 * @param i probe number
	 * @param capacity of the hash table
	 * @return
	 */
	public int calculate(HashObject<E> insertedHash, int i, int capacity) {
		int m = capacity;
		int k = insertedHash.getKey();
		int primary = k % m;
		if(primary < 0) {
			primary += capacity;
		}
		int returnVal = (primary + i) % m;
		return returnVal;
	}
}
