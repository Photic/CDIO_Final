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
			case 1:
				MatadorGrant();
				break;
			case 2:
				IncreaseInTaxes();
				break;
			case 3:
				Oilprices();
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

	private void MatadorGrant() {
		
	}
	
	private void IncreaseInTaxes() {

	}
	
	private void Oilprices() {

	}

	
	

}
