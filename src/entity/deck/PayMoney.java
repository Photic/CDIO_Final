package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class PayMoney extends Card {
	
	public PayMoney(String description, int action) {
		super(description, action);
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist) {
		if (this.action == 0)
		{
			ekstra();
		}
		else
		{
			p.setBalance(-this.action);
		}
	}
	
	/**
	 * Function build to handle special cards.
	 */
	public void ekstra() {
		switch (this.description.charAt(1)) {
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
