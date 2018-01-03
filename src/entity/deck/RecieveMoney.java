package entity.deck;

import entity.gameboard.GameBoard;
import entity.player.Player;
import entity.player.PlayerList;

public class RecieveMoney extends Card {
	
	public RecieveMoney(String description, int action) {
		super(description, action);
	}

	/**
	 * Abstract override, extended from Card.
	 */
	@Override
	public void cardFunction(Player p, PlayerList plist, GameBoard gameboard) {
		if (this.action == 0)
		{
			birthday(p, plist);
		}
		else
		{
			p.setBalance(p.getBalance() + this.action);
		}
	}

	/**
	 * If the player, taken from the PlayerList is not the player recieving the card, 
	 * give money to the card holder and remove money from the PlayerList player. 
	 * @param p
	 * @param plist
	 */
	private void birthday(Player p, PlayerList plist) {
		for (int i = 0; i < plist.getLength(); i++) {
			if (plist.getPlayer(i).getName() != p.getName()) {
				p.setBalance(p.getBalance() + 200);
				plist.getPlayer(i).setBalance(plist.getPlayer(i).getBalance() - 200);
			}
		}
	}
}
