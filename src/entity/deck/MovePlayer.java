package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class MovePlayer extends Card {
	
	public MovePlayer(String description, int action) {
		super(description, action);
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist) {
		p.setPosition(this.action);
	}

}
