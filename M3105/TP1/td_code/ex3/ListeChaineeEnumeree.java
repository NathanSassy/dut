import java.util.Enumeration;
/** Java class "ListeChaineeEnumeree.java"
*/
public class
ListeChaineeEnumeree
 implements Enumeration {
    private Noeud noeudCourant;
    public ListeChaineeEnumeree (Noeud racine) {
 noeudCourant = racine;
    }
public boolean hasMoreElements() {
      return noeudCourant != null;
    }
    public Object nextElement() {
         Object unObjet = noeudCourant.contenu();
         noeudCourant = noeudCourant.suivant();
         return unObjet;
     } //end nextElement()
}
