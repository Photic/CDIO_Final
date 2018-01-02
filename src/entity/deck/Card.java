package entity.deck;

import entity.Player;
import entity.PlayerList;

public abstract class Card {
	
	/**
	 * Constructor
	 * @param description
	 * @param action
	 */
	public Card(String description, int action) {
		// Abstract class to create a card type
	}

	public abstract void cardFunction(Player p, PlayerList plist);
	
}
