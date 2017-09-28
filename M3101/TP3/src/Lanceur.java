import cache.Aleatoire;
import cache.FIFO;
import cache.LRU;

import java.util.Random;

public class Lanceur {

    static public int [] tab;
    static public int [] cache;

    private static void initValues() {
        tab = new int[24];
        cache = new int [3];

        for (int i=0; i<tab.length; i++) {
            tab[i]=i+1;
        }

        for (int i=0; i<cache.length; i++) {
            tab[i]=-1;
        }
    }
	
	public static void main (String[] args) {

        System.out.println("Test 12x FIFO : ");
        long deltaT = 0;
        for(int i = 0; i < 12; i++) {
            initValues();
            deltaT += testFIFO(cache, tab);
        }
        deltaT = deltaT / 12;
        System.out.println("Temps moyen (en ms) = " + deltaT);

        System.out.println("\nTest 12x LRU : ");
        deltaT = 0;
        for(int i = 0; i < 12; i++) {
            initValues();
            deltaT += testLRU(cache, tab);
        }
        deltaT = deltaT / 12;
        System.out.println("Temps moyen (en ms) = " + deltaT);

        System.out.println("\nTest 12x Alea : ");
        deltaT = 0;
        for(int i = 0; i < 12; i++) {
            initValues();
            deltaT += testAlea(cache, tab);
        }
        deltaT = deltaT / 12;
        System.out.println("Temps moyen (en ms) = " + deltaT);
	}

	private static long testFIFO(int cache[], int tab[]) {
        CalculeEfficacite calculeEfficacite = new CalculeEfficacite();
        calculeEfficacite.start();

        FIFO algo = new FIFO();
        for(int i = 0; i < 128; i++) {
            algo.placer(cache, new Random().nextInt(tab.length));
        }

        calculeEfficacite.end();
        return calculeEfficacite.calc();
    }

    private static long testLRU(int cache[], int tab[]) {
        CalculeEfficacite calculeEfficacite = new CalculeEfficacite();
        calculeEfficacite.start();

        LRU algo = new LRU(cache);
        for(int i = 0; i < 128; i++) {
            algo.placer(new Random().nextInt(tab.length));
        }

        calculeEfficacite.end();
        return calculeEfficacite.calc();
    }

    private static long testAlea(int cache[], int tab[]) {
        CalculeEfficacite calculeEfficacite = new CalculeEfficacite();
        calculeEfficacite.start();

        Aleatoire algo = new Aleatoire();
        for(int i = 0; i < 128; i++) {
            algo.placer(cache, new Random().nextInt(tab.length));
        }

        calculeEfficacite.end();
        return calculeEfficacite.calc();
    }
	
	
}
