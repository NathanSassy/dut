package datastruct;

/**
 * List interface.
 */
public interface List {
	
	/**
	 * Insert new object to the list.
	 * Become the current object.
	 * @param data the object you want to add
	 */
	public void insert(Object data);
	
	/**
	 * Delete the current object
	 * @return true if deleted
	 */
	public boolean delete();
	
	/**
	 * Check if object is contained in the list.
	 * @param data the object you want to check
	 * @return true if the object is already in the list
	 */
	public boolean contains(Object data);
	
	/**
	 * @return the value of the current object
	 */
	public Object getValue();
	
	/**
	 * Set new data into the current object.
	 * @param newData the new value
	 */
	public void setValue(Object newData);
	
	/**
	 * @return true if the list is empty
	 */
	public boolean isEmpty();
	
	/**
	 * @return the size of the list
	 */
	public int getSize();
}