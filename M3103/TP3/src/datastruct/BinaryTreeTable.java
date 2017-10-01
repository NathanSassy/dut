package datastruct;

public class BinaryTreeTable<E extends Comparable<E>, T> implements Table<E, T> {
    private Node root;

    // Table methods

    @Override
    public T select(E key) {
        return null;
    }

    @Override
    public boolean insert(E key, T data) {
        return false;
    }

    @Override
    public boolean delete(E key) {
        return false;
    }

    // BinaryTreeTable methods

    @Override
    public String toString() {
        String ret = "";
        return ret;
    }

    // Inner class Node
    private class Node {
        // Attributs
        Node lSon; // fils gauche (null si pas de fils gauche)
        Node rSon; // fils droit (null si pas de fils droit)
        Node father; // père (null si le nœud est root)
        T theValue; // donnée stockée
        E key; // clé unique

        // Constructeur
        public Node(Node lSon, Node rSon, Node father, T theValue, E key) {
            this.lSon = lSon;
            this.rSon = rSon;
            this.father = father;
            this.theValue = theValue;
            this.key = key;
        }
    }
}
