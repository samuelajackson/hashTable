/**
 * Hash table implemented using an array; uses either linear probing
 * or double hashing.
 *
 * @author samjackson
 */
public class HashTable<E> {

	public static enum OpenAddressType { 
		LinearProbing, DoubleHashing
	};
	private int capacity, dupCount, totalProbes, numElements, uniqueElements;
	private double maxLoadFactor;
	private OpenAddressType type;
	private HashObject<E> array[];
	
	/**
	 * Constructor: default values
	 * 
	 */
	@SuppressWarnings("unchecked")
	public HashTable() {
		dupCount = totalProbes = numElements = uniqueElements = 0;
		capacity = 95791;
		maxLoadFactor = .75;	
		type = OpenAddressType.LinearProbing;
		array = new HashObject[capacity];
	}
	
	/**
	 * Constructor
	 * @param size maxsize
	 * @param loadFactor between zero and one
	 * @param passedType linearprobing or doublehashing
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size, double loadFactor, OpenAddressType passedType) {
		dupCount = totalProbes = numElements = uniqueElements = 0;
		capacity = size;
		maxLoadFactor = loadFactor;
		type = passedType;
		array = new HashObject[capacity];
	}
	
	/**
	 * Maps the element into the hash table
	 * @param element
	 */
	public void put(E element) {
		HashObject<E> insertHash = new HashObject<E>(element);
		boolean dupOrInserted = false;
		int index = 0;
		int i = 0;
		while(!dupOrInserted) { //checks if the element is a duplicate or was inserted
			if(type == OpenAddressType.LinearProbing) {
				LinearProbe<E> putType = new LinearProbe<E>();
				index = putType.calculate(insertHash, i, capacity);
			}
			else {
				DoubleHashing<E> putType = new DoubleHashing<E>();
				index = putType.calculate(insertHash, i, capacity);
			}
			if(array[index] == null) { //open slot; insert
				array[index] = insertHash;
				totalProbes += (i + 1);
				insertHash.setProbCount(i+1);
				uniqueElements++;
				dupOrInserted = true;
			}
			else if(array[index].equals(insertHash)) { //duplicate; do not insert, increment dupcount
				array[index].setFreqCount(array[index].getFreqCount() + 1);
				dupCount++;
				insertHash.setProbCount(i+1);
				dupOrInserted = true;
			}
			else { //collision; increment i and try again
				i++;
			}
		}
		numElements++;
	}
	
	/**
	 * @return maxLoadFactor
	 */
	public double getLoadFactor() {
		return maxLoadFactor;
	}
	
	/**
	 * @return duplicate count for all elements
	 */
	public int totalDuplicates() {
		return dupCount;
	}
	
	/**
	 * @return total probes for all elements
	 */
	public int totalProbes() {
		return totalProbes;
	}

	/**
	 * @return average probes per elements
	 */
	public double averageProbs() {
		return (double)totalProbes/uniqueElements;
	}
	
	/**
	 * @return number of elements in the array
	 */
	public int getSize() {
		return uniqueElements;
	}
	
	/**
	 * @return number of elements in the hash table
	 */
	public int getElements() {
		return numElements;
	}
	
	/**
	 * @return a list of array indices with the element's toString
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < capacity; i++) {
			if(array[i] != null) { 	//if the slot is empty, append its information
				s.append("table["+i+"]: " + array[i].toString() + "\n");
			}
		}
		return s.toString();
	}
}
