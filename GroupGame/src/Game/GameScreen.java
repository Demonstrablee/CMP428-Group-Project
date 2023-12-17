package Game;

import javax.swing.*;
import java.awt.*;

import static Game.Game.GAME_HEIGHT;
import static Game.Game.GAME_WIDTH;

public abstract class GameScreen extends JPanel {

	protected final Game game;
	protected Image bg;
	private final String name;

	public GameScreen(Game game, String name) {
		this.game = game;
		this.name = name;
		setLayout(new GridBagLayout());
		setVisible(false);
		setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * Set the background image for a level
	 *
	 * @param fileName The name of the image file. (including filetype)
	 */
	public void setBg(String fileName) {
		String pathFolder;
		if (GameWindow.OS.contains("Mac"))pathFolder = "GroupGame/src/images/";
		else pathFolder ="GroupGame\\src\\images\\";
		bg = Toolkit.getDefaultToolkit().getImage(pathFolder + fileName);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
