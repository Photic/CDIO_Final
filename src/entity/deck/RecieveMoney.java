package entity.deck;

import entity.player.Player;
import entity.player.PlayerList;

public class RecieveMoney extends Card {

	private String description;
	private final int credit;
	
	public RecieveMoney(String description, int action) {
		
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
			birthday(p, plist);
		}
		else
		{
			p.setBalance(credit);
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
				p.setBalance(200);
				plist.getPlayer(i).setBalance(-200);
			}
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
