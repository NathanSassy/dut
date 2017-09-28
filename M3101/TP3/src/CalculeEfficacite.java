import java.util.*;

public class CalculeEfficacite {

	
	public long startTime=0;
	public long endTime=0;
	
	
	public void start() {
		startTime = System.nanoTime();
	}
	
	public void end() {
		endTime = System.nanoTime();
	}
	
	public long calc() {
		long time = endTime - startTime;
		return time / 1000;
	}
	
	
}
