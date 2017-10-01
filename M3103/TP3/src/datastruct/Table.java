package datastruct;

public interface Table<E, T> {
    /**
     * Return the data bind to the key
     * @param key the key
     * @return the value, or null if the key doesnt exist
     */
    public T select(E key);

    /**
     * If the key doesnt exist already, this method add a value
     * at the right place into the tree
     * @param key the key
     * @param data the value you want to add
     * @return false if the insert is impossible
     */
    public boolean insert(E key, T data);

    /**
     * Delete the binary tree at the given key
     * @param key the key
     * @return false if the deletion is impossible
     */
    public boolean delete(E key);
}
