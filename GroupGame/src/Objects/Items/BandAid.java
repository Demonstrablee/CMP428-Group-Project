package Objects.Items;

import Sprites.Characters.Player;

public class BandAid extends Item {

	private final Player player;

	public BandAid(Player player) {
		this.player = player;
	}

	@Override
	public String getName() {
		return "Band Aid";
	}

	@Override
	public void use() {
		player.getHealthBar().increaseHealth();
		player.getInventory().removeSelectedItem();
	}
}
