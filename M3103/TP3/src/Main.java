import datastruct.BinaryTreeTable;

public class Main {

    public static void main(String[] args) {
        BinaryTreeTable<Integer, String> tree = new BinaryTreeTable<>();
        tree.insert(3, "test 3");
        tree.insert(1, "test 1");
        tree.insert(6, "test 6");
        tree.insert(4, "test 4");
        tree.insert(2, "test 2");
        tree.insert(-1, "test -1");
        tree.insert(5, "test 5");
        tree.insert(8, "test 8");

        System.out.println(tree);
        System.out.println(tree.balanceLevel());

    }
}
