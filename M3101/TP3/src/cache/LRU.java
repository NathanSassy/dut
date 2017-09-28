package cache;

public class LRU implements AlgoCache{

	private int[] cache;
	private int[] nbOccurence;
	
	public LRU(int[] cache) {
		this.cache=cache;
		nbOccurence = new int[cache.length];
	}
	
	
	public void placer(int val) {
		boolean placer = false;
		for(int i = 0;i<cache.length && !placer;i++) {
			if(val==cache[i]) {
				placer = true;
				nbOccurence[i]++;
			}
		}
		
		if(!placer) {
			int min = nbOccurence[0];
			int posMin = 0;
			for(int i = 1;i<nbOccurence.length;i++) {
				if(nbOccurence[i]<min) {
					min = nbOccurence[i];
					posMin=i;
				}
			}
			
			cache[posMin]=val;
            nbOccurence[posMin]++;
			placer=true;
		}	
	}
	
	
	
	
	
	
	
}
