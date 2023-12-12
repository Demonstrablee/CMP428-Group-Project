package Game;

import Levels.Managers.SimpleSoundPlayer;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

	public static final String OS = System.getProperty("os.name");

	public GameWindow() {
		setTitle("In the Night");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Game.RESOURCE_URL + "icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new GamePanel(this));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		SimpleSoundPlayer.playSoundForever("GroupGame/src/music/Main_Theme.wav");
	}

	/**
	 * Resizes the window to the given dimension.
	 *
	 * @param dimension The new size of this component/
	 */
	@Override
	public void resize(Dimension dimension) {
		setPreferredSize(dimension);
		pack();
		setLocationRelativeTo(null);
	}
}
