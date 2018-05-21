import java.util.Enumeration;
public class
ListeChainee
 {
     private Noeud racine;
     public Enumeration enumerate() {
          return new ListeChaineeEnumeree (racine);
     } // end enumerate
 }
