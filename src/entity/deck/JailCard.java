package entity.deck;

import entity.Player;
import entity.PlayerList;

public class JailCard extends Card {
	
	private String description;
	private int action;
	
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
		
	}

	/**
	 * GUI Description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
}
