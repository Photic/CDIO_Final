package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class JailCard extends Card {
	
	public JailCard(String description, int action) {
		super(description, action);
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist) {
		if (this.action == 0)
		{
			// Some code that allows the player to keep at card to get out of jail.
		}
		else if (this.action == 1)
		{
			p.setPosition(10);
		}
	}
	
}
