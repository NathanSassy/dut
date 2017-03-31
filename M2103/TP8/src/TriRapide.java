package tri;
import pays.*;

public class TriRapide implements ITri
{

    private Pays tab[];

    /**
    * The constructof of TriRapide
    * @param tab the array of Pays
    */
    public TriRapide(Pays[] tab)
    {

        if(tab != null)
            this.tab = tab;
    }

    /**
    *
    */
    public void trier()
    {
        int indL = 0;
        int indR = tab.length - 1;

        this.triRapideRec(indL, indR);
    }

    /**
    *
    * @param indL
    * @param indR
    */
    private void triRapideRec(int indL, int indR)
    {

        int indPivot;

        indPivot = this.separer(indL, indR);

        if(indPivot - 1 > indL)
            this.triRapideRec(indL, (indPivot - 1));

        if(indPivot + 1 < indR)
            this.triRapideRec((indPivot + 1), indR);

    }

    private int separer(int indL, int indR)
    {

        double pivot;

        pivot = tab[indL].getSurface();

        while(indL < indR)
        {
            if(tab[indL].compareTo(tab[indR]) > 0)
                this.swap(indL, indR);

            if(tab[indL].getSurface() == pivot)
                indR--;
            else
                indL++;            
        }

        return  indL;
    }

    private void swap(int i, int j)
    {

        Pays tmp = this.tab[i];
        this.tab[i] = this.tab[j];
        this.tab[j] = tmp;
    }
}