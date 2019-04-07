import java.util.Random;

// Interface for random interactions
public interface RandomGenerator {
	
	// Pick a random food coordinate on the game grid within game borders
	public static int foodPosition(int maxValue, int gridSize) {
		Random r = new Random();
		int i = r.nextInt(maxValue/gridSize);
		i *= gridSize;
		return i;
	}
}
