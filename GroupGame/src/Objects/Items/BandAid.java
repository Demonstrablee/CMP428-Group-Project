package Objects.Items;

import Sprites.Characters.PlayerCharacter;

/**
 * Written by Nicholas Cercos
 * Created on Nov 29 2023
 **/
public class BandAid extends Item {

	private final PlayerCharacter player;

	public BandAid(PlayerCharacter player) {
		this.player = player;
	}

	@Override
	public String getName() {
		return "Band Aid";
	}

	@Override
	public String getDescription() {
		return "Applies health";
	}

	@Override
	public void use() {
		player.getHealthBar().increaseHealth(player.getHealthBar().getMaxHealth());
		player.getInventory().removeSelectedItem();
	}
}
