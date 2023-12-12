package Game;

import javax.swing.*;
import java.awt.*;

public abstract class GameScreen extends JPanel {

	protected final Game game;
	protected Image bg;
	private final String name;

	public GameScreen(Game game, String name) {
		this.game = game;
		this.name = name;
		setLayout(new GridBagLayout());
		setVisible(false);
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
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
	}

	@Override
	public void setVisible(boolean visible) {
		setVisible(visible, false);
	}

	public void setVisible(boolean visible, boolean room) {
		if(visible && game != null)
			game.getPanel().getWindow().resize(room ? Game.ROOM_DIMENSION : Game.MENU_DIMENSION);
		super.setVisible(visible);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
