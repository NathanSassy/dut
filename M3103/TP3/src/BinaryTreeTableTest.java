import datastruct.BinaryTreeTable;

public class BinaryTreeTableTest {

    public static void main(String[] args) {
        testInsert();
        testDelete();
        testSelect();
    }

    public static void testInsert() {
        System.out.println("\n******** testInsert  ********\n");

        BinaryTreeTable<Integer, String> tree = new BinaryTreeTable<>();

        // formal insert
        System.out.println("Cas nominaux : ajout de 3, 1, 6, 4, 2, 5, 8");
        tree.insert(3, "test 3");
        tree.insert(1, "test 1");
        tree.insert(6, "test 6");
        tree.insert(4, "test 4");
        tree.insert(2, "test 2");
        tree.insert(5, "test 5");
        tree.insert(8, "test 8");

        // Negative insert
        System.out.println("Cas limite 'negatif' : ajout de -1");
        tree.insert(-1, "test -1");

        // Insert with the same key
        System.out.println("Cas limites 'meme key' : ajout de 5, 8");
        tree.insert(5, "test 5 v2");
        tree.insert(8, "test 8 v2");

        System.out.println("Affchage de l'arbre : \n" + tree);
        System.out.println(tree.balanceLevel());

        System.out.println("\n*****************************\n");
    }

    public static void testDelete() {

        System.out.println("\n******** testDelete *********\n");

        BinaryTreeTable<Integer, String> tree = new BinaryTreeTable<>();

        // inserts
        tree.insert(3, "test 3");
        tree.insert(1, "test 1");
        tree.insert(6, "test 6");
        tree.insert(4, "test 4");
        tree.insert(7, "test 7");
        tree.insert(8, "test 8");
        tree.insert(8, "test 8v2");
        tree.insert(-1, "test -1");

        System.out.println("Avant : \n" + tree);
        System.out.println();

        // delete them all
        tree.delete(7);
        System.out.println("apres del 7 : " + tree);
        System.out.println();

        tree.delete(1);
        System.out.println("apres del 1 : " + tree);
        System.out.println();

        tree.delete(8);
        System.out.println("apres del 8 : " + tree);
        System.out.println();

        tree.delete(3);
        System.out.println("apres del 3 : " + tree);
        System.out.println();

        tree.delete(4);
        System.out.println("apres del 4 : " + tree);
        System.out.println();

        tree.delete(6);
        System.out.println("apres del 6 : " + tree);
        System.out.println();

        tree.delete(-1);
        System.out.println("apres del -1 : " + tree);
        System.out.println();

        tree.delete(8);
        System.out.println("apres del 8 : " + tree);
        System.out.println();

        System.out.println("\n*****************************\n");
    }

    public static void testSelect() {
        System.out.println("\n******** testSelect *********\n");

        BinaryTreeTable<Integer, String> tree = new BinaryTreeTable<>();

        // inserts
        tree.insert(3, "test 3");
        tree.insert(1, "test 1");
        tree.insert(6, "test 6");
        tree.insert(4, "test 4");
        tree.insert(7, "test 7");
        tree.insert(8, "test 8");
        tree.insert(8, "test 8v2");
        tree.insert(-1, "test -1");

        System.out.println("Arbre : \n" + tree);
        System.out.println();

        System.out.println("Select(1) : " + tree.select(1));
        System.out.println("Select(-1) : " + tree.select(-1));
        System.out.println("Select(8) : " + tree.select(8));
        System.out.println("Select(3) : " + tree.select(3));



        System.out.println("\n*****************************\n");
    }
}
