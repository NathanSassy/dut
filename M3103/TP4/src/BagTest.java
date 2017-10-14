import datastruct.Bag;
import java.util.*;

public class BagTest {
    private static int nbTest = 0;
    private static int nbTestOK = 0;

    public static void main(String[] args) {
        System.out.println("**** Test de Bag ****\n");

        System.out.println("testIsEmpty --> " + testIsEmpty());
        System.out.println("testAdd --> " + testAdd());
        System.out.println("testToString --> " + testToString());
        System.out.println("testContain --> " + testContain());
        System.out.println("testClear --> " + testClear());
        System.out.println("testRemove --> " + testRemove());
        System.out.println("testOperationsWithCollections --> " + testOperationsWithCollections());
        System.out.println("testIterator --> " + testIterator());
        System.out.println("testRandomness --> " + testRandomness());

        System.out.println();
        System.out.println("Tests succeed = " + nbTestOK + " / "
                + nbTest + " (" + (nbTestOK/nbTest*100) +"%)");


        System.out.println("\n*********************\n");
    }

    private static String testIsEmpty() {
        nbTest++;
        Bag bag1 = new Bag();
        Bag bag2 = new Bag();
        bag2.add("data");

        if(!bag1.isEmpty() || bag2.isEmpty())
            return "Erreur avec isEmpty";


        nbTestOK++;
        return "OK";
    }

    private static String testAdd() {
        nbTest++;
        Bag bag = createSimpleBag();

        if(bag.size() != 5)
            return "Erreur avec add";

        nbTestOK++;
        return "OK";
    }

    private static String testToString() {
        nbTest++;
        Bag bag = createSimpleBag();

        String s = bag.toString();
        if(s == null && s.isEmpty())
            return "Erreur avec toString";

        nbTestOK++;
        return "OK";
    }

    private static String testContain() {
        nbTest++;
        Bag bag = createSimpleBag();

        boolean b1 = bag.contains("data3");
        boolean b2 = bag.contains("data8");

        if(b1 == false || b2 == true)
            return "Erreur avec contain";

        nbTestOK++;
        return "OK";
    }

    private static String testClear() {
        nbTest++;
        Bag bag = createSimpleBag();
        bag.clear();

        if(bag.size() != 0)
            return "Erreur avec clear";

        nbTestOK++;
        return "OK";
    }

    private static String testRemove() {
        nbTest++;
        Bag bag = createSimpleBag();

        int sizeBefore = bag.size();
        boolean rmNom = bag.remove("data3");
        boolean rmLim = bag.remove("data8");
        int sizeAfter = bag.size();

        if(rmNom != true)
            return "Erreur avec remove dans le cas nominal";
        else if(rmLim == true)
            return "Erreur avec remove dans le cas limite";
        else if(sizeBefore == sizeAfter)
            return "Erreur avec remove la taille est inchange";


        nbTestOK++;
        return "OK";
    }

    private static String testOperationsWithCollections() {
        nbTest++;
        Bag bag = createSimpleBag();

        ArrayList<String> c1 = new ArrayList<>();
        c1.add("data2");
        c1.add("data3");

        ArrayList<String> c2 = new ArrayList<>();
        c2.add("data6");
        c2.add("data7");

        if(!bag.containsAll(c1))
            return "Erreur avec containsAll lorsque les elements sont presents";
        if(bag.containsAll(c2))
            return "Erreur avec containsAll lorsque les elements ne sont pas presents";

        boolean b1 = bag.addAll(c2);
        if(b1 == false || !bag.containsAll(c2))
            return "Erreur avec addAll";

        boolean b2 = bag.removeAll(c1);
        if(b2 == false || bag.containsAll(c1))
            return "Erreur avec removeAll";

        nbTestOK++;
        return "OK";
    }

    private static String testRandomness() {
        nbTest++;
        Bag bag = new Bag();

        final int SIZE = 10;
        final int NB = 10000;
        int placeBoule[][] = new int[SIZE+1][SIZE];
        for(int i = 0; i <= NB; i++) {
            for(int j = 1; j <= SIZE+1; j++) {
                bag.add(j);
            }
            Iterator it = bag.iterator();
            int place = 0;
            while(place < SIZE) {
                int boule = (int) it.next();
                placeBoule[boule-1][place]++;
                place++;
            }
            bag.clear();
        }

        System.out.println("\nAffichage des places de boules : ");
        for(int i = 1; i <= SIZE; i++) {
            for(int j = 1; j < placeBoule[0].length; j++) {
                int nb = placeBoule[i][j];
                double target = NB/SIZE;
                double diff = (NB/SIZE)*0.1; // 10% de marge d'erreur
                if(nb > (target+diff) || nb < (target-diff))
                    return "Erreur avec l'aleatoire de add";
            }
            System.out.println("Places de la boule n° " + i + " : " + Arrays.toString(placeBoule[i]));
        }
        System.out.println();

        nbTestOK++;
        return "OK";
    }

    private static String testIterator() {
        nbTest++;
        Bag bag = createSimpleBag();
        Iterator it = bag.iterator();

        int ind = 0;
        while(it.hasNext()) {
            it.next();
            if(ind > bag.size())
                return "Erreur avec hasNext";
            else
                ind++;
        }

        int sizeBefore = bag.size();
        it.remove();
        it.remove();
        if(bag.size()+1 != sizeBefore)
            return "Erreur avec remove : 2 removes sans next";

        bag.add("dataX");
        try {
            it.next();
            return "Erreur avec next : iterator pas invalidé alors qu'il y a eu un add";
        }
        catch (ConcurrentModificationException err) {}

        nbTestOK++;
        return "OK";
    }

    private static Bag createSimpleBag() {
        Bag bag = new Bag();
        for(int i = 0; i < 5; i++) {
            bag.add("data" + i);
        }
        return bag;
    }
}
