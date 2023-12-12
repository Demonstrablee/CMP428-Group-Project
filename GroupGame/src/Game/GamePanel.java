package Game;

import javax.swing.*;
import java.awt.*;

import static Game.Game.GAME_HEIGHT;
import static Game.Game.GAME_WIDTH;

public class GamePanel extends JLayeredPane {

	private final GameWindow window;
	private final Game game;
	private Image scene;
	private Graphics pen;

	public GamePanel(GameWindow window) {
		this.window = window;
		game = new Game(this);
		setFocusable(true);
		addKeyListener(game);
		addMouseListener(game);
		requestFocus();
	}

	/**
	 * Renders visuals to display.
	 *
	 * @param g The graphics context.
	 */
	@Override
	public void paint(Graphics g) {
		if(scene == null || scene.getWidth(null) != window.getWidth() || scene.getHeight(null) != window.getHeight()) {
			scene = createImage(window.getWidth(), window.getHeight());
			pen = scene.getGraphics();
		}
		pen.clearRect(0, 0, window.getWidth(), window.getHeight());
		super.paint(pen);
		game.draw(pen);
		g.drawImage(scene, 0, 0, this);
	}

	public GameWindow getWindow() {
		return window;
	}
}
