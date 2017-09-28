package cache;

public class FIFO {
    private int indiceCache;

    public FIFO() {
        indiceCache = 0;
    }

    public void placer(int[] cache, int number) {
        boolean exist = false;
        for(int i = 0; i < cache.length; i++) {
            if(cache[i] == number) {
                exist = true;
            }
        }

        if(!exist) {
            cache[indiceCache] = number;

            if(indiceCache == 2)
                indiceCache = 0;
            else
                indiceCache++;
        }
    }
}
