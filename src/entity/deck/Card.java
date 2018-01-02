package entity.deck;

import entity.Player;

public abstract class Card {
	
	/**
	 * Constructor
	 * @param name
	 * @param description
	 * @param money
	 */
	public Card(String name, String description, int money) {
		// Abstract class to create a card
	}

	public abstract void cardFunction(Player p);
	
}
