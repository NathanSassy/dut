import cache.Aleatoire;
import cache.FIFO;
import cache.LRU;

public class Main {

    public static void main(String[] args) {
        int cache[] =   {-1, -1, -1};
        LRU algo = new LRU(cache);

        printCache(cache);
        algo.placer( 3);
        printCache(cache);
        algo.placer( 2);
        printCache(cache);
        algo.placer( 2);
        printCache(cache);
        algo.placer( 4);
        printCache(cache);
        algo.placer( 5);
        printCache(cache);
        algo.placer( 4);
        printCache(cache);
    }

    private static void printCache(int[] cache) {
        System.out.println(cache[0] + " - " + cache[1] + " - " + cache[2]);
    }
}
