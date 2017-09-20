package datastruct;

import java.util.Iterator;

public interface SortedList {

    /**
     * Insert a new element into the list
     * @param data the element you want to add
     */
    public void insert(Comparable data);

    /**
     * @return true if the list is empty
     */
    public boolean isEmpty();

    /**
     * @return return the number of elements in the list
     */
    public int getSize();

    /**
     * Textual information of the list
     * @return data about the lists and each element
     */
    public String toString();

    /**
     * @return the Iterator of the list
     */
    public Iterator getIterator();

}
