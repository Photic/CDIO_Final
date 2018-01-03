package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class PayMoney extends Card {

	protected String description;
	protected int credit;
	
	public PayMoney(String description, int action) {
		super(description, action);
		this.credit = action;
		this.description = description;
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist) {
		if (credit == 0)
		{
			ekstra();
		}
		else
		{
			p.setBalance(-credit);
		}
	}
	
	/**
	 * Function build to handle special cards.
	 */
	public void ekstra() {
		switch (description.charAt(1)) {
		case 'D':
			// Code der indtager alle værdier og ændre på spillerens balance.
			break;
		case 'E':
			// Code der indtager alle værdier og ændre på spillerens balance.
			break;
		case 'O':
			// Code der indtager alle værdier og ændre på spillerens balance.
			break;
		default:
			System.err.println("Something went wrong in Card Sub Class PayMoney");
			break;
		}
	}
}
