import datastruct.Bag;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.Random;

public class BagTest {

    public static void main(String[] args) {
        Bag bag = new Bag();
        bag.add("o1");
        bag.add("o2");
        bag.add("o3");
        bag.add("o4");
        bag.add("o5");

        //System.out.println("bag size = " + bag.size());
        System.out.println("bag = " + bag.toString());

        /*Iterator it = bag.iterator();
        while(it.hasNext()) {
            System.out.println("value = " + it.next());
        }*/

        bag.test();


    }
}
