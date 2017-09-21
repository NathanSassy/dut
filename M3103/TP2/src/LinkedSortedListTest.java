import datastruct.LinkedSortedList;

import java.util.Iterator;

public class LinkedSortedListTest {

    public static void main(String[] args) {
        System.out.println("****** LinkedSortedListTest ******\n\n");

        System.out.println("Create a LinkedSortedList with Integer.class");
        LinkedSortedList list = new LinkedSortedList(Integer.class);
        System.out.println("\nInsert values : 6, -1, 3, 1, 10, 1, 5");
        list.insert(new Integer(6));
        list.insert(new Integer(-1));
        list.insert(new Integer(3));
        list.insert(new Integer(1));
        list.insert(new Integer(10));
        list.insert(new Integer(1));
        list.insert(new Integer(5));
        System.out.println("\nSorted list (using toString()) : " + list + "\n");

        System.out.println("Try to insert a not compatible data type : insert a Double");
        try {
            list.insert(new Double("3.3"));
        }
        catch (RuntimeException e) {
            System.out.println("Error : " + e.getMessage());
        }

        System.out.println("\nDisplay all values using the iterator : ");
        Iterator iterator = list.getIterator();
        while(iterator.hasNext()) {
            Integer i = (Integer) iterator.next();
            System.out.println("value = " + i);
        }

        // Go to the first element
        //iterator.next();

        System.out.println("\nTry to delete elements : ");

        System.out.println("List before (1) : " + list);
        System.out.println("Delete");
        iterator.remove();
        System.out.println("List after (1) : " + list);

        System.out.println("\nTry to delete again (forbiden)");
        iterator.remove();
        System.out.println("List before (2) : " + list);
        System.out.println("Delete");
        System.out.println("List after (2) : " + list);

        System.out.println("\nTry to delete after next");
        iterator.next();
        iterator.remove();
        System.out.println("List before (3) : " + list);
        System.out.println("Delete");
        System.out.println("List after (3) : " + list);


        System.out.println("\n\n**********************************");
    }
}


