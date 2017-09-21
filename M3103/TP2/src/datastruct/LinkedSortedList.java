package datastruct;

import java.util.Iterator;

public class LinkedSortedList implements SortedList {
    private int size;
    private  Class dataType;
    private Element sentinel;
    private Itr iterator;

    /**
     * Constructor of LinkedSortedList. Element
     * need to implement Comparable.
     * @param theType type of element of the list
     * @throws RuntimeException if Class Type doesnt implement Comparable
     */
    public LinkedSortedList(Class theType) throws RuntimeException {
        Class comp = Comparable.class ;
        if (!comp.isAssignableFrom(theType)) {
            throw new RuntimeException ( "Pré-condition violée : " + theType.toString() +
                    "n’est pas de type Comparable !" ) ;
        }

        dataType = theType;
        size = 0;
        sentinel = new Element(null, null);
        iterator = new Itr();
    }

    // SortedList methods

    /**
     * Insert a new element into the list
     * @param data the element you want to add
     * @throws RuntimeException if the data type is not the same as dataType attribute
     */
    @Override
    public void insert(Comparable data) throws RuntimeException {
        if(data == null)
            return;

        if(!dataType.isAssignableFrom(data.getClass()))
            throw new RuntimeException("Incorrect data type");

        if(sentinel.next == null) {
            sentinel.next = new Element(data, sentinel);
            iterator.actualise();
        }
        else {
            int index = 0;
            Element it = sentinel.next;
            Element e = null;

            while(it != sentinel && data.compareTo(it.theValue) > 0) {
                index++;
                e = it;
                it = it.next;
            }

            if(index == 0) {
                sentinel.next = new Element(data, sentinel.next);
                iterator.actualise();
            }
            else if(index == size)
                e.next = new Element(data, sentinel);
            else
                e.next = new Element(data, e.next);
        }

        size++;
    }

    /**
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return return the number of elements in the list
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * @return the Iterator of the list
     */
    @Override
    public Iterator getIterator() {
        return iterator;
    }

    /**
     * Textual information of the list
     * @return data about the lists and each element
     */
    @Override
    public String toString() {
        String ret = "";
        if(size == 0)
            return ret;

        Element e = sentinel.next;
        for(int i = 0; i < size; i++) {
            if(e == null || e.theValue == null)
                ret += "\nElement [" + i + "] : null";
            else
                ret += "\nElement [" + i + "] : " + e.theValue.toString();
            e = e.next;
        }
        return ret;
    }


    // Internal classes

    private class Element {
        // Attributs
        Element next ; // Connexion à l’élément suivant de la liste
        Comparable theValue ; // Donnée stockée
        // Constructeur d’un élément de la liste
        Element ( Comparable data, Element next ) {
            this.next = next;
            this.theValue = data;
        }
    }

    private class Itr implements Iterator {
        Element current;
        Element pastCurrent;

        void actualise() {
            current = sentinel.next;
            pastCurrent = null;
        }

        // Iterator methods

        /**
         * @return true if there is an element left
         */
        @Override
        public boolean hasNext() {
            return current != null && (pastCurrent == null || pastCurrent.next != sentinel);
        }

        /**
         * @return the next object of list
         */
        @Override
        public Object next() {
            pastCurrent = current;
            current = current.next;
            return pastCurrent.theValue;
        }

        /**
         * Remove the current element of the list.
         * Cannot remove twice without using next.
         */
        @Override
        public void remove() {
            if(current == pastCurrent)
                return;

            if(current == sentinel.next) {
                pastCurrent = current.next;
                sentinel.next = pastCurrent;
                current = pastCurrent;
            }
            else if(current == sentinel) {
                sentinel = pastCurrent;
                current = pastCurrent;
            }
            else {
                pastCurrent.next = current.next;
                current = pastCurrent;
            }
            size--;
        }
    };
}
