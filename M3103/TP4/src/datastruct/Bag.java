package datastruct;

import java.security.SecureRandom;
import java.util.*;

public class Bag extends AbstractCollection {
    private int size;
    private Element sentinel;
    private int modCount;

    // Bag Methods

    public Bag() {
        this.size = 0;
        this.modCount = 0;
        sentinel = new Element(null, null);
        sentinel.next = sentinel;
    }
    public Bag(Collection c) {
        this();
        addAll(c);
    }

    // AbstractCollection Methods

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    @Override
    public int size() {
        if(size < 0)
            size = 0;
        return size;
    }

    @Override
    public boolean add(Object data) {
        if(size >= Integer.MAX_VALUE)
            return false;

        if(size <= 0) {
            if(size < 0)
                size = 0;
            sentinel.next = new Element(sentinel, data);
        }
        else {
            int index = new SecureRandom().nextInt(size);
            Element e = sentinel;
            for (int i = 0; i < index; i++) {
                e = e.next;
            }

            Element next = e.next;
            e.next = new Element(next, data);
        }

        size++;
        modCount++;
        return true;
    }

    // Element class

    private class Element {
        // Attributs
        Element next ; // Connexion à l’élément suivant de la liste
        Object theValue ; // Donnée stockée
        // Constructeur d’un élément de la liste
        public Element(Element next, Object theValue) {
            this.next = next;
            this.theValue = theValue;
        }
    }

    // Itr class

    private class Itr implements Iterator {
        Element current;
        Element pastCurrent;
        int expectedModCount;

        public Itr() {
            expectedModCount = modCount;
            current = sentinel.next;
            pastCurrent = null;
        }

        // Iterator methods

        /**
         * @return true if there is an element left
         */
        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException("Iterator not valid");

            return current != sentinel;
        }

        /**
         * @return the next object of list
         */
        @Override
        public Object next() throws ConcurrentModificationException {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException("Iterator not valid");

            pastCurrent = current;
            current = current.next;
            return pastCurrent.theValue;
        }

        /**
         * Remove the current element of the list.
         * Cannot remove twice without using next.
         */
        @Override
        public void remove() throws ConcurrentModificationException {
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException("Iterator not valid");

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
            modCount++;
            expectedModCount = modCount;
        }
    };
}
