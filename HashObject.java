/**
 * HashObject is a container for elements to be inserted into the hashtable.
 *
 * @author samjackson
 */
public class HashObject<E> {

	private E element;
	private int freqCount, probCount;
	
	/**
	 * Constructor
	 * @param elem element to be contained
	 */
	public HashObject(E elem) {
		element = elem;
		freqCount = probCount = 0;
	}

	/**
	 * @return probe count
	 */
	public int getProbCount() {
		return probCount;
	}
	
	/**
	 * @param probCount 
	 */
	public void setProbCount(int probCount) {
		this.probCount = probCount;
	}

	/**
	 * @return frequency count
	 */
	public int getFreqCount() {
		return freqCount;
	}

	/**
	 * @param freqCount
	 */
	public void setFreqCount(int freqCount) {
		this.freqCount = freqCount;
	}

	/**
	 * @return a unique key based on the element
	 */
	public int getKey() {
		return element.hashCode();
	}

	/**
	 * @return element within the hashobject
	 */
	public E getElement() {
		return element;
	}

	/**
	 * @param element to be set
	 */
	public void setElement(E element) {
		this.element = element;
	}
	
	/**
	 * @return the element's tostring plus the frequency count plus the probe count
	 */
	public String toString() {
		String print = element.toString() + " " + freqCount +
				" " + probCount;
		return print;
	}
	
	/**
	 * @param h hashobject to be compared to
	 * @return true if this hashobject has the same key and element as the passed in
	 */
	public boolean equals(HashObject<E> h) {
		return (h.getKey() == this.getKey() && h.getElement().equals(this.element));
	}
}
