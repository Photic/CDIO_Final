package entity.deck;

import entity.gameboard.GameBoard;
import entity.player.Player;
import entity.player.PlayerList;

public abstract class Card {
	
	protected String description;
	protected int action;
	
	/**
	 *  Abstract class to create a card types.
	 * @param description
	 * @param action
	 */
	public Card(String description, int action) {
		this.description = description;
		this.action = action;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getAction()
	{
		return this.action;
	}

	public abstract void cardFunction(Player p, PlayerList plist, GameBoard gameboard);
	
}
