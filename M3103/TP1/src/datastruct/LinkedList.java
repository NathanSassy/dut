package datastruct;

public class LinkedList implements List {
    // Attributes
    private Element sentinel ; // pointe sur l’élément sentinelle
    private Element current ; // pointe sur l’élément courant
    private int size ; // nombre d’éléments dans la liste


    // LinkedList methods

    public LinkedList() {
        size = 0;
        current = null;
        sentinel = new Element (null, null, null);
    }

    /**
     * Set the current element at the beginning of the list
     */
    public void goToHead() {
        current = sentinel.next;
    }

    /**
     * Set the current element at the end of the list
     */
    public void goToEnd() {
        current = sentinel.prev;
    }

    /**
     * Set the current element at the next element
     * @return true if current element is correctly set
     */
    public boolean next() {
        boolean ret = false;
        if( this.hasNext()) {
            ret = true;
            current = current.next;

        }
        return ret;
    }

    /**
     * Set the current element at the previous element
     * @return true if current element is correctly set
     */
    public boolean previous() {
        boolean ret = false;
        if( this.hasPrevious()) {
            ret = true;
            current = current.prev;

        }
        return ret;
    }

    /**
     * @return Return every elements in a textual form
     */
    public String toString() {
        String ret = "";
        Element e = sentinel.next;

        for(int i = 0; i < size; i++) {
            ret += "The value [" + i + "] is : " + e.theValue.toString() + "\n";
            e = e.next;
        }

        return ret;
    }

    /**
     * Give the value at the specified index
     * @param index the index
     * @return the value at this index
     */
    public Object getValueAt(int index) {
        Element tmp = sentinel.next;
        for(int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.theValue;
    }

    /**
     * @return true if the current element have a precedent
     */
    public boolean hasPrevious() {
        return (current.prev != null);
    }

    /**
     * @return true if the current element have a next
     */
    public boolean hasNext() {
        return (current.next != null);
    }


    // List methods

    @Override
    public void insert(Object data) {
        size++;
        if(current == null) {
            sentinel = new Element(null, null, null);
            current = new Element(null, null, data);
            sentinel.next = sentinel.prev = current;

            current.next = current;
        }
        else {
            current.next = new Element(current, current.next, data);
            current = current.next;
        }
    }

    @Override
    public boolean delete() {
        if(size == 0)
            return false;

        if(hasPrevious()) {
            current.prev.next = current.next;

            if(hasNext()) {
                current.next.prev = current.prev;
            }
            else {
                sentinel.next = current.prev;
            }
            previous();
        }
        else if(hasNext()) {
            current.next.prev = null;
            next();
            sentinel.prev = current;
        }
        else {
            current = null;
            sentinel.next = null;
            sentinel.prev = null;
        }

        size--;
        return true;
    }

    @Override
    public boolean contains(Object data) {
        Element e = sentinel.next;

        for(int i = 0; i < size; i++) {
            if(e.theValue != null && e.theValue == data) {
                return true;
            }
            else {
                e = e.next;
            }
        }

        return false;
    }

    @Override
    public Object getValue() {
        return current.theValue;
    }

    @Override
    public void setValue(Object newData) {
        current.theValue = newData;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int getSize() {
        return size;
    }

    private class Element {
        Element prev ; // Connexion à l’élément précédent de la liste
        Element next ; // Connexion à l’élément suivant de la liste
        Object theValue ; // Donnée stockée
        // Constructeur d’un élément à insérer dans la liste doublement chaînée
        Element (Element prev, Element next, Object data ) {
            this.prev = prev;
            this.next = next;
            theValue = data;
        }
    }
}
