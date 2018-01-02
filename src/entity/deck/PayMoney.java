package entity.deck;

import entity.Player;
import entity.PlayerList;

public class PayMoney extends Card {

	private String description;
	private int money;
	
	public PayMoney(String description, int action) {
		super(description, action);
		this.money = action;
		this.description = description;
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist) {
		if (money == 0)
		{
			ekstra();
		}
		else
		{
			p.setBalance(p.getBalance()-money);
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

	/**
	 * GUI Description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
}
