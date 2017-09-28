package cache;

import java.util.Random;

public class Aleatoire {
    public void placer(int[] cache, int number) {
        boolean exist = false;
        for(int i = 0; i < cache.length; i++) {
            if(cache[i] == number) {
                exist = true;
            }
        }

        if(!exist) {
            int indiceCache = new Random().nextInt(3);
            cache[indiceCache] = number;
        }
    }
}
