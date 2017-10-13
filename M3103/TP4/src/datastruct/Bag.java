package datastruct;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class Bag extends AbstractCollection {
    private int size;
    private Element sentinel;

    // Bag Methods

    public Bag() {
        this.size = 0;
        sentinel = new Element(sentinel, null);
    }
    public Bag(Collection c) {
        this();
        addAll(c);
    }

    public void test() {
        Element e = sentinel.next;
        while(e != sentinel) {
            System.out.println(e.theValue);
            e = e.next;
        }
    }

    @Override
    public String toString() {
        String ret = "[";
        Element itr = sentinel;
        for(int i = 0; i < size+1; i++) {
            ret += itr.theValue + ",";
            itr = itr.next;
        }
        ret +="]";
        return ret;
    }


    // AbstractCollection Methods

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(Object data) {
        if(size >= Integer.MAX_VALUE)
            return false;

        if(size == 0) {
            sentinel.next = new Element(sentinel, data);
            size++;
            return true;
        }

        int randomIndex = new Random().nextInt(size) + 1;

        Element e = sentinel;
        for(int i = 0; i < randomIndex; i++) {
            e = e.next;
        }

        e.next = new Element(e.next.next, data);
        size++;

        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean ret = true;
        Iterator itr = c.iterator();
        while(itr.hasNext()) {
            boolean ok = add(itr.next());
            if(ok) size++;
            else ret = false;
        }
        return ret;
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

        public Itr() {
            current = sentinel.next;
            pastCurrent = null;
        }

        // Iterator methods

        /**
         * @return true if there is an element left
         */
        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        /**
         * @return the next object of list
         */
        @Override
        public Object next() {
            pastCurrent = current;
            current = current.next;
            return current.theValue;
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
