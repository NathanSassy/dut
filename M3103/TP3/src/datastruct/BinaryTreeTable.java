package datastruct;

public class BinaryTreeTable<E extends Comparable<E>, T> implements Table<E, T> {
    private Node root;

    // Table methods

    @Override
    public T select(E key) {
        return findNode(root, key).theValue;
    }

    @Override
    public boolean insert(E key, T data) {
        if(root == null) {
            root = new Node(null, null, null, data, key);
            return true;
        }

        boolean ret = false;
        Node father = seekFather(key);
        Node newNode = new Node(null, null, father, data, key);

        if(father.lSon == null && father.key.compareTo(key) > 0) {
            father.lSon = newNode;
            ret = true;
        }
        else if(father.rSon == null && father.key.compareTo(key) <= 0) {
            father.rSon = newNode;
            ret = true;
        }

        return ret;
    }

    @Override
    public boolean delete(E key) {
        Node nodeToDel = findNode(root, key);
        if(nodeToDel == null) {
            return false;
        }
        delete(nodeToDel);
        return true;
    }

    // BinaryTreeTable methods

    @Override
    public String toString() {
        return root != null ? getInfo(root) : "\nArbre vide";
    }

    /**
     * Find the father for the given key
     * @param key key of new node
     * @return the father node
     */
    private Node seekFather(E key) {
        Node father = root;
        while(father.lSon != null && father.rSon != null) {
            if(father.key.compareTo(key) > 0)
                father = father.lSon;
            else if(father.key.compareTo(key) <= 0)
                father = father.rSon;
        }

        if(!(father.lSon == null && father.key.compareTo(key) > 0) && !(father.rSon == null && father.key.compareTo(key) <= 0)) {
            if(father.key.compareTo(key) > 0)
                father = father.lSon;
            else if(father.key.compareTo(key) <= 0)
                father = father.rSon;
        }

        return father;
    }

    /**
     * Recursive method to get info about the tree
     * @param theN the root of the tree
     * @return info found
     */
    private String getInfo(Node theN ) {
        String infosLNode, infosRNode, infosNode;
        String ret = "";
        if ( theN != null ) {
            infosLNode = getInfo(theN.lSon);
            infosRNode = getInfo(theN.rSon);
            String infoFather = theN.father != null ? "Fkey=" + theN.father.key : "Fkey=n";
            infosNode = "\n" + infoFather + "\t" + "clé=" + theN.key + "\tdata=" + theN.theValue;

            if(theN.father != null) {
                if(theN.father.lSon == theN)
                    infosNode += "   -> L";
                else
                    infosNode += "   -> R";
            }

            ret = infosLNode + infosNode + infosRNode;
        }
        return ret ;
    }

    /**
     * Find a specific node with the key
     * @param theNode where the search begin
     * @param key the given key
     * @return the node if found
     */
    private Node findNode(Node theNode, E key) {
        if(theNode == null) {
            return null;
        }
        else if(theNode.key.compareTo(key) == 0) {
            return theNode;
        }
        else {
            Node foundL =  findNode(theNode.lSon, key);
            Node foundR =  findNode(theNode.rSon, key);

            if(foundL != null)
                return foundL;
            else if (foundR != null)
                return foundR;
            else
                return null;
        }
    }

    /**
     * Delete a specific node
     * @param nodeToDel the node you want to delete
     */
    private void delete(Node nodeToDel) {
        if(nodeToDel == null)
            return;

        if(nodeToDel == root && nodeToDel.lSon == null && nodeToDel.rSon == null) {
            root = null;
        }
        else if(nodeToDel.lSon == null && nodeToDel.rSon == null) {
            if(nodeToDel.father.lSon == nodeToDel)
                nodeToDel.father.lSon = null;
            else if(nodeToDel.father.rSon == nodeToDel)
                nodeToDel.father.rSon = null;

            nodeToDel.father = null;
        }
        else if(nodeToDel.lSon != null && nodeToDel.rSon == null) {
            nodeToDel.lSon.father = nodeToDel.father;
            nodeToDel.father.lSon = nodeToDel.lSon;
        }
        else if(nodeToDel.lSon == null && nodeToDel.rSon != null ) {
            nodeToDel.rSon.father = nodeToDel.father;
            nodeToDel.father.rSon = nodeToDel.rSon;
        }
        else if(nodeToDel.lSon != null && nodeToDel.rSon != null) {
            Node theGNode;
            if(nodeToDel.lSon.key.compareTo(nodeToDel.rSon.key) > 0) {
                theGNode = nodeToDel.lSon;
            }
            else {
                theGNode = nodeToDel.rSon;
            }

            nodeToDel.key = theGNode.key;
            nodeToDel.theValue = theGNode.theValue;
            delete(theGNode);
        }
    }

    /**
     * Output the balance level of each branch
     * @return the text info
     */
    public String balanceLevel() {
        if(root == null) {
            return "Arbre vide";
        }

        int sizeL = sizeHeight(root.lSon, 0);
        int sizeR = sizeHeight(root.rSon, 0);

        String ret = "";
        ret += "\nHauteur à gauche : " + sizeL;
        ret += "\nHauteur à droite : " + sizeR;
        ret += "\nHauteur de l'arbre : " + (sizeL > sizeR ? sizeL + 1 : sizeR + 1);

        return ret;
    }

    /**
     * Recursive method that find out the height of a node
     * @param theNode the node
     * @param currentSize the current size, init to 0
     * @return the size height
     */
    private int sizeHeight(Node theNode, int currentSize) {
        if(theNode == null)
            return 0;

        if(theNode.lSon == null && theNode.rSon == null) {
            return currentSize + 1;
        }
        else if(theNode.lSon != null && theNode.rSon == null) {
            return sizeHeight(theNode.lSon, currentSize + 1);
        }
        else if(theNode.lSon == null && theNode.rSon != null) {
            return sizeHeight(theNode.rSon, currentSize + 1);
        }
        else {
            int sizeL = sizeHeight(theNode.lSon, currentSize + 1);
            int sizeR = sizeHeight(theNode.rSon, currentSize + 1);

            if(sizeL > sizeR)
                return sizeL + 1;
            else
                return sizeR + 1;
        }
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
