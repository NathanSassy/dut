import datastruct.LinkedSortedList;

import java.util.Iterator;

public class LinkedSortedListTest {

    public static void main(String[] args) {
        LinkedSortedList list = new LinkedSortedList(Integer.class);
        list.insert(new Integer(6));
        list.insert(new Integer(-1));
        list.insert(new Integer(3));

        Iterator iterator = list.getIterator();
        System.out.println("list 1 = " + list);
        iterator.next();
        iterator.remove();
        System.out.println("list 2 = " + list);


        /*list.insert(new Integer(5));
        list.insert(new Integer(8));
        list.insert(new Integer(1));
        list.insert(new Integer(10));
        list.insert(new Integer(1));
        list.insert(new Integer(5));
        list.insert(new Integer(2));
        list.insert(new Integer(3));
        System.out.println("list  = " + list);

        Iterator it = list.getIterator();
        while(it.hasNext()) {
            Integer i = (Integer) it.next();
            System.out.println("val = " + i);
        }
        */

    }
}


