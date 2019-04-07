
public class Main {

	public static void main(String[] args) {
		// Main loop for application. Creates thread and runs the game in said thread
		Thread t1 = new Thread(new WindowFrame());
		t1.start();
	}
}