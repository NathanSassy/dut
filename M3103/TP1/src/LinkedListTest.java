import datastruct.LinkedList;

public class LinkedListTest {

    public static void main(String args[]) {
        System.out.println("****** LinkedListTest ******\n\n");
        LinkedList list = new LinkedList();

        // Add some elements
        list.insert((Object) "A");
        list.insert((Object) "B");
        list.insert((Object) "C");
        list.insert((Object) "D");


        System.out.println("[OK] Elements added");
        System.out.println("[OK] List size = " + list.getSize());

        // Try delete
        list.delete();
        System.out.println("[OK] Element deleted");
        System.out.println("[OK] List size = " + list.getSize());

        // 1st element
        list.goToHead();
        System.out.println("[OK] 1st element = " + list.getValue());

        // Read 2nd element
        list.next();
        if(list.getValue() != null)
            System.out.println("[OK] 2nd value = " + list.getValue());
        else
            System.out.println("[FAILED] 2nd not available");

        // Show list
        System.out.println("list = " + list.toString());

        // Try next
        System.out.println("3 uses of next : ");
        for(int i = 0; i < 3; i++) {
            System.out.println("current = " + list.getValue());
            System.out.println("next()");
            list.next();
        }

        // Try prev
        System.out.println("3 uses of previous : ");
        for(int i = 0; i < 3; i++) {
            System.out.println("current = " + list.getValue());
            System.out.println("previous()");
            list.previous();
        }

        // Try getValueAt
        System.out.println("[OK] getValueAt(0) = " + list.getValueAt(0));
        System.out.println("[OK] getValueAt(1) = " + list.getValueAt(1));


        System.out.println("\n\n****************************");
    }
}
