package entity.deck;

import entity.gameboard.GameBoard;
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
	public void getCardFunction(Player p, PlayerList plist, GameBoard gameboard) {
		if (this.action < 4)
		{
			switch (this.action) {
			case 1: // MatadorGrant
				if (p.getAccount().getPlayerWorth(p) <= 15_000)
					p.setBalance(40_000);
				break;
			case 2: // Increase in taxes
				p.setBalance(-(p.getAccount().getHousesowned()*800));
				p.setBalance(-(p.getAccount().getHotelsowned()*2300));
				break;
			case 3: // Increase in Oil
				p.setBalance(-(p.getAccount().getHousesowned()*500));
				p.setBalance(-(p.getAccount().getHotelsowned()*2000));
				break;
			default:
				System.err.println("Something went wrong in Card Sub Class PayMoney");
				break;
			}
		}
		else
		{
			p.setBalance(p.getBalance()-this.action);
		}
	}

}
