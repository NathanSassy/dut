package datastruct;

import java.util.Iterator;

public class LinkedSortedList implements SortedList {
    private int size;
    private  Class dataType;
    private Element sentinel;
    private Itr iterator;

    public LinkedSortedList(Class theType) {
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

    @Override
    public void insert(Comparable data) {
        if(data == null)
            return;

        if(sentinel.next == null) {
            sentinel.next = new Element(data, sentinel);
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

            if(index == 0)
                sentinel.next = new Element(data, sentinel.next);
            else if(index == size)
                e.next = new Element(data, sentinel);
            else
                e.next = new Element(data, e.next);
        }

        iterator.actualise();
        size++;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterator getIterator() {
        return iterator;
    }

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

    // Internal classe Element

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


    // Iterator of the list

    private class Itr implements Iterator {
        Element current;
        Element pastCurrent;

        void actualise() {
            current = pastCurrent = sentinel.next;
        }

        // Iterator methods

        @Override
        public boolean hasNext() {
            return pastCurrent != null && pastCurrent.next != sentinel;
        }

        @Override
        public Object next() {
            pastCurrent = current;
            current = current.next;
            return pastCurrent.theValue;
        }

        @Override
        public void remove() {
            if(current == pastCurrent)
                return;

            Element next = current.next;
            current = pastCurrent;
            current.next = next;
        }
    };
}
