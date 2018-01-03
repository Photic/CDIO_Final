package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class JailCard extends Card {
	
	protected String description;
	protected int action;
	
	public JailCard(String description, int action) {
		super(description, action);
		this.action = action;
		this.description = description;
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist) {
		if (action == 0)
		{
			// Some code that allows the player to keep at card to get out of jail.
		}
		else if (action == 1)
		{
			// Some code that puts the player in jail,
		}
	}
	
}
