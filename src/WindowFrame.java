import javax.swing.JFrame;

public class WindowFrame extends JFrame implements Runnable {
	
	private KeyboardListener listener = new KeyboardListener();
	private Game game = new Game(listener);

	// Constructor
	public WindowFrame() {
		super("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		add(game);
		pack();
		setLocationRelativeTo(null);
		addKeyListener(listener);
	}
	
	@Override
	public void run() {
		game.run();
	}

}
